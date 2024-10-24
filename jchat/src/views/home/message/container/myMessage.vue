<template>
    <Message>
        <template #userList>
            <div class="user-box  w-full   space-y-2 p-1">
                <div v-for="(item,index) in messageStore.getChatMessages()" :key="index">
                    <InfoBox :message="item"/>
                </div>
            </div>
        </template>
        <template #messageBox>
    <div class="chat-container" ref="chatRef">
            <Info :messages="messageStore.getMessageInfos()"/>
    </div>

        </template>
        <template #inputBox>
            <div class="h-[20px] flex space-x-2 w-full mx-2  mt-2">
                <Expression/>
                <File/>
                <ChatRecord/>
            </div>
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
        </template>
    </Message>
</template>

<script setup>


import Message from '@/components/layout/message/index.vue';
import InfoBox from '@/components/message/layout/info-box.vue';
import ChatRecord from '@/icon/chatRecord.vue';
import Expression from '@/icon/expression.vue';
import File from '@/icon/file.vue';
import { useMessageStore } from '@/stores/message';
import { onMounted, onUpdated, reactive, ref } from 'vue';
import Info from '../info/index.vue';
import { useInfoStore } from '@/stores/info';
import getTime from '@/utils/time';
const infoStore= useInfoStore()
const messageStore= useMessageStore()
const content=ref('')
const textRaw=ref(1)
onMounted(()=>{
   var i=((window.innerHeight*0.85*0.3-25)/21)
   var row= Math.trunc(i)
   textRaw.value=row+1
})

const chatRef = ref(null);
// TODO 输入到底部需添加
const scrollToBottom = () => {
  if (!chatRef.value)return
  const chatBoxHeight=window.innerHeight*0.85*0.7
    if(chatRef.value.scrollHeight>chatBoxHeight){
        chatRef.value.style.top=-(chatRef.value.scrollHeight-chatBoxHeight)+'px'
        // chatRef.value.scroll({
        //   top: -(chatRef.value.scrollHeight-chatBoxHeight),
        //   behavior: 'smooth', // 平滑滚动
        // })
    }
};

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
    console.log(chatRef.value.getBoundingClientRect());
    
    scrollToBottom()
}
</script>

<style >
.input-box .el-textarea__inner {
    box-shadow: none;
}
.chat-container {
    overflow: hidden;
    display: flex;
    flex-direction: column;
    width: 100%;
    padding: 16px;
    .name {
      font-family: PingFangSC-Regular;
      font-weight: 400;
      font-size: 12px;
      margin-bottom: 6px;
    }
  }
</style>