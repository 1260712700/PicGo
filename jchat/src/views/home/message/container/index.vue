<template>
    <Message>
        <template #userList>
            <div class="user-box  w-full  space-y-2 p-1">
            <InfoBox :message="messageStore.getMessage()"/>
            <InfoBox :message="messageStore.getMessage()"/>
            </div>
        </template>
        <template #messageBox>
            <div class="h-full w-full bg-slate-600">1234</div>
        </template>
    </Message>
</template>

<script setup>
import Message from '@/components/layout/message/index.vue';
import InfoBox from '@/components/message/layout/info-box.vue';
import { useMessageStore } from '@/stores/message';
const messageStore= useMessageStore()
</script>

<style scoped>

</style>