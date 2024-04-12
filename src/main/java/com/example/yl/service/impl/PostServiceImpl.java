package com.example.yl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yl.entity.Post;
import com.example.yl.service.PostService;
import com.example.yl.mapper.PostMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.yl.utils.Common.MAX_SIZE;

/**
* @author 10833
* @description 针对表【post】的数据库操作Service实现
* @createDate 2024-01-24 20:00:33
*/
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
    implements PostService{



    @Override
    public Page<Post> listByPage(Integer curr) {
        Page<Post> page = new Page<>(curr,MAX_SIZE);

        List<Post> list = this.baseMapper.listByPage(curr);

        page.setRecords(list);
        page.setTotal(this.count());
        return page;
    }

    @Override
    public Page<Post> likesByPage(Integer curr) {
        Page<Post> page = new Page<>(curr,MAX_SIZE);

        List<Post> list = this.baseMapper.likesByPage(curr);

        page.setRecords(list);
        page.setTotal(this.count());
        return page;
    }

    @Override
    public Page<Post> visitByPage(Integer curr) {
        Page<Post> page = new Page<>(curr,MAX_SIZE);

        List<Post> list = this.baseMapper.visitByPage(curr);

        page.setRecords(list);
        page.setTotal(this.count());
        return page;
    }

    @Override
    public Page<Post> findByUid(Integer curr,Integer uid) {
        Page<Post> page = new Page<>(curr,MAX_SIZE);
        List<Post> list = this.baseMapper.findByUid(curr,uid);
        page.setRecords(list);
        page.setTotal(list.size());
        System.out.println(page.getRecords());
        return page;
    }

    @Override
    public List<Post> findPostByUid(Integer uid) {
        //通过uid查询全部的post
        List<Post> list = this.baseMapper.listByUid(uid);
        return list;
    }

    @Override
    public List<Post> search(String key) {
        List<Post> list = this.baseMapper.search(key);
        return list;
    }
}




