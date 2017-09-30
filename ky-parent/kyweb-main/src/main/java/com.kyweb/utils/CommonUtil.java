package com.kyweb.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/9/22.
 */
@Slf4j
public class CommonUtil {

    /**
     * 判断bean对象中的properties是否为空（null或空白字符串）<br/>
     * 通过properties，利用反射机制获取properties的get方法进行属性值的检测
     * @param obj
     * @param properties
     * @return 返回为空的属性信息
     */
    public static String checkBeanPropertiesHaveBlank(Object obj, String... properties) {
        StringBuffer sb = new StringBuffer();
        for (String pro : properties) {
            String value = (String) getFieldValueByName(pro, obj);
            if (StringUtils.isEmpty(value)) {
                sb.append("属性[" + pro + "]是[" + value + "], 请修正;");
            }
        }
        String re = sb.toString();
        if (re != null && !"".equals(re.trim())) {
            re = re.substring(0, re.length() - 1);
        }
        return re;
    }

    /**
     * 根据属性名获取属性值
     * */
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

}
