<template>
    <el-menu
            background-color="#28435e"
            text-color="#fff"
            active-text-color="#ffd04b"
            style="height: 100%;"
            default-active="/home"
            :collapse="isCollapse"
            :collapse-transition="false"
            router>
        <!--点击伸缩图标->提交->父组件->改变->aside子组件（collapse）-->
        <el-menu-item style="background-color: rgb(40, 67, 94)">
            <!--            <i class="el-icon-s-home"></i>-->
            <!--            <span class="titleBlock" style="color: rgb(191, 203, 217)">{{$t('homeName')}}</span>-->
            <span class="titleBlock" style="color: rgb(191, 203, 217);"><img src="../assets/shop2.png" class="logo"
                                                                             style="height: 29px;">
                </span>
        </el-menu-item>

        <el-menu-item index="/Home">
            <i class="iconfont icon-shouye1" style="font-size: 20px;"></i>
            <!--            <span style="color: rgb(191, 203, 217) ;margin-left: 5px" slot="title">{{$t('homePage')}}</span>-->
            <span style="color: rgb(191, 203, 217) ;margin-left: 5px" slot="title">首页</span>
        </el-menu-item>

        <el-menu-item :index="'/'+item.menuclick" v-for="(item,i) in menu" :key="i">
            <i :class="item.menuicon" style="font-size: 20px;"></i>
            <span slot="title" style="color: rgb(191, 203, 217);margin-left: 5px">{{item.menuname}} </span>
        </el-menu-item>

        <!--        <el-menu-item index="/Admin">-->
        <!--            <i class="iconfont icon-guanliyuan_guanliyuanliebiao" style="font-size: 20px;"></i>-->
        <!--            <span style="color: rgb(191, 203, 217) ;margin-left: 5px"  slot="title">管理员管理</span>-->
        <!--        </el-menu-item>-->

        <!--        <el-menu-item index="/User">-->
        <!--            <i class="iconfont icon-yonghuguanli1" style="font-size: 20px;"></i>-->
        <!--            <span style="color: rgb(191, 203, 217) ;margin-left: 5px" slot="title">用户管理</span>-->
        <!--        </el-menu-item>-->

        <!--        <el-menu-item index="">-->
        <!--            <i class="iconfont icon-cangku_kucun_o" style="font-size: 20px;"></i>-->
        <!--            <span style="color: rgb(191, 203, 217) ;margin-left: 5px"  slot="title">仓库管理</span>-->
        <!--        </el-menu-item>-->

        <!--        <el-menu-item index="">-->
        <!--            <i class="iconfont icon-huowudui" style="font-size: 20px;"></i>-->
        <!--            <span style="color: rgb(191, 203, 217) ;margin-left: 5px"  slot="title">物品分类管理</span>-->
        <!--        </el-menu-item>-->

        <!--        <el-menu-item index="">-->
        <!--            <i class="iconfont icon-icon-goods" style="font-size: 20px;"></i>-->
        <!--            <span style="color: rgb(191, 203, 217) ;margin-left: 5px"  slot="title">物品管理</span>-->
        <!--        </el-menu-item>-->

        <!--        <el-menu-item index="">-->
        <!--            <i class="iconfont icon-jiluguanli" style="font-size: 20px;"></i>-->
        <!--            <span style="color: rgb(191, 203, 217) ;margin-left: 5px"  slot="title">记录管理</span>-->
        <!--        </el-menu-item>-->
    </el-menu>
</template>

<script>
    export default {
        name: "Aside",
        data() {
            return {}
        },
        computed: {
            "menu": {
                get() {
                    return this.$store.state.menu
                }
            }
        },
        props: {
            isCollapse: Boolean
        }
    }
</script>

<style scoped>
    .titleBlock {
        margin-left: -5px;
    }

    .logo {
        margin-top: -7px;
        padding: 1px;
    }

    .el-menu-item >>> is-active[data-v-29a55538] {
        color: #100a03;
    }

</style>