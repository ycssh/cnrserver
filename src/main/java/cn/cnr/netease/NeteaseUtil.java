package cn.cnr.netease;

import cn.cnr.admin.base.model.Result;
import cn.cnr.admin.base.util.JsonUtil;
import cn.cnr.admin.base.util.OkHttpUtils;
import cn.cnr.admin.spring.CustomPropertyConfigurer;
import cn.cnr.netease.group.dto.NeteaseResult;

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

    static Map<Integer,String> codeMap = new HashMap(){
        {
            put(200,"操作成功");
            put(201,"客户端版本不对，需升级sdk");
            put(301,"被封禁");
            put(302,"用户名或密码错误");
            put(315,"IP限制");
            put(403,"非法操作或没有权限");
            put(404,"对象不存在");
            put(405,"参数长度过长");
            put(406,"对象只读");
            put(408,"客户端请求超时");
            put(413,"验证失败(短信服务)");
            put(414,"参数错误");
            put(415,"客户端网络问题");
            put(416,"频率控制");
            put(417,"重复操作");
            put(418,"通道不可用(短信服务)");
            put(419,"数量超过上限");
            put(422,"账号被禁用");
            put(431,"HTTP重复请求");
            put(500,"服务器内部错误");
            put(503,"服务器繁忙");
            put(508,"消息撤回时间超限");
            put(509,"无效协议");
            put(514,"服务不可用");
            put(998,"解包错误");
            put(999,"打包错误");
            put(801,"群人数达到上限");
            put(802,"没有权限");
            put(803,"群不存在");
            put(804,"用户不在群");
            put(805,"群类型不匹配");
            put(806,"创建群数量达到限制");
            put(807,"群成员状态错误");
            put(808,"申请成功");
            put(809,"已经在群内");
            put(810,"邀请成功");
            put(9102,"通道失效");
            put(9103,"已经在他端对这个呼叫响应过了");
            put(11001,"通话不可达，对方离线状态");
            put(13001,"IM主连接状态异常");
            put(13002,"聊天室状态异常");
            put(13003,"账号在黑名单中,不允许进入聊天室");
            put(13004,"在禁言列表中,不允许发言");
            put(10431,"输入email不是邮箱");
            put(10432,"输入mobile不是手机号码");
            put(10433,"注册输入的两次密码不相同");
            put(10434,"企业不存在");
            put(10435,"登陆密码或帐号不对");
            put(10436,"app不存在");
            put(10437,"email已注册");
            put(10438,"手机号已注册");
            put(10441,"app名字已经存在");
        }
    };

    public static Result send(String url, Map<String, String> params){
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
            NeteaseResult result = JsonUtil.parse(s,NeteaseResult.class);
            if(result.getCode()==200) {
                return new Result();
            }else if(codeMap.containsKey(result.getCode())){
                return new Result(1, codeMap.get(result.getCode()));
            }else{
                return new Result(1, "","");
            }
        } catch (IOException e) {
            return new Result(1,"请求出错");
        }
    }
}
