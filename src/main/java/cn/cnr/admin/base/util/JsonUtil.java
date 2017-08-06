package cn.cnr.admin.base.util;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * @author 作者姓名 yc E-mail: ycssh2@163.com
 * @version 创建时间：2014-5-13 下午03:30:38 类说明
 */
public class JsonUtil {
	public static final ObjectMapper mapper = new ObjectMapper();


	/** object to json string */
	public static String toJSONString(Object obj) throws IOException {
			return mapper.writeValueAsString(obj);
	}

	/** json string to object */
	public static <T> T parse(String text, Class<T> clazz) throws IOException {
			return mapper.readValue(text, clazz);
	}

}
