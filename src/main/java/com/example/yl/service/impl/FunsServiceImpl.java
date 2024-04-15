package com.example.yl.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yl.entity.Funs;
import com.example.yl.entity.User;
import com.example.yl.pojo.UserFuns;
import com.example.yl.service.FunsService;
import com.example.yl.mapper.FunsMapper;
import com.example.yl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;

import static com.example.yl.utils.Common.MAX_FUNS_SIZE;
import static java.util.stream.Collectors.toList;

/**
* @author 10833
* @description 针对表【funs】的数据库操作Service实现
* @createDate 2024-01-27 17:27:58
*/
@Service
public class FunsServiceImpl extends ServiceImpl<FunsMapper, Funs>
    implements FunsService{
    @Autowired
    private UserService userService;
    @Override
    public Page<UserFuns> listByPage(Integer curr, Integer uid) {
        Page<UserFuns> page = new Page<>(curr,MAX_FUNS_SIZE);
        List<Funs> list = this.baseMapper.ByPage(curr,uid);
        List<UserFuns> funs = new ArrayList<>();
        //把查询到的user存入
        for(Funs item : list){
            UserFuns userFuns = BeanUtil.copyProperties(item, UserFuns.class);
            User u = userService.getById(item.getFid());
            userFuns.setUser(u);
            funs.add(userFuns);
        }

        page.setRecords(funs);
        page.setTotal(funs.size());

        return page;
    }

    @Override
    public void like(Integer uid,Integer fid) {

        //通过uid和fid查询对应的用户
        Funs funs = this.baseMapper.findFunByUidAndFid(uid,fid);
        System.out.println(funs);
        //如果没差到就封装一个Funs

        if(funs == null){
            funs = new Funs(uid,fid,0);
        }
        funs.setFollow(funs.getFollow()^1);
        System.out.println(funs);
        //
        this.saveOrUpdate(funs);

    }

    @Override
    public List<Funs> findFunsByUid(Integer uid) {
        List<Funs> list = this.baseMapper.findFunsByUid(uid);
        for(Funs item : list){
            item.setUser(findUserForUid(item.getUid()));
        }
        return list;
    }

    public User findUserForUid(Integer uid){
        return userService.getById(uid);
    }

    @Override
    public List<Funs> findUserFunsByUid(Integer uid,Integer fid) {
        //查询共同id

        //1.查询当前用户的funs
        List<Funs> ufuns = this.findFunsByUid(uid);

        //2.查询登录用户的funs
        List<Funs> ffuns = this.findFunsByUid(fid);

        //获取ufuns全部的fid
        for (Funs i : ufuns){
            for(Funs j : ffuns){
                if(i.getFid().equals(j.getUid())){
                    i.setSama(1);
                    break;
                }
            }
        }
        return ufuns;
    }

    @Override
    public List<Funs> findFlowers(Integer uid) {
        //
        List<Funs> list = this.baseMapper.findFlowers(uid);
        return list;
    }


}




