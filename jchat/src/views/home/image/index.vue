<template>  
    <div class="imgs">  
      <ul>  
        <li  
          class="item"  
          v-for="(image, index) in imageList"  
          :key="index"  
          :style="getImageStyle(index)"  
        >  
          <!-- 这里可以添加图片内容，比如 <img :src="image" alt="">，但在这个例子中我们使用背景图片 -->  
        </li>  
      </ul>  
    </div>  
  </template>  
    
  <script setup>  
  import { useInfoStore } from "@/stores/info";  
  import { onMounted, ref, computed } from "vue";  
    
  const imageList = ref([]);  
  const infoStore = useInfoStore();  
    
  onMounted(() => {  
    imageList.value = infoStore.targetUserBg; // 假设这是从store中获取的图片数组  
    animationDuration.value=(imageList.value.length-1)*6
  });  
    
  const baseDelay = 6000; // 每张图片的基本延迟时间，单位毫秒  
  const animationDuration = ref(30); // 动画的总持续时间，单位秒  
    
  // 计算属性，用于根据图片数量计算每个图片的动画延迟  
  const animationDelays = computed(() => {  
    const delays = [];  
    const totalImages = imageList.value.length;  
    
    for (let i = 0; i < totalImages; i++) {  
      // 计算每张图片的延迟时间，并转换为秒  
      delays.push(`${(i * baseDelay) / 1000}s`);  
    }  
    
    return delays;  
  });  
    
  // 方法，用于返回每个图片的样式对象  
  function getImageStyle(index) {  
    return {  
      backgroundImage: `url(${imageList.value[index]})`,  
      animationDelay: animationDelays.value[index],  
      animationDuration: `${animationDuration.value}s`, // 这里其实不需要为每个图片单独设置，因为它们在同一个动画循环中  
      // 但如果我们想为每个图片设置不同的动画时长（不推荐，因为会打乱动画节奏），  
      // 可以根据需求动态计算并设置这个值。在这个例子中，我们保持统一。  
    };  
  }  
  </script>  
    
  <style scoped>  
  .imgs {  
    position: fixed;  
    top: 0;  
    left: 0;  
    width: 100%;  
    height: 100vh;  
    z-index: -9;  
    background-color: #363636;  
    overflow: hidden;  
  }  
    
  .item {  
    position: absolute;  
    top: 0;  
    left: 0;  
    width: 100%;  
    height: 100%;  
    background-size: cover;  
    background-position: center;  
    opacity: 0;  
    animation: imageAnimation linear infinite;  
    backface-visibility: hidden;  
    transform-style: preserve-3d;  
  }  
    
  @keyframes imageAnimation {  
    0% {  
      opacity: 0;  
      animation-timing-function: ease-in;  
    }  
    10% { /* 根据动画总时长调整百分比 */  
      opacity: 1;  
    }  
    20% { /* 同上 */  
      opacity: 1;  
      transform: scale(1.05);  
      animation-timing-function: ease-out;  
    }  
    40% { /* 同上，确保动画在总时长内完成 */  
      opacity: 1;  
      transform: scale(1.1);  
    }  
    50% { /* 同上 */  
      opacity: 0;  
      transform: scale(1.1);  
    }  
    100% {  
      opacity: 0;  
    }  
  }  
  </style>