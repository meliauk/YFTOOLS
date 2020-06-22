package com.yifan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yifan.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 弋凡
 * @since 2020-05-11
 */
@Component
public interface UserMapper extends BaseMapper<User> {

    List<User> getListUser();

}
