package com.example.yl.mapper;

import com.example.yl.entity.Funs;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 10833
* @description 针对表【funs】的数据库操作Mapper
* @createDate 2024-01-27 17:27:58
* @Entity com.example.yl.entity.UserFuns
*/
public interface FunsMapper extends BaseMapper<Funs> {

    List<Funs> ByPage(Integer curr,Integer uid);

    Funs findFunByUidAndFid(Integer uid, Integer fid);


    List<Funs> findFunsByUid(Integer uid);

}




