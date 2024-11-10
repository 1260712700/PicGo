package com.jp.domain.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.jp.domain.BaseData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @Author JP
 * @Date 2024/11/5 14:57
 */
@Data
public class ArticleVO implements BaseData {
    @Schema(description = "id")
    private Long id;
    @Schema(description = "标题")
    private String title;
    @Schema(description = "正文")
    private String content;
    @Schema(description = "标签")
    private String tags;
    /**
     * 文章图片，最多9张，多张以,隔开
     */
    @Schema(description = "文章图片")
    private String images;
    /**
     * 分类id
     */
    @Schema(description = "分类id")
    private Long categoryId;
    /**
     * 类型 (1原创 2转载 3翻译)
     */
    @Schema(description = "文章类型")
    private String articleType;
    /**
     * 文章状态 (1公开 2私密 3草稿)
     */
    @Schema(description = "文章状态")
    private String status;
    /**
     * 文章创建时间
     */
    @Schema(description = "文章创建时间")
    private Date createTime;

    /**
     * 文章更新时间
     */
    @Schema(description = "文章更新时间")
    private Date updateTime;
}
