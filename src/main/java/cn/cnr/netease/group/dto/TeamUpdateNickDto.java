package cn.cnr.netease.group.dto;

/**
 * @author yuchao
 * @date 2017/8/7
 * @desc：
 */
public class TeamUpdateNickDto {

    private String tid;    //群唯一标识，创建群时网易云通信服务器产生并返回
    private String owner;   //群主 accid
    private String accid;   //要修改群昵称的群成员 accid
    private String nick;    //accid 对应的群昵称，最大长度32字符
    private String custom;  //自定义扩展字段，最大长度1024字节

}
