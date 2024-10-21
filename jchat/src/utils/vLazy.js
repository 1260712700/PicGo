// 图片懒加载 只加载一次
export function lazyLoading(el,callback) {
    const obServer = new IntersectionObserver(entries => {
        entries.forEach((entry) => {  
            if (entry.isIntersecting) {  
                callback();
                obServer.disconnect(); // 只加载一次  
            }  
          });  
    });
    obServer.observe(el);
};
