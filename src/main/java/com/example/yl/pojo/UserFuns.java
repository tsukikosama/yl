package com.example.yl.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.yl.entity.User;
import lombok.Data;

@Data
public class UserFuns {
    /**
     *
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    private Integer uid;

    /**
     *
     */
    private String fid;

    private Integer follow;

    private User user;
}
