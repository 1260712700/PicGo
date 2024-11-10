package com.jp.constants;

/**
 * @Author JP
 * @Date 2024/10/31 6:32
 */
public class RedisConstant {

    /**
     * jwt 黑名单（退出登录的用户jwt加入黑名单）
     */
    public static final String JWT_WHITE_LIST = "jwt:white:list:";

    /**
     * 邮箱验证码
     */
    public static final String VERIFY_CODE = "verifyCode:";

    /**
     * 邮箱验证码过期时间
     */
    public static final Integer VERIFY_CODE_EXPIRATION = 5;

    /**
     * 分隔符
     */
    public static final String SEPARATOR = ":";

    /**
     * 注册
     */
    public static final String REGISTER = "register";

    /**
     * 重置密码
     */
    public static final String RESET = "reset";

    /**
     * 重置密码
     */
    public static final String ARTICLE_CATEGORY = "article:category";
    /**
     * 重置邮箱
     */
    public static final String RESET_EMAIL = "resetEmail";

    /**
     * 文章收藏数
     */
    public static final String ARTICLE_FAVORITE_COUNT = "article:count:favorite:";
    /**
     * 文章点赞数
     */
    public static final String ARTICLE_LIKE_COUNT = "article:count:like:";
    /**
     * 文章评论数
     */
    public static final String ARTICLE_COMMENT_COUNT = "article:count:comment:";

    /**
     * 文章访问数
     */
    public static final String ARTICLE_VISIT_COUNT = "article:count:visit:";

    /**
     * 邮箱确认友链通过，状态码
     */
    public static final String EMAIL_VERIFICATION_LINK = "email:verification:link:";
}