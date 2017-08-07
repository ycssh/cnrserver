package cn.cnr.admin.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by luyufeng on 2016/6/29.
 */
public class LoggerUtils {
    private static final Logger logger = LoggerFactory.getLogger(LoggerUtils.class);

    public static Logger getLogger() {
        return logger;
    }
}
