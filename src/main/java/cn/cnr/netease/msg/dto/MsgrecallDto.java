package cn.cnr.netease.msg.dto;

import lombok.Data;

/**
 * @author yuchao
 * @date 2017/8/7
 * @desc： 消息撤回
 */
@Data
public class MsgrecallDto {

    private String deleteMsgid;//要撤回消息的msgid
    private long timetag;//要撤回消息的创建时间
    private int type;//7:表示点对点消息撤回，8:表示群消息撤回，其它为参数错误
    private String from;//发消息的accid
    private String to;//如果点对点消息，为接收消息的accid,如果群消息，为对应群的tid
    private String msg;//可以带上对应的描述
    private String ignoreTime;//1表示忽略撤回时间检测，其它为非法参数，如果需要撤回时间检测，不填即可

}
