import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import '../src/assets/global.css'
import axios from "axios"; //跨域
// import VueI18n from 'vue-i18n'
//router注册
import VueRouter from 'vue-router';
import router from './router';
import store from "./store";
//icon包
import './assets/font_wela3e87lsb/iconfont.css'

Vue.prototype.$axios = axios;
Vue.prototype.$httpUrl = 'http://localhost:8090'
Vue.config.productionTip = false
Vue.use(VueRouter);
Vue.use(ElementUI);
// Vue.use(VueI18n);
// Vue.use(ElementUI,{size:'small'});

//国际化
// const i18n = new VueI18n({
//     //实例化VueI18n对象
//     locale:'en',
//     messages: {
//         zh: {
//             loginTxt:'登录',
//             logout:'退出登录',
//             mc:'个人中心',
//             change:'改变',
//             homeName:'仓库管理系统',
//             homePage:'首页',
//         },
//         en: {
//             loginTxt:'login',
//             logout:'Logout',
//             mc:'My Center',
//             change:'change',
//             homeName:'warehouse management system',
//             homePage:'HomePage',
//         }
//     },
//     // silentTranslationWarn:false
// });
// const i18n = new  VueI18n({
//     local:'en',
//     message:{
//         zh: {
//
//         },
//         en: {
//
//         }
//     }
// })


new Vue({
    router,
    store,
    // i18n,
    render: h => h(App),
}).$mount('#app')


//修改浏览器名
router.beforeEach((to, from, next) => {
    window.document.title = to.meta.title == undefined ? '仓库系统' : to.meta.title
    if (to.meta.requireAuth) {
        let token = Cookies.get('access_token');
        let anonymous = Cookies.get('user_name');
        if (token) {
            next({
                path: '/login',
                query: {
                    redirect: to.fullPath
                }
            })

        }
    }
    next();
})

/* 路由异常错误处理，尝试解析一个异步组件时发生错误，重新渲染目标页面 */
// router.onError((error) => {
//     const pattern = /Loading chunk (\d)+ failed/g;
//     const isChunkLoadFailed = error.message.match(pattern);
//     const targetPath = router.history.pending.fullPath;
//     if(isChunkLoadFailed){
//         router.replace(targetPath);
//     }
// })