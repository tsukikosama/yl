package com.example.yl.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.yl.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;


import java.util.List;

/**
* @author 10833
* @description 针对表【forum】的数据库操作Service
* @createDate 2024-01-24 20:04:58
*/
public interface TagService extends IService<Tag> {

    Page<Tag> getTagByPage(Integer curr);

}
