package com.jp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jp.domain.entity.Message;
import com.jp.service.MessageService;
import com.jp.mapper.MessageMapper;
import org.springframework.stereotype.Service;

/**
* @author 12607
* @description 针对表【j_message】的数据库操作Service实现
* @createDate 2024-10-30 22:19:24
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService{

}




