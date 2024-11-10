package com.jp.domain.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.jp.domain.BaseData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @Author JP
 * @Date 2024/11/5 14:57
 */
@Data
@Builder
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
     * 文章缩略图
     */
    private String articleCover;
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
     * 点赞
     */
    @Schema(description = "点赞")
    private Long like;
    /**
     * 访问量
     */
    @Schema(description = "访问量")
    private Long visitCount;
    /**
     * 收藏
     */
    @Schema(description = "收藏数量")
    private Long collectCount;
    /**
     * 收藏
     */
    @Schema(description = "分享数量")
    private Long shareCount;
    /**
     * 评论数量
     */
    @Schema(description = "分享数量")
    private Long commentCount;
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
