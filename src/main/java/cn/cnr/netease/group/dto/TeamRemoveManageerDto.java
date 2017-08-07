package cn.cnr.netease.group.dto;

import lombok.Data;

/**
 * @author yuchao
 * @date 2017/8/7
 * @desc：
 */
@Data
public class TeamRemoveManageerDto {
    private String tid  	;//String	是	网易云通信服务器产生，群唯一标识，创建群时会返回，最大长度128字符
    private String owner	;//String	是	群主用户帐号，最大长度32字符
    private String members	;//String	["aaa","bbb"](JSONArray对应的accid，如果解析出错会报414)，长度最大1024字符（一次解除最多10个管理员）
}
