package com.example.yl.mapper;

import com.example.yl.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 10833
* @description 针对表【forum】的数据库操作Mapper
* @createDate 2024-01-24 20:04:58
* @Entity com.example.yl.entity.Tag
*/
public interface TagMapper extends BaseMapper<Tag> {
    public List<Tag> selectAll();

    List<Tag> getUserByPage(Integer curr);
}




