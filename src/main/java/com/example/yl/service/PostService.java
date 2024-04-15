package com.example.yl.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.yl.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 10833
* @description 针对表【post】的数据库操作Service
* @createDate 2024-01-24 20:00:33
*/
public interface PostService extends IService<Post> {

    Page<Post> listByPage(Integer curr);

    Page<Post> likesByPage(Integer page);

    Page<Post> visitByPage(Integer page);

    Page<Post> findByUid(Integer curr,Integer uid);

    List<Post> findPostByUid(Integer uid);

    List<Post> search(String key);

    List<Post> hot();

    Long Collect(Integer pid , Integer uid);

    Page<Post> findCollectListByUid(Integer uid, Integer curr);

    Page<Post> followList(Integer uid,Integer curr);
}
