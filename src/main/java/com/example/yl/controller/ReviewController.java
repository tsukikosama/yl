package com.example.yl.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.yl.common.Result;
import com.example.yl.entity.Review;
import com.example.yl.service.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
@Api("评论接口")
public class ReviewController {



    @Autowired
    private ReviewService reviewService;



    @PostMapping("/addreview")
    public Result addReview(@RequestBody Review review){
//        System.out.println(review);
        String msg = reviewService.addRevice(review);


        return Result.ok(msg);
    }
    @PostMapping("delreview")
    public Result delReview(@RequestParam("rid") Integer rid){
        boolean success = reviewService.removeById(rid);
        if(!success){
            return Result.fail("删除失败请稍后再试");
        }
        return Result.ok("删除成功");
    }
    @GetMapping("/findall")
    public Result findAllReview(@RequestParam("pid")Integer bid){
        List<Review> list = reviewService.findAllForBid(bid);
        return Result.ok(list);
    }

    /**
     * 判断用户是否点赞
     * @return
     */
    @PostMapping("/islike")
    public Result islike(@RequestParam("pid") String bid, @RequestParam("uid")String uid,@RequestParam("rid")Integer rid){
        System.out.println(bid + uid + rid);
        String msg = reviewService.islike(bid,uid,rid);
        return Result.ok(msg);
    }

    /**
     * 查询博客点赞的用户
     * @param bid 博客id
     * @param uid 用户id
     * @return
     */
    @GetMapping("/likes")
    public Result likes(@RequestParam("pid")String bid,@RequestParam("uid")String uid) {
        Set<String> list =  reviewService.likes(bid,uid);
       return Result.ok(list);
    }

    /**
     * 通过博客id获取评论
     * @param bid
     * @return
     */
    @GetMapping("/getreplybybid")
    public Result GetReplyByBid(@RequestParam("pid") Integer bid){
        List<Review> list = reviewService.getReplyByBid(bid);
        return Result.ok(list);
    }

    @ApiOperation(value ="分页查询评论")
    @GetMapping("/list/{curr}")
    public Result getReviewByPage(@PathVariable("curr") Integer curr){
        List<Review> list = reviewService.getReviewByPage(curr);
        return Result.ok(list);
    }

    @ApiOperation(value = "查询全部评论")
    @GetMapping("/list")
    public Result getAllReview(){

        return Result.ok(reviewService.list());
    }
}
