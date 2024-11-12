package com.jp.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
@Slf4j
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
        Article article = HandleArticle(articleSaveDTO);
        //检测草稿箱是否有数据
        List<Article> articles = articleMapper.selectList(new LambdaQueryWrapper<Article>()
                .eq(Article::getUserId,SecurityUtil.getUserId())
                .eq(Article::getStatus,3)
        );
        if(articles.size()>0){
            for (Article a : articles) {
                if(articleSaveDTO.getStatus()==3){
                    article.setId(a.getId());
                }
                articleMapper.updateById(article);
                return ResponseResult.success();
            }
        }
        articleMapper.insert(article);
        return ResponseResult.success();
    }


    /**
     * 根据类型获取文章，支持分页
     * @param type  0：获取草稿箱文章 1：获取个人所有文章 2：获取推荐文章 3：获取所有文章 4:获取审核中私人 5：获取审核中公开
     * @param pageNum 第几页
     * @param pageSize 每页多少
     * @return
     */
    @Override
    public ResponseResult<Page<ArticleVO>> getArticleByType(String type,Integer pageNum,Integer pageSize) {
        // 创建分页对象
        Page<Article> page = new Page<>(pageNum, pageSize); // 创建 MyBatis-Plus 的 Page 对象
        LambdaQueryWrapper<Article>lqw=new LambdaQueryWrapper<>();
        switch (type){
            case ArticleConstant.DRAFTS_ARTICLE:
                lqw.eq(Article::getUserId,SecurityUtil.getUserId()).eq(Article::getStatus,3);
                break;
            case ArticleConstant.PERSONAL_ARTICLE:
                lqw.eq(Article::getUserId,SecurityUtil.getUserId()).ne(Article::getStatus,3);
                break;
//                todo 推荐文章
            case ArticleConstant.RECOMMEND_ARTICLE:
                break;
            case ArticleConstant.ALL_ARTICLE:
                break;
            default:
              return  ResponseResult.failure("系统错误");
        }
        Page<ArticleVO> pageVO = HandleArticlePage(page, lqw);
        return  ResponseResult.success(pageVO);
    }

    @Override
    public ResponseResult<Integer> getArticleCount(String type) {
        int ret=0;
        LambdaQueryWrapper<Article> lqw=new LambdaQueryWrapper<>();
        switch (type){
            case ArticleConstant.DRAFTS_ARTICLE:
                lqw.eq(Article::getUserId, SecurityUtil.getUserId()).eq(Article::getStatus, "3");
                break;
            case ArticleConstant.PERSONAL_ARTICLE:
                lqw.eq(Article::getUserId, SecurityUtil.getUserId());
                break;
            case ArticleConstant.RECOMMEND_ARTICLE:
                break;
            case ArticleConstant.ALL_ARTICLE:
                break;
            default:
                return  ResponseResult.failure("系统错误");
    }
        ret= Math.toIntExact(articleMapper.selectCount(lqw));
        return ResponseResult.success(ret);
    }

    @Override
    public ResponseResult<Integer> deleteArticleById(String id) {
        return ResponseResult.success(articleMapper.deleteById(id));
    }

    /**
     * 根据文章分类id获取文章
     * @param categoryId 文章分类
     * @param condition 是否只查找公开的
     * @param pageNum 当前页
     * @param pageSize 当前页大小
     * @return
     */
    @Override
    public ResponseResult<Page<ArticleVO>> getArticleByCategoryId(String categoryId, boolean condition, Integer pageNum, Integer pageSize) {
        // 创建分页查询
        Page<Article> page = new Page<>(pageNum, pageSize); // 创建 MyBatis-Plus 的 Page 对象
        // 创建 LambdaQueryWrapper 对象
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<Article>()
                .eq(Article::getCategoryId, categoryId); // 基本条件：按分类查询
        // 如果 condition 为 true，则添加条件：只查找状态为 1 的（假设 1 为公开）
        if (condition) {
            lqw.eq(Article::getStatus, 1);
        }
        // 分页查询
        Page<ArticleVO> pageVO = HandleArticlePage(page, lqw);
        // 返回结果
        return ResponseResult.success(pageVO);
    }

    /**
     *
     * @param conditionPage 条件分页
     * @param lqw 查询条件
     * @return 分页结果
     */
    private Page<ArticleVO> HandleArticlePage(Page<Article>conditionPage,LambdaQueryWrapper<Article>lqw){
        Page<Article> articleList = articleMapper.selectPage(conditionPage, lqw);
        // 将查询结果转换为 VO 对象
        List<ArticleVO> articleVOList = articleList.getRecords().stream()
                .map(this::HandleArticleVO)
                .collect(Collectors.toList());
        // 构造返回的分页 VO
        Page<ArticleVO> pageVO = new Page<>(conditionPage.getPages(), conditionPage.getSize(), articleList.getTotal());
        pageVO.setRecords(articleVOList);
        return pageVO;
    }
    /**
     * 处理数据库文章返回到前端 ArticleVO
     * @param article
     * @return
     */
    private ArticleVO HandleArticleVO(Article article){
//        处理文章标签
        List<Tag> tags = HandleArticleTagsByArticleId(article.getId());
        StringBuilder articleTag=new StringBuilder();
        if(tags!=null){
            for (Tag tag : tags) {
                articleTag.append(tag.getTagName()).append(",");
            }
        }
        return ArticleVO.builder()
                .userId(article.getUserId())
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
    }
    /**
     * 处理文章到数据库entity
     * @param articleSaveDTO
     */
    private Article HandleArticle(ArticleSaveDTO articleSaveDTO){
        String cover="";
        if(!StrUtil.isBlank(articleSaveDTO.getImages())){
            String[] split = articleSaveDTO.getImages().split(",");
            cover=split[0];
        }
        return Article.builder()
                .id(articleSaveDTO.getId()==null?null: Long.valueOf(articleSaveDTO.getId()))
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
    }
    /**
     * 根据文章id获取该文章的标签
     */
    private  List<Tag> HandleArticleTagsByArticleId(Long id) {
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
    @Deprecated
    private List<Article> handleGetPersonalArticle() {
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Article::getUserId,SecurityUtil.getUserId()).ne(Article::getStatus,3);
        return articleMapper.selectList(lqw);
    }

    /**
     * 获取草稿箱中的文章 status=3
     * @return
     */
    @Deprecated
    private List<Article> HandleGetDraftsArticle() {
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Article::getUserId, SecurityUtil.getUserId()).eq(Article::getStatus,3);
        return articleMapper.selectList(lqw);
    }

    /**
     * 处理标签 返回文章分类
     * @return
     */
    @Deprecated
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




