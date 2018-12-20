package com.minuor.dynamic.check.dao.mapper;

import com.minuor.dynamic.check.dao.model.DynamicCheckRuleModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: minuor
 * @Date: 2018/4/21
 * @Desc:
 */
public interface DynamicCheckRuleMapper {
    List<DynamicCheckRuleModel> queryRuleByBeanName(@Param("beanName") String beanName);
}
