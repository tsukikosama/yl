package com.example.yl.mapper;

import com.example.yl.entity.Post;
import com.example.yl.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author 10833
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-01-24 20:04:45
* @Entity com.example.yl.entity.UserPo
*/
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT post.uid from post\n" +
            "INNER JOIN user on user.uid = post.uid \n" +
            "INNER JOIN review on review.pid = post.pid  where review.pid = #{pid} limit 1;\n" +
            "\n")
    Integer selectUserByBid(@Param("pid") Integer pid);

    @Select("select * from post limit ${curr*15},15;")
    List<User> listByPage(Integer curr);
}




