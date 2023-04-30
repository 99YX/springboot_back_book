package com.study.yanxin.service.impl;

import java.util.*;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.yanxin.controller.request.BaseRequest;
import com.study.yanxin.mapper.UserMapper;
import com.study.yanxin.pojo.User;
import com.study.yanxin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author 26414
 */
@Service
public class IUserServiceImpl implements IUserService {
  @Autowired
    private UserMapper userMapper;
    @Override

    public List<User> ListUser() {
        return userMapper.ListUser();
    }

    @Override
    public PageInfo<User> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<User> users = userMapper.listByCondition(baseRequest);
        return new PageInfo<>(users);
    }

    @Override
    public void save(User user) {
        /*创建日期*/
        Date date=new Date();


        /*卡号由年份+随机生成id组成*/

        /*设置卡号,使用hutool工具类*/
        user.setUsername(DateUtil.format(date,"yyyyMMdd")+IdUtil.fastUUID());

        userMapper.save(user);
    }
    /*根据id查询数据*/
    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }
    /*根据id更新数据*/
    @Override
    public int  update(User user) {
        return userMapper.update(user);
    }


}
