package cn.cnr.admin.base.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 
 * 类功能描述:从http中获取查询参数，组装成map，适用于查询参数太多，而没有对应的一个实体类匹配的情况 创建者： 余超 创建日期： 2016年7月4日
 * 
 * 修改内容： 修改者： 修改日期：
 */
public class HttpParamsUtil {

	public static Map<String, Object> getParams(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
		Enumeration<String> names = request.getParameterNames();
		String eL = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
		Pattern p = Pattern.compile(eL);
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String value = request.getParameter(name);
			if ("page".equals(name) || "rows".equals(name)) {
				// 分页的参数直接转换成int
				map.put(name, Integer.parseInt(value));
			} else if (StringUtils.hasLength(value)) {
				if (p.matcher(value).matches()) {
					try {
						// 日期格式的转换成日期
						if (name.endsWith("End")) {
							map.put(name, new Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value+" 23:59:59").getTime()+1000));
						} else if (name.endsWith("Start")) {
							map.put(name, new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss").parse(value
									+ " 00:00:00"));
						} else {
							map.put(name, new SimpleDateFormat("yyyy-MM-dd")
									.parse(value));
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				} else if (StringUtils.hasLength(value)) {
					map.put(name, value);
				}

			}

		}
		return map;
	}
}