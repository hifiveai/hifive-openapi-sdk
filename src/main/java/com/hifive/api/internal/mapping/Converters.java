package com.hifive.api.internal.mapping;


import com.hifive.api.ApiException;
import com.hifive.api.Constants;
import com.hifive.api.HifiveResponse;
import com.hifive.api.internal.util.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 转换工具类。
 *
 * @author carver.gu
 * @since 1.0, Apr 11, 2010
 */
public class Converters {

    /**
     * 是否对JSON返回的数据类型进行校验，默认不校验。给内部测试JSON返回时用的开关。
     * 规则：返回的"基本"类型只有String,Long,Boolean,Date,采取严格校验方式，如果类型不匹配，报错
     */
    public static boolean isCheckJsonType = false;

    private static final Set<String> baseFields = new HashSet<String>();

    private static Map<String, Type> tClass = new HashMap<>();

    static {
        baseFields.add("code");
        baseFields.add("msg");
        baseFields.add("subCode");
        baseFields.add("subMsg");
        baseFields.add("body");
        baseFields.add("params");
        baseFields.add("success");
        baseFields.add("taskId");
        baseFields.add("topForbiddenFields");
    }

    private Converters() {
    }

    /**
     * 使用指定 的读取器去转换字符串为对象。
     *
     * @param <T>    领域泛型
     * @param clazz  领域类型
     * @param reader 读取器
     * @return 领域对象
     * @throws ApiException
     */
    public static <T> T convert(Class<T> clazz, Reader reader) throws ApiException {
        T rsp = null;

        try {
            rsp = clazz.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor pd : pds) {
                Method method = pd.getWriteMethod();
                if (method == null) { // ignore read-only fields
                    continue;
                }

                String itemName = pd.getName();
                String listName = null;

                Field field;
                if (baseFields.contains(itemName) && HifiveResponse.class.isAssignableFrom(clazz)) {
                    field = HifiveResponse.class.getDeclaredField(itemName);
                } else {
                    //field = clazz.getDeclaredField(itemName);
                    field = ReflectionUtils.findField(clazz, itemName);
                    ReflectionUtils.makeAccessible(field);


                }
                //目前先写死
                tClass.put(itemName, field.getGenericType());

                ApiField jsonField = field.getAnnotation(ApiField.class);
                if (jsonField != null) {
                    itemName = jsonField.value();
                }
                ApiListField jsonListField = field.getAnnotation(ApiListField.class);
                if (jsonListField != null) {
                    listName = jsonListField.value();
                }

                if (!reader.hasReturnField(itemName)) {
                    if (listName == null || !reader.hasReturnField(listName)) {
                        continue; // ignore non-return field
                    }
                }

                Class<?> typeClass = field.getType();
                // 目前
                if (String.class.isAssignableFrom(typeClass)) {
                    Object value = reader.getPrimitiveObject(itemName);
                    if (value instanceof String) {
                        method.invoke(rsp, value.toString());
                    } else {
                        if (isCheckJsonType && value != null) {
                            throw new ApiException(itemName + " is not a String");
                        }
                        if (value != null) {
                            method.invoke(rsp, value.toString());
                        } else {
                            method.invoke(rsp, "");
                        }
                    }
                } else if (Long.class.isAssignableFrom(typeClass)) {
                    Object value = reader.getPrimitiveObject(itemName);
                    if (value instanceof Long) {
                        method.invoke(rsp, (Long) value);
                    } else {
                        if (isCheckJsonType && value != null) {
                            throw new ApiException(itemName + " is not a Number(Long)");
                        }
                        if (StringUtils.isNumeric(value)) {
                            method.invoke(rsp, Long.valueOf(value.toString()));
                        }
                    }
                } else if (Integer.class.isAssignableFrom(typeClass)) {
                    Object value = reader.getPrimitiveObject(itemName);
                    if (value instanceof Integer) {
                        method.invoke(rsp, (Integer) value);
                    } else {
                        if (isCheckJsonType && value != null) {
                            throw new ApiException(itemName + " is not a Number(Integer)");
                        }
                        if (StringUtils.isNumeric(value)) {
                            method.invoke(rsp, Integer.valueOf(value.toString()));
                        }
                    }
                } else if (Boolean.class.isAssignableFrom(typeClass)) {
                    Object value = reader.getPrimitiveObject(itemName);
                    if (value instanceof Boolean) {
                        method.invoke(rsp, (Boolean) value);
                    } else {
                        if (isCheckJsonType && value != null) {
                            throw new ApiException(itemName + " is not a Boolean");
                        }
                        if (value != null) {
                            method.invoke(rsp, Boolean.valueOf(value.toString()));
                        }
                    }
                } else if (Double.class.isAssignableFrom(typeClass)) {
                    Object value = reader.getPrimitiveObject(itemName);
                    if (value instanceof Double) {
                        method.invoke(rsp, (Double) value);
                    } else {
                        if (isCheckJsonType && value != null) {
                            throw new ApiException(itemName + " is not a Double");
                        }
                    }
                } else if (Number.class.isAssignableFrom(typeClass)) {
                    Object value = reader.getPrimitiveObject(itemName);
                    if (value instanceof Number) {
                        method.invoke(rsp, (Number) value);
                    } else {
                        if (isCheckJsonType && value != null) {
                            throw new ApiException(itemName + " is not a Number");
                        }
                    }
                } else if (Date.class.isAssignableFrom(typeClass)) {
                    DateFormat format = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                    format.setTimeZone(TimeZone.getTimeZone(Constants.DATE_TIMEZONE));
                    Object value = reader.getPrimitiveObject(itemName);
                    if (value instanceof String) {
                        method.invoke(rsp, format.parse(value.toString()));
                    }
                } else if (List.class.isAssignableFrom(typeClass)) {
                    Type fieldType = field.getGenericType();
                    if (fieldType instanceof ParameterizedType) {
                        ParameterizedType paramType = (ParameterizedType) fieldType;
                        Type[] genericTypes = paramType.getActualTypeArguments();
                        if (genericTypes != null && genericTypes.length > 0) {
                            if (genericTypes[0] instanceof Class<?>) {
                                Class<?> subType = (Class<?>) genericTypes[0];
                                List<?> listObjs = reader.getListObjects(listName, itemName, subType);
                                if (listObjs != null) {
                                    method.invoke(rsp, listObjs);
                                }
                            } else {
                                String name = org.springframework.util.StringUtils.uncapitalize(clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1));
                                fieldType = tClass.get(name);
                                if (fieldType != null) {
                                    if (fieldType instanceof ParameterizedType) {
                                        paramType = (ParameterizedType) fieldType;
                                        genericTypes = paramType.getActualTypeArguments();
                                        if (genericTypes != null && genericTypes.length > 0) {
                                            if (genericTypes[0] instanceof Class<?>) {
                                                Class<?> subType = (Class<?>) genericTypes[0];
                                                List<?> listObjs = reader.getListObjects(listName, itemName, subType);
                                                if (listObjs != null) {
                                                    method.invoke(rsp, listObjs);
                                                }
                                            }else {
                                                paramType =   (ParameterizedType)genericTypes[0];
                                                Type rawType = paramType.getRawType();
                                                Class<?> subType = (Class<?>) rawType;
                                                name = org.springframework.util.StringUtils.uncapitalize(subType.getTypeName().substring(subType.getTypeName().lastIndexOf(".") + 1));
                                                tClass.put(name,paramType.getActualTypeArguments()[0]);
                                                List<?> listObjs = reader.getListObjects(listName, itemName, subType);
                                                if (listObjs != null) {
                                                    method.invoke(rsp, listObjs);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    Object obj = reader.getObject(itemName, typeClass);
                    if (obj != null) {
                        method.invoke(rsp, obj);
                    }
                }
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }

        return rsp;
    }
}
