package com.jp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jp.domain.entity.Comment;
import com.jp.service.CommentService;
import com.jp.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author 12607
* @description 针对表【j_comment】的数据库操作Service实现
* @createDate 2024-10-30 05:13:34
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




