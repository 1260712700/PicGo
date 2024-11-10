package com.jp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jp.domain.entity.ArticleComment;
import com.jp.service.ArticleCommentService;
import com.jp.mapper.ArticleCommentMapper;
import org.springframework.stereotype.Service;

/**
* @author 12607
* @description 针对表【j_article_comment】的数据库操作Service实现
* @createDate 2024-11-10 22:36:38
*/
@Service
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment>
    implements ArticleCommentService{

}




