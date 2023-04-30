package com.study.yanxin.controller.request;

import lombok.Data;

@Data
public class BorrowPageRequest extends BaseRequest{
    private String bookName;
    private String bookNo;
    private String userName;
}
