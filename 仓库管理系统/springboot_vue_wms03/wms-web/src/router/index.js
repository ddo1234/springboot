import VueRouter from 'vue-router';
//定义路由组件
//两个一级路由,七个二级路由
const routes = [
    {
        path: '/',
        name: 'login',
        component: () => import('../components/Login')
    },
    {
        path: '/index',
        name: 'index',
        component: () => import('../components/index'),
        children: [
            {
                path: '/Home',
                name: 'home',
                meta: {
                    title: '首页'
                },
                component: () => import('../components/Home')
            },

            /*{
                path:'/Admin',
                name:'admin',
                meta:{
                    title:'管理员管理'
                },
                component:()=>import('../components/admin/AdminManage.vue')
            },
            {
                path:'/User',
                name:'user',
                meta:{
                    title:'用户管理'
                },
                component:()=>import('../components/user/UserManage.vue')
            },*/
        ]
    }
]

//优化点1:由于使用了history模式,没有用hashmap模式
const router = new VueRouter({
    mode: 'hash',
    // base: process.env.BASE_URL,  // 如果使用history模式，必须设置base参数
    routes
});
//因为to属性会渲染为href属性带#
//错误处理，解决1
export function resetRouter() {
    router.matcher = new VueRouter({
        mode: 'hash',
        routes: []
    }).matcher
}

//解决2
const VueRouterPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(to) {
    return VueRouterPush.call(this, to).catch(err => err)
}
export default router;