package com.yifan.service.impl;

import com.yifan.entity.User;
import com.yifan.mapper.UserMapper;
import com.yifan.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
