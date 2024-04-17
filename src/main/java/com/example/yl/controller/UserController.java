package com.example.yl.controller;

import com.example.yl.common.Result;
import com.example.yl.entity.Funs;
import com.example.yl.entity.Post;
import com.example.yl.entity.User;
import com.example.yl.pojo.Info;
import com.example.yl.pojo.Sign;
import com.example.yl.pojo.UserPo;
import com.example.yl.service.FunsService;
import com.example.yl.service.PostService;
import com.example.yl.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Api(value = "用户接口")
public class UserController {

    private final UserService userService;

    private final PostService postService;

    private final FunsService funsService;

    @ApiOperation(value = "登录")
    @PostMapping("/getone")
    public Result login(@RequestBody User user){

        return Result.ok(userService.login(user));
    }

    @ApiOperation(value = "注册")
    @PostMapping("/reg")
    public Result reg(@RequestBody UserPo user){
        return Result.ok(userService.reg(user));
    }

    @ApiOperation(value = "查询用户")
    @GetMapping("/list")
    public Result all(){
        return Result.ok(userService.list());
    }

    @ApiOperation("分页查询帖子")
    @RequestMapping("/list/{page}")
    public Result getAll(@PathVariable(value = "page",required = false)Integer page){

        return Result.ok(userService.listByPage(page));
    }

    @ApiOperation(value = "签到功能")
    @PostMapping("/sign")
    public Result sign(@RequestParam("uid")Integer uid){
        Sign sign = userService.sign(uid);
        if (sign == null){
            return Result.fail("已经签到过了");
        }
        return Result.ok(sign);
    }
    @ApiOperation(value = "查询签到信息")
    @GetMapping("/signed/{uid}")
    public Result signed(@PathVariable("uid")Integer uid){
        Sign sign = userService.signByUid(uid);
        return Result.ok(sign);
    }

    @ApiOperation("签到排行")
    @GetMapping("/signsort")
    public Result signsort(){
        List<User> list = userService.signsort();
        return Result.ok(list);
    }
    @ApiOperation("经验排行")
    @GetMapping("/sortbyxp")
    public Result sortByXp(){
        List<User> list =  userService.sortByXp();
        return Result.ok(list);
    }

    @ApiOperation("查询用户的粉丝")
    @GetMapping("/getinfo/{uid}")
    public Result gerInfo(@PathVariable("uid")Integer uid){
        Info info = new Info();
        //查询经验
        User user = userService.getById(uid);
        //查询文章数
        List<Post> postlist = postService.findPostByUid(uid);
        //查询粉丝数
        List<Funs> funsList = funsService.findFunsByUid(uid);
        //通过funs的id查询对应用户
        for(Funs u :funsList){
            User users = userService.getById(u.getFid());
            u.setUser(users);
        }
        //查询评论数

        info.setXp(user.getXp());
        info.setPosts(postlist);
        info.setFuns(funsList);
        return Result.ok(info);
    }

}
