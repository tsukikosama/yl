package com.example.yl.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.example.yl.entity.Review;
import com.example.yl.entity.User;
import com.example.yl.mapper.ReviewMapper;
import com.example.yl.mapper.UserMapper;
import com.example.yl.service.ReviewService;
import com.example.yl.service.UserService;
import com.example.yl.utils.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.example.yl.utils.Common.LIKES;
import static com.example.yl.utils.Common.MANAGE_SIZE;


@Service
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review> implements ReviewService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserMapper mapper;

    /**
     * 通过博客的id来查询所有的评论
     * @param bid
     * @return
     */
    @Override
    public List<Review> findAllForBid(Integer bid) {
        LambdaQueryWrapper<Review> wrapper = new LambdaQueryWrapper<Review>();

        wrapper.eq(Review::getPid, bid).eq(Review::getReply, 0);
        //全部的评论字段
        List<Review> list = list(wrapper);
        //获取全部的评论 查看他们是否有回复
//        List<Integer> ids = new ArrayList<>();
        wrapper.clear();

        List<Review> replys = new ArrayList<>();
        for(Review r : list){


            wrapper.eq(Review::getRid,r.getId()).orderBy(true,true,Review::getLikes);

            replys = list(wrapper);

            r.setReplays(replys);
            wrapper.clear();
        }
        System.out.println(list);
        return list;
    }

    /**
     * 判断用户是否点赞
     * @param bid
     * @param uid
     * @return
     */
    @Override
    public String islike(String bid ,String uid,Integer rid) {
        //拼接redis的key
        String key = LIKES + bid + ":" + uid ;
        //判断redis是否存在改数据
        Double score = redisTemplate.opsForZSet().score(key,rid.toString());

        //先从redis中看看这个用户是否点赞过 如果不存在就是没有点赞过
        if (score == null){
            //不存在这个用户 就把这个用户添加并且+1赞

            redisTemplate.opsForZSet().add(key,rid.toString(),System.currentTimeMillis());
            //更新点赞数
            update().setSql("likes = likes +1").eq("id",rid).update();
            return "更新成功";
        }
        //存在的情况 吧用户从集合中移除
        redisTemplate.opsForZSet().remove(key,rid.toString());
        update().setSql("likes = likes - 1").eq("id",rid).update();
        return "更新成功";
    }

    @Override
    public Set<String> likes(String bid, String uid) {
        String key = LIKES + bid + ":" + uid ;
        //获取全部的数据
        Set<String> range = redisTemplate.opsForZSet().range(key, 0, -1);
        System.out.println(range);
        return range;
    }

    @Override
    public String  addRevice(Review review) {
        review.setTime(DateUtil.date().toString());
        review.setLikes(0);
        boolean flag = this.save(review);
        //通过bid去查询用户
        Integer uid = mapper.selectUserByBid(review.getPid());
        //通过这个uid去查询博客的邮箱;
        User user = userService.getOneById(uid);
        System.out.println(user);
        mailService.sendMsg(user.getEmail());
        //发送邮件提示有人评论了
        if(!flag){
            return "回复失败，请稍后再试";
        }

        return "回复成功";
    }

    @Override
    public List<Review> getReplyByBid(Integer bid) {
        LambdaQueryWrapper<Review> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Review::getPid,bid);
        List<Review> list = list(wrapper);
        return list;
    }

    @Override
    public List<Review> getReviewByPage(Integer curr) {

        List<Review> list = this.baseMapper.getReviewByPage(curr);

        return list;
    }
}
