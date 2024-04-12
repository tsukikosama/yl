package com.example.yl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName funs
 */
@TableName(value ="funs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Funs implements Serializable {
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
    private Integer fid;

    public Funs(Integer uid, Integer fid, Integer follow) {
        this.uid = uid;
        this.fid = fid;
        this.follow = follow;
    }

    private Integer follow;

    @TableField(exist = false)
    private User user;

    //相同关注
    @TableField(exist = false)
    private Integer sama;
}