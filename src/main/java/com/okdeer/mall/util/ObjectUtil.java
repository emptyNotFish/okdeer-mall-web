package com.okdeer.mall.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * ClassName: ObjectUtil 
 * @Description: 对象操作类
 * @author zengjizu
 * @date 2017年2月21日
 *
 * =================================================================================================
 *     Task ID			  Date			     Author		      Description
 * ----------------+----------------+-------------------+-------------------------------------------
 *
 */
public class ObjectUtil {

	/**
	 * @Description: 对string 类型的子段进行空格去除
	 * @param object 要去除空格的对象
	 * @author zengjizu
	 * @date 2017年2月21日
	 */
	public static void propertyToTrim(Object object) {
		if (object == null) {
			return;
		}
		Method readMethod = null;
		Method writeMethod = null;
		PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(object);

		for (int i = 0; i < propertyDescriptors.length; i++) {
			String name = propertyDescriptors[i].getName();
			if ("class".equals(name)) {
				continue;
			}
			readMethod = propertyDescriptors[i].getReadMethod();
			try {
				Object value = readMethod.invoke(object, new Object[0]);
				if (value == null) {
					continue;
				}
				if (value instanceof String) {
					writeMethod = propertyDescriptors[i].getWriteMethod();
					writeMethod.invoke(object, StringUtils.trim((String) value));
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * @Description: 对map去除对象
	 * @param dataMap map对象
	 * @author zengjizu
	 * @date 2017年2月21日
	 */
	public static void mapPropertyToTrim(Map<String, String> dataMap) {
		Set<String> keys = dataMap.keySet();

		if (keys != null && keys.size() > 0) {
			String value = null;
			for (String key : keys) {
				value = dataMap.get(key);
				if (value == null) {
					continue;
				}
				if (value instanceof String) {
					dataMap.put(key, StringUtils.trim(value));
				}
			}
		}
	}

	/**
	 * @Description: 获取对象属性
	 * @param obj obj 对象
	 * @return
	 * @author zengjizu
	 * @date 2017年2月21日
	 */
	private static PropertyDescriptor[] getPropertyDescriptors(Object obj) {

		PropertyDescriptor[] descriptors = null;

		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(obj.getClass());
		} catch (IntrospectionException e) {
			return new PropertyDescriptor[0];
		}
		descriptors = beanInfo.getPropertyDescriptors();
		if (descriptors == null) {
			descriptors = new PropertyDescriptor[0];
		}
		return descriptors;
	}

}
