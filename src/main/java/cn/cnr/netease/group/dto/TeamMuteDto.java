package cn.cnr.netease.group.dto;

import lombok.Data;

/**
 * @author yuchao
 * @date 2017/8/7
 * @desc：
 */
@Data
public class TeamMuteDto {

    private String tid;//	String	是	网易云通信服务器产生，群唯一标识，创建群时会返回
    private String accid;//	String	是	要操作的群成员accid
    private int ope;//	int	是	1：关闭消息提醒，2：打开消息提醒，其他值无效
}
