<template>
    <div class="user-info-container" 
    >
    <div class="user-info-brand" >
        <img class="user-avatar" width="80"  />
        <div class="user-name text-blog-color  text-lg"></div>
        <!-- <div class="intro text-blog-deep-color  ">{{ infoStore.targetUser.intro}}</div> -->
    </div>
    
    <div class="user-info-operation">
        <div class="w-[100px]">
            <el-button  class="w-[100px]" >
                <div class=" text-sm">立即聊天</div>
                </el-button>
        </div>
        <div v-if="true" class="w-[100px]">
            <el-button @click="targetUser.isSubscribe=1" 
             class="w-[100px]" type="primary">
                <div class="font-bold"> + </div>
                <div class=" text-sm">关注</div>
                </el-button>
        </div>
        <div v-else>
            <el-dropdown>
                <el-button 
                 class="w-[100px]" type="primary">
                    <el-icon ><Expand /></el-icon>
                    <div  >已关注</div>
                    </el-button>
                <template  #dropdown>
                  <el-dropdown-menu  >
                    <el-dropdown-item>设置分组</el-dropdown-item>
                    <!-- TODO 取消关注 -->
                    <el-dropdown-item >取消关注</el-dropdown-item>
                 
                  </el-dropdown-menu>
                </template>
              </el-dropdown>
         
        </div>
    </div>
    <div class="user-info-show">
        <el-row>
            <el-col :span="8">
                <el-statistic  >
                  <template #title>
                      <div class="flex items-center  justify-center">关注数
                      </div>
                    </template>
                </el-statistic>
              </el-col>
              <el-col :span="8">
                <el-statistic   >
                  <template #title>
                      <div class="  flex items-center justify-center">粉丝数
                      </div>
                    </template>
                </el-statistic>
              </el-col>
              <el-col :span="8">
                <el-statistic   >
                  <template #title>
                      <div class="flex items-center  justify-center">贡献文章
                      </div>
                    </template>
                    
                </el-statistic>
              </el-col>
          </el-row>
    </div>
    </div>
  </template>
    
    <script setup>
  
  </script>
    
    <style scoped>
  
  .user-info-container {
    @apply transition-colors duration-700 w-full  min-w-[200px] h-[300px] shadow-md rounded-md overflow-hidden;
    background-color: var(--el-bg-color);
  }
  .user-info-brand{
    @apply   h-[175px] w-[100%] bg-cover bg-no-repeat flex flex-col items-center justify-center  ;
  }
  .user-info-operation{
    @apply  my-2  w-full  flex  space-x-2 justify-center  ;
  }

  </style>