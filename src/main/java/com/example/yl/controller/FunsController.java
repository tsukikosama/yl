package com.example.yl.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.yl.common.Result;
import com.example.yl.entity.Funs;
import com.example.yl.entity.User;
import com.example.yl.pojo.UserFuns;
import com.example.yl.service.FunsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("粉丝接口")
@RestController
@RequestMapping("/funs")
@RequiredArgsConstructor
public class FunsController {
    private final FunsService funsService;

    @ApiOperation("通过uid查询关注的人")
    @GetMapping("/{page}/{uid}")
    public Result getFuns(@PathVariable("page")Integer curr, @PathVariable("uid")Integer uid ){
        Page<UserFuns> page = funsService.listByPage(curr,uid);
        return Result.ok(page);
    }
    @ApiOperation("是否关注")
    @PostMapping("/like/{uid}/{fid}")
    public Result like(@PathVariable("uid") Integer uid,@PathVariable("fid")Integer fid){
        funsService.like(uid,fid);
        return Result.ok();
    }

    @ApiOperation("查询当前用户关注的用户")
    @GetMapping("/same/{uid}/{fid}")
    public Result userFuns(@PathVariable("uid") Integer uid,@PathVariable("fid")Integer fid){
        List<Funs> list = funsService.findUserFunsByUid(uid,fid);
        return Result.ok(list);
    }


}
