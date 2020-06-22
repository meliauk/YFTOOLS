package com.yifan.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yifan.dto.ResultDTO;
import com.yifan.entity.Movie;
import com.yifan.entity.User;
import com.yifan.service.MovieService;
import com.yifan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 弋凡
 * @since 2020-05-11
 */
@RestController
@RequestMapping("/user/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private UserService userService;


    /*----------得到直播源数据*/
    @GetMapping("getMData")
    public ResultDTO<Movie> getMData(@RequestParam String name, @RequestParam String token){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUtoken,token).eq(User::getUname,name);
        User user = userService.getOne(wrapper);
        if(!StringUtils.isEmpty(user)){
            List<Movie> movieList = movieService.list();
            if(!StringUtils.isEmpty(movieList)){
                ResultDTO<Movie> resultDTO = new ResultDTO<>();
                resultDTO.setListObj(movieList);
                resultDTO.setMsg("成功");
                resultDTO.setCode(200);
                return resultDTO;
            }
        }
        return ResultDTO.MError("请求失败",400);

    }



}

