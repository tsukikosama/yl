package com.example.yl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.yl.entity.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReviewMapper extends BaseMapper<Review> {

    List<Review> getReviewByPage( Integer curr);

}
