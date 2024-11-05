package com.jp.service;

import com.jp.domain.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jp.domain.response.ResponseResult;
import com.jp.domain.vo.CategoryVO;

import java.util.List;

/**
* @author 12607
* @description 针对表【j_category】的数据库操作Service
* @createDate 2024-10-30 05:13:34
*/
public interface CategoryService extends IService<Category> {
    ResponseResult<List<CategoryVO>>getCategory();
}
