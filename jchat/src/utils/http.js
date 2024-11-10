// 封装axios
import { GET_TOKEN } from '@/api/auth'
import { Jwt_Prefix } from '@/constant/jwt'
import axios from 'axios'
import { handleDateTime } from './time'

// 创建axios实例
const http = axios.create({
    baseURL: "http://localhost:8080", // api的base_url
    timeout: 60000, // 请求超时时间
    headers: {
        'Content-Type': 'application/json;charset=UTF-8'
    }
})
// request拦截器
http.interceptors.request.use((config) => {
    config.headers['X-Client-Type'] = 'Frontend'
    if (GET_TOKEN() == null) return config
    config.headers['Authorization'] = Jwt_Prefix + GET_TOKEN()
    return config;
}, error => {
    console.log(err);
    
    return Promise.reject(error)
})
// response拦截器
http.interceptors.response.use(
    (response) => {
          if (Array.isArray(response.data.data)) {
            response.data.data.forEach(item => {
              if (item.updateTime) {
                item.updateTime = handleDateTime(item.updateTime);
              }
              if (item.createTime) {
                item.createTime = handleDateTime(item.createTime);
              }
            });
        }
        return response.data
    },
    (error) => {
        let message = error.message;
        if (message == "Network Error") {
            message = "后端接口连接异常";
        } else if (message.includes("timeout")) {
            message = "系统接口请求超时";
        } else if (message.includes("Request failed with status code")) {
            message = "系统接口" + message.substring(message.length - 3) + "异常";
        }
        return Promise.reject(error.response)
    }
)


export default http



