package com.minuor.dynamic.check.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author: minuor
 * @Date: 2018/4/21
 * @Desc:
 */
@Data
public class DynamicCheckReqDto {
    private Integer id;
    private Integer intCheck;
    private String strCheck;
    private Long longCheck;
    private Double doubleCheck;
    private Date dateCheck;
}
