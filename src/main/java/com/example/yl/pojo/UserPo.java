package com.example.yl.pojo;

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
@ApiModel(description = "用户注册实体")
public class UserPo implements Serializable {
    /**
     * 
     */

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

    private String checkPsd;
    private String code;
}