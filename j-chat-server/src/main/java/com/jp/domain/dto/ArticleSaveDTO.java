package com.jp.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author JP
 * @Date 2024/11/4 11:43
 */
@Data
public class ArticleSaveDTO {
    @Schema(description = "标题")
    private String title;
    @Schema(description = "正文")
     private String content;
    @Schema(description = "标签")
    private String tags;
    @Schema(description = "图片文件")
    private List<MultipartFile> imgList;
    /**
     * 类型 (1原创 2转载 3翻译)
     */
    @Schema(description = "文章类型")
    private Integer articleType;
    /**
     * 文章状态 (1公开 2私密 3草稿)
     */
    @Schema(description = "文章状态")
    private Integer status;


}
