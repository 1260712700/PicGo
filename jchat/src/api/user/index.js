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
//用户退出
export function logout(){
    return http.post("/user/logout")
}
export function register(data){
    return http({
        url: '/user/register',
        data: data,
        method: 'post'
    })
}
//重置密码步骤一
export function resetPasswordStepOne(data){
    return http({
        url: '/user/reset-confirm',
        data: data,
        method: 'post'
    })
}
//重置密码步骤二
export function resetPasswordStepTwo(data){
    return http({
        url: '/user/reset-password',
        data: data,
        method: 'post'
    })
}
// 获取用户集合
export function getUserList(){
    return http({
        url: '/user/list',
        method: 'get'
    })
}
// 获取当前登录用户信息
export function getUserInfo() {
    return http({
        url: '/user/auth/info',
        method: 'get'
    })
}
// 获取其他用户信息
export function getOtherUserInfo(id) {
    return http.get(`/user/info/${id}`)
}