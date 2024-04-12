package com.example.yl.controller;

import com.example.yl.common.Result;
import com.example.yl.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
@Api("标签接口")
public class TagController {

    private final TagService tagService;

    @GetMapping("/all")
    @ApiOperation("查询全部tag")
    public Result getAll(){
        return Result.ok(tagService.list());
    }


}
