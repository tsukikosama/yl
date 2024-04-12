package com.example.yl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String content;
    private Integer reply;
    private String time;

    private Integer likes;
    private String username;
    private Integer pid;
    @TableField(exist = false)
    private List<Review> replays;
    private Integer rid;
    private String avatar;
}
