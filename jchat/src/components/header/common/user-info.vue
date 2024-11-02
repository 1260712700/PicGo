<template>
        <div class="user-info relative min-h-[40px]  flex items-center justify-center">
            <el-dropdown>
                <el-avatar @click="showFriendBar" :class="avatarClass" :size="35" :src="avatar" />
                <template #dropdown>
                  <el-dropdown-menu>
                    <el-dropdown-item>
                        <el-link href="/user-page">
                            个人主页
                        </el-link>
                        </el-dropdown-item>
                    <el-dropdown-item >
                        <el-link href="/setting">
                            网页设置
                        </el-link>
                        </el-dropdown-item>
                    <el-dropdown-item divided>
                        <div @click="logout" >
                            退出登录
                        </div>
                    </el-dropdown-item>
                  </el-dropdown-menu>
                </template>
              </el-dropdown>
       </div>
  
</template>

<script setup>
import { useSettingStore } from '@/stores/setting';
import { useUserStore } from '@/stores/user';
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';
const settingStore= useSettingStore()
const avatar=ref(null)
const avatarClass=ref('normal')
const userStore= useUserStore()
onMounted(()=>{
    const userInfo= userStore.userInfo
    if(userInfo)avatar.value=userInfo.avatar
})
function showFriendBar(){
    settingStore.setIsFriendActive(true)
}
const router=useRouter()
function logout(){
    router.push('/welcome')
}


</script>

<style  scoped>

</style>