package com.jp.controller;

import com.jp.domain.dto.ArticleSaveDTO;
import com.jp.domain.entity.Article;
import com.jp.domain.response.ResponseResult;
import com.jp.domain.vo.ArticleVO;
import com.jp.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author JP
 * @Date 2024/11/4 11:38
 */
@RestController
@Tag(name = "文章接口")
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;
    /**
     * 根据类型获取文章
     * @param type 0：获取草稿箱文章 1：获取个人所有文章 2：获取推荐文章 3：获取所有文章
     * @return
     */
    @GetMapping("/auth/{type}")
    @Operation(summary = "获取文章")
    public ResponseResult<List<ArticleVO>> getArticleByType(@PathVariable String type){
       return articleService.getArticleByType(type);
    }
    @GetMapping("/auth/count/{type}")
    @Operation(summary = "获取文章统计")
    public ResponseResult<Integer> getArticleCount(@PathVariable String type){
        return articleService.getArticleCount(type);
    }
    @DeleteMapping("/auth/{id}")
    @Operation(summary = "删除文章")
    public ResponseResult<Integer> deleteArticleById(@PathVariable String id){
        return articleService.deleteArticleById(id);
    }
    @PostMapping("/auth/publish")
    @Operation(summary = "发布文章")
    public ResponseResult<Void> saveArticle(
            @RequestBody ArticleSaveDTO articleSaveDTO
    ){
        return   articleService.publishArticle(articleSaveDTO);
    }
}
