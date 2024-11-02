// 获取token
import { useUserStore } from "@/stores/user";
import {ElMessage} from "element-plus";

// 获取token
// export const GET_TOKEN = () => {
//     const str = localStorage.getItem(TOKEN_KEY) || sessionStorage.getItem(TOKEN_KEY)
//     if (!str) return null
//     // 解析json
//     const authObject = JSON.parse(str)
//     // 判断token是否过期
//     if (new Date(authObject.expire) <= new Date()) {
//         REMOVE_TOKEN()
//         ElMessage.warning('登录状态已过期，请重新登录')
//         return null
//     }

//     return authObject.token
// }
// // 设置token
// export const SET_TOKEN = (token, expire, remember) => {
//     const userStore = useUserStore()
//     const authObject = {token, expire}
//     const str = JSON.stringify(authObject);
//     // 是否记住密码(记住密码token存localStorage,否则存sessionStorage)
//     remember ? localStorage.setItem(TOKEN_KEY, str) : sessionStorage.setItem(TOKEN_KEY, str)
//     userStore.token = token
// }

// // 移除token
// export const REMOVE_TOKEN = () => {
//     localStorage.removeItem(TOKEN_KEY)
//     sessionStorage.removeItem(TOKEN_KEY)
// }


 /**
     * 设置cookie方法
     * @param key{string} cookie名称
     * @param val{string} cookie值
     * @param hours{number} 过期时间（小时）
     */
export  function setCookie (key, val, hours) {
    if(getCookie(key)!='')return
    let date = new Date(); 
    let expiresHours = parseInt(hours);  
    date.setTime(date.getTime() + expiresHours * 24 * 3600 * 1000); 
    document.cookie = key + "=" + val + ";expires=" + date.toGMTString();  
  }
  /**
   * 移除cookie方法
   * @param key{string} cookie名称
   */
   export function removeCookie(key) {
    this.set(key, "", 0)
  }
  /**
   * 获取cookie方法
   * @param key{string} cookie名称
   * @returns {string} 对应名称的cookie值
   */
  export function getCookie (key) {
    let getCookie = document.cookie.replace(/[ ]/g, "");  
    let arrCookie = getCookie.split(";")  
    let tips = "";  //声明变量tips
    for (let i = 0; i < arrCookie.length; i++) {   
      let arr = arrCookie[i].split("=");   
      if (key === arr[0]) { 
        tips = arr[1]; 
        break;   
      }
    }
    return tips;
  }