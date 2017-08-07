package cn.cnr.netease.group.dto;

import lombok.Data;

/**
 * @author yuchao
 * @date 2017/8/7
 * @desc：
 */
@Data
public class TeamChangeOwnerDto {
    private String tid;//	String	是	网易云通信服务器产生，群唯一标识，创建群时会返回，最大长度128字符
    private String owner;//	String	是	群主用户帐号，最大长度32字符
    private String newowner;//	String	是	新群主帐号，最大长度32字符
    private int leave;//	int	是	1:群主解除群主后离开群，2：群主解除群主后成为普通成员。其它414

}
