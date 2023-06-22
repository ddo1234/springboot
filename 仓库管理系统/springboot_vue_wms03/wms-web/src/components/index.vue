<template>
    <el-container style="height: 100%; border: 0px solid #eeeeee;">
        <!--侧边栏-->
        <el-aside width="aside_width" style="background-color:#3f65e0;height: 142vh">
            <!-- ;margin-left: -1px-->
            <Aside :isCollapse="isCollapse"></Aside>
        </el-aside>
        <!--头部-->
        <el-container style="height: 100%;">
            <el-header style="text-align: right; font-size: 12px; height: 100%;border-bottom: darkgray 0px solid">
                <Header @doCollapse="doCollapse" :icon="icon"></Header>
            </el-header>
            <!--主体-->
            <el-main style="height: 100%; width: 100%">
                <!-- <Main></Main>-->
                <!-- 通过router-view占位符，按路由规则匹配组件，这里匹配的是每个页面的main-->
                <router-view/>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
    import Aside from "@/components/Aside";
    import Header from "@/components/Header";

    export default {
        // eslint-disable-next-line vue/multi-word-component-names
        name: "index",
        components: {Aside, Header},
        data() {
            return {
                isCollapse: false,
                aside_width: '200px',
                icon: 'el-icon-s-fold'
            }
        },
        methods: {
            doCollapse() {
                console.log(111)
                this.isCollapse = !this.isCollapse
                //展开
                if (!this.isCollapse) {
                    this.aside_width = '200px'
                    this.icon = 'el-icon-s-fold'

                    //关闭
                } else {
                    this.aside_width = '64px'
                    this.icon = 'el-icon-s-unfold'
                }
            }
        }
    }
</script>

<style scoped>
    .el-header {
        /*background-color: #B3C0D1;*/
        color: #333;
        line-height: 60px;
    }

    .el-main {
        padding: 0px;
        margin: 0px;
        overflow-y: hidden;
    }

    .el-aside {
        height: 130.9vh;
    }
</style>