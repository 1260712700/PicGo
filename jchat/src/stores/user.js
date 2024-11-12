import { getOtherUserInfo, getUserInfo } from '@/api/user';
import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useUserStore = defineStore('user', () => {
    const userInfo=ref(null)
    const token=ref('')
    const targetUserInfo=ref(null);
    async function getInfoById(id){
        getOtherUserInfo(id).then(res=>{
            if(res.code===200){
                targetUserInfo.value=res.data
            }
        })
    }
     // 获取用户信息
     const getInfo = async () => {
        getUserInfo().then((res) => {
            if (res.code === 200) {
                userInfo.value = res.data
            }
        })
    }
    return {
        getInfo,token,userInfo,getInfoById,targetUserInfo
    }
});
