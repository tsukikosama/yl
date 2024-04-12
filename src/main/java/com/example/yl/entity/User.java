package com.example.yl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 
 * @TableName user
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(description = "用户表单实体")
public class User implements Serializable {
    /**
     * 
     */
    @TableId(value = "uid",type = IdType.AUTO)
    private Integer uid;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String nickname;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String avatar;

    /**
     * 
     */
    private String type;

    /**
     * 
     */
    private Integer xp;

    private String email;

    private String state;

}