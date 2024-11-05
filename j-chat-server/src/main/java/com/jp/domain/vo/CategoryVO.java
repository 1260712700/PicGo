package com.jp.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @Author JP
 * @Date 2024/11/4 10:17
 */
@Data
@Schema(name = "CategoryVO", description = "文章分类VO")
public class CategoryVO {
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 分类名
     */
    @Schema(description = "文章分类名称")
    private String categoryName;


}
