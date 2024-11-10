// 获取时分
export  function getTime(){
    const d= new Date()
    const m=d.getMinutes()
    // const s=d.getSeconds()
    const hour=d.getHours();
    const minute = m >= 10 ? m : "0" + m;
// const second = s >= 10 ? s : "0" + s;
return hour+":"+minute
}
export  function getDate(){
    const d= new Date()
    const Y=d.getFullYear()
    const M=d.getMonth()
    const D=d.getDate()
    const h = d.getHours();
const m = d.getMinutes();
const s = d.getSeconds();
const hour = h >= 10 ? h : "0" + h;
const minute = m >= 10 ? m : "0" + m;
const second = s >= 10 ? s : "0" + s;
return Y+"-"+M+"-"+D+"  "+hour + ":" + minute + ":" + second;
}
export function handleDateTime(dateTime) {
    const date = new Date(dateTime); // 将datetime字符串转为Date对象
  
    const year = date.getFullYear(); // 获取年份
    const month = String(date.getMonth() + 1).padStart(2, '0'); // 获取月份，+1是因为getMonth()返回的是0-11，需加1
    const day = String(date.getDate()).padStart(2, '0'); // 获取日期
    const hours = String(date.getHours()).padStart(2, '0'); // 获取小时
    const minutes = String(date.getMinutes()).padStart(2, '0'); // 获取分钟
    const seconds = String(date.getSeconds()).padStart(2, '0'); // 获取秒钟
    // 返回格式化后的日期字符串
    return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
  }
// const dom = d.getDate();
// const dow = d.getDay();
// const month = m >= 10 ? m : "0" + m;
// const day = dom >= 10 ? dom : "0" + dom;

// return d.getFullYear() + "-" + month + "-" + day + " 星期" + dayList[dow ];
// };
// const handleTime = (d) => {
// const h = d.getHours();
// const m = d.getMinutes();
// const s = d.getSeconds();
// const hour = h >= 10 ? h : "0" + h;
// const minute = m >= 10 ? m : "0" + m;
// const second = s >= 10 ? s : "0" + s;
// return hour + ":" + minute + ":" + second;