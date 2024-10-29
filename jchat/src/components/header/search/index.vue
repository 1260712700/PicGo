<template>
  <Search>
    <template #search-input>
  <div class="search-input  flex justify-center items-center" @focusin="searchFocusin" @focusout="searchFocusout">
    <div :class="searchStyle">
      <div class="h-[30px]">
        <SearchIcon/>
      </div>
      <el-input v-model="content" placeholder="搜索"></el-input>
      <div class="h-[30px] clean-content" @click="cleanContent" v-show="!contentIsNull">
          <Clean/>
      </div>
    </div>
  </div>
</template>
<template #history>

  <transition name="fade">
    <div v-show="showHistory" class="history-container " >
      <div class="history-head  mb-3 items-center flex">
        <div class="history-title text-xl">
          搜索历史
      </div>
      <button 
      @mouseenter="cleanHistoryMouseIn"
      @mouseleave="cleanHistoryMouseOut"
      @click="searchStore.clearHistoryRecord()"
      :class="cleanHistoryStyle">
        <div class="h-[16px] w-[16px]">
        <Garbage :color="cleanHistoryIconColor"/>
        </div>
        清空历史
      </button>
    </div>
      <div class="history-record">
        <button class='history-item' 
        @click="selectItem(item)"
        v-for="(item,index) in searchStore.getHistoryRecord()" :key="index">{{item}}</button>
      </div>
      <div class="hot-recommend ">
        <div class="text-xl "> 
          热门推荐
        </div>
        <div class="recommend-list flex-wrap w-full flex">
          <button class="recommend-item w-[50%] h-[38px] flex items-center " v-for="(item ,index) in searchStore.getHotRecommend()" :key="index" >
            <p class=" mr-2">{{index+1}}</p>
            <p >{{item}}</p>
            </button>
        </div>
    </div>
    </div>
  </transition>
</template>
</Search>
</template>

<script setup>
import SearchIcon from '@/icon/search.vue';
import { computed, ref, watch } from 'vue';
import Clean from '../../../icon/delete.vue';
import Search from './layout/index.vue';
import Garbage from '@/icon/garbage.vue';
import { useSearchStore } from '@/stores/search';
import { useSettingStore } from '@/stores/setting';

const searchStore= useSearchStore()

const content=ref('')
const contentIsNull=computed(()=>{
  return content.value===''||content.value===null
})
function cleanContent(){
  content.value=''
}
const showHistory=ref(false)
const searchStyle=ref('search-container')
function searchFocusin(){
  searchStyle.value='search-container is-focus'
  showHistory.value=true
}
function searchFocusout(){
  searchStyle.value='search-container'
  showHistory.value=false
}
const cleanHistoryStyle=ref('clean-history')
const cleanHistoryIconColor=ref('var(--theme-little-text-color)')
// 清空历史字样悬浮
function cleanHistoryMouseIn(){
  cleanHistoryIconColor.value='#6b97df'
  cleanHistoryStyle.value='clean-history is-hover'
}
function cleanHistoryMouseOut(){
  cleanHistoryIconColor.value='var(--theme-little-text-color)'
  cleanHistoryStyle.value='clean-history'
}
function selectItem(item){
  content.value=item
}
const settingStore= useSettingStore()

watch(settingStore.scrollTopGetter(),(cur)=>{
    if(cur>=45){
      searchFocusout()
    }
})
</script>

<style>
.search-container{
  @apply border-2 flex rounded-2xl w-[100%] transition-all  duration-700  pl-3 pr-1;
}

.is-focus{
  --tw-shadow: 0 4px 6px -1px rgb(0 0 0 / 0.1), 0 2px 4px -2px rgb(0 0 0 / 0.1);
    --tw-shadow-colored: 0 4px 6px -1px var(--tw-shadow-color), 0 2px 4px -2px var(--tw-shadow-color);
    box-shadow: var(--tw-ring-offset-shadow, 0 0 #0000), var(--tw-ring-shadow, 0 0 #0000), var(--tw-shadow);
  background-color: var(--el-bg-color);
  border-width: 2px;
  border-bottom-left-radius: 0;
  border-bottom-right-radius: 0;
border-color: var(--el-color-primary);
}
.search-input .el-input__wrapper{
  border: none;
  background-color: transparent;
  box-shadow: none;
}
.clean-content:hover{
  opacity: 0.6;
}
.history-container{
  @apply w-full h-full rounded-b-2xl p-[16px];
  background-color: var(--el-bg-color);
}
.clean-history{
  @apply ml-auto justify-center items-center flex;
  color: var(--theme-little-text-color);
}
.is-hover{
  color: #6b97df;
}
.history-record{
  @apply space-x-4  flex flex-wrap mb-3;
}
.history-item{
  @apply px-3 mb-3 border;
  color: var(--el-color-primary);
  border-color:var(--el-color-primary);
}
.history-item:hover{
  opacity: 0.6;
}
.recommend-item:hover{
  opacity: 0.6;
}
</style>