import { defineStore } from 'pinia';
import { reactive, ref } from 'vue';

export const useSearchStore = defineStore('search', () => {
    const  historyRecord=reactive(['马画藤','栀子花计划','黄磊','今天你起飞了吗?'])
    const  hotRecommend=reactive(['中国人要登月了','栀子花计划','封神第二部定档','黄磊希望大家多看戏','我国守卫女性航天飞行员','向佐回应扇巴掌行为','罗德里获2024金球奖',"网络热梗"])
    const isShowHistory=ref(false)
    function setShowHistory(state){
        isShowHistory.value=state
    }
    function getShowHistory(){
        return isShowHistory.value
    }
    function pushHistoryRecord(val){
        historyRecord.push(val)
    }
    function getHistoryRecord(){
        return historyRecord
    }
    function clearHistoryRecord(){
      historyRecord.length=0
    }
    function setHotRecommend(val){
        hotRecommend.values=val
    }
    function clearHotRecommend(){
        hotRecommend.length=0
    }
    function getHotRecommend(){
        return hotRecommend
    }
    return {
        pushHistoryRecord,getHistoryRecord,clearHistoryRecord,
        setShowHistory,getShowHistory,
        setHotRecommend,clearHotRecommend,getHotRecommend
    }
});
