package com.study.yanxin.controller.request;

import lombok.Data;

/**
 * @author 26414
 */
@Data
public class UserPageRequest extends BaseRequest{
    private String name;
    private String phone;
}
