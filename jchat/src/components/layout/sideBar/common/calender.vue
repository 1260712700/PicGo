<template>
    <div class="calender-container">
        <el-calendar class="calendar"  v-model="value" />
    </div>
  </template>
  
  <script  setup>
  import { ref } from 'vue'
  const value = ref(new Date())
  </script>
  <style >
  
  .calender-container {
    @apply  transition-colors duration-700 w-full min-w-[200px] h-auto  overflow-hidden shadow-md rounded-md ;
    background-color: var(--el-bg-color);
  }
  .el-calendar{
    @apply bg-transparent !important;
  }
 .calender-container .el-calendar .el-calendar__body .el-calendar-table  .el-calendar-table__row .el-calendar-day{
    height: 40px
 }
</style>