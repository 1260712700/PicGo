<template>
  <div :ref="setImageRef" class="article-cover top w-full h-full rounded-md overflow-hidden">
    <el-skeleton
      style="width: 100%; height: 100%"
      :loading="loading"
      animated
      :throttle="500"
    >
      <template #template>
        <el-skeleton-item 
          class="transition-colors duration-700"
          variant="image"
          style="width: 100%; height: 100%"
        />
      </template>
      <template #default>
        <img
          class="h-full w-full object-cover transition-all duration-700"
          :ref="setArticleImageListRef"
          :src="bgCover"
          @load="handleImageLoad"
        >
      </template>
    </el-skeleton>
  </div>
</template>

<script setup>
import { lazyLoading } from '@/utils/vLazy';
import { onMounted, ref } from 'vue';

const articleImage = ref();
const imageRef = ref();
const loading = ref(true);
const bgCover = ref('');

const props = defineProps({
  item: Object,
});

onMounted(() => {
  lazyLoading(imageRef.value, () => {
    // 图片进入视口，开始加载图片 URL
    bgCover.value = props.item.articleCover;
  });
});

// 暴露给父组件的方法
defineExpose({
  enLargeArticleCover,
  reduceArticleCover,
});

// 设置图片引用
function setImageRef(el) {
  imageRef.value = el;
}

function setArticleImageListRef(el) {
  articleImage.value = el;
}

// 处理图片加载完成事件
function handleImageLoad() {
  loading.value = false; // 图片加载完成，隐藏骨架屏
}

// 放大
function enLargeArticleCover() {
  if (articleImage.value) {
    articleImage.value.style.transform = 'scale(1.2)';
  }
}

// 缩小
function reduceArticleCover() {
  if (articleImage.value) {
    articleImage.value.style.transform = 'scale(1)';
  }
}
</script>

<style lang="scss" scoped>
/* 可以添加样式 */
</style>
