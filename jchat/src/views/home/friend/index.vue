<template>
    <div class="friend-container">
        <el-drawer @closed="handleClose" 
   
      :lockScroll="true" :with-header="false" v-model="showFriend" :direction="direction">
            <template #default>
              <div class="h-[50px] w-full bg-black">
                <el-button type="primary">点击</el-button>
              </div>
            </template>
            <template #footer>
                <div class="h-[50px] w-full bg-black"></div>
            </template>
          </el-drawer>
    </div>
  
</template>

<script setup>
import { ref, watch } from 'vue'
import { useSettingStore } from '@/stores/setting';
const settingStore= useSettingStore()
const direction = ref('rtl')
const showFriend=ref(false)

watch(()=>settingStore.getIsFriendActive(),(cur)=>{
    showFriend.value=cur
})
function handleClose(){
    settingStore.setIsFriendActive(false)
}

</script>

<style  >
.friend-container .el-drawer.rtl {
    @apply h-[85%] top-[7.5%] rounded-md;
}
.friend-container .el-drawer__body{
    @apply p-0
}
</style>