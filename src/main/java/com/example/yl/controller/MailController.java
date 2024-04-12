package com.example.yl.controller;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.yl.common.Result;
import com.example.yl.entity.User;
import com.example.yl.service.UserService;
import com.example.yl.utils.MailService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

import static com.example.yl.utils.Common.REDIS_REG_CODE;

@Api("邮件管理接口")
@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {
    @Autowired
    private MailService service;

    private final StringRedisTemplate redisTemplate;

    @Autowired
    private UserService userService;
    /**
     * 验证码功能
     * @param address
     */
    @PostMapping("/send")
    public Result sendTextMail(@RequestParam("address") String address){
        //判断是否有这个邮箱
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail, address);
        User one = userService.getOne(wrapper);
        System.out.println(one);
        //判断是否有这个用户
        if(one != null){
            return Result.fail("用户存在");
        }
        //用户不存在 判断是否发送过验证码
        if (!redisTemplate.hasKey(address)){
            //没有发送验证码
            System.out.println("测试");
            //如果没有这个验证就就把验证码存入redis
            String code = RandomUtil.randomNumbers(6);
            redisTemplate.opsForValue().set(REDIS_REG_CODE +address,code,300,TimeUnit.SECONDS);
            //并且发送邮件
            service.sendTextMailMessage(address,code);
            return Result.ok("成功");
        }
            return Result.fail("禁止重复发送消息");

    }
    @PostMapping("/ban")
    public Result banUser(@RequestBody User address){

        System.out.println("mail"+address);
        service.sendBan(address.getEmail());
        return Result.ok();
    }
    @PostMapping("/feedback")
    public Result feedback(@RequestBody User address){
        service.sendEmail(address);
        return Result.ok();
    }
}
