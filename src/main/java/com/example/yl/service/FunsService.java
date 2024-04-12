package com.example.yl.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.yl.entity.Funs;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yl.entity.User;
import com.example.yl.pojo.UserFuns;

import java.util.List;

/**
* @author 10833
* @description 针对表【funs】的数据库操作Service
* @createDate 2024-01-27 17:27:58
*/
public interface FunsService extends IService<Funs> {

    Page<UserFuns> listByPage(Integer curr, Integer uid);

    void like(Integer uid,Integer fid);

    List<Funs> findFunsByUid(Integer uid);

    List<Funs> findUserFunsByUid(Integer uid,Integer fid);
}
