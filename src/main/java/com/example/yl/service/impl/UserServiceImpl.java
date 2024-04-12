package com.example.yl.service.impl;

import cn.hutool.Hutool;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yl.entity.Post;
import com.example.yl.entity.User;
import com.example.yl.pojo.Sign;
import com.example.yl.pojo.UserPo;
import com.example.yl.service.UserService;
import com.example.yl.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.example.yl.utils.Common.*;

/**
* @author 10833
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-01-24 20:04:45
*/
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    private final StringRedisTemplate stringTemplate;

    @Override
    public User login(User user) {
        //通过用户名和密码查询用户
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper();
        wrapper.eq(User::getUsername,user.getUsername());
        wrapper.eq(User::getPassword,user.getPassword());
        User one = this.getOne(wrapper);
        if (one == null) {
            return null;
        }
        return one;
    }

    @Override
    public String reg(UserPo user) {
        //1.验证验证码是否正确
        //1.1从redis中验证
        if ( !stringTemplate.hasKey(REDIS_REG_CODE + user.getEmail())){
            //没有这个key返回失败 没有发送验证码
            return "验证码过期";
        }
        //3.保存密码
        //3.1保存密码
        User newuser = BeanUtil.copyProperties(user, User.class);
        //设置默认昵称
        newuser.setNickname(user.getUsername());
        newuser.setXp(0);
        newuser.setType("0");

        //把用户存入数据库
        this.save(newuser);
        //清除redis中的验证码
        stringTemplate.delete(REDIS_REG_CODE + user.getEmail());
        return "注册成功";
    }


    public void singed(){

    }

    @Override
    public Sign sign(Integer uid) {
        //创建一个对象
        Sign sign = new Sign();
        //先从redis中查询到签到的全部用户
        ZSetOperations<String, String> Zset = stringTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> typedTuples = Zset.rangeWithScores(REDIS_SIGN, 0, -1);

        List<String> range = stringTemplate.opsForList().range(REDIS_SIGNED,0,-1);
        if (!range.contains(uid.toString())) {
            //存入list 判断今天是否签到了
            stringTemplate.opsForList().rightPush(REDIS_SIGNED,uid.toString());
            stringTemplate.expire(REDIS_SIGNED,1,TimeUnit.DAYS);
        }else{
            return null;
        }
        //把当前用户的经验+3
        User u = this.getById(uid);
        u.setXp(u.getXp()+3);
        //存入
        this.saveOrUpdate(u);
        //判断当前用户是否签到过
        for (ZSetOperations.TypedTuple<String> key : typedTuples){
            //循环判断是否有这个key
            if(key.getValue().equals(uid.toString())){
                //把当前的值+1
                stringTemplate.opsForZSet().incrementScore(REDIS_SIGN,uid.toString(),1.0d);
                sign.setSignCount(stringTemplate.opsForZSet().size(REDIS_SIGN));
                sign.setScore(key.getScore()+1);
                return sign;
            }
        }
        //如果没有当前用户 就把当前用户存入
        stringTemplate.opsForZSet().incrementScore(REDIS_SIGN,uid.toString(),1);
        sign.setSignCount(stringTemplate.opsForZSet().count(REDIS_SIGN,0,-1));
        sign.setScore(1d);
        sign.setSignCount(1L);
        return sign;
    }

    @Override
    public Sign signByUid(Integer uid) {
        //创建一个对象
        Sign sign = new Sign();
        //查询签到人数
        Long size = stringTemplate.opsForList().size(REDIS_SIGNED);
        sign.setSignCount(size);
        //先从redis中查询到签到的全部用户
        ZSetOperations<String, String> Zset = stringTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> typedTuples = Zset.rangeWithScores(REDIS_SIGN, 0, -1);
        //通过uid查询签到天数
        for (ZSetOperations.TypedTuple<String> key : typedTuples){
            //循环判断是否有这个key
            if(key.getValue().equals(uid.toString())){
                //把当前的值+1
              sign.setScore(key.getScore());
            }
        }
        return sign;
    }

    @Override
    public List<User> signsort() {
        //查询全部签到的用户
        List<String> range = stringTemplate.opsForList().range(REDIS_SIGNED, 0, 4);
        List<User> list = new ArrayList<>();
        User u = new User();
        for(String item : range){
            u = this.getById(item);
            list.add(u);
        }
        return list;
    }

    @Override
    public List<User> sortByXp() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(User::getXp);
        wrapper.last("limit 0,5");
        List<User> list = list(wrapper);
        return list;
    }

    @Override
    public User getOneById(Integer uid) {
        User user = this.getById(uid);

        return user;
    }

    @Override
    public Page<User> listByPage(Integer curr) {
        Page<User> page = new Page<>(curr,MAX_SIZE);

        List<User> list = this.baseMapper.listByPage(curr);

        page.setRecords(list);
        page.setTotal(this.count());
        return page;
    }
}




