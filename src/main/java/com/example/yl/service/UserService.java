package com.example.yl.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.yl.entity.Post;
import com.example.yl.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yl.pojo.Sign;
import com.example.yl.pojo.UserPo;

import java.util.List;

/**
* @author 10833
* @description 针对表【user】的数据库操作Service
* @createDate 2024-01-24 20:04:45
*/
public interface UserService extends IService<User> {

    User login(User user);

    String reg(UserPo user);

    Sign sign(Integer uid);

    Sign signByUid(Integer uid);

    List<User> signsort();

    List<User> sortByXp();

    User getOneById(Integer uid);

    Page<User> listByPage(Integer page);
}
