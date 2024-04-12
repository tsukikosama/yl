package com.example.yl.mapper;

import com.example.yl.entity.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 10833
* @description 针对表【post】的数据库操作Mapper
* @createDate 2024-01-24 20:00:33
* @Entity com.example.yl.entity.Post
*/
public interface PostMapper extends BaseMapper<Post> {

    List<Post> listByPage(Integer curr);

    List<Post> likesByPage(Integer curr);

    List<Post> visitByPage(Integer curr);

    List<Post> findByUid(Integer curr, Integer uid);

    List<Post> listByUid(Integer uid);

    List<Post> search(String key);

}




