package com.example.yl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yl.entity.Tag;
import com.example.yl.mapper.TagMapper;

import com.example.yl.service.TagService;
import org.springframework.stereotype.Service;

/**
* @author 10833
* @description 针对表【forum】的数据库操作Service实现
* @createDate 2024-01-24 20:04:58
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService {

}




