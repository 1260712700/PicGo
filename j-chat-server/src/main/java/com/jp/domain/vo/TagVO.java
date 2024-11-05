package com.jp.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @Author JP
 * @Date 2024/11/4 10:14
 */
@Data
@Schema(name = "TagVO", description = "标签VO")
public class TagVO {
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 标签名称
     */
    @Schema(description = "标签名称")
    private String tagName;
}
