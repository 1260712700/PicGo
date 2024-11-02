package com.jp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jp.domain.entity.Category;
import com.jp.service.CategoryService;
import com.jp.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author 12607
* @description 针对表【j_category】的数据库操作Service实现
* @createDate 2024-10-30 05:13:34
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




