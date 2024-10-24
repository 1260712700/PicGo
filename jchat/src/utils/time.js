
export default function getTime(){
    const d= new Date()
    const m=d.getMinutes()
    const s=d.getSeconds()
    const minute = m >= 10 ? m : "0" + m;
const second = s >= 10 ? s : "0" + s;
return minute+":"+second
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