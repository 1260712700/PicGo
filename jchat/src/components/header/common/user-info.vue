<template>
        <div class="user-info relative min-h-[40px]  flex items-center justify-center">
            <el-dropdown>
                      <el-avatar v-if="avatar === ''" class="flex items-center justify-center" :size="35" @click="router.push('/welcome')" >登录</el-avatar>
                      <el-avatar v-else class="flex items-center justify-center" :size="35" :src="avatar"></el-avatar>
                <template #dropdown>
                  <el-dropdown-menu>
                    <el-dropdown-item>
                        <el-link @click="showFriendBar">
                            个人主页
                        </el-link>
                        </el-dropdown-item>
                    <el-dropdown-item >
                        <el-link href="/setting">
                            网页设置
                        </el-link>
                        </el-dropdown-item>
                    <el-dropdown-item divided>
                        <div @click="userLogout" >
                            退出登录
                        </div>
                    </el-dropdown-item>
                  </el-dropdown-menu>
                </template>
              </el-dropdown>
       </div>
  
</template>
<script setup>
import { REMOVE_TOKEN } from '@/api/auth';
import { logout } from '@/api/user';
import { useSettingStore } from '@/stores/setting';
import { useUserStore } from '@/stores/user';
import { ElMessage } from 'element-plus';
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';
import { watch } from 'vue';

const settingStore= useSettingStore()
const avatar=ref('')
const userStore= useUserStore()
const router=useRouter()
watch(() => userStore.userInfo, (newValue) => {
  if (newValue && newValue.avatar) {
    avatar.value = newValue.avatar;
  } else {
    avatar.value = '';  // 没有头像时
  }
});
onMounted(()=>{

})


function showFriendBar(){
    settingStore.setIsFriendActive(true)
}
function userLogout(){
    logout().then(res=>{
        
        if(res.code===200){
            ElMessage.success("退出登录成功")
        }
        if(res.code===1002){
            ElMessage.error("清先登录")
        }
        userStore.userInfo=''
        REMOVE_TOKEN()
        router.push('/welcome')
    })
}

</script>

<style  scoped>

</style>