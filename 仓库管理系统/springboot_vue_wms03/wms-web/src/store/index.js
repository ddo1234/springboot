import vue from 'vue'
import Vuex from 'vuex'
import router, {resetRouter} from "../router";
//解决vuex可持续化导致刷新空白问题
import createPersistedState from "vuex-persistedstate"

vue.use(Vuex)

function addNewRoute(menuList) {
    let routes = router.options.routes
    //通过循环将所有路由找出来
    routes.forEach(routeItem => {
        //添加子路由加入存在这个路径
        if (routeItem.path == "/index") {
            //再循环添加
            menuList.forEach(menu => {
                let childRoute = {
                    path: '/' + menu.menuclick,
                    name: menu.menuname,
                    meta: {
                        title: menu.menuname,
                        keepAlive: true, // 需要被缓存
                    },
                    component: () => import('../components/' + menu.menucomponent)
                }
                routeItem.children.push(childRoute)
            })
        }
    })
    resetRouter()
    //将新的子路由添加进去
    router.addRoutes(routes)
}

export default new Vuex.Store({
    //关于刷新导致路由丢失就是，vuex的时候menu丢了，导致mutations也丢失了
    //解决方案，下载vuex可持续化插件，使其把数据存起来，刷新后取出来
    state: {
        menu: []
    },
    mutations: {
        //通过setMenu添加新的路由
        setMenu(state, menuList) {
            //前端登录界面把值丢进setMenu里面，赋值给menu
            state.menu = menuList
            //添加路由
            addNewRoute(menuList)
        },
        setRouter(state, menuList) {
            addNewRoute(menuList)
        }
    },
    getters: {
        getMenu(state) {
            return state.menu
        }
    },
    ////解决vuex可持续化导致刷新空白问题
    plugins: [createPersistedState()]
})