package com.example.yl.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.yl.common.Result;
import com.example.yl.entity.Post;
import com.example.yl.entity.Tag;
import com.example.yl.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Api("论坛接口")
public class PostController {
    private final PostService postService;


    @ApiOperation("分页查询帖子")
    @RequestMapping("/list/{page}")
    public Result getAll(@PathVariable(value = "page",required = false)Integer page){

        return Result.ok(postService.listByPage(page));
    }

    @ApiOperation("查询点赞高的")
    @RequestMapping("/likes/{page}")
    public Result likesByPage(@PathVariable(value = "page",required = false)Integer page){

        return Result.ok(postService.likesByPage(page));
    }

    @ApiOperation("查询点击最高的")
    @RequestMapping("/visit/{page}")
    public Result visitByPage(@PathVariable(value = "page",required = false)Integer page){
        return Result.ok(postService.visitByPage(page));
    }
    @ApiOperation("通过uid来查询对应的帖子")
    @GetMapping("/{uid}/{curr}")
    public Result postByUid(@PathVariable(value = "uid",required = false)Integer uid,@PathVariable("curr")Integer curr){
        Page<Post> page =  postService.findByUid(curr,uid);
        return Result.ok(page);
    }

    @ApiOperation("通过id来查询对应的文章")
    @GetMapping("/{pid}")
    public Result postById(@PathVariable("pid")Integer pid){
        return Result.ok(postService.getById(pid));
    }

    @ApiOperation("添加文章")
    @PostMapping("/add")
    public Result addPost(@RequestBody Post post){
        String s = "";
        for(String key : post.getSaveTag()){
            s += key + ",";
        }
        post.setCtime(DateUtil.now());

        post.setTag(s);
        System.out.println(post);
        postService.save(post);
        return Result.ok("添加成功");
    }

    @ApiOperation("搜索文章")
    @GetMapping("/search")
    public Result search(@RequestParam("key")String key){
       List<Post> list =  postService.search(key);
       return Result.ok(list);
    }

    @ApiOperation("查询全部")
    @GetMapping("/list")
    public Result all(){
        return Result.ok(postService.list());
    }

    @ApiOperation("通过pid删除帖子")
    @PostMapping("/del")
    public Result del(@RequestParam("pid") Integer pid){
        return Result.ok(postService.removeById(pid));
    }

    @PostMapping("/save")
    @ApiOperation("保存帖子")
    public Result save(@RequestBody Post post){

        postService.saveOrUpdate(post);
        return Result.ok("保存成功");
    }

    @GetMapping("/hot")
    @ApiOperation("查询热门帖子")
    public Result hot(){
        return Result.ok(postService.hot());
    }

    @PostMapping("/collect")
    @ApiOperation("收藏")
    public Result Collect(@RequestParam Integer pid,@RequestParam Integer uid){
        Long collect = postService.Collect(pid, uid);
        return Result.ok(collect);
    }

    @ApiOperation("通过用户查询关注列表")
    @GetMapping("/collect/list/{curr}")
    public Result List(@RequestParam Integer uid,@PathVariable Integer curr){
        Page<Post> list = postService.findCollectListByUid(uid,curr);
        return Result.ok(list);
    }

    @ApiOperation("通过用户查询关注用户的帖子")
    @GetMapping("/follow/list/{curr}")
    public Result followList(@RequestParam Integer uid,@PathVariable Integer curr){
        return Result.ok(postService.followList(uid,curr));
    }

}
