package com.minuor.dynamic.check.aspect;

import com.minuor.dynamic.check.dao.model.DynamicCheckRuleModel;
import com.minuor.dynamic.check.exception.DynamicCheckException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author: minuor
 * @Date: 2018/4/19
 * @Desc:
 */
@Slf4j
@Component
public class DynamicCheckEngine {

    /**
     * 综合校验分发器
     *
     * @param paramRules
     */
    public void checkParamter(List<DynamicCheckRuleModel> paramRules) throws Exception {
        paramRules.sort(Comparator.comparing(DynamicCheckRuleModel::getCheckLevel));
        for (DynamicCheckRuleModel ruleModel : paramRules) {
            Method method = this.getClass().getMethod(ruleModel.getTemplateId(), DynamicCheckRuleModel.class);
            Object result = method.invoke(this, ruleModel);
            if (result != null) {
                throw new DynamicCheckException((String) result);
            }
        }
    }

    /**
     * 检查非空
     * 模板编号：notBlank
     */
    public String notBlank(DynamicCheckRuleModel roleModel) throws DynamicCheckException {
        Object fieldValue = roleModel.getFieldValue();
        if (fieldValue == null) {
            return generateToastMsg(roleModel);
        } else {
            if ((fieldValue instanceof String) && StringUtils.isBlank((String) fieldValue)) {
                return generateToastMsg(roleModel);
            }
        }
        return null;
    }

    /**
     * 检查非空
     * 模板编号：notNull
     */
    public String notNull(DynamicCheckRuleModel roleModel) throws DynamicCheckException {
        if (roleModel.getFieldValue() == null) return generateToastMsg(roleModel);
        return null;
    }

    /**
     * 检查长度最大值
     * 模板编号：lengthMax
     */
    public String lengthMax(DynamicCheckRuleModel roleModel) throws DynamicCheckException {
        String fieldValue = (String) roleModel.getFieldValue();
        if (fieldValue.length() > Integer.valueOf(roleModel.getRuleExpress().trim())) {
            return generateToastMsg(roleModel);
        }
        return null;
    }

    /**
     * 检查长度最小值
     * 模板编号：lengthMin
     */
    public String lengthMin(DynamicCheckRuleModel roleModel) throws DynamicCheckException {
        String fieldValue = (String) roleModel.getFieldValue();
        if (fieldValue.length() < Integer.valueOf(roleModel.getRuleExpress().trim())) {
            return generateToastMsg(roleModel);
        }
        return null;
    }

    /**
     * 检查值最大值
     * 模板编号：valueMax
     */
    public String valueMax(DynamicCheckRuleModel roleModel) throws DynamicCheckException {
        Double fieldValue = Double.valueOf(roleModel.getFieldValue().toString());
        if (fieldValue > Double.valueOf(roleModel.getRuleExpress())) {
            return generateToastMsg(roleModel);
        }
        return null;
    }

    /**
     * 检查值最小值
     * 模板编号：valueMin
     */
    public String valueMin(DynamicCheckRuleModel roleModel) throws DynamicCheckException {
        Double fieldValue = Double.valueOf(roleModel.getFieldValue().toString());
        if (fieldValue < Double.valueOf(roleModel.getRuleExpress())) {
            return generateToastMsg(roleModel);
        }
        return null;
    }

    /**
     * 正则格式校验
     * 模板编号：regex
     */
    public String regex(DynamicCheckRuleModel roleModel) throws DynamicCheckException {
        String value = (String) roleModel.getFieldValue();
        if (!Pattern.matches(roleModel.getRuleExpress(), value)) {
            return generateToastMsg(roleModel);
        }
        return null;
    }

    /**
     * 构建结果信息
     */
    private String generateToastMsg(DynamicCheckRuleModel roleModel) throws DynamicCheckException {
        String[] element = new String[]{StringUtils.isNotBlank(roleModel.getFieldDesc())
                ? roleModel.getFieldDesc() : roleModel.getFieldName(), roleModel.getRuleExpress()};
        String toast = roleModel.getToastMsg();
        int index = 0;
        while (index < element.length) {
            String replace = toast.replace("{" + index + "}", element[index] + "");
            if (toast.equals(replace)) break;
            toast = replace;
            index++;
        }
        return toast;
    }
}