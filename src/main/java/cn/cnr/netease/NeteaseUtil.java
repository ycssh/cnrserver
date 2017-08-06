package cn.cnr.netease;

import cn.cnr.admin.base.model.Result;
import cn.cnr.admin.base.util.OkHttpUtils;
import cn.cnr.admin.spring.CustomPropertyConfigurer;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author yuchao
 * @date 2017/8/6
 * @desc：
 */
public class NeteaseUtil {

    private static final String appKey = CustomPropertyConfigurer.getProperty("appKey");
    private static final String appSecret = CustomPropertyConfigurer.getProperty("appSecret");

    public Result send(String url, Map<String, String> params){
        String nonce = UUID.randomUUID().toString();
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);//参考 计算CheckSum的java代码
        Map<String, String> headers = new HashMap(){
            {put("AppKey", appKey);}
            {put("Nonce", nonce);}
            {put("CurTime", curTime);}
            {put("CheckSum", checkSum);}
            {put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");}
        };

        try {
            String s = OkHttpUtils.sendPost(url, params, headers);
            return new Result();
        } catch (IOException e) {
            return new Result(1,"请求出错");
        }
    }
}
