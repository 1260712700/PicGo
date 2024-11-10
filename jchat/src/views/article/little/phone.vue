<template>
  <div class="preview w-[252px] rounded-3xl border-2 h-[544px]">
    <div class="area px-3 pt-2 flex w-full h-auto">
      <button class="time h-[16px] text-xs">00:00</button>
      <div class="ml-auto space-x-1 flex h-[12px]">
        <MobileSignal />
        <Wifi />
        <Electricity />
      </div>
    </div>
    <div class="head flex h-auto p-2 w-full">
      <div class="flex h-[24px] space-x-1">
        <div class="return">
          <el-icon><ArrowLeftBold /></el-icon>
        </div>
        <div class="avatar">
          <el-avatar :src="avatar" :size="24" />
        </div>
        <div class="h-[9px] mt-1" style="font-size: 9px">{{ nickName }}</div>
      </div>
      <div class="ml-auto flex items-center space-x-1 h-[24px]">
        <div
          class="border rounded-lg p-1 border-red-600 text-red-600"
          style="font-size: 9px"
        >
          关注
        </div>
        <div class="h-[16px]">
          <Share />
        </div>
      </div>
    </div>

    <div class="phone-container mt-0 overflow-auto w-full h-[421px] p-[2px] no-scrollbar">
      <div class="show-picture">
        <!-- 绑定 v-model:current-index -->
        <el-carousel
          ref="carousel"
          v-if="articleStore.saveArticle.images"
          indicator-position="outside"
          v-model:active-index="currentIndex"
          @change="handleCarouselChange"
        >
          <el-carousel-item v-for="(item, index) in list" :key="index">
            <img
              :src="item"
              alt="carousel image"
              class="carousel-image"
              :style="{ maxHeight: minImageHeight + 'px' }"
              @load="handleImageLoad"
            />
          </el-carousel-item>
        </el-carousel>
        <!-- 自定义导航条 -->
        <div
          class="custom-indicator flex justify-center mt-[5px] space-x-2 mb-2"
        >
          <div
            v-for="(item, index) in list"
            :key="index"
            @click="goToIndex(index)"
            @mouseenter="goToIndex(index)"
            :style="{
              opacity: index === currentIndex ? 0.3 : 1, // 当前选中的项透明度降低
              transition: 'opacity 0.3s', // 添加平滑过渡
            }"
          >
            <div
              class="rounded-full h-[9px] w-[9px]"
              style="background-color: var(--theme-icon-color)"
            ></div>
          </div>
        </div>
      </div>
      <div class="content p-2">
        <div class="title text-lg break-words whitespace-normal">
          {{ articleStore.saveArticle.title }}
        </div>
        <div class="title text-base break-words whitespace-normal">
          {{ articleStore.saveArticle.content }}
        </div>
      </div >
      <div class="update-time p-2 text-little"
        style="color: var(--theme-little-text-color)"
      >
        编辑于： {{ articleStore.saveArticle.updateTime }}
      </div>
      <div class="h-[1px] border-t mx-2 my-3"></div>
        <div class="comment-input-area p-2 space-x-2 flex">
            <el-avatar :src="avatar"  :size="24" ></el-avatar>
            <div class="comment-input rounded-xl text-little w-[80%] h-[24px] flex items-center pl-3 little-input" >喜欢就给个评论支持一下~</div>
        </div>
        <div class="comment-show-area h-[100p] mt-auto text-center">
            <div class="msg text-little mx-auto" style="color:var(--theme-little-text-color)">
                还没有评论哦~
            </div>
            <div class="text-xs w-[70px] p-1 px-2 mx-auto rounded-xl border mt-3">抢首评</div> 
        </div>
    </div>
    <div class="foot h-[54px] p-2  border-t">
        <div class="foot-container flex  items-center ">
            <div class="input p-2 pr-4 space-x-1 items-center rounded-xl little-input flex h-[28px]">
                <editPen/>
                <div class="text-little">
                    说点什么吧~
                </div>
            </div>
            <div class="h-[20px] ml-2 space-x-1 flex">
                <Like/>
                <div class="text-little">***</div>
                <Collect/>
                <div class="text-little">***</div>
    
                <Comment/>
                <div class="text-little">***</div>
            </div>
        </div>
        <div class="home-button mx-auto mt-[10px] w-[100px] rounded-lg h-[5px]" style="background-color:var(--home-button-color)"></div>
    </div>
  </div>
</template>
  <script setup>
  
import Electricity from '@/icon/electricity.vue'
import MobileSignal from '@/icon/mobileSignal.vue'
import Share from '@/icon/share.vue'
import Wifi from '@/icon/wifi.vue'
import { useArticleStore } from '@/stores/article'
import { useUserStore } from '@/stores/user'
import { computed, onMounted, ref, watch } from 'vue'
import editPen from '../../../icon/editPen.vue'
import Like from '@/icon/like.vue';
import Collect from '@/icon/collect.vue';
import Comment from '@/icon/comment.vue';

const userStore = useUserStore()
const avatar = ref(null)
const nickName = ref('')
const articleStore = useArticleStore()
const currentIndex = ref(0) // 当前选中的索引

// 获取文章图片列表
const list = computed(() => {
  let list = []
  if (articleStore.saveArticle.images) {
    list = articleStore.saveArticle.images.split(',')
    list.length = list.length - 1
  }
  return list.length !== 0 ? list : []
})

onMounted(() => {
  if (userStore.userInfo) {
    avatar.value = userStore.userInfo.avatar
    nickName.value = userStore.userInfo.nickname
  }
})

// 改变当前选中的索引
const goToIndex = index => {
  currentIndex.value = index
  const carouselInstance = carousel.value
  if (carouselInstance) {
    carouselInstance.setActiveItem(index)
  }
}

// 处理轮播图自适应
const carousel = ref(null)
const minImageHeight = ref(null)
const handleImageLoad = event => {
  const imageHeight = event.target.clientHeight // 获取图片当前显示的高度
  if (minImageHeight.value === null || imageHeight < minImageHeight.value) {
    minImageHeight.value = imageHeight // 更新最小高度为当前图片高度
  }
  updateCarouselHeight()
}

const updateCarouselHeight = () => {
  if (carousel.value && minImageHeight.value !== null) {
    carousel.value.$el.style.maxHeight = minImageHeight.value + 'px' // 设置轮播图的最大高度
  }
}
// 监听轮播图索引变化
const handleCarouselChange = index => {
  currentIndex.value = index
}
</script>
  
  <style>
.carousel-image {
  width: auto;
  max-width: 100%;
  height: auto; /* 保持图片的原始宽高比 */
  object-fit: contain; /* 较大的图片会有左右留白 */
  display: block;
  margin: 0 auto; /* 图片居中 */
}
.little-input{
    color:var(--theme-little-text-color); 
    background-color:var(--input-bg-color)
}
</style>
  