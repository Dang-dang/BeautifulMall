package com.beautiful.mall01.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * oss上传文件的回调结果
 */
@Data
public class OssCallbackResult {

    @ApiModelProperty("文件名称")
    private String filename;
    @ApiModelProperty("文件大小")
    private String size;
    @ApiModelProperty("文件的mimeType")
    private String mimeType;
    @ApiModelProperty("图片宽度")
    private String width;
    @ApiModelProperty("图片的高")
    private String height;
}
