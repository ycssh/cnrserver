package cn.cnr.admin;

import cn.cnr.admin.spring.CustomPropertyConfigurer;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author 作者姓名 yc E-mail: ycssh2@163.com
 * @version 创建时间：2014-5-5 下午01:52:08
 *          类说明 系统常量
 */
@Configuration
public class Constants {
    public static final String CURRENT_USER = "user";
    public static final String SESSION_FORCE_LOGOUT_KEY = "session.force.logout";
    public static final String LOG_RECORD = "log";
    public static final String TREE_OPEN = "open";
    public static final String TREE_CLOSE = "closed";

    //卡密批次号码
    public static AtomicInteger batch;
    //卡号
    public static AtomicLong cardNo;
    //代理人
    public static AtomicInteger promotersNo;

    public static String getFileDir() {
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name").toUpperCase();
        if (os.startsWith("WIN")) {
            return CustomPropertyConfigurer.getProperty("windowsFileDir");
        } else {
            return CustomPropertyConfigurer.getProperty("linuxFileDir");
        }
    }

    public static Map<String, Object> cache = new HashMap<String, Object>();

}
