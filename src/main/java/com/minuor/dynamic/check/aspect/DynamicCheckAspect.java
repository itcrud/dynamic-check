package com.minuor.dynamic.check.aspect;

import com.minuor.dynamic.check.dao.model.DynamicCheckRuleModel;
import com.minuor.dynamic.check.exception.DynamicCheckException;
import com.minuor.dynamic.check.service.DynamicCheckRuleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: minuor
 * @Date: 2018/4/21
 * @Desc:
 */
@Component
@Slf4j
@Aspect
public class DynamicCheckAspect {

    @Autowired
    private DynamicCheckRuleService dynamicCheckRuleService;
    @Autowired
    private DynamicCheckEngine paramCheckEngine;

    /**
     * 定义切点
     */
    @Pointcut("execution(* com.minuor.dynamic.check.controller.*.*(..))")
    public void pointcut() {
    }

    /**
     * 定义环切
     */
    @Around("pointcut()")
    public void check(ProceedingJoinPoint joinPoint) {
        try {
            // 查询获取请求参数封装类(dto)的类名
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Class<?>[] parameterTypes = signature.getMethod().getParameterTypes();
            String beanName = null;
            if (parameterTypes != null && parameterTypes.length > 0) {
                beanName = parameterTypes[0].getSimpleName();
            }
            //查询当前beanName下字段的所有校验规则
            List<DynamicCheckRuleModel> modelList = null;
            if (StringUtils.isNotBlank(beanName)) {
                modelList = dynamicCheckRuleService.queryRuleByBeanName(beanName);
            }
            if (modelList != null && !modelList.isEmpty()) {
                //规则分类(根据字段名分类)
                Map<String, List<DynamicCheckRuleModel>> ruleMap = new HashMap<>();
                for (DynamicCheckRuleModel ruleModel : modelList) {
                    List<DynamicCheckRuleModel> fieldRules = ruleMap.get(ruleModel.getFieldName());
                    if (fieldRules == null) fieldRules = new ArrayList<>();
                    fieldRules.add(ruleModel);
                    ruleMap.put(ruleModel.getFieldName(), fieldRules);
                }
                //获取请求参数
                Object[] args = joinPoint.getArgs();
                if (args != null && args.length > 0) {
                    Object reqDto = args[0];
                    Field[] fields = reqDto.getClass().getDeclaredFields();
                    if (fields != null && fields.length > 0) {
                        for (Field field : fields) {
                            String fieldName = field.getName();
                            boolean isCheck = ruleMap.containsKey(fieldName);
                            if (!isCheck) continue;
                            field.setAccessible(true);
                            List<DynamicCheckRuleModel> paramRules = ruleMap.get(fieldName);
                            for (DynamicCheckRuleModel ruleModel : ruleMap.get(fieldName)) {
                                ruleModel.setFieldValue(field.get(reqDto));
                            }
                            //校验
                            paramCheckEngine.checkParamter(paramRules);
                        }
                    }
                }
            }
            joinPoint.proceed();
        } catch (Exception e) {
            throw new DynamicCheckException(e.getMessage());
        } catch (Throwable throwable) {
            throw new DynamicCheckException(throwable.getMessage());
        }
    }
}
