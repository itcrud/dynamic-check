package com.minuor.dynamic.check.service.impl;

import com.minuor.dynamic.check.dao.mapper.DynamicCheckRuleMapper;
import com.minuor.dynamic.check.dao.model.DynamicCheckRuleModel;
import com.minuor.dynamic.check.service.DynamicCheckRuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: minuor
 * @Date: 2018/4/21
 * @Desc:
 */
@Service
@Slf4j
public class DynamicCheckRuleServiceImpl  implements DynamicCheckRuleService {

    @Autowired
    private DynamicCheckRuleMapper dynamicCheckRuleMapper;


    @Override
    public List<DynamicCheckRuleModel> queryRuleByBeanName(String beanName) {
        return dynamicCheckRuleMapper.queryRuleByBeanName(beanName);
    }
}
