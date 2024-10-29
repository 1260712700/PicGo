<template>
    <div class="h-[20px] flex space-x-2 w-full mx-2  mt-2">
        <div class="h-[20px]">
            <el-tooltip content="表情" placement="top">
                <Expression/>
            </el-tooltip>
        </div>
        <div class="h-[20px]">
            <el-tooltip content="文件" placement="bottom">
            <File/>
        </el-tooltip>
        </div>
        <div class="h-[20px]">
            <el-tooltip content="聊天记录" placement="top">
            <ChatRecord/>
        </el-tooltip>
        </div>
    </div>
    <div>
        <el-input
        v-model="content"
        class="w-full"
        type="textarea"
        :rows="textRaw"
        resize="none"
        placeholder="请输入"
         @keydown.enter.prevent="sendMessage"
      />
                <div class=" absolute bottom-[3%] right-[3%]">
                    <el-button type="primary" @click="sendMessage">发送</el-button>
                </div>
    </div>
</template>

<script setup>
import ChatRecord from '@/icon/chatRecord.vue';
import Expression from '@/icon/expression.vue';
import File from '@/icon/file.vue';
import getTime from '@/utils/time';
import { useInfoStore } from '@/stores/info';
import { useMessageStore } from '@/stores/message';
import { onMounted, ref } from 'vue';
const messageStore= useMessageStore()
const textRaw=ref(1)//默认1行
onMounted(()=>{
    // 25：工具栏高度 window.innerHeight*0.85：消息盒子高度  *0.3：输入域高度
   var i=((window.innerHeight*0.85*0.3-25)/21)//根据盒子高度计算文本域占的行数
   var row= Math.trunc(i)
   textRaw.value=row+1
})
const content=ref('')
const infoStore= useInfoStore()

function sendMessage(){
    if(!content.value)return
    const user=infoStore.getCurUser()
    const m={
        text:content.value,
        align:'right',
        name:user.username,
        time:getTime()
    }
    content.value=''
    messageStore.pushMessageInfos(m)
}
</script>

<style lang="scss" scoped>

</style>