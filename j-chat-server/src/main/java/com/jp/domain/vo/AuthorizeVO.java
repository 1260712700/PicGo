package com.jp.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @Author JP
 * @Date 2024/10/31 7:40
 */
@Data
@Schema(name = "AuthorizeVO", description = "授权VO")
public class AuthorizeVO {
    // token
    @Schema(description = "token")
    private String token;
    // token 过期时间
    @Schema(description = "token 过期时间")
    private Date expire;
}
