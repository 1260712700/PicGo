<template>
    <div class="message-container">
        <el-drawer @closed="handleClose" 
      :lockScroll="false" :with-header="false"  v-model="showFriend" :direction="direction">
            <template #default>
                <div class="h-full w-full">
                    <el-tabs 
                    class="tabs"
                    type="border-card" 
                    v-model="activeName">
                      <el-tab-pane label="我的消息" name="first">
                        <div class="h-[100vh] w-full bg-black"></div>
                      </el-tab-pane>
                      <el-tab-pane label="回复我的" name="second">
                      </el-tab-pane>
                      <el-tab-pane label="@我的" name="third">
                      </el-tab-pane>
                      <el-tab-pane label="收到的赞" name="four">
                    </el-tab-pane>
                    <el-tab-pane label="系统通知" name="five">
                    </el-tab-pane>
                    </el-tabs>
                </div>
            </template>
          </el-drawer>
    </div>
  
</template>

<script setup>
import { ref, watch } from 'vue'
import { useSettingStore } from '@/stores/setting';
const settingStore= useSettingStore()
const direction = ref('ltr')
const showFriend=ref(false)

const activeName = ref('second')

watch(()=>settingStore.getIsMessageActive(),(cur)=>{
    showFriend.value=cur
})
function handleClose(){
    settingStore.setIsMessageActive(false)
}

</script>

<style  >
.message-container .el-tabs--border-card {
    @apply h-full w-full ;
}
.message-container .el-tabs--border-card>.el-tabs__content {
    @apply p-0 h-full w-full overflow-auto;
}
.message-container .el-drawer.ltr {
    @apply h-[85%] top-[7.5%] w-[50%] rounded-md !important; 
}
.message-container .el-drawer__body{
    @apply p-0
}
</style>