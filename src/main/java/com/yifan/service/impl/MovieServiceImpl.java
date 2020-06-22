package com.yifan.service.impl;

import com.yifan.entity.Movie;
import com.yifan.mapper.MovieMapper;
import com.yifan.service.MovieService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 弋凡
 * @since 2020-05-11
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements MovieService {

}
