package com.minuor.dynamic.check.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: minuor
 * @Date: 2018/4/21
 * @Desc:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DynamicCheckRespDto {
    private Integer code;
    private String msg;
}
