package com.jp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jp.constants.RedisConstant;
import com.jp.domain.entity.Category;
import com.jp.domain.response.ResponseResult;
import com.jp.domain.vo.CategoryVO;
import com.jp.service.CategoryService;
import com.jp.mapper.CategoryMapper;
import com.jp.utils.RedisCache;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author 12607
* @description 针对表【j_category】的数据库操作Service实现
* @createDate 2024-10-30 05:13:34
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private RedisCache redisCache;
    @Override
    public ResponseResult<List<CategoryVO>> getCategory() {
        List<Category> categories = categoryMapper.selectList(null);
        List<CategoryVO>ret=new ArrayList<>();
        for (Category category : categories) {
            ret.add(category.asViewObject(CategoryVO.class));
        }
        return ResponseResult.success(ret);
    }
}




