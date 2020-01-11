package com.cmr.hotshop.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chenmengrui
 * @Description: oss上传文件的回调结果
 * @date 2020/1/11 10:12
 */
@Data
public class OssCallbackResult {

    @ApiModelProperty("文件名称")
    private String filename;

    @ApiModelProperty("文件大小")
    private String size;

    @ApiModelProperty("文件的mimeType")
    private String mimeType;

    @ApiModelProperty("图片文件的宽")
    private String width;

    @ApiModelProperty("图片文件的高")
    private String height;

}
