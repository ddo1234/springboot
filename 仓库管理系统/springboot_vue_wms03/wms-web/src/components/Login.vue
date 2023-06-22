<template>
    <div class="login-wrapper">
        <div :class="['login-container', type == 'login' ? 'active' : '']">
            <div class="switch-wrapper row aCenter jCenter">
                <div class="btn-wrapper row aCenter jCenter">
                    <div
                            v-if="type == 'login'"
                            class="txt row aCenter jCenter"
                            @click="type = 'register'"
                    >
                        去注册
                    </div>
                    <div v-else class="txt row aCenter jCenter" @click="type = 'login'">
                        去登录
                    </div>
                </div>
            </div>
            <div :class="['outerBox', type == 'login' ? 'active' : '']">
                <div class="container row aCenter jCenter">
                    <div class="login" v-show="type == 'login'">
                        <el-form
                                :model="loginUser"
                                status-icon
                                :rules="loginRules"
                                ref="loginUser"
                        >
                            <div class="title">登录</div>
                            <el-form-item prop="userName">
                                <el-input
                                        type="text"
                                        prefix-icon="el-icon-user"
                                        placeholder="请输入账号 / 手机号 / 邮箱"
                                        v-model="loginUser.userName"
                                />
                            </el-form-item>
                            <el-form-item prop="password">
                                <el-input
                                        type="password"
                                        prefix-icon="el-icon-view"
                                        placeholder="请输入密码"
                                        v-model="loginUser.password"
                                        @keyup.enter.native="login"
                                />
                            </el-form-item>


                            <!--验证码-->
                            <!--                            <el-form-item prop="checkCode" class="checkCode">-->
                            <!--                                <el-input-->
                            <!--                                        size="small"-->
                            <!--                                        clearable-->
                            <!--                                        v-model="loginUser.checkCode"-->
                            <!--                                        placeholder="请输入验证码"-->
                            <!--                                />-->
                            <!--                                <el-button-->
                            <!--                                        @click="sendVerificationCode"-->
                            <!--                                        size="mini"-->
                            <!--                                        type="primary"-->
                            <!--                                        style="margin-left: 10px"-->
                            <!--                                        v-if="show"-->
                            <!--                                        class="send"-->
                            <!--                                >发送验证码</el-button>-->

                            <!--                                <el-button-->
                            <!--                                        size="mini"-->
                            <!--                                        type="primary"-->
                            <!--                                        style="margin-left: 10px"-->
                            <!--                                        v-if="!show"-->
                            <!--                                        disabled-->
                            <!--                                        class="chongfa"-->
                            <!--                                >{{count}}秒后重发</el-button>-->
                            <!--                            </el-form-item>-->

                            <span>验证码：</span>
                            <el-input
                                    style="width: 180px"
                                    type="text"
                                    v-model="inputCode"
                                    placeholder="请输入您的验证码"
                            />
                            <div @click="refreshCode()">
                                <!--验证码组件-->
                                <securityCode :identifyCode="identifyCode"></securityCode>
                            </div>


                            <el-form-item>
                                <el-button type="primary" class="btn-login" @click="getSubmitData"
                                >登录
                                </el-button
                                >
                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary" class="btn-forget" @click="update(row)"
                                >忘记密码
                                </el-button
                                >
                            </el-form-item>


                        </el-form>
                    </div>
                    <div class="register" v-show="type == 'register'">
                        <el-form
                                :model="registerUser"
                                status-icon
                                :rules="registerRules"
                                ref="registerUser"
                        >
                            <div class="title">注册</div>
                            <el-form-item prop="userName">
                                <el-input
                                        type="text"
                                        prefix-icon="el-icon-user"
                                        placeholder="请输入用户名"
                                        v-model="registerUser.userName"

                                />
                            </el-form-item>
                            <el-form-item prop="phone">
                                <el-input
                                        type="text"
                                        prefix-icon="el-icon-phone"
                                        placeholder="请输入手机号码"
                                        v-model="registerUser.phone"
                                />
                            </el-form-item>
                            <el-form-item prop="password">
                                <el-input
                                        type="password"
                                        prefix-icon="el-icon-view"
                                        placeholder="请输入密码"
                                        v-model="registerUser.password"

                                />
                            </el-form-item>
                            <el-form-item prop="name">
                                <el-input
                                        type="text"
                                        prefix-icon="el-icon-view"
                                        placeholder="请输入姓名"
                                        v-model="registerUser.name"

                                />
                            </el-form-item>
                            <el-form-item prop="age">
                                <el-input
                                        type="text"
                                        prefix-icon="el-icon-view"
                                        placeholder="请输入年龄"
                                        v-model="registerUser.age"

                                />
                            </el-form-item>
                            <el-form-item label="性别" prop="sex">
                                <el-radio-group v-model="registerUser.sex">
                                    <el-radio label="0">男</el-radio>
                                    <el-radio label="1">女</el-radio>
                                </el-radio-group>
                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary" class="btn-login" @click="register"
                                >确认注册
                                </el-button
                                >
                            </el-form-item>
                        </el-form>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <el-dialog
                    title="是不是忘记了密码？请您重置密码！"
                    :visible.sync="centerDialogVisible"
                    width="30%"
                    center
            >
                <!--      修改表单-->
                <el-form
                        ref="form"
                        :model="form"
                        :rules="rules"
                        label-width="80px"
                        size="small"
                        style="text-align: left"
                >
                    <!--                    <el-form-item label="用户名" prop="userName">-->
                    <!--                        <el-col :span="20">-->
                    <!--                            <el-input v-model="form.userName" ></el-input>-->
                    <!--                        </el-col>-->
                    <!--                    </el-form-item>-->

                    <el-form-item label="姓名" prop="name">
                        <el-col :span="20">
                            <el-input v-model="form.name" clearable="true"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="电话" prop="phone">
                        <el-col :span="20">
                            <el-input v-model="form.phone" clearable="true"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="新密码" prop="password">
                        <el-col :span="20">
                            <el-input v-model="form.password" clearable="true"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="性别" prop="sex">
                        <el-radio-group v-model="form.sex">
                            <el-radio label="1">男</el-radio>
                            <el-radio label="0">女</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item label="角色" prop="roleId">
                        <el-radio-group v-model="form.roleId">
                            <el-radio label="0">超级管理员</el-radio>
                            <el-radio label="1">管理员</el-radio>
                            <el-radio label="2">用户</el-radio>
                        </el-radio-group>
                    </el-form-item>
                </el-form>

                <span slot="footer" class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click=doUpdate>确 定</el-button>
      </span>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    import securityCode from "./securityCode";

    export default {
        name: "Login",
        components: {
            securityCode,
        },
        data() {
            let checkDuplicate = (rule, value, callback) => {
                this.$axios
                    .get(
                        this.$httpUrl + "/user/findByUserName?userName=" + this.registerUser.userName
                    )
                    .then((res) => res.data)
                    .then((res) => {
                        if (res.code != 200) {
                            callback();
                        } else {
                            callback(new Error("账号已经存在"));
                        }
                    });
            };

            return {

                identifyCodeType: "1234567890", //定义验证类型 1.数字 2.字母
                identifyCode: "",
                inputCode: "", //text框输入的验证码


                confirm_disabled: false,
                type: "login", // 类型判断
                loginUser: {
                    //登录表单
                    userName: "",
                    password: "",
                    checkCode: ""
                },
                loginRules: {
                    userName: [
                        {required: true, message: "请输入用户名", trigger: "blur"},
                    ],
                    password: [{required: true, message: "请输入密码", trigger: "blur"}],
                    checkCode: [
                        {required: true, message: "验证码不能为空", trigger: "blur"},
                        {max: 4, message: "验证码为4位数字", trigger: "blur"}
                    ],

                },
                //与验证码有关
                show: true,
                count: "",
                timer: null,


                //忘记密码表单
                tableData: [],
                pageNum: 1,
                pageSize: 9,
                total: 0,
                userName: "",
                centerDialogVisible: false,
                form: {
                    id: '',
                    // userName: '',

                    name: '',
                    phone: '',
                    password: '',
                    age: "",
                    sex: "0",

                    roleId: "1",
                },
                rules: {
                    userName: [
                        {required: true, message: "用户名不能为空", trigger: "blur"},
                        {validator: checkDuplicate, trigger: "blur"},
                    ],
                    password: [
                        {required: true, message: "密码不能为空", trigger: "blur"},
                        {min: 3, max: 8, message: "长度在 3 到 8 个字符", trigger: "blur"},
                    ],
                    name: [{required: true, message: "姓名不能为空", trigger: "blur"}],
                    phone: [
                        {required: true, message: "手机号不能为空", trigger: "blur"},
                        {
                            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
                            message: "请输入正确的手机号码",
                            trigger: "blur",
                        },
                    ],
                    age: [{required: true, message: "年龄不能为空", trigger: "blur"}]
                },


                registerUser: {
                    //注册表单
                    userName: "",
                    name: "",
                    phone: "",
                    password: "",
                    // email: "",


                    age: "",
                    sex: 1 + "",
                    roleId: 2 + '',
                },
                registerRules: {
                    // userName: [
                    //     { required: true, message: "请输入用户名", trigger: "blur" },
                    //     { validator: checkDuplicate, trigger: "blur" }
                    // ],
                    name: [{required: true, message: "请输入姓名", trigger: "blur"}],
                    // email: [{ required: true, message: "请输入邮箱地址", trigger: "blur" }],
                    phone: [
                        {required: true, message: "请输入手机号码", trigger: "blur"},
                    ],
                    password: [{required: true, message: "请输入密码", trigger: "blur"},
                        {min: 3, max: 8, message: "长度在 3 到 8 个字符", trigger: "blur"}],

                    age: [{required: true, message: "请输入年龄", trigger: "blur"}]
                },

            };
        },

        //验证码
        mounted() {
        },

        methods: {
            //登录功能
            login() {
                this.$refs.loginUser.validate((valid) => {
                    if (valid) {
                        this.$axios
                            .post(this.$httpUrl + '/user/login', this.loginUser).then((res) => res.data)
                            .then((res) => {
                                console.log(res);
                                if (res.code == 200) {
                                    //登录成功
                                    // console.log(this.loginUser);
                                    //存储
                                    sessionStorage.setItem("CurUser", JSON.stringify(res.data.user))

                                    console.log(res.data.menu)
                                    //把menu添加到store里面的setMenu集合
                                    this.$store.commit("setMenu", res.data.menu)
                                    //跳转到主页
                                    this.$router.replace('/Index');
                                    // // 保存到session
                                } else {
                                    this.confirm_disabled = false;
                                    alert("验证失败，用户名或密码错误!")
                                }
                            });
                    } else {
                        this.confirm_disabled = false;
                        this.$message.error("校验失败");
                    }
                });
            },

            //注册功能
            register() {
                this.$refs.registerUser.validate(async (valid) => {
                    if (valid) {
                        this.$axios
                            .post(this.$httpUrl + '/user/save', this.registerUser).then((res) => res.data)
                            .then((res) => {
                                console.log(res);
                                if (res.code == 200) {
                                    //登录成功
                                    // console.log(this.loginUser);
                                    alert("注册成功，请登录");
                                    //返回登录页面
                                    this.type = "login";
                                    //添加到store里面取
                                    this.$store.commit("setMenu", res.data.menu)

                                    //存储
                                    sessionStorage.setItem("CurUser", JSON.stringify(res.data.user))
                                    // //跳转到主页
                                    // this.$router.replace('');
                                    // // 保存到session
                                    // this.$router.replace("/index");
                                } else {
                                    this.confirm_disabled = false;
                                    alert("验证失败，用户名或密码错误!")
                                }
                            });
                    } else {
                        return false;
                    }
                });
            },


            //忘记密码
            update(row) {
                // this.centerDialogVisible = true;
                let userid = prompt("请输入您的用户名:");
                this.$axios
                    .get(
                        this.$httpUrl + "/user/findByUserName?userName=" + userid
                    )
                    .then((res) => res.data)
                    .then((res) => {
                        if (res.code == 200) {

                            alert("用户名:" + userid + "\n密码:" + res.data[0].password)
                        } else {
                            alert("用户名不存在")
                        }
                    });
            },
            doUpdate() {
                this.$refs.form.validate((valid) => {
                    if (valid) {
                        this.$axios
                            .post(this.$httpUrl + "/user/update", this.form)
                            .then((res) => res.data)
                            .then((res) => {
                                console.log(res);
                                if (res.code == 200) {
                                    this.$message({
                                        message: "管理员修改成功！",
                                        type: "success",
                                    });
                                    this.loadPost();
                                    this.centerDialogVisible = false;
                                    this.resetForm();
                                    // this.refresh(); //页面刷新
                                } else this.$message.error("管理员修改失败！");
                            });
                    } else {
                        console.log("error submit!!");
                        return false;
                    }
                });
            },
            save() {
                console.log(this.form.id);
                if (this.form.id) {
                    this.doUpdate();
                } else {
                    this.doSave();
                }
            },


            //  验证码  倒计时
            sendVerificationCode() {
                let TIME_COUNT = 60;
                if (!this.timer) {
                    this.count = TIME_COUNT;
                    this.show = false;
                    this.timer = setInterval(() => {
                        if (this.count > 0 && this.count <= TIME_COUNT) {
                            this.count--;
                        } else {
                            this.show = true;
                            clearInterval(this.timer);
                            this.timer = null;
                        }
                    }, 1000);
                    this.getCode();

                }
            },
            //验证码
            getCode() {
                // axios.get("/verificationCode.json").then((res) =>{
                //     if(res.status==200){
                //         setTimeout(()=>{
                //             this.loginUser.checkCode=res.data.code;
                //             this.show=true;
                //             clearInterval(this.timer);
                //             this.timer=null;
                //         },3000)
                //     }
                // })

            },


            //验证码规则
            getSubmitData() {
                if (this.inputCode == "") {
                    this.identifyCode = ""; //输入框置空
                    alert("请输入验证码");
                    return;
                }
                if (this.identifyCode !== this.inputCode) {
                    this.inputCode = "";
                    this.refreshCode();
                    alert("请输入正确的验证码!");
                    return;
                } else {
                    this.$message({
                        message: "验证成功",
                        type: "success",
                    });
                    this.login();
                }
            },

            //验证码
            randomNum(min, max) {
                return Math.floor(Math.random() * (max - min) + min);
            },
            //初始化验证码
            refreshCode() {
                this.identifyCode = ""; //输入框置空
                this.makeCode(this.identifyCodeType, 4); //验证码长度为4
            },
            //随机切换验证码
            makeCode(o, l) {
                for (let i = 0; i < l; i++) {
                    this.identifyCode +=
                        this.identifyCodeType[
                            this.randomNum(0, this.identifyCodeType.length)
                            ];
                }
                console.log(this.identifyCode);

            }

        }

    };


