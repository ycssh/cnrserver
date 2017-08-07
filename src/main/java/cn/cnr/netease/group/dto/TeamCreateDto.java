package cn.cnr.netease.group.dto;

import lombok.Data;

/**
 * @author yuchao
 * @date 2017/8/7
 * @desc：
 */
@Data
public class TeamCreateDto {

    private String tname;   //String	是	群名称，最大长度64字符
    private String owner;   //String	是	群主用户帐号，最大长度32字符
    private String members;   //String	是	["aaa","bbb"](JSONArray对应的accid，如果解析出错会报414)，一次最多拉200个成员
    private String announcement;    //String	否	群公告，最大长度1024字符
    private String intro;   //String	否	群描述，最大长度512字符
    private String msg;   //String	是	邀请发送的文字，最大长度150字符
    private int     magree;   //int	是	管理后台建群时，0不需要被邀请人同意加入群，1需要被邀请人同意才可以加入群。其它会返回414
    private int joinmode;       //int	是	群建好后，sdk操作时，0不用验证，1需要验证,2不允许任何人加入。其它返回414
    private String custom;   //String	否	自定义高级群扩展属性，第三方可以跟据此属性自定义扩展自己的群属性。（建议为json）,最大长度1024字符
    private String icon;       //String	否	群头像，最大长度1024字符
    private int beinvitemode;    //int	否	被邀请人同意方式，0-需要同意(默认),1-不需要同意。其它返回414
    private int invitemode;   //int	否	谁可以邀请他人入群，0-管理员(默认),1-所有人。其它返回414
    private int uptinfomode;   //int	否	谁可以修改群资料，0-管理员(默认),1-所有人。其它返回414
    private int upcustommode;    //int	否	谁可以更新群自定义属性，0-管理员(默认),1-所有人。其它返回414
}
