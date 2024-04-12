package com.example.yl.controller;

import com.example.yl.common.Result;
import com.example.yl.entity.Tag;
import com.example.yl.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
@Api("标签接口")
public class TagController {

    private final TagService tagService;

    @GetMapping("/list")
    @ApiOperation("查询全部tag")
    public Result getAll(){
        return Result.ok(tagService.list());
    }

    @GetMapping("/list/{curr}")
    @ApiOperation("分页查询")
    public Result getTagByPage(@RequestParam("curr") Integer curr){
        return Result.ok(tagService.getTagByPage(curr));
    }

    @PostMapping("/sava")
    @ApiOperation("更新或保存")
    public Result saveOrUpdate(@RequestBody Tag tag){
        return Result.ok(tagService.saveOrUpdate(tag));
    }

    @PostMapping("/del")
    @ApiOperation("通过tid删除")
    public Result remove(@RequestParam("tid") Integer tid){
        return Result.ok(tagService.removeById(tid));
    }

}
