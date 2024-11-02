package com.jp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jp.domain.entity.Fans;
import com.jp.service.FansService;
import com.jp.mapper.FansMapper;
import org.springframework.stereotype.Service;

/**
* @author 12607
* @description 针对表【j_fans】的数据库操作Service实现
* @createDate 2024-10-30 22:19:24
*/
@Service
public class FansServiceImpl extends ServiceImpl<FansMapper, Fans>
    implements FansService{

}




