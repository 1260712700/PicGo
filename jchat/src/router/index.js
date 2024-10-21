import { createRouter, createWebHistory } from 'vue-router'
import {constantRouter} from '@/router/routers.js'
let router = createRouter({
  // 路由模式 History
  history: createWebHistory(),
  routes: constantRouter
})

export default router
