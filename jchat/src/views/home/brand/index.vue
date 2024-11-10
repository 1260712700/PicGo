<template>
    <div class="h-screen">
        <div class="brand-container ">
          <div class="brand">
            <div class="art-brand">
              {{username}}的世界</div>
            <div class="brand-text">
              <div class="title">{{ brandTitle }}</div>
              <div class="easy-typed-cursor">&nbsp;|&nbsp;</div>
            </div>
          </div>
        </div>
        <div class="arrow" @click="scrollToMain" width="50px" height="50px" />
      
          <Wave/>
      </div>
</template>

<script setup>

import { computed, onBeforeUnmount, ref } from "vue";
import { onMounted } from "vue";
import { useInfoStore } from "@/stores/info";
import Wave from "@/components/wave/index.vue";
import { useUserStore } from "@/stores/user";
const infoStore= useInfoStore()
const userStore= useUserStore()
const username=computed(()=>{
  return userStore.userInfo?userStore.userInfo.nickname:'TA';
})
onMounted(() => {
 init()
});

function init(){
  userWord.value=infoStore.targetUserWord
  getTitle();
}
const userWord=ref('')

const brandTitle = ref("");

// 标题下面的艺术字
// 获取艺术字(pinia中)
const getTitle = () => {
  const w = userWord.value[wordIndex.value];
  let i = 0;
  wordIntervalId.value = setInterval(() => {
    const ch = w.charAt(i++);
    brandTitle.value += ch;
    if (i >= w.length) {
      if (wordIndex.value++ >= userWord.value.length - 1) {
        wordIndex.value = 0;
      }
      clearInterval(wordIntervalId.value)
      setTimeout(() => {
        clearTitle();
      }, 1000);
      return;
    }
  }, 300);
};
// 清空艺术字
const clearTitle = () => {
  wordIntervalId.value = setInterval(() => {
    const len = brandTitle.value.length;
    if (len <= 0) {
        clearInterval(wordIntervalId.value)

      getTitle();
      return;
    }
    brandTitle.value = brandTitle.value.substring(0, len - 1);
  }, 100);
};
function scrollToMain(){
  const screenHight=window.innerHeight
  window.scrollTo({
    top:screenHight,
    behavior:"smooth"
  })
}
//根据长度置0，左边长度大于右边时置0
const wordIndex = ref(0);
// 计时器id
const wordIntervalId = ref(null);


</script>

<style  scoped>
/* 标题盒子--------------------- */
.brand-container{
  z-index: -3;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 85vh;
  position: fixed;
  min-height: 10rem;
}
.brand{
 display: flex;
 flex-direction: column;
 justify-content: center;
 align-items: center;
  position: absolute;
  z-index:-1;
  top: 15rem;
}

.art-brand{
  color: white;
  font-family: Fredericka the Great, Mulish, -apple-system, PingFang SC, Microsoft YaHei, sans-serif;
  font-size: 4.5em;
  line-height: 1.5;
  text-shadow: 0 1px 0 hwb(170 79% 19%),0 2px 0 hsl(174,5%,75%),0 3px 0 hsl(174,5%,70%),0 4px 0 hsl(174,5%,66%),0 5px 0 hsl(174,5%,64%),0 6px 0 hsl(174,5%,62%),0 7px 0 hsl(174,5%,61%),0 8px 0 hsl(174,5%,60%),0 0 5px rgba(0,0,0,.05),0 1px 3px rgba(0,0,0,.2),0 3px 5px rgba(0,0,0,.2),0 5px 10px rgba(0,0,0,.2),0 10px 10px rgba(0,0,0,.2),0 20px 20px rgba(0,0,0,.3);
}

.brand .brand-text{
  display: flex;
  background: rgba(255, 255, 255, .5);
  padding: .5em;
  border-radius: .5em
}
.brand .title{
  background: linear-gradient(90deg,#f79533,#f37055,#ef4e7b,#a166ab,#5073b8,#1098ad,#07b39b,#6fba82);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  font-size: 1.5rem;
  font-weight: 700;
}
/* 彩色字体--------------------- */
.easy-typed-cursor{
  animation: blink infinite  1s;
  font-size: 1.5rem;
  font-weight: 700;
  background: 
  linear-gradient(90deg,#f79533,#f37055,#ef4e7b,#a166ab,#5073b8,#1098ad,#07b39b,#6fba82);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}
@media (max-width:762px){
.art-brand{
font-size: 3em;
}
.brand .title{
font-size: 1rem;
}
.easy-typed-cursor{
font-size: 1rem;
}
}
@keyframes blink{
  0%, 100% {
      opacity: 0;
     }
     50% {
      opacity: 1;
     }

}
/* 箭头样式-------------------- */
.arrow {
  position: absolute;
  bottom: 15vh;
  left: 50%;
  -webkit-animation: arrow-shake 1.5s ease-out infinite;
  animation: arrow-shake 1.5s ease-out infinite;
  cursor: pointer;
  z-index: 8;
}
@keyframes arrow-shake {
  0% {
    opacity: 1;
    transform: translateY(0);
  }

  30% {
    opacity: 0.5;
    transform: translateY(30px);
  }

  100% {
    opacity: 1;
    transform: translateY(0);
  }
}
.arrow::after {
  transition: border-color ease-in 1s;
  content: "";
  position: absolute;
  z-index: 11;
  display: block;
  width: 25px;
  height: 25px;
  border-top: 5px solid #ffffff;
  border-left: 5px solid #ffffff;
  top: 10px;
  transform: rotate(225deg);
}

</style>