package com.example.yl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yl.entity.Funs;
import com.example.yl.entity.Post;
import com.example.yl.service.PostService;
import com.example.yl.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.yl.utils.Common.COLLECT;
import static com.example.yl.utils.Common.MAX_SIZE;

/**
* @author 10833
* @description 针对表【post】的数据库操作Service实现
* @createDate 2024-01-24 20:00:33
*/
@Service
@RequiredArgsConstructor
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
    implements PostService{

    private final StringRedisTemplate redisTemplate;

    private final FunsServiceImpl funsService;
    @Override
    public Page<Post> listByPage(Integer curr) {
        Page<Post> page = new Page<>(curr,MAX_SIZE);
        List<Post> list = this.baseMapper.listByPage(curr);
        page.setRecords(list);
        page.setTotal(this.count());
        page.setPages(list.size()/5);
        return page;
    }

    @Override
    public Page<Post> likesByPage(Integer curr) {
        Page<Post> page = new Page<>(curr,MAX_SIZE);

        List<Post> list = this.baseMapper.likesByPage(curr);

        page.setRecords(list);
        page.setTotal(this.count());
        return page;
    }

    @Override
    public Page<Post> visitByPage(Integer curr) {
        Page<Post> page = new Page<>(curr,MAX_SIZE);

        List<Post> list = this.baseMapper.visitByPage(curr);

        page.setRecords(list);
        page.setTotal(this.count());
        return page;
    }

    @Override
    public Page<Post> findByUid(Integer curr,Integer uid) {
        Page<Post> page = new Page<>(curr,MAX_SIZE);
        List<Post> list = this.baseMapper.findByUid(curr,uid);
        page.setRecords(list);
        page.setTotal(list.size());

        return page;
    }

    @Override
    public List<Post> findPostByUid(Integer uid) {
        //通过uid查询全部的post
        List<Post> list = this.baseMapper.listByUid(uid);
        return list;
    }

    @Override
    public List<Post> search(String key) {
        List<Post> list = this.baseMapper.search(key);
        return list;
    }

    @Override
    public List<Post> hot() {
        List<Post> list = this.baseMapper.hot();
        return list;
    }

    @Override
    public Long Collect(Integer pid, Integer uid) {
        //拼key
        String key = COLLECT + uid ;
        //查询把当前的添加进去
        Long add = redisTemplate.opsForSet().add(key, String.valueOf(pid));
        if (add == 0){
            //如果存在就移除
            redisTemplate.opsForSet().remove(key, String.valueOf(pid));
        }
        return add;
    }

    @Override
    public Page<Post> findCollectListByUid(Integer uid, Integer curr) {
        String key = COLLECT + uid;
        //通过uid查询收藏的帖子
        Set<String> members = redisTemplate.opsForSet().members(key);
        Page<Post> page = new Page<>(curr,MAX_SIZE);
        //通过id去查询帖子
        List<Post> list = members.stream().map(item -> this.baseMapper.selectById(item)).collect(Collectors.toList());
        page.setRecords(list);
        page.setTotal(list.size());
        return page;
    }

    //查询关注列表的帖子
    @Override
    public Page<Post> followList(Integer uid,Integer curr) {
        Page<Post> page = new Page<>(1,MAX_SIZE);
        List<Post> list = this.baseMapper.followListByUid(uid,curr);
        page.setRecords(list);
        page.setTotal(list.size());
        return page;
    }
}




