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
                    meta:{
                        title:"Jp-system | 上下求索的求学者"
                    },
                    children:[
                        {
                            path: 'article/:id',
                            component: () => import('@/views/home/pop/article/container/index.vue'),
                            name: 'articleDetail',
                            // 路由传参
                            props:true
                        },
                        {
                            path: 'article/publish',
                            component: () => import('@/views/article/container/publish-article/index.vue'),
                            name: 'articlePublish',
                        },
                        {
                            path: 'article/home',
                            component: () => import('@/views/article/container/home/index.vue'),
                            name: 'articleHome',
                        },
                        {
                            path: 'article/management',
                            component: () => import('@/views/article/container/management/index.vue'),
                            name: 'articleManagement',
                        }
                    ]
                },
              
            ]
        },
        {
            path: '/welcome',
            component: () => import('@/views/welcome/index.vue'),
            name: 'welcome',
            redirect: '/login',
            children: [
                {
                    path: '/login',
                    component: () => import('@/views/welcome/login/index.vue'),
                    name: 'welcome-login',
                    meta: {
                        title: '用户登录',
                }
            },
                {
                    path: '/register',
                    component: () => import('@/views/welcome/register/index.vue'),
                    name: 'welcome-register',
                    meta: {
                        title: '用户注册',
                }
            },
                {
                    path: '/reset',
                    component: () => import('@/views/welcome/reset/index.vue'),
                    name: 'welcome-reset',
                    meta: {
                        title: '用户重置密码',
                },
            }
            ]
        },
        // 测试
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