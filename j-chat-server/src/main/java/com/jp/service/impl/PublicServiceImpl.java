package com.jp.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.jp.constants.ArticleConstant;
import com.jp.constants.RedisConstant;
import com.jp.domain.dto.ArticleSaveDTO;
import com.jp.domain.response.ResponseResult;
import com.jp.service.PublicService;
import com.jp.utils.RedisCache;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author JP
 * @Date 2024/11/1 8:32
 */
@Slf4j
@Service
public class PublicServiceImpl implements PublicService {
    @Resource
    private RedisCache redisCache;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.routingKey.email}")
    private String routingKey;

    @Value("${spring.rabbitmq.exchange.email}")
    private String exchange;
    @Value("${jp-chat.article.base-url}")
    private String uploadBaseUrl;
    @Value("${jp-chat.article.article-url}")
    private String uploadArticleUrl;
    /**
     * 发送邮箱注册验证码
     * @param type 邮箱类型
     * @param email 邮箱地址
     * @return
     */
    @Override
    public ResponseResult<String> registerEmailVerifyCode(String type, String email) {
        // 加锁，防止同一时间被同一人调用多次
        synchronized (email.intern()) {
            // 生成验证码
            String verifyCode = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
            // 保存到redis，设置过期时间为5分钟
            redisCache.setCacheObject(RedisConstant.VERIFY_CODE + type + RedisConstant.SEPARATOR + email, verifyCode, RedisConstant.VERIFY_CODE_EXPIRATION, TimeUnit.MINUTES);
            // 发送邮件
            Map<String, Object> senEmail = Map.of("email", email, "code", verifyCode, "type", type);
//            发送到消息队列中
            rabbitTemplate.convertAndSend(exchange, routingKey, senEmail);
           return   ResponseResult.success("验证码已发送，请注意查收！");
        }
    }
    /**
     * 发送邮件
     * @param type 邮箱类型
     * @param email 邮箱地址
     * @param content 邮箱内容
     * @return
     */
    @Override
    public ResponseResult<Void> sendEmail(String type, String email, Map<String, Object> content) {
        if (content != null) {
            content.put("email", email);
            content.put("type", type);
            rabbitTemplate.convertAndSend(exchange, routingKey, content);
        } else rabbitTemplate.convertAndSend(exchange, routingKey, Map.of("email", email, "type", type));

        log.info("邮件通知消息发送完毕，发送时间为：{}，发送的消息类型：{}，发送的邮箱：{}", new Date(), type, email);
        return   ResponseResult.success();
    }

    /**
     * 上传图片
     * @param images 图片文件
     * @param type 文件类型
     * @return
     */
    @Override
    public ResponseResult<String> uploadImage(List<MultipartFile> images, String type) {
        String imageNameList = handleImgList(images);
        handleUploadLocal(images,imageNameList);
        return ResponseResult.success(imageNameList);
    }
    private String handleImgList(List<MultipartFile> images) {
        String randomNumber = RandomUtil.randomNumbers(1);
        StringBuilder newImgNameList=new StringBuilder();
        for (int i = 0; i < images.size(); i++) {
            // 生成 UUID 作为文件名
            String uuid = UUID.randomUUID().toString();
            // 获取文件扩展名
            String originalFilename = images.get(i).getOriginalFilename();
            String extension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".")) : "";
            // 构建新的文件名
            String newFilename = uuid + extension;
            String imgUrl=uploadArticleUrl+ ArticleConstant.SEPARATOR+ randomNumber + ArticleConstant.SEPARATOR+newFilename;
            newImgNameList.append(imgUrl).append(",");
        }
        return newImgNameList.toString();
    }

    private void handleUploadLocal( List<MultipartFile> images,String newImgNameList) {
        String[] split = newImgNameList.split(",");
        for (int i = 0; i < split.length; i++) {
            File dest = new File(uploadBaseUrl+split[i]);
            File destDir = dest.getParentFile();
            if (!destDir.exists() && !destDir.mkdirs()) {
                throw new RuntimeException("Failed to create directories: " + destDir);
            }
            try (InputStream fis = images.get(i).getInputStream();
                 FileOutputStream fos = new FileOutputStream(dest)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
