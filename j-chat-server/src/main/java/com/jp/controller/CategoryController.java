package com.jp.controller;

import com.jp.domain.response.ResponseResult;
import com.jp.domain.vo.CategoryVO;
import com.jp.domain.vo.TagVO;
import com.jp.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author JP
 * @Date 2024/11/4 10:10
 */
@Tag(name = "标签接口")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;
    @GetMapping
    @Operation(summary = "获取分类")
    public ResponseResult<List<CategoryVO>>getCategory(){
        return categoryService.getCategory();
    }
}
