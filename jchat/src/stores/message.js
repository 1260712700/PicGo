import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useMessageStore = defineStore('messageStore', () => {
    const message=ref({
        name:'马飞飞',
        content:'你好j-chat',
        avatar:'/icon/2/avatar-boy.png',
        // time:'2024年2月15日 14:32'
         time:'昨天'
    })
    const isMessageActive=ref(false)
    const messageIndex=ref('')
    function setMessage(m){
        message.value.name=m.name
        message.value.content=m.content
        message.value.avatar=m.v
        message.value.time=m.time
    }
    function getMessage(){
        return message.value
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
        setMessage,getMessage

    }
});
