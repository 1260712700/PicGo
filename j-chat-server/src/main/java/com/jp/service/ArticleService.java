package com.jp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jp.domain.dto.ArticleSaveDTO;
import com.jp.domain.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jp.domain.response.ResponseResult;
import com.jp.domain.vo.ArticleVO;

import java.util.List;

/**
* @author 12607
* @description 针对表【j_article】的数据库操作Service
* @createDate 2024-11-04 14:40:40
*/
public interface ArticleService extends IService<Article> {

    ResponseResult<Void> publishArticle(ArticleSaveDTO articleSaveDTO);

    ResponseResult<Page<ArticleVO>> getArticleByType(String type, Integer pageNum, Integer pageSize);

    ResponseResult<Integer> getArticleCount(String type);

    ResponseResult<Integer> deleteArticleById(String id);

    ResponseResult<Page<ArticleVO>> getArticleByCategoryId(String categoryId,boolean condition,Integer pageNum,Integer pageSize);
}
