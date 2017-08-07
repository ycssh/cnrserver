package cn.cnr.netease.msg.dto;

import lombok.Data;

/**
 * @author yuchao
 * @date 2017/8/7
 * @desc： 批量发送消息
 */
@Data
public class MsgSendBatchAttachMsgDto {
    private String fromAccid;   //发送者accid，用户帐号，最大32字符，APP内唯一
    private String toAccids;    //["aaa","bbb"]（JSONArray对应的accid，如果解析出错，会报414错误），最大限500人
    private String attach;  //自定义通知内容，第三方组装的字符串，建议是JSON串，最大长度4096字符
    private String pushcontent; //iOS推送内容，第三方自己组装的推送内容,不超过150字符
    private String payload; //iOS推送对应的payload,必须是JSON,不能超过2k字符
    private String sound;   //如果有指定推送，此属性指定为客户端本地的声音文件名，长度不要超过30个字符，如果不指定，会使用默认声音
    private int save;    //1表示只发在线，2表示会存离线，其他会报414错误。默认会存离线
    private String option;  //"发消息时特殊指定的行为选项,Json格式，可用于指定消息计数等特殊行为;option中字段不填时表示默认值。


}
