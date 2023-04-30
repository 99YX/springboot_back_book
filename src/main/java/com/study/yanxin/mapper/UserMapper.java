package com.study.yanxin.mapper;

import com.study.yanxin.controller.request.BaseRequest;
import com.study.yanxin.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface UserMapper {
   /* @Select("SELECT * FROM user")*/
    List<User> ListUser();
    List<User> listByCondition(BaseRequest baseRequest);

    /*新增方法*/
    void save(User user);

    User getById(Integer id);

    int  update(User user);

}
