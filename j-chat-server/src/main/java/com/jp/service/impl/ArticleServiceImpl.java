package com.jp.service.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jp.constants.ArticleConstant;
import com.jp.domain.dto.ArticleSaveDTO;
import com.jp.domain.entity.*;
import com.jp.domain.response.ResponseResult;
import com.jp.domain.vo.ArticleVO;
import com.jp.mapper.*;
import com.jp.service.ArticleService;
import com.jp.utils.SecurityUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author 12607
* @description 针对表【j_article】的数据库操作Service实现
* @createDate 2024-11-04 14:40:40
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{
    @Resource
    private  ArticleMapper articleMapper;

    @Resource
    private ArticleTagMapper articleTagMapper;
    @Resource
    private TagMapper tagMapper;
    @Resource
    private TagCategoryMapper tagCategoryMapper;

    @Resource
    private ArticleCollectMapper articleCollectMapper;
    @Resource
    private LikeMapper likeMapper; //还有一种是评论点赞，因此不只是属于文章范畴
    @Resource
    private CommentMapper commentMapper;
    /**
     * 发布文章
     * @param articleSaveDTO
     * @return
     */
    @Override
    public ResponseResult<Void> publishArticle(ArticleSaveDTO articleSaveDTO) {
        HandleArticle(articleSaveDTO);
        return ResponseResult.success();
    }
    /**
     * 处理文章到数据库
     * @param articleSaveDTO
     */
    private void HandleArticle(ArticleSaveDTO articleSaveDTO){
        String cover="";
        if(!StrUtil.isBlank(articleSaveDTO.getImages())){
            String[] split = articleSaveDTO.getImages().split(",");
            cover=split[0];
        }
        Article article= Article.builder()
                .id(articleSaveDTO.getId().isEmpty()?null: Long.valueOf(articleSaveDTO.getId()))
                .articleCover(cover)
                .articleImages(articleSaveDTO.getImages())
                .articleTitle(articleSaveDTO.getTitle())
                .articleContent(articleSaveDTO.getContent())
                .articleType(articleSaveDTO.getArticleType())
                .status(articleSaveDTO.getStatus()==3? articleSaveDTO.getStatus() :articleSaveDTO.getStatus()+3 )
                .shareCount(0L)
                .isTop(0)
                .visitCount(0L)
                .isDeleted(0)
                .categoryId(articleSaveDTO.getCategoryId()==null?12L:articleSaveDTO.getCategoryId())
                .userId(SecurityUtil.getUserId())
                .build();
        List<Article> articles = handleGetDraftsArticle();//检测草稿箱是否有数据
        if(articles.size()>0){
            for (Article a : articles) {
                if(articleSaveDTO.getStatus()==3){
                    article.setId(a.getId());
                }
                articleMapper.updateById(article);
                return;
            }
        }
        articleMapper.insert(article);
    }

    /**
     * 获取文章
     * @param type 0：获取草稿箱文章 1：获取个人所有文章 2：获取推荐文章 3：获取所有文章
     * @return
     */
    @Override
    public ResponseResult<List<ArticleVO>> getArticleByType(String type) {
        List<Article>articleList=new ArrayList<>();
        switch (type){
            case ArticleConstant.DRAFTS_ARTICLE:
                articleList= handleGetDraftsArticle();
                break;
            case ArticleConstant.PERSONAL_ARTICLE:
                articleList=handleGetPersonalArticle();
                break;
            case ArticleConstant.RECOMMEND_ARTICLE:
                break;
            case ArticleConstant.ALL_ARTICLE:
                break;
            default:
              return  ResponseResult.failure("系统错误");
        }
        List<ArticleVO>ret=new ArrayList<>();
        for (Article article : articleList) {
            List<Tag> tags = handleArticleTagsByArticleId(article.getId());
            StringBuilder articleTag=new StringBuilder();
            if(tags!=null){
                for (Tag tag : tags) {
                    articleTag.append(tag.getTagName()).append(",");
                }
            }
            ArticleVO articleVO =ArticleVO.builder()
                    .like(likeMapper.selectCount(new LambdaQueryWrapper<Like>().eq(Like::getTypeId,article.getId())))
                    .commentCount(commentMapper.selectCount(new LambdaQueryWrapper<Comment>().eq(Comment::getTypeId,article.getId())))
                    .shareCount(article.getShareCount())
                    .collectCount(articleCollectMapper.selectCount(new LambdaQueryWrapper<ArticleCollect>().eq(ArticleCollect::getArticleId,article.getId())))
                    .id(article.getId())
                    .visitCount(article.getVisitCount())
                    .content(article.getArticleContent())
                    .title(article.getArticleTitle())
                    .images(article.getArticleImages())
                    .categoryId(article.getCategoryId())
                    .updateTime(article.getUpdateTime())
                    .createTime(article.getCreateTime())
                    .status(article.getStatus()==3?"1":article.getStatus().toString())
                    .articleType(article.getArticleType().toString())
                    .tags(articleTag.toString())
                    .articleCover(article.getArticleCover())
                    .build()
                    ;
            ret.add(articleVO);
        }
        return  ResponseResult.success(ret);
    }

    @Override
    public ResponseResult<Integer> getArticleCount(String type) {
        Integer ret=0;
        switch (type){
            case ArticleConstant.DRAFTS_ARTICLE:
                ret= Math.toIntExact(articleMapper.selectCount(new LambdaQueryWrapper<Article>().eq(Article::getUserId, SecurityUtil.getUserId()).eq(Article::getStatus, "3")));
                break;
            case ArticleConstant.PERSONAL_ARTICLE:
                ret= Math.toIntExact(articleMapper.selectCount(new LambdaQueryWrapper<Article>().eq(Article::getUserId, SecurityUtil.getUserId())));
                break;
            case ArticleConstant.RECOMMEND_ARTICLE:
                break;
            case ArticleConstant.ALL_ARTICLE:
                break;
            default:
                return  ResponseResult.failure("系统错误");
    }
    return ResponseResult.success(ret);
    }

    @Override
    public ResponseResult<Integer> deleteArticleById(String id) {
        return ResponseResult.success(articleMapper.deleteById(id));
    }

    /**
     * 根据文章id获取该文章的标签
     */
    private  List<Tag> handleArticleTagsByArticleId(Long id) {
        LambdaQueryWrapper<ArticleTag> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ArticleTag::getArticleId,id);
        List<ArticleTag> articleTags = articleTagMapper.selectList(lqw);
        List<Long> tagIds = articleTags.stream().map(ArticleTag::getTagId).collect(Collectors.toList());
        if(!tagIds.isEmpty())return tagMapper.selectList(new LambdaQueryWrapper<Tag>().in(Tag::getId,tagIds));
        return null;
    }

    /**
     * 获取个人所有文章除了草稿箱中的 status!=3
     * @return
     */
    private List<Article> handleGetPersonalArticle() {
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Article::getUserId,SecurityUtil.getUserId()).ne(Article::getStatus,3);
        return articleMapper.selectList(lqw);
    }

    /**
     * 获取草稿箱中的文章 status=3
     * @return
     */
    private List<Article> handleGetDraftsArticle() {
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Article::getUserId, SecurityUtil.getUserId()).eq(Article::getStatus,3);
        return articleMapper.selectList(lqw);
    }

    /**
     * 处理标签 返回文章分类
     * @return
     */
    private Long handleCategory(String tags){
        String[] split = tags.split(",");
// 创建 LambdaQueryWrapper 来查找标签
        LambdaQueryWrapper<Tag> lqw = new LambdaQueryWrapper<>();
        List<Tag> tagList = new ArrayList<>();
// 查找标签并将其添加到 tagList 中
        for (String tagName : split) {
            lqw.clear(); // 确保每次都清除之前的条件
            lqw.eq(Tag::getTagName, tagName);  // 根据标签名查询标签
            Tag tag = tagMapper.selectOne(lqw);  // 查询标签对象
            if (tag != null) {
                tagList.add(tag);
            }
        }
        // 使用批量查询，一次性获取所有与 tagIds 相关的 TagCategory
        List<Long> tagIds = tagList.stream()
                .map(Tag::getId)  // 获取每个 Tag 对象的 id
                .collect(Collectors.toList());
        if(tagIds.size()<=0){
            return 11L;
        }
        List<TagCategory> tagCategoryList = tagCategoryMapper.selectList(
                new LambdaQueryWrapper<TagCategory>().in(TagCategory::getTagId, tagIds)
        );
        int max = 0;  // 记录最多的出现次数
        Long ret = null;  // 用 null 初始化 ret，表示没有找到最多的标签
        Map<Long, Integer> countMap = new HashMap<>();  // 使用 Map 来存储每个 categoryId 的出现次数
        for (TagCategory tagCategory : tagCategoryList) {
            System.out.println(tagCategory);
            Long categoryId = tagCategory.getCategoryId();
            int count = countMap.getOrDefault(categoryId, 0) + 1;  // 获取当前 categoryId 的出现次数
            countMap.put(categoryId, count);  // 更新出现次数
            // 如果当前 categoryId 出现次数更高，更新 max 和 ret
            if (count > max) {
                max = count;
                ret = categoryId;
            }
        }
        return ret==null?11L:ret;
    }


}




