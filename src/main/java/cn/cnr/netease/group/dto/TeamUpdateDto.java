package cn.cnr.netease.group.dto;

import lombok.Data;

/**
 * @author yuchao
 * @date 2017/8/7
 * @desc：
 */
@Data
public class TeamUpdateDto {

    private String tid; //是	网易云通信服务器产生，群唯一标识，创建群时会返回
    private String tname;   //否	群名称，最大长度64字符
    private String owner;   //是	群主用户帐号，最大长度32字符
    private String announcement;    //否	群公告，最大长度1024字符
    private String intro;   //否	群描述，最大长度512字符
    private int joinmode;   //否	群建好后，sdk操作时，0不用验证，1需要验证,2不允许任何人加入。其它返回414
    private String custom;  //否	自定义高级群扩展属性，第三方可以跟据此属性自定义扩展自己的群属性。（建议为json）,最大长度1024字符
    private String icon;    //否	群头像，最大长度1024字符
    private int beinvitemode;   //否	被邀请人同意方式，0-需要同意(默认),1-不需要同意。其它返回414
    private int invitemode; //否	谁可以邀请他人入群，0-管理员(默认),1-所有人。其它返回414
    private int uptinfomode;    //否	谁可以修改群资料，0-管理员(默认),1-所有人。其它返回414
    private int upcustommode;   //否	谁可以更新群自定义属性，0-管理员(默认),1-所有人。其它返回414

}
