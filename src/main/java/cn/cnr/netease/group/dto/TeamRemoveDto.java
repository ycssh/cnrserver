package cn.cnr.netease.group.dto;

import lombok.Data;

/**
 * @author yuchao
 * @date 2017/8/7
 * @desc：
 */
@Data
public class TeamRemoveDto {
    private String tid;//	是	网易云通信服务器产生，群唯一标识，创建群时会返回，最大长度128字符
    private String owner;    //String	是	群主用户帐号，最大长度32字符
}
