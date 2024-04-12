package com.example.yl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yl.entity.Review;


import java.util.List;
import java.util.Set;

public interface ReviewService extends IService<Review> {
    List<Review> findAllForBid(Integer bid);

    String islike(String bid, String uid,Integer rid);

    Set<String> likes(String bid, String uid);

    String addRevice(Review review);

    List<Review> getReplyByBid(Integer bid);

}
