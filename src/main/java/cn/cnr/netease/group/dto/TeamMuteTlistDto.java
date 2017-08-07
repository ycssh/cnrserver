package cn.cnr.netease.group.dto;

import lombok.Data;

/**
 * @author yuchao
 * @date 2017/8/7
 * @desc：
 */
@Data
public class TeamMuteTlistDto {
   private String tid;//	String	是	网易云通信服务器产生，群唯一标识，创建群时会返回
   private String owner;//	String	是	群主accid
   private String accid;//	String	是	禁言对象的accid
   private int mute;//	int	是	1-禁言，0-解禁
}
