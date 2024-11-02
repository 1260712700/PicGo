import http from "@/utils/http.js";
/**
 * 发送邮件
 * @param email 邮件地址
 * @param type 类型
 */
export function sendEmail(email, type) {
    return http({
        url: '/public/ask-code',
        params: {
            email: email,
            type: type
        },
        method: 'get'
    })
}