package cn.cnr.netease.msg.controller;

import cn.cnr.admin.base.model.Result;
import cn.cnr.admin.base.util.BeanMapConvert;
import cn.cnr.admin.base.util.LoggerUtils;
import cn.cnr.netease.NeteaseUtil;
import cn.cnr.netease.msg.dto.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author yuchao
 * @date 2017/8/7
 * @desc：
 */
@Controller
@RequestMapping("/msg")
public class MsgController {


    /**
     *  给用户或者高级群发送普通消息，包括文本，图片，语音，视频和地理位置
     * @param msgSendDto
     * @param request
     * @return
     */
    @RequestMapping("/sendMsg")
    public @ResponseBody
    Result sendMsg(MsgSendDto msgSendDto, HttpServletRequest request) {
        LoggerUtils.getLogger().info("[TeamAddController:sendMsg], data:{}", msgSendDto);
        Map<String, String> params = BeanMapConvert.beanToStringMap(msgSendDto);
        return NeteaseUtil.send("https://api.netease.im/nimserver/msg/sendMsg.action", params);
    }

    /**
     *  1.给用户发送点对点普通消息，包括文本，图片，语音，视频，地理位置和自定义消息。
     2.最大限500人，只能针对个人,如果批量提供的帐号中有未注册的帐号，会提示并返回给用户。
     3.此接口受频率控制，一个应用一分钟最多调用120次，超过会返回416状态码，并且被屏蔽一段时间；
     具体消息参考下面描述。
     * @param msgSendBatchDto
     * @param request
     * @return
     */
    @RequestMapping("/sendBatchMsg")
    public @ResponseBody
    Result sendBatchMsg(MsgSendBatchDto msgSendBatchDto, HttpServletRequest request) {
        LoggerUtils.getLogger().info("[TeamAddController:sendBatchMsg], data:{}", msgSendBatchDto);
        Map<String, String> params = BeanMapConvert.beanToStringMap(msgSendBatchDto);
        return NeteaseUtil.send("https://api.netease.im/nimserver/msg/sendBatchMsg.action", params);
    }

    /**发送自定义系统通知
     *  1.自定义系统通知区别于普通消息，方便开发者进行业务逻辑的通知；
     2.目前支持两种类型：点对点类型和群类型（仅限高级群），根据msgType有所区别。
     * @param msgSendAttachMsgDto
     * @param request
     * @return
     */
    @RequestMapping("/sendAttachMsg")
    public @ResponseBody
    Result sendAttachMsg(MsgSendAttachMsgDto msgSendAttachMsgDto, HttpServletRequest request) {
        LoggerUtils.getLogger().info("[TeamAddController:sendAttachMsg], data:{}", msgSendAttachMsgDto);
        Map<String, String> params = BeanMapConvert.beanToStringMap(msgSendAttachMsgDto);
        return NeteaseUtil.send("https://api.netease.im/nimserver/msg/sendAttachMsg.action", params);
    }

    /**批量发送点对点自定义系统通知
     * 1.系统通知区别于普通消息，应用接收到直接交给上层处理，客户端可不做展示；
     2.目前支持类型：点对点类型；
     3.最大限500人，只能针对个人,如果批量提供的帐号中有未注册的帐号，会提示并返回给用户；
     4.此接口受频率控制，一个应用一分钟最多调用120次，超过会返回416状态码，并且被屏蔽一段时间；
     * @param msgSendBatchAttachMsgDto
     * @param request
     * @return
     */
    @RequestMapping("/sendBatchAttachMsg")
    public @ResponseBody
    Result sendBatchAttachMsg(MsgSendBatchAttachMsgDto msgSendBatchAttachMsgDto, HttpServletRequest request) {
        LoggerUtils.getLogger().info("[TeamAddController:sendBatchAttachMsg], data:{}", msgSendBatchAttachMsgDto);
        Map<String, String> params = BeanMapConvert.beanToStringMap(msgSendBatchAttachMsgDto);
        return NeteaseUtil.send("https://api.netease.im/nimserver/msg/msgSendBatchAttachMsgDto.action", params);
    }

    /**
     * 文件上传，字符流需要base64编码，最大15M。
     * @param msgSendBatchAttachMsgDto
     * @param request
     * @return
     */
    @RequestMapping("/upload")
    public @ResponseBody
    Result upload(MsgSendBatchAttachMsgDto msgSendBatchAttachMsgDto, HttpServletRequest request) {
        LoggerUtils.getLogger().info("[TeamAddController:upload], data:{}", msgSendBatchAttachMsgDto);
        Map<String, String> params = BeanMapConvert.beanToStringMap(msgSendBatchAttachMsgDto);
        return NeteaseUtil.send("https://api.netease.im/nimserver/msg/msgSendBatchAttachMsgDto.action", params);
    }

    /**
     * 文件上传，字符流需要base64编码，最大15M。
     * @param msgrecallDto
     * @param request
     * @return
     */
    @RequestMapping("/recall")
    public @ResponseBody
    Result recall(MsgrecallDto msgrecallDto, HttpServletRequest request) {
        LoggerUtils.getLogger().info("[TeamAddController:msgrecallDto], data:{}", msgrecallDto);
        Map<String, String> params = BeanMapConvert.beanToStringMap(msgrecallDto);
        return NeteaseUtil.send("https://api.netease.im/nimserver/msg/recall.action", params);
    }
}
