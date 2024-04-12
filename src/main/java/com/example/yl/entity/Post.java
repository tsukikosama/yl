package com.example.yl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName post
 */
@TableName(value ="post")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post implements Serializable {
    /**
     * 
     */
    @TableId(value = "pid",type = IdType.AUTO)
    private Integer pid;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private String tag;
    /**
     * 
     */
    private Integer visit;

    /**
     * 
     */
    private Integer likes;

    /**
     * 
     */
    private Integer replycount;

    /**
     * 
     */
    private String ctime;

    private String username;

    private String avatar;

    private Integer uid;

    @TableField(exist = false)
    private List<String> saveTag;

}