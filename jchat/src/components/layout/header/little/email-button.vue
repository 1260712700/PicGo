<template>
    <el-dropdown>
        <span class="el-dropdown-link">
    <el-badge :value="12" :max="99" class="item">
        <div class=" bg-cover h-[25px] w-[25px]" style="background-image: var(--email);">
        </div>
      </el-badge>
        </span>
        <template #dropdown>
          <el-dropdown-menu class=" overflow-x-hidden">
            <el-dropdown-item >
                <div class="h-[30px]">
                    <el-badge :value="12" :max="99" class="item">
                        回复我的
                        </el-badge>
                </div>
             
            </el-dropdown-item>
            <el-dropdown-item>
                <div class="h-[30px]">
                    <el-badge :value="12" :max="99" class="item">
                        系统消息
                    </el-badge>
                </div>
            
                </el-dropdown-item>
            <el-dropdown-item>
                <div class="h-[30px]">
                <el-badge :value="12" :max="99" class="item">
                评论和@
            </el-badge>
        </div>

            </el-dropdown-item>
            <el-dropdown-item >
                <div class="h-[30px]">

                <el-badge :value="12" :max="99" class="item">
                新增关注
            </el-badge>
        </div>

            </el-dropdown-item>
            <el-dropdown-item >
                <div class="h-[30px]">
                <el-badge :value="12" :max="99" class="item">
                收到的赞
            </el-badge>
        </div>

            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>


  

</template>

<script setup>

</script>

<style lang="scss" scoped>

</style>