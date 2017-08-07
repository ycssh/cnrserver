package cn.cnr.netease.group.dto;

/**
 * @author yuchao
 * @date 2017/8/7
 * @desc：
 */
public class TeamQueryDto {

    private String tids;   //	String	是	群id列表，如["3083","3084"]
    private String ope;//	int	是	1表示带上群成员列表，0表示不带群成员列表，只返回群信息

}
