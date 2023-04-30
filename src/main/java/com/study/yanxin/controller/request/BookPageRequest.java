package com.study.yanxin.controller.request;

import lombok.Data;

@Data
public class BookPageRequest extends BaseRequest{
    private String name;
    private String bookNo;
}
