package com.okdeer.mall.utils;

import java.util.Collection;
import java.util.List;

import org.assertj.core.internal.Lists;
import org.dozer.DozerBeanMapper;

/**
 * 简单封装Dozer, 实现深度转换Bean<->Bean的Mapper.实现:
 * 
 * 1. 持有Mapper的单例.
 * 2. 返回值类型转换.
 * 3. 批量转换Collection中的所有对象.
 * 4. 区分创建新的B对象与将对象A值复制到已存在的B对象两种函数.
 * 对于相同的属性可以直接转换  对于多层的对象也可以转化 效率比spring的转换要高
 * @author calvin
 */
public class BeanMapper {

    /**
     * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.
     */
    private static DozerBeanMapper dozer = new DozerBeanMapper();

    /**
     * 基于Dozer转换对象的类型.
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        if (source == null) {
            return null;
        }
        return dozer.map(source, destinationClass);
    }

    /**
     * 基于Dozer转换Collection中对象的类型.
     */
    public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        List<T> destinationList = Lists.newArrayList();
        for (Object source : sourceList) {
            T destinationObject = dozer.map(source, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

    /**
     * 基于Dozer将对象A的值拷贝到对象B中.
     */
    public static void copy(Object source, Object destinationObject) {
        dozer.map(source, destinationObject);
    }
}

