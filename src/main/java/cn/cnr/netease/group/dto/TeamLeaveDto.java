package cn.cnr.netease.group.dto;

import lombok.Data;

/**
 * @author yuchao
 * @date 2017/8/7
 * @desc：
 */
@Data
public class TeamLeaveDto {

    private String tid;	//String	是	网易云通信服务器产生，群唯一标识，创建群时会返回
    private String accid;//	String	是	退群的accid
}
