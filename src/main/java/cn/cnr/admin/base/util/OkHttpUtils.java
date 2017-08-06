package cn.cnr.admin.base.util;

import com.squareup.okhttp.*;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author yuchao
 * @date 2017/7/21
 * @desc： OKHttpClient的工具类
 */
public class OkHttpUtils {
    private static final OkHttpClient okHttpClient = new OkHttpClient();
    //短链接
    private static final OkHttpClient shortOkHttpClient = new OkHttpClient();
    private static final String CHARSET_NAME = "UTF-8";

    static {
        okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
        shortOkHttpClient.setConnectTimeout(3, TimeUnit.SECONDS);
    }

    public static OkHttpClient getOkHttpClient(){
        return okHttpClient;
    }
    /**
     * 该不会开启异步线程。
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static Response execute(Request request) throws IOException {
        return okHttpClient.newCall(request).execute();
    }
    public static Response shortExecute(Request request) throws IOException {
        return shortOkHttpClient.newCall(request).execute();
    }

    /**
     * 开启异步线程访问网络
     *
     * @param request
     * @param responseCallback
     */
    public static void enqueue(Request request, Callback responseCallback) {
        okHttpClient.newCall(request).enqueue(responseCallback);
    }

    /**
     * 开启异步线程访问网络, 且不在意返回结果（实现空callback）
     *
     * @param request
     */
    public static void enqueue(Request request) {
        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(Response arg0) throws IOException {

            }

            @Override
            public void onFailure(Request arg0, IOException arg1) {

            }
        });
    }

    /**
     * 发送get请求，得到返回的body
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String sendGet(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = shortExecute(request);
        if (response.isSuccessful()) {
            String responseUrl = response.body().string();
            return responseUrl;
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }
    /**
     * 发送get请求，得到返回的body
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String sendShortGet(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = shortExecute(request);
        if (response.isSuccessful()) {
            String responseUrl = response.body().string();
            return responseUrl;
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }
    /**
     * 发送get请求，得到返回的body
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String sendShortGet(String url, List<BasicNameValuePair> params) throws IOException {
        url = attachHttpGetParams(url, params);
        Request request = new Request.Builder().url(url).build();
        Response response = execute(request);
        if (response.isSuccessful()) {
            String responseUrl = response.body().string();
            return responseUrl;
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * 发送get请求，得到返回的body
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String sendPost(String url, List<BasicNameValuePair> params) throws IOException {
        FormEncodingBuilder form = new FormEncodingBuilder();
        if(params!=null&& params.size()>0) {
            for(BasicNameValuePair nameValuePair:params) {
                form.add(nameValuePair.getName(), nameValuePair.getValue());
            }
        }
        RequestBody formBody = form.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        };

        return response.body().string();
    }
    /**
     * 发送get请求，得到返回的body
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String sendPost(String url, Map<String,String> params) throws IOException {
        FormEncodingBuilder form = new FormEncodingBuilder();
        if(params!=null&& params.size()>0) {
            for(Map.Entry<String,String> entry:params.entrySet()) {
                form.add(entry.getKey(), entry.getValue());
            }
        }
        RequestBody formBody = form.build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        };
        return response.body().string();
    }
    /**
     * 发送Post请求，得到返回的body
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String sendPost(String url, Map<String,String> params, Map<String,String> headers) throws IOException {
        FormEncodingBuilder form = new FormEncodingBuilder();
        if(params!=null&& params.size()>0) {
            for(Map.Entry<String,String> entry:params.entrySet()) {
                form.add(entry.getKey(), entry.getValue());
            }
        }
        RequestBody formBody = form.build();
        Request.Builder builder = new Request.Builder();
        for(Map.Entry<String,String> entry:headers.entrySet()){
            builder.addHeader(entry.getKey(),entry.getValue());
        }
        Request request = builder.url(url).post(formBody).build();
        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        };
        return response.body().string();
    }


    /**
     * 这里使用了HttpClinet的API。只是为了方便
     *
     * @param params
     * @return
     */
    public static String formatParams(List<BasicNameValuePair> params) {
        return URLEncodedUtils.format(params, CHARSET_NAME);
    }

    /**
     * 为HttpGet 的 url 方便的添加多个name value 参数。
     *
     * @param url
     * @param params
     * @return
     */
    public static String attachHttpGetParams(String url, List<BasicNameValuePair> params) {
        return url + "?" + formatParams(params);
    }

    /**
     * 为HttpGet 的 url 方便的添加1个name value 参数。
     *
     * @param url
     * @param name
     * @param value
     * @return
     */
    public static String attachHttpGetParam(String url, String name, String value) {
        return url + "?" + name + "=" + value;
    }


    public static String getRemoteHost(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }
}
