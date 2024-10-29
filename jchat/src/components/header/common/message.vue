<template>
    <el-dropdown>
        <span class="el-dropdown-link">
    <el-badge :value="12" :max="99" class="item">
        <div class=" email h-[30px] w-[30px]">
            <Email/>
        </div>
      </el-badge>
        </span>
        <template #dropdown>
          <el-dropdown-menu class=" overflow-x-hidden">
            <el-dropdown-item @click="openMessageBox('second')">
                <div  class="h-[30px]">
                    <el-badge :value="12" :max="99" class="item">
                        <div >回复我的</div>
                        </el-badge>
                </div>
            </el-dropdown-item>
            <el-dropdown-item @click="openMessageBox('third')">
                <div class="h-[30px]">
                    <el-badge :value="12" :max="99" class="item">
                        <div >系统通知</div>
                    </el-badge>
                </div>
            
                </el-dropdown-item>
            <el-dropdown-item @click="openMessageBox('four')">
                <div class="h-[30px]">
                <el-badge :value="12" :max="99" class="item">
                        <div >评论和@</div>
            </el-badge>
        </div>

            </el-dropdown-item>
            <el-dropdown-item @click="openMessageBox('five')">
                <div class="h-[30px]">
                <el-badge :value="12" :max="99" class="item">
                    <div >新增关注</div>
            </el-badge>
        </div>

            </el-dropdown-item>
            <el-dropdown-item @click="openMessageBox('six')">
                <div class="h-[30px]">
                <el-badge :value="12" :max="99" class="item">
                    <div >收到的赞</div>
            </el-badge>
        </div>

            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>

</template>

<script setup>
import Email from '@/icon/email.vue';
import { useMessageStore } from '@/stores/message';

const messageStore= useMessageStore()
function openMessageBox(index){
    messageStore.setIsMessageActive(true)
    messageStore.setMessageIndex(index)
}
</script>

<style lang="scss" scoped>

</style>