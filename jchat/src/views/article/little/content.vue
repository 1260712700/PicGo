<template>
    <div class="py-[8px] pl-[20px] w-full h-full rounded-md bg-transparent ">
        <div class="title text-2xl font-bold">{{item.title}}</div>
        <!-- TODO 统计值的绑定 -->
        <div class="count h-[16px] space-x-2 flex mt-2">
          <div class="count-item ">
            <Eye/>
            <div class="num">
              {{ item.visitCount }}
            </div>
          </div>
          <div class="count-item ">
            <Comment/>
            <div class="num">
            {{ item.commentCount }}
            </div>
          </div>
          <div class="count-item ">
            <Like/>
            <div class="num">
            {{ item.like }}
            </div>
          </div>
          <div class="count-item ">
            <Collect/>
            <div class="num">
            {{ item.visitCount }}
            </div>
          </div>
          <div class="count-item">
            <Share/>
            <div class="num">
            {{ item.shareCount }}
            </div>
          </div>
        </div>
        <div class="h-[28px] tag mt-2">
          {{ item.tags }}
        </div>
        <div class="foot h-[20px] text-sm mt-[16px]">
          发布于：{{ item.createTime }}
        </div>
        <div class="foot h-[20px] text-sm">
          最后更新时间：{{ item.updateTime }}
        </div>
      </div>
</template>

<script setup>
import Collect from '@/icon/collect.vue';
import Comment from '@/icon/comment.vue';
import Eye from '@/icon/eye.vue';
import Like from '@/icon/like.vue';
import Share from '@/icon/share.vue';
defineProps({
  item:Object
})
</script>

<style  scoped>
.count-item {
  @apply flex space-x-1 justify-center items-center
}
</style>