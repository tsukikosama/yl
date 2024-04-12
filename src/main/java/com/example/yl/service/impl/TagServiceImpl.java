package com.example.yl.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yl.entity.Tag;
import com.example.yl.mapper.TagMapper;

import com.example.yl.service.TagService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author 10833
* @description 针对表【forum】的数据库操作Service实现
* @createDate 2024-01-24 20:04:58
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService {

    @Override
    public Page<Tag> getTagByPage(Integer curr) {
        Page<Tag> page = new Page<>(curr,10);
        //查询当前页面的数据
        List<Tag> list = this.baseMapper.getUserByPage(curr);
        //封装page对象
        //设置总数
        page.setTotal(list().size());
        //设置页面数据
        page.setRecords(list);
        return null;
    }
}




