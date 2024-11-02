package com.jp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jp.domain.entity.Article;
import com.jp.service.ArticleService;
import com.jp.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

/**
* @author 12607
* @description 针对表【j_article】的数据库操作Service实现
* @createDate 2024-10-30 05:13:34
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

}




