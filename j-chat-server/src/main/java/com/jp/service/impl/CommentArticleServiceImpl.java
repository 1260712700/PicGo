package com.jp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jp.domain.entity.CommentArticle;
import com.jp.service.CommentArticleService;
import com.jp.mapper.CommentArticleMapper;
import org.springframework.stereotype.Service;

/**
* @author 12607
* @description 针对表【j_comment_article】的数据库操作Service实现
* @createDate 2024-10-30 22:19:24
*/
@Service
public class CommentArticleServiceImpl extends ServiceImpl<CommentArticleMapper, CommentArticle>
    implements CommentArticleService{

}




