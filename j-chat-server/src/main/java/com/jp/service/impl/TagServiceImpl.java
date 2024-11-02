package com.jp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jp.domain.entity.Tag;
import com.jp.service.TagService;
import com.jp.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author 12607
* @description 针对表【j_tag】的数据库操作Service实现
* @createDate 2024-10-30 05:13:34
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

}




