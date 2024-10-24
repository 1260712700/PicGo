import { defineStore } from 'pinia';
import { reactive, ref } from 'vue';

export const useMessageStore = defineStore('messageStore', () => {
    const chatMessages=reactive([{
        id:'12',
        name:'马飞飞',
        content:'你好j-chat',
        avatar:'/icon/2/avatar-boy.png',
         time:'昨天'
    },
    {
        id:'112',
        name:'小丽',
        content:'你好啊马飞',
        avatar:'/icon/2/avatar-girl.png',
        time:'24/2/15/ 14:32'
    }])
    const  messageInfos=reactive([
        { text: "你好！", align: "left", name: "王阳阳", time: "18:07" },
        { text: "你好！", align: "right", name: "丽丝", time: "19:21" },
        { text: "如何才能帮助您？", align: "left", name: "王阳阳", time: "21:26" },
        {
          text: "我需要帮助进行Vue.js开发我需要帮助进行Vue.js开发我需要帮助进行Vue.js开发我需要帮助进行Vue.js开发",
          align: "right",
          name: "丽丝",
          time: "22:37"
        }
      ])
      function pushMessageInfos(message){
        console.log(message);
        messageInfos.push(message)
    }
    function getMessageInfos(){
        return messageInfos
    }
    const isMessageActive=ref(false)
    const messageIndex=ref('')
    function setChatMessages(m){
        chatMessages.values=m
    }
    function getChatMessages(){
        return chatMessages
    }
    function setMessageIndex(val){
        messageIndex.value=val
       }
       function getMessageIndex(){
        return messageIndex.value
       }
    function setIsMessageActive(val){
     isMessageActive.value=val
    }
    function getIsMessageActive(){
     return isMessageActive.value
    }
    return {
        setIsMessageActive,getIsMessageActive,
        setMessageIndex,getMessageIndex,
        setChatMessages,getChatMessages,
        pushMessageInfos,getMessageInfos
    }
});
