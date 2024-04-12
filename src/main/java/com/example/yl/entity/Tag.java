package com.example.yl.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName forum
 */
@TableName(value ="tag")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tag implements Serializable {
    /**
     * 
     */
    @TableId(value = "tid",type = IdType.AUTO)
    private Integer fid;

    /**
     * 
     */
    private String tag;


}