<template>
    <div style="text-align: center;background-color: #f1f1f3;height: 100vh;padding: 0px;margin: 0px;overflow-x: hidden;overflow-y: hidden">
        <h1 style="font-size: 50px;">{{'Walmat，欢迎你！'}}</h1>
        <!--显示当前时间 -->
        <DateUtils></DateUtils>
        <el-card>
            <el-descriptions class="margin-top" title="个人中心" :column="4" direction="vertical">
                <el-descriptions-item label="用户名" style="font-weight: 700">{{user.name}}</el-descriptions-item>
                <el-descriptions-item label="手机号">{{user.phone}}</el-descriptions-item>
                <el-descriptions-item label="性别">
                    <el-tag :type="user.sex === '1' ? 'primary' : 'danger'" disable-transitions>
                        <i :class="user.sex==1?'el-icon-male':'el-icon-female'"></i>{{user.sex==1?"男":"女"}}
                    </el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="角色">
                    <el-tag type="success" disable-transitions>{{user.roleId==0?"超级管理员":(user.roleId==1?"管理员":"用户")}}
                    </el-tag>
                </el-descriptions-item>
            </el-descriptions>
        </el-card>
        <!--统计数值-->
        <div>
            <el-row :gutter="20">
                <el-col :span="14">
                    <div style="width: 100%; display: inline-block; ">
                    </div>
                    <!--答辩倒计时-->
                    <div style="width: 70%; display: inline-block; margin-top: 50px; ">
                        <el-statistic @finish="hilarity" :value="deadline3" time-indices title="答辩倒计时：">
                            <template slot="suffix">
                                <el-button type="primary " size="small" @click="add">add 10 second</el-button>
                            </template>
                        </el-statistic>
                    </div>
                    <!--日志-->
                </el-col>
                <el-col :span="10">
                    <el-card shadow="hover" style="width: 100%;margin-right: -4px;margin-top: 20px;">
                        <div slot="header" class="clearfix">
                            <span>系统日志</span>
                            <el-button style="float: right; padding: 3px 0" type="text" @click="clickFn">新增</el-button>
                        </div>
                        <div style="font-size: 18px;text-align: left">12-17 修复了vuex持久化，刷新导致menu丢失问题</div>
                        <div style="font-size: 18px;text-align: left;">12-17 更新了面包屑</div>
                        <div style="font-size: 18px;text-align: left">12-16 国际化未实现，库文件版本冲突</div>
                        <div style="font-size: 18px;text-align: left">12-16 修改了系统图标</div>
                        <div style="font-size: 18px;text-align: left">12-13 系统测试，发现刷新menu丢失</div>
                        <div style="font-size: 18px;text-align: left">12-12 实现了各个模块整合</div>
                        <div style="margin-top: 40px;"></div>
                    </el-card>
                </el-col>
            </el-row>

        </div>
        <!-- footer-->
        <div class="pfd cf">
            <div class="w1200">
                <div class="logopfd">
                    <img src="../assets/logoft.png">
                </div>
                <div class="left-pfd cf">
                    <div class="grp01-pfd fl">
                        <div class="title">线上购买：</div>
                        <div class="app">
                            <img src="../assets/img/1629352748611736.jpg">
                            沃尔玛
                            <br>
                            网上超市
                        </div>
                        <div class="app">
                            <img src="../assets/img/1592133238780931.png">
                            山姆APP
                        </div>
                    </div>
                    <div class="grp02-pfd fl">
                        <div class="title newtitle01" style="margin-left: -405px;">
                            关注我们：
                        </div>
                        <div class="share flex">
                            <div class="new01">
                                <img src="../assets/img/ewm_1_ft.png">
                                沃尔玛中国
                                <br>
                                微信公众号
                            </div>
                            <div class="new01">
                                <img src="../assets/img/ewm_2_ft.png">
                                沃尔玛招聘
                                <br>
                                微信公众号
                            </div>
                            <div class="new01">
                                <img src="../assets/img/ewm_3_ft.png">
                                沃尔玛大卖场
                                <br>
                                微信公众号
                            </div>
                            <div class="new01">
                                <img src="../assets/img/ewm_4_ft.png">
                                山姆会员商店
                                <br>
                                微信公众号
                            </div>
                        </div>
                    </div>
                </div>
                <div class="right-pfd cf">
                    <div class="others">
                        <a target="_blank" href="https://corporate.walmart.com"
                           style="  color:white;   margin-left: -1163px;}">
                            沃尔玛全球官网
                        </a>
                    </div>
                </div>
                <div style="text-align: center;">
                    <div class="copyright___32N-3">© 1996-2022 沃尔玛（中国）投资有限公司 版权所有</div>
                    <a href="https://beian.miit.gov.cn/#/Integrated/recordQuery" target="_blank" style="color: white">粤ICP备10219734号</a>
                    <img src="../assets/img/gongan.png">
                    <a href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=44030402004694"
                       target="_blank" style="color: white">
                        粤公网安备 44030402004694号
                    </a>
                </div>
            </div>
        </div>

    </div>
