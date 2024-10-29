<template>
    <div class="message-box h-full w-full flex">
        <div class="left h-full border-r-2 overflow-auto w-[25%]" style=" 
        scrollbar-width: none;-ms-overflow-style: none; border-color:var(--theme-background-color)">
        <div class="user-box  w-full   space-y-2 p-1">
        <slot name="userList"></slot>
    </div>

        </div>
        <div class="right h-full w-[75%]">
            <div ref="chatRef" class=" p-[16px] h-[70%] scroll-smooth overflow-auto relative  w-full border-b-2" style="scrollbar-width: none;-ms-overflow-style: none;">
                <div class="chat-container h-auto w-full overflow-y-auto transition-all duration-700 "  >
            <slot name="messageBox"></slot>
        </div>
    </div>
        <div class="input-box  h-[30%] overflow-hidden w-full"
        style="scrollbar-width: none;-ms-overflow-style: none; ">
            <slot name="inputBox" ></slot>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, watch } from 'vue';
import { useMessageStore } from '@/stores/message';
const messageStore= useMessageStore()
 const chatRef=ref()
 watch( messageStore.getMessageInfos(),()=>{
    setTimeout(()=>{
        scrollToBottom()//延迟下滑保证元素加载才能下滑到底部
    }
    ,100
    )
 })
const scrollToBottom = () => {
  if (!chatRef.value)return
chatRef.value.scrollTop=chatRef.value.scrollHeight;
};

</script>

<style>

</style>