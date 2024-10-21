export const constantRouter = [
    {
        path: '/',
        name: 'layout',
        component: () => import('@/views/layout/index.vue'),
            children: [
                // 首页
                {
                    path: '',
                    component: () => import('@/views/home/index.vue'),
                    name: 'home',
                    children:[
                        {
                            path: 'article/:id',
                            component: () => import('@/views/article/index.vue'),
                            name: 'articleDetail',
                   
                        }
                    ]
                },
              
            ]
        },
        {
            path: '/test',
            name: 'test',
            component: () => import('@/views/test/test.vue'),
        },
    // 访问其他任何不存在的路由，重定向到首页
    {
        path: '/:pathMatch(.*)*',
        redirect: '/',
        name: 'any',
    }
]