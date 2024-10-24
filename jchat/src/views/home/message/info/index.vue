<template>
      <div
        v-for="(message, index) in messages"
        :key="index"
        :class="message.align === 'left' ? 'message-left' : 'message-right'"
      >
        <div class="name">
          <span>{{ message.name }} {{ message.time }} </span>
        </div>
        <div class="chat_message">
          {{ message.text }}
        </div>
      </div>
  </template>
   
  <script setup>
  defineProps({
    messages:Object
  })
  </script>
   
  <style scoped>
   
  .chat_message {
    padding: 6px 12px;
    border-radius: 4px;
    margin-bottom: 15px;
    word-wrap: break-word;
   
    font-weight: 400;
    font-size: 14px;
  }
   
  .message-left {
    max-width: 418px;
    align-self: flex-start;
    .chat_message{
        background-color: var(--theme-bg-left-message);
    }
    .name {
        color: var( --theme-little-text-color);
      align-self: flex-start;
    }
  }
   
  .message-right {
    max-width: 418px;
    align-self: flex-end;
    display: flex;
    flex-direction: column;
    .chat_message{
        background-color: var(--theme-bg-right-message);
    }
    .name {
        color: var( --theme-little-text-color);
      align-self: flex-end;
    }
  }
  </style>