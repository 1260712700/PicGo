<template>
    <Message>
        <template #userList>
                    <UserList :userList="messageStore.getChatMessages()"/>
        </template>
        <template #messageBox>
                 <Info :messages="messageStore.getMessageInfos()"/>
        </template>
         <template #inputBox>
                <MessageInput/>
        </template> 
    </Message>
</template>
<script setup>


import Message from '@/components/layout/message/index.vue';
import UserList from '@/components/message/layout/user-list.vue';
import Info from '@/components/message/info/index.vue';
import MessageInput from '@/components/message/message-input/index.vue';
import { useMessageStore } from '@/stores/message';
const messageStore= useMessageStore()
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
    .name {
      font-family: PingFangSC-Regular;
      font-weight: 400;
      font-size: 12px;
      margin-bottom: 6px;
    }
  }
</style>