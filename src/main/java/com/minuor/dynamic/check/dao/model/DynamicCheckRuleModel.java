package com.minuor.dynamic.check.dao.model;

import lombok.Data;

/**
 * @Author: minuor
 * @Date: 2018/4/21
 * @Desc:
 */
@Data
public class DynamicCheckRuleModel {
    /**
     * 校验规则
     */
    private String ruleExpress;
    /**
     * 提示信息
     */
    private String toastMsg;
    /**
     * 字段名
     */
    private String fieldName;
    /**
     * 字段值
     */
    private Object fieldValue;
    /**
     * 模板编号
     */
    private String templateId;
    /**
     * 字段描述
     */
    private String fieldDesc;
    /**
     * 检查级别
     */
    private String checkLevel;
}
