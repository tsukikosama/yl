package com.example.yl.pojo;

import com.example.yl.entity.Funs;
import com.example.yl.entity.Post;
import lombok.Data;

import java.util.List;

@Data
public class Info {
    private List<Funs> funs;
    private Integer replys;
    private List<Post> posts;
    private Integer xp;
}
