<template>
    <!--有两个节点要用div包着，否则报错    -->
    <div class="Header-div">
        <div style="font-size: 20px;cursor: pointer">
            <i :class="icon" @click="collapse"
               style="color: rgb(191, 203, 217);margin-left: 8px ;cursor: pointer;"> </i>
        </div>
        <!--面包屑-->
        <div class="tabTop">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item v-for="breadCrumbItem in breadCrumbList" :key="breadCrumbItem.path">
                    {{breadCrumbItem.meta.title}}
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <!--        <div class="user_logo">-->
        <!--            <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>-->
        <!--        </div>-->
        <el-dropdown>
            <!--设置按钮的位置 -->
            <!--{{user.name}}-->
            <i class="el-icon-arrow-down" style="margin: 5px"></i><span>{{user.name}}</span>
            <el-dropdown-menu slot="dropdown">
                <!--退出登录和个人中心-->
                <el-dropdown-item @click.native="toUser">个人中心</el-dropdown-item>
                <el-dropdown-item @click.native="logOut">退出登录</el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
        <!--用户头像，未来可以升级用户自定义头像-->
        <div class="user_logo">
            <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
        </div>
    </div>
</template>

<script>
    export default {
        // eslint-disable-next-line vue/multi-word-component-names
        computed: {
            breadCrumbList() {
                return this.$route.matched;
            }
        },
        mounted() {
            console.log(this.$route);
        },
        name: "Header",
        data() {
            return {
                user: JSON.parse(sessionStorage.getItem('CurUser'))
            }
        },
        props: {
            icon: String
        },
        methods: {
            toUser() {
                console.log("to_user")
                this.$router.push("/imformation")

            }, logOut() {

                this.$confirm('您确定要退出登录吗？', '提示', {
                    confirmButtonText: '确定',  //确认按钮的文字显示
                    type: 'warning',
                    center: true, //文字居中显示
                    // showCancelButton: false, //不显示取消按钮
                    // showClose: false, //是否显示右上角的x
                    // closeOnClickModal: false, //是否可以点击空白处关闭弹窗
                })
                    .then(() => {
                        this.$message({
                            type: 'success',
                            message: '退出登录成功'
                        })
                        console.log("quit")
                        this.$router.push("/")
                        sessionStorage.clear();
                    })
                    .catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消退出登录'
                        })
                    });
            },
            collapse() {
                //传递值给父组件
                this.$emit('doCollapse')
            }
        },
        created() {
            this.$router.push("/Home")
        }
    }
</script>
<style scoped>
    .Header-div {
        /*position: fixed;*/
        /*top: 0;*/
        /*left: 60px;*/
        /*width: 100%;*/
        /*z-index: 2001;*/
        /*overflow: hidden;*/
        height: 56px;
        display: flex;
        line-height: 60px;
        background-color: rgb(40, 67, 94);
        padding: 0;
    }

    .el-dropdown {
        margin-right: 0px;
        color: rgb(191, 203, 217);
        right: 5px;
        position: absolute;

    }

    .tabTop {
        margin-top: 22px;
        position: relative;
    }

    /* 不被选中时的颜色 */
    .el-breadcrumb ::v-deep .el-breadcrumb__inner {
        color: #d9bb95 !important;
        font-weight: 400 !important;
    }

    /* 被选中时的颜色 */
    .el-breadcrumb__item:last-child ::v-deep .el-breadcrumb__inner {
        color: rgb(191, 203, 217) !important;
        font-weight: 800 !important;
    }

    .user_logo {
        position: absolute;
        margin-left: 1222px;
        margin-top: 10px;
    }
</style>