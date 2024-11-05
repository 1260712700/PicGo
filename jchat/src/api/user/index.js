import http from "@/utils/http.js";

// 用户登录
export function login(data) {
    return http({
        url: '/user/login',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        data: data,
        method: 'post'
    })
}
export function register(data){
    return http({
        url: '/user/register',
        data: data,
        method: 'post'
    })
}
export function resetPasswordStepOne(data){
    return http({
        url: '/user/reset-confirm',
        data: data,
        method: 'post'
    })
}
export function resetPasswordStepTwo(data){
    return http({
        url: '/user/reset-password',
        data: data,
        method: 'post'
    })
}
export function getUserList(){
    return http({
        url: '/user/list',
        method: 'get'
    })
}
// 获取用户信息
export function getUserInfo() {
    return http({
        url: '/user/auth/info',
        method: 'get'
    })
}