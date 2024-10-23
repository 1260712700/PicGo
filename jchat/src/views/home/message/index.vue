<template>
    <div class="message-container">
        <el-drawer @closed="handleClose" 
      :lockScroll="false" :with-header="false"  v-model="showMessage" :direction="direction">
            <template #default>
                <div class="h-full w-full">
                    <el-tabs 
                    class="tabs"
                    type="border-card" 
                    v-model="activeName">
                      <el-tab-pane label="我的消息" name="first">
                        <MessageBox/>
                      </el-tab-pane>
                      <el-tab-pane label="回复我的" name="second">
                      </el-tab-pane>
                      <el-tab-pane label="系统通知" name="third">
                    </el-tab-pane>
                      <el-tab-pane  label="评论和@" name="four">
                      </el-tab-pane>
                      <el-tab-pane  label="新增关注" name="five">
                    </el-tab-pane>
                      <el-tab-pane label="收到的赞" name="six">
                    </el-tab-pane>
                    </el-tabs>
                </div>
            </template>
          </el-drawer>
    </div>
  
</template>

<script setup>
import { ref, watch } from 'vue'
import { useMessageStore } from '@/stores/message';
import MessageBox from './container/index.vue';
const messageStore= useMessageStore()
const direction = ref('ltr')
const showMessage=ref(false)
const activeName = ref('second')
watch(()=>messageStore.getIsMessageActive(),(cur)=>{
    showMessage.value=cur
})
watch(()=>messageStore.getMessageIndex(),(cur)=>{
    activeName.value=cur
})
watch(()=>activeName.value,(cur)=>{
    messageStore.setMessageIndex(cur)
})
function handleClose(){
    messageStore.setIsMessageActive(false)
}
</script>

<style  >
.message-container .el-tabs--border-card {
    @apply h-full w-full ;
}
.message-container .el-tabs--border-card>.el-tabs__content {
    @apply p-0 w-full h-[80vh];
}
.message-container .el-drawer.ltr {
    @apply h-[85%] top-[7.5%] w-[50%] rounded-md !important; 
}
.message-container .el-drawer__body{
    @apply p-0
}

</style>