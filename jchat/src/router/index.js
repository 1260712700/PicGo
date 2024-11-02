import { createRouter, createWebHistory } from 'vue-router'
import {constantRouter} from '@/router/routers.js'
let router = createRouter({
  // 路由模式 History
  history: createWebHistory(),
  routes: constantRouter
})
router.beforeEach((to) => {
  const { title } = to.meta;
  const defaultTitle = 'Default Title';
  document.title = title || defaultTitle
})
export default router
