import '@/styles/index.css'
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import ElementPlus from 'element-plus'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
// svg插件需要配置代码
import App from './App.vue'
import router from './router'
// 引入全局注册组件
import globalComponent from '@/components/index.js'

const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
  }
app.use(createPinia())
app.use(router)
app.use(ElementPlus,{
    locale: zhCn,
  })
// app.use(globalComponent)

app.mount('#app')
