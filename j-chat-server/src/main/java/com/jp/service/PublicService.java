package com.jp.service;

import com.jp.domain.response.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @Author JP
 * @Date 2024/11/1 8:32
 * @JDK_Verson 17.0.2
 */
public interface PublicService {

    /**
     * 邮箱验证码发送
     * @param type 邮箱类型
     * @param email 邮箱地址
     * @return 是否成功
     */
    ResponseResult<String> registerEmailVerifyCode(String type, String email);

    /**
     * 邮箱通知
     * @param type 邮箱类型
     * @param email 邮箱地址
     * @param content 邮箱内容
     */
    ResponseResult<Void> sendEmail(String type, String email, Map<String, Object> content);

    ResponseResult<String> uploadImage(List<MultipartFile> images, String type);
}
