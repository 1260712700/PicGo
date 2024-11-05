<template>
    <div class="container px-[20px]  text-center overflow-hidden  h-full w-full">
        <div class="title w-full mt-[150px] text-2xl text-center">
            登录
        </div>
        <div class="form mt-[50px] w-full">
            <el-form :model="form" :rules="rule" ref="formRef">
                <el-form-item prop="username">
                  <el-input  @keydown.enter="userLogin()" v-model="form.username" maxlength="20" type="text" placeholder="用户名/邮箱">
                    <template #prefix>
                      <el-icon>
                        <User/>
                      </el-icon>
                    </template>
                  </el-input>
                </el-form-item>
                <el-form-item prop="password">
                  <el-input @keydown.enter="userLogin()" v-model="form.password" type="password" maxlength="20" placeholder="密码">
                    <template #prefix>
                      <el-icon>
                        <Lock/>
                      </el-icon>
                    </template>
                  </el-input>
                </el-form-item>
                <el-row>
                  <el-col :span="12" style="text-align: left">
                    <el-form-item prop="remember">
                      <el-checkbox v-model="form.remember" label="记住我"></el-checkbox>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12" style="text-align: right">
                    <el-link @click="$router.push('/reset')">忘记密码</el-link>
                  </el-col>
                </el-row>
              </el-form>
        </div>
        <div style="margin-top: 30px">
            <el-button style="width: 270px" type="success" plain @click="userLogin()">立即登录</el-button>
          </div>
          <el-divider>
            <span style="font-size: 13px;color: grey">没有账号</span>
          </el-divider>
          <div>
            <el-button @click="$router.push('register')" style="width: 270px" type="danger" plain>立即注册</el-button>
          </div>
    </div>
</template>

<script setup>

import { GET_TOKEN, SET_TOKEN } from '@/api/auth';
import { login } from '@/api/user';
import { useUserStore } from '@/stores/user';

import { ElMessage } from 'element-plus';
import { onMounted, reactive, ref } from 'vue';
import { useRouter } from 'vue-router';
onMounted(()=>{
init()
})
function  init(){

}
const form = reactive({
  username: '',
  password: '',
  remember: false
})

const rule = {
  username: [
    {required: true, message: '请输人用户名'}
  ],
  password: [
    {required: true, message: '请输入密码'}
  ]
}
const userStore=useUserStore()

const router= useRouter()
const formRef=ref()
function userLogin(){
    formRef.value.validate((valid) => {
    if (valid) {
      login(form).then(res=>{
        checkIsSuccess(res)
        
      }).catch(err=>{
        ElMessage.error(err.msg)
      })
        }
      }
    )}
function checkIsSuccess(res){
  if(res.code!==200){
    ElMessage.error(res.msg)
    return;
  }
  SET_TOKEN(res.data.token, res.data.expire, form.remember)
  console.log(res);
     router.push('/')
     userStore.getInfo()
}
</script>

<style lang="scss" scoped>

</style>