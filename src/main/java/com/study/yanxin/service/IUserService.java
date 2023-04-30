package com.study.yanxin.service;

import com.github.pagehelper.PageInfo;
import com.study.yanxin.controller.request.BaseRequest;
import com.study.yanxin.pojo.User;


import java.security.PublicKey;
import java.util.List;

public interface IUserService {
    List<User> ListUser();
    PageInfo<User> page(BaseRequest baseRequest);

     public void save(User user);

    public User getById(Integer id);

    public  int update(User user);


}


