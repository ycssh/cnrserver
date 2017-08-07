package cn.cnr.netease.group.dto;

import lombok.Data;

/**
 * @author yuchao
 * @date 2017/8/7
 * @desc：
 */
@Data
public class TeamKickDto {
    private String tid;//网易云通信服务器产生，群唯一标识，创建群时会返回，最大长度128字符
    private String owner;//群主的accid，用户帐号，最大长度32字符
    private String member;//被移除人的accid，用户账号，最大长度字符
    private String attach;//自定义扩展字段，最大长度512

}
