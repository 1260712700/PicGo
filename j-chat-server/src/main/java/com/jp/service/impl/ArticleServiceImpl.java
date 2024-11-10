package com.jp.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jp.constants.ArticleConstant;
import com.jp.domain.dto.ArticleSaveDTO;
import com.jp.domain.entity.Article;
import com.jp.domain.entity.ArticleTag;
import com.jp.domain.entity.Tag;
import com.jp.domain.entity.TagCategory;
import com.jp.domain.response.ResponseResult;
import com.jp.domain.vo.ArticleVO;
import com.jp.mapper.ArticleTagMapper;
import com.jp.mapper.TagCategoryMapper;
import com.jp.mapper.TagMapper;
import com.jp.service.ArticleService;
import com.jp.mapper.ArticleMapper;
import com.jp.utils.UserHolder;
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
            ArticleVO articleVO =new ArticleVO();
            List<Tag> tags = handleArticleTagsByArticleId(article.getId());
            if(tags!=null){
                StringBuilder stringBuilder=new StringBuilder();
                for (Tag tag : tags) {
                    stringBuilder.append(tag.getTagName()).append(",");
                }
                stringBuilder.setLength(stringBuilder.length()-1);
                articleVO.setTags(stringBuilder.toString());
            }
            articleVO.setId(article.getId());
            articleVO.setContent(article.getArticleContent());
            articleVO.setTitle(article.getArticleTitle());
            articleVO.setImages(article.getArticleImages());
            articleVO.setCategoryId(article.getCategoryId());
            articleVO.setUpdateTime(article.getUpdateTime());
            articleVO.setCreateTime(article.getCreateTime());
            articleVO.setStatus(article.getStatus()==3?"1": article.getStatus().toString());
            articleVO.setArticleType(article.getArticleType().toString());
            ret.add(articleVO);
        }
        return  ResponseResult.success(ret);
    }

    @Override
    public ResponseResult<Integer> getArticleCount(String type) {
        Integer ret=0;
        switch (type){
            case ArticleConstant.DRAFTS_ARTICLE:
                ret= Math.toIntExact(articleMapper.selectCount(new LambdaQueryWrapper<Article>().eq(Article::getUserId, UserHolder.getUser().getId()).eq(Article::getStatus, "3")));
                break;
            case ArticleConstant.PERSONAL_ARTICLE:
                ret= Math.toIntExact(articleMapper.selectCount(new LambdaQueryWrapper<Article>().eq(Article::getUserId, UserHolder.getUser().getId())));
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

    private List<Article> handleGetPersonalArticle() {
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Article::getUserId,UserHolder.getUser().getId());
        return articleMapper.selectList(lqw);
    }
    private List<Article> handleGetDraftsArticle() {
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Article::getUserId,UserHolder.getUser().getId()).eq(Article::getStatus,3);
        return articleMapper.selectList(lqw);
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
                .id(null)
                .articleCover(cover)
                .articleImages(articleSaveDTO.getImages())
                .articleTitle(articleSaveDTO.getTitle())
                .articleContent(articleSaveDTO.getContent())
                .articleType(articleSaveDTO.getArticleType())
                .status(articleSaveDTO.getStatus())
                .isTop(0)
                .visitCount(0L)
                .isDeleted(0)
                .categoryId(articleSaveDTO.getCategoryId()==null?12L:articleSaveDTO.getCategoryId())
                .userId(UserHolder.getUser().getId())
                .build();
        List<Article> articles = handleGetDraftsArticle();//检测草稿箱是否有数据
        if(articles.size()>0){
            for (Article a : articles) {
                article.setId(a.getId());
                articleMapper.updateById(article);
                return;
            }
        }
        articleMapper.insert(article);
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




