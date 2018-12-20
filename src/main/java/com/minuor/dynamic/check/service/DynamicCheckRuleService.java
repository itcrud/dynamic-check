package com.minuor.dynamic.check.service;

import com.minuor.dynamic.check.dao.model.DynamicCheckRuleModel;

import java.util.List;

/**
 * @Author: minuor
 * @Date: 2018/4/21
 * @Desc:
 */
public interface DynamicCheckRuleService {
    List<DynamicCheckRuleModel> queryRuleByBeanName(String beanName);
}
