import { defineStore } from "pinia";
import {  ref } from "vue";
export const useSettingStore= defineStore("settingStore",()=>{
    const  IS_DARK=ref(false);//是否暗黑模式
   const scrollTop=ref(0)//滑动顶部值
    const screenWidth=ref(window.innerWidth)//屏幕宽度
    const screenHeight=ref(window.innerHeight)//屏幕高度
   const mouseX=ref(0)//鼠标横轴位置
   const mouseY=ref(0)//鼠标纵轴
   const boxRect=ref('')//盒子信息
   function setBoxRect(box){
    boxRect.value=box
   }
   function getBoxRect(box){
    return boxRect.value
   }
    function darkSwitch(){
        IS_DARK.value=!IS_DARK.value
    }
  
   function  screenWidthSetter(val){
    screenWidth.value=val
}  
function screenWidthGetter(){
    return  screenWidth
}

function  screenHeightSetter(val){
    screenHeight.value=val
}  

function screenHeightGetter(){
    return  screenHeight
}


   function mouseXGetter(){
    return  mouseX
}
function  mouseXSetter(val){
    mouseX.value=val
}  

function mouseYGetter(){
    return  mouseY
}
function  mouseYSetter(val){
    mouseY.value=val
}
   function scrollTopGetter(){
    return  scrollTop
}
function  scrollTopSetter(val){
    scrollTop.value=val
}

    return {
        screenWidthSetter,screenWidthGetter,
        screenHeightSetter,screenHeightGetter,
        mouseXGetter,mouseXSetter,
        mouseYGetter,mouseYSetter,
        scrollTopGetter,scrollTopSetter,
        IS_DARK,darkSwitch,
        setBoxRect,getBoxRect
    }
})