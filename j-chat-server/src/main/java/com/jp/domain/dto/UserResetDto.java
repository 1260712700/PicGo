package com.jp.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @Author JP
 * @Date 2024/11/1 7:31
 */
@Data
public class UserResetDto {
    // 密码
    @Schema(description = "密码")
    @Length(min = 6, max = 20)
    private String password;
    //验证码
    @Schema(description = "验证码")
    @Length(max = 6, min = 6)
    private String code;
    // 邮箱
    @Schema(description = "邮箱")
    @Email
    @Length(min = 4)
    private String email;
}
