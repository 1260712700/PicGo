import HeaderBox from "./layout/header/index.vue";
import MainBox from "./layout/main/index.vue";

const globalComponents = [HeaderBox,MainBox];

export default {
  install (Vue) {
    globalComponents.forEach(component => {
      // 这里用的是组件里面的name
      //如果引入的组件没有写name，会报错 ： vue.esm.js:100 Uncaught TypeError: Cannot read properties of undefined (reading 'toLowerCase')
      Vue.component(component.name, component);
    });
  }
}