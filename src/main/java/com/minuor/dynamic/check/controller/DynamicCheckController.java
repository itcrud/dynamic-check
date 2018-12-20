package com.minuor.dynamic.check.controller;

import com.minuor.dynamic.check.dto.DynamicCheckReqDto;
import com.minuor.dynamic.check.dto.DynamicCheckRespDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: minuor
 * @Date: 2018/4/21
 * @Desc:
 */
@Controller
@Slf4j
@RequestMapping("/")
public class DynamicCheckController {

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @ResponseBody
    public DynamicCheckRespDto index(@RequestBody DynamicCheckReqDto reqDto) {
        return DynamicCheckRespDto.builder().code(1).msg("请求成功").build();
    }
}
