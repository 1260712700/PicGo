<template>
  <div class="clock-container" >
    <div class="title">
      <el-icon color="#c9cfce" :class="clockIcon" :size="30"><Clock /></el-icon>
      <div class="font-bold  ml-3 text-xl">电子时钟</div>
    </div>
    <div class="content p-[10px]">
      <div class="date  mb-1 text-center">{{ date }}</div>
      <p class="time  font-bold text-3xl text-center">
        {{ time }}
      </p>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";


const date = ref("");
const time = ref("");
const clockIcon = ref("clock-icon");
onMounted(() => {
  let b = true;

  setInterval(() => {
    b = !b;
    clockIcon.value = b ? "clock-icon" : "clock-icon-active";
    const d = new Date();
    date.value = handleDate(d);
    time.value = handleTime(d);
  }, 1000);
});
const handleDate = (d) => {
  let dayList = ["日","一", "二", "三", "四", "五", "六"];
  const m = d.getMonth() + 1;
  const dom = d.getDate();
  const dow = d.getDay();
  const month = m >= 10 ? m : "0" + m;
  const day = dom >= 10 ? dom : "0" + dom;
  
  return d.getFullYear() + "-" + month + "-" + day + " 星期" + dayList[dow ];
};
const handleTime = (d) => {
  const h = d.getHours();
  const m = d.getMinutes();
  const s = d.getSeconds();
  const hour = h >= 10 ? h : "0" + h;
  const minute = m >= 10 ? m : "0" + m;
  const second = s >= 10 ? s : "0" + s;
  return hour + ":" + minute + ":" + second;
};
</script>

<style  scoped>
.title {
  @apply h-[30]  p-[10px] flex  items-center;
}
.clock-icon {
  scale: 1;
  transition: scale 1s linear;
}
.clock-icon-active {
  scale: 1.3;
  transition: scale 1s linear;
}
.clock-container {
  @apply transition-colors duration-700 w-full min-w-[200px] h-[132px] shadow-md rounded-md;
   background-color: var(--el-bg-color);
}
</style>