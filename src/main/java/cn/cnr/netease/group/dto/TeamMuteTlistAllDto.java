package cn.cnr.netease.group.dto;

import lombok.Data;

/**
 * @author yuchao
 * @date 2017/8/7
 * @desc：
 */
@Data
public class TeamMuteTlistAllDto {
    private String tid;//	String	是	网易云通信服务器产生，群唯一标识，创建群时会返回
    private String owner;//	String	是	群主的accid
    private String mute;//	String	是	true:禁言，false:解禁
}