</script>

<style scoped>
    .login-wrapper {
        background-color: #ededed;
        display: flex;
        width: 100vw;
        height: 100vh;
        margin: auto;
    }

    .login-container {
        background-color: #fff;
        position: relative;
        width: 1000px;
        height: 600px;
        margin: auto;
        overflow: hidden;
        border-radius: 5px;
        box-shadow: 0 0 10px 5px #ddd;
    }

    .switch-wrapper {
        position: absolute;
        z-index: 99;
        left: 0;
        overflow: hidden;
        width: 32%;
        height: 100%;
        transition: transform 1s ease-in-out;
    }

    .switch-wrapper::after {
        content: "";
        display: block;
        background-image: url("../assets/Login4.jpg");
        background-size: 1000px 550px;
        background-position: top left;
        width: 100%;
        height: 100%;
        overflow: hidden;
        transition: all 1s ease-in-out;
    }

    .active .switch-wrapper {
        transform: translateX(calc(1000px - 100%));
    }

    .active .switch-wrapper::after {
        background-position: top right;
    }

    .txt {
        width: 100%;
        height: 100%;
        transition: all 1s ease-in-out;
    }

    .top-wrapper {
        position: absolute;
        width: 260px;
        height: 168px;
        top: 50px;
        background-color: rgba(255, 255, 255, 0.7);
        padding: 10px;
        border-radius: 5px;
    }

    .btn-wrapper {
        position: absolute;
        width: 100px;
        height: 36px;
        color: #fffffe;
        background-color: #6689e2;
        font-size: 15px;
        border-radius: 30px;
        cursor: pointer;
        flex-wrap: wrap;
        overflow: hidden;
    }

    .outerBox {
        position: absolute;
        z-index: 3;
        left: 32%;
        overflow: hidden;
        width: 68%;
        height: 100%;
        transition: all 1s ease-in-out;
    }

    .container {
        width: 100%;
        height: 100%;
        background-color: #fffffe;
    }

    .active .outerBox {
        transform: translateX(calc(-1000px + 100%));
    }

    .ld {
        width: 200px;
        height: 40px;
        position: absolute;
        right: 16px;
        top: 16px;
    }

    .title {
        font-size: 36px;
        line-height: 1.5;
        text-align: center;
        margin-bottom: 30px;
        color: #666;
    }

    .btn-login {
        width: 100%;
    }

    .row {
        display: flex;
        display: -webkit-flex;
        flex-direction: row;
    }

    .column {
        display: flex;
        display: -webkit-flex;
        flex-direction: column;
    }

    .aCenter {
        align-items: center;
    }

    .jCenter {
        justify-content: center;
    }

    .login-wrapper .login-container .outerBox .container .btn-forget {
        background-color: white;
        border: 1px;
        color: #6689e2;
        margin-left: 100px;
        position: absolute;
        left: 36px;

    }

    .login-wrapper .login-container .outerBox .container .login .checkCode {
        width: 57%;

    }

    .login-wrapper .login-container .outerBox .container .login .send, .login-wrapper .login-container .outerBox .container .login .chongfa {
        position: absolute;
        top: 3px;
        left: 95%;
        height: 33px;


    }

    /* .checkCode ::v-deep .el-form-item_label{*/
    /*    display: flex;*/
    /*    align-items: center;*/
    /*    justify-content: space-around;*/
    /*    width: 16px;*/
    /*}*/
    /*::v-deep .el-form-item_label{*/
    /*    font-size: 18px;*/
    /*    font-width: bold;*/
    /*    padding: 0 0 0 0;*/
    /*    color: #fff;*/
    /*    text-align: left;*/
    /*}*/
    /*.sige>button{*/
    /*    width: 100%;*/
    /*    font-size: 18px;*/
    /*}*/
</style>
