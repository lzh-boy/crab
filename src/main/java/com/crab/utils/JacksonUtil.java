package com.crab.utils;

import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

import java.text.SimpleDateFormat;

/**
 * Jackson Json 工具类
 * 
 * @author ligang
 */
public class JacksonUtil {

	private static ObjectMapper defaultMapper;
	private static ObjectMapper formatedMapper;

	static {
		defaultMapper = new ObjectMapper();// 默认的ObjectMapper
		defaultMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		
		formatedMapper = new ObjectMapper();
		formatedMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		formatedMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));// 所有日期格式都统一为固定格式
		formatedMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS,false);
	}

	/**
	 * 将对象转化为json数据
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String toJson(Object obj) throws Exception {
		if (obj == null) {
			throw new IllegalArgumentException("this argument is required; it must not be null");
		}
		return defaultMapper.writeValueAsString(obj);
	}

	/**
	 * json数据转化为对象(Class)
	 * 
	 * <br>
	 * e.g. <br>
	 * User u = JacksonUtil.parseJson(jsonValue, User.class); <br>
	 * User[] arr = JacksonUtil.parseJson(jsonValue, User[].class);
	 * 
	 * @param jsonValue
	 * @param classValue
	 * @return
	 * @throws Exception
	 */
	public static <T> T parseJson(String jsonValue, Class<T> valueType) throws Exception {
		if (jsonValue == null || valueType == null) {
			throw new IllegalArgumentException("this argument is required; it must not be null");
		}
		return (T) defaultMapper.readValue(jsonValue, valueType);
	}

	/**
	 * json数据转化为对象(JavaType)
	 * 
	 * @param jsonValue
	 * @param valueType
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T parseJson(String jsonValue, JavaType valueType) throws Exception {
		if (jsonValue == null || valueType == null) {
			throw new IllegalArgumentException("this argument is required; it must not be null");
		}
		return (T) defaultMapper.readValue(jsonValue, valueType);
	}

	/**
	 * json数据转化为对象(TypeReference)
	 * 
	 * <br>
	 * e.g. <br>
	 * TypeReference<List<User>> typeRef = new TypeReference<List<User>>(){}; <br>
	 * List<User> list = JacksonUtil.parseJson(jsonValue, typeRef);
	 * 
	 * @param jsonValue
	 * @param valueTypeRef
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T parseJson(String jsonValue, TypeReference<T> valueTypeRef) throws Exception {
		if (jsonValue == null || valueTypeRef == null) {
			throw new IllegalArgumentException("this argument is required; it must not be null");
		}
		return (T) defaultMapper.readValue(jsonValue, valueTypeRef);
	}

	/**
	 * 将对象转化为json数据(时间转换格式： "yyyy-MM-dd HH:mm:ss")
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String toJsonWithFormat(Object obj) throws Exception {
		if (obj == null) {
			throw new IllegalArgumentException("this argument is required; it must not be null");
		}
		return formatedMapper.writeValueAsString(obj);
	}

	/**
	 * json数据转化为对象(时间转换格式： "yyyy-MM-dd HH:mm:ss")
	 * 
	 * <br>
	 * e.g. <br>
	 * User u = JacksonUtil.parseJsonWithFormat(jsonValue, User.class); <br>
	 * User[] arr = JacksonUtil.parseJsonWithFormat(jsonValue, User[].class);
	 * 
	 * @param jsonValue
	 * @param valueType
	 * @return
	 * @throws Exception
	 */
	public static <T> T parseJsonWithFormat(String jsonValue, Class<T> valueType) throws Exception {
		if (jsonValue == null || valueType == null) {
			throw new IllegalArgumentException("this argument is required; it must not be null");
		}
		return (T) formatedMapper.readValue(jsonValue, valueType);
	}

	/**
	 * json数据转化为对象(JavaType)
	 * 
	 * @param jsonValue
	 * @param valueType
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T parseJsonWithFormat(String jsonValue, JavaType valueType) throws Exception {
		if (jsonValue == null || valueType == null) {
			throw new IllegalArgumentException("this argument is required; it must not be null");
		}
		return (T) formatedMapper.readValue(jsonValue, valueType);
	}

	/**
	 * json数据转化为对象(TypeReference)
	 * 
	 * <br>
	 * e.g. <br>
	 * TypeReference<List<User>> typeRef = new TypeReference<List<User>>(){}; <br>
	 * List<User> list = JacksonUtil.parseJsonWithFormat(jsonValue, typeRef);
	 * 
	 * @param jsonValue
	 * @param valueTypeRef
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T parseJsonWithFormat(String jsonValue, TypeReference<T> valueTypeRef) throws Exception {
		if (jsonValue == null || valueTypeRef == null) {
			throw new IllegalArgumentException("this argument is required; it must not be null");
		}
		return (T) formatedMapper.readValue(jsonValue, valueTypeRef);
	}

}
