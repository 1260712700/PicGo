package com.jp.controller;

import com.jp.anotation.AccessLimit;
import com.jp.domain.response.ResponseResult;
import com.jp.service.PublicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 公共接口
 * 发送验证码
 * @Author JP
 * @Date 2024/11/1 7:15
 */
@RestController
@Tag(name="公共接口")
@RequestMapping("/public")
@Valid
public class PublicController {
    @Resource
    private PublicService publicService;

    /**
     * 邮件发送
     */
    @Operation(summary = "邮件发送")
    @Parameters({
            @Parameter(name = "email", description = "邮箱", required = true),
            @Parameter(name = "type", description = "邮箱类型", required = true)
    })
    @AccessLimit(seconds = 60, maxCount = 1)
    @GetMapping("/ask-code")
    public ResponseResult<String> askVerifyCode(
            @RequestParam @Email String email,
            @RequestParam @Pattern(regexp = "(register|reset|resetEmail)",message = "邮箱类型错误" ) String type
    ) {
        return publicService.registerEmailVerifyCode(type, email);
    }

    /**
     * 图片上传
     */
    @Operation(summary = "图片上传")
    @Parameters({
            @Parameter(name = "images", description = "图片", required = true),
            @Parameter(name = "type", description = "图片类型", required = true)
    })
    @AccessLimit(seconds = 1, maxCount = 1)
    @PostMapping("/auth/upload")
    public ResponseResult<String> uploadImage(
            @RequestParam List<MultipartFile> images,
            @RequestParam @Pattern(regexp = "(article|avatar)",message = "图片类型错误" ) String type
    ) {
        return publicService.uploadImage(images, type);
    }

}