</template>
<!--动态跳转=动态路由+动态菜单-->
<script>
    import information from "./information";
    import DateUtils from "./DateUtils";

    export default {

        name: "information" && 'card_calendar',
        components: {DateUtils},
        data() {
            return {
                format: 'HH小时:mm:ss:SSS',
                deadline: Date.now() + 1000 * 60 * 60 * 24 * 2,
                deadline3: Date.now() + 1000 * 60 * 10,
            }
        },
        computed: {},
        methods: {
            init() {
                this.user = JSON.parse(sessionStorage.getItem('CurUser'))
            },
            hilarity() {
                this.$notify({
                    title: '提示',
                    message: '答辩结束，恭喜通关！',
                    duration: 0
                });
            },
            clickFn() {
                this.$refs.statistic.suspend(this.stop)
                this.stop = !this.stop
            },
            add() {
                this.deadline3 = this.deadline3 + 1000 * 10
            }
        },
        created() {
            this.init()
        },
    };
</script>

<style scoped>
    .el-descriptions {
        width: 90%;
        height: 100px;
        margin: 0 auto;
        text-align: center;
    }

    .home {
        height: 90vh;
    }

    .poetry {
        text-align: center;
    }

    .el-statistic {
        margin-left: -61px;
    }

    .stat {
        margin-left: 490px;
        margin-top: -60px;
    }

    .poetry_part {
        margin-left: 127px;
    }

    .el-descriptions__body {
        border-radius: 3px;
    }

    .el-statistic >>> .head {
        font-size: 30px;
    }

    .el-descriptions__body >>> .el-descriptions__table >>> .el-descriptions-item__cell {
        font-weight: 700;
        padding: 9px;
    }

    /*    统计数值*/
    .like {
        cursor: pointer;
        font-size: 25px;
        display: inline-block;
    }

    .el-card {
        margin-left: -63px;
    }

    /*    底部*/
    .pfd {
        height: 472px;
        background: rgb(40, 67, 94);
        color: #fff;
        margin-top: 20px;
        position: absolute;
    }

    .cf {
        clear: both;
    }

    .w1200 {
        width: 1326px;
        margin: 0 auto;
    }

    .logopfd {
        padding-top: 36px;
        margin-bottom: 30px;
    }

    .logopfd img {
        width: 548px;
    }

    img {
        display: inline-block;
        vertical-align: bottom;
        max-width: 100%;
    }

    .left-pfd {
        width: 100%;
        height: 185px;
        border-bottom: 1px solid #fff;
        border-bottom-width: 1px;
        border-bottom-style: solid;
        border-bottom-color: rgb(255, 255, 255);
    }

    .grp01-pfd {
        text-align: left;
        width: 356px;
    }

    .fl {
        float: left;
    }

    .grp01-pfd .title, .grp02-pfd .title {
        height: 42px;
    }

    .grp01-pfd .title, .grp02-pfd .title {
        letter-spacing: 2px;
        font-size: 18px;
    }

    .grp01-pfd .app {
        width: 100px;
        text-align: center;
        line-height: 24px;
        font-size: 18px;
        float: left;
        margin-right: 35px;
    }

    .grp01-pfd .app img {
        margin-bottom: 10px;
        width: 80px;
        display: block;
        margin: 0 auto 10px auto;
    }

    .fl {
        float: left;
    }

    .newtitle01 {
        margin-left: 33px;
    }

    .grp02-pfd .share {
        float: left;
    }

    .flex {
        display: flex;
    }

    .grp02-pfd .share .new01 {
        width: 140px;
        text-align: center;
        line-height: 22px;
    }

    .grp02-pfd .share .new01 img {
        display: block;
        margin: 0 auto 10px auto;
        width: 80px;
    }

    .others {
        padding-top: 37px;
        padding-left: 40px;
        background: url(../assets/img/qiu.png) no-repeat left 30px;
        padding-bottom: 10px;
        margin-left: 10px;
        font-size: 16px;
    }

    .others a {
        margin: 0 3px;
        display: inline-block;
        color: #fff;
    }

    .pfd a {
        color: #fff;
    }

    a {
        text-decoration: none;
        color: #000;
    }

    a:-webkit-any-link {
        color: -webkit-link;
        cursor: pointer;
        text-decoration: underline;
    }

</style>
