import { getUserInfo } from '@/api/user';
import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useUserStore = defineStore('user', () => {
    const userInfo=ref()
    const token=ref('')
     // 获取用户信息
     const getInfo = async () => {
        getUserInfo().then((res) => {
            if (res.code === 200) {
                userInfo.value = res.data
            }
        })
    }
    return {
        getInfo,token,userInfo
    }
});
