<template>
        <div class="user-info relative min-h-[40px]  flex items-center justify-center">
            <el-dropdown>
                <el-avatar @click="showFriendBar" @mouseenter="rotate" @mouseleave="endRotate" :class="avatarClass" :size="35" :src="avatar" />
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
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';
const settingStore= useSettingStore()
const avatar=ref(null)
const avatarClass=ref('normal')
onMounted(()=>{
avatar.value=''
})
function showFriendBar(){
    settingStore.setIsFriendActive(true)
}
const router=useRouter()
function logout(){
    router.push('/login')
}

const timeoutId=ref(null)
function rotate(){
    avatarClass.value='rotating'
    timeoutId.value= setTimeout(()=>{
    avatarClass.value='normal'
    },1000)
}
function endRotate(){
    if(!timeoutId.value)return
     avatarClass.value='normal'
}
</script>

<style  scoped>
.rotating{
    transition: transform .7s ease-in;
    transform: rotate(360deg);
}
</style>