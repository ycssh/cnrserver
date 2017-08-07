package cn.cnr.netease.msg.dto;

import lombok.Data;

/**
 * @author yuchao
 * @date 2017/8/7
 * @desc：
 */
@Data
public class MsgUploadDto {
    private String content;//	String	是	字符流base64串(Base64.encode(bytes)) ，最大15M的字符流
    private String type;//	String	否	上传文件类型
}
