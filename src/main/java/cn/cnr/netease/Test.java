package cn.cnr.netease;

import cn.cnr.admin.base.util.OkHttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.*;

public class Test {
    public static void main(String[] args) throws Exception {
        String url = "https://api.netease.im/nimserver/user/create.action";

        String appKey = "32e3df5f5e256ed8a9eb06fedaf37378";
        String appSecret = "88c681637724";
        String nonce = UUID.randomUUID().toString();
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);//参考 计算CheckSum的java代码
        Map<String, String> params = new HashMap(){
                {put("accid", "ycssh24");}
        };

        Map<String, String> headers = new HashMap(){
                {put("AppKey", appKey);}
                {put("Nonce", nonce);}
                {put("CurTime", curTime);}
                {put("CheckSum", checkSum);}
                {put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");}
        };

        String s = OkHttpUtils.sendPost(url, params, headers);

        System.out.println(s);
    }
}