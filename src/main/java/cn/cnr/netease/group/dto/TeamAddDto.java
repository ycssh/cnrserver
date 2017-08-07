package cn.cnr.netease.group.dto;

import cn.cnr.admin.base.util.BeanMapConvert;
import lombok.Data;

import java.util.Map;

/**
 * @author yuchao
 * @date 2017/8/7
 * @desc：
 */
@Data
public class TeamAddDto {
    private String tid;//网易云通信服务器产生，群唯一标识，创建群时会返回，最大长度128字符
    private String owner;//群主用户帐号，最大长度32字符
    private String members;//["aaa","bbb"](JSONArray对应的accid，如果解析出错会报414)，一次最多拉200个成员
    private int magree;//管理后台建群时，0不需要被邀请人同意加入群，1需要被邀请人同意才可以加入群。其它会返回414
    private String msg;//邀请发送的文字，最大长度150字符
    private String attach;//自定义扩展字段，最大长度512
}
