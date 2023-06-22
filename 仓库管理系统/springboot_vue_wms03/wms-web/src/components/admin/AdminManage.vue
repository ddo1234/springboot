<template>
    <div class="adminMain" style="margin-left: 69px; ">
        <div style="margin-bottom: 10px;margin-left: 0px">
            <el-input
                    v-model="userName"
                    style="width: 350px; text-align: left"
                    suffix-icon="el-icon-search"
                    @keyup.enter.native="loadPost"
                    clearable="true"
                    placeholder="请输入用户名"
            ></el-input>
            <el-button
                    type="primary"
                    @click="loadPost"
                    style="margin-left: 15px"
                    round
                    clearable="true"
            >查询
            </el-button
            >

            <!--      <el-button type="reset"  round>重置</el-button>-->
            <el-button type="info" @click="add" round>新增</el-button>
            <!--重置-->
            <el-tooltip
                    class="item"
                    effect="dark"
                    placement="top-start">
                <el-button type="reset" style="margin-left: 5px;border-radius: 50%;" @click="resetParam">
                    重置
                </el-button>
            </el-tooltip>
        </div>

        <!--    显示表单-->
        <el-table
                class="adminTable"
                :data="tableData"
                style="width: 100%; margin-top: 0"
                max-height="570px"
                :header-cell-style="{ background: '#f2f5fc', color: '#555555' }"
                border
        >

            <el-table-column type="expand">
                <template slot-scope="scope">
                    <el-form label-position="left" inline class="demo-table-expand">
                        <el-form-item label="用户名">
                            <span>{{ scope.row.userName }}</span>
                        </el-form-item>
                        <el-form-item label="密码">
                            <span>{{ scope.row.password }}</span>
                        </el-form-item>
                        <el-form-item label="姓名">
                            <span>{{ scope.row.name }}</span>
                        </el-form-item>
                        <el-form-item label="年龄">
                            <span>{{ scope.row.age }}</span>
                        </el-form-item>

                    </el-form>
                </template>
            </el-table-column>

            <el-table-column prop="id" label="#" width="180" style="left: 50px"></el-table-column>
            <el-table-column prop="userName" label="用户名" width="180"></el-table-column>
            <!--            <el-table-column prop="name" label="姓名" width="180"> </el-table-column>-->
            <el-table-column prop="sex" label="性别" width="120">
                <template slot-scope="scope">
                    <el-tag
                            :type="scope.row.sex === 0 ? 'success' : 'primary'"
                            disable-transitions
                    >{{ scope.row.sex === 0 ? "女" : "男" }}
                    </el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="roleId" label="角色" width="180">
                <template slot-scope="scope">
                    <el-tag
                            :type="
              scope.row.roleId === 0
                ? 'primary'
                : scope.row.roleId === 1
                ? 'danger'
                : 'success'
            "
                            disable-transitions
                    >{{
                        scope.row.roleId === 0
                        ? "超级管理员"
                        : scope.row.roleId === 1
                        ? "管理员"
                        : "用户"
                        }}
                    </el-tag
                    >
                </template>
            </el-table-column>
            <el-table-column prop="phone" label="电话" width="170"></el-table-column>
            <el-table-column label="操作" width="190">
                <template slot-scope="scope">
                    <el-button type="success" size="small" @click="update(scope.row)"
                    >修改
                    </el-button
                    >
                    <el-popconfirm
                            title="确定删除吗？"
                            @confirm="del(scope.row.id)"
                            style="margin-left: 5px"
                    >
                        <el-button slot="reference" type="danger" size="small"
                        >删除
                        </el-button
                        >
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>

        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pageNum"
                :page-sizes="[ 6, 9, 12,18]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total"
                style="text-align: center"
        >
        </el-pagination>

        <!--      <Page1 :total="total"/>-->
        <!--      <el-pagination-->

        <!--      >-->
        <!--      </el-pagination>-->

        <el-dialog
                title="提示"
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
                <el-form-item label="用户名" prop="userName">
                    <el-col :span="20">
                        <el-input v-model="form.userName"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-col :span="20">
                        <el-input v-model="form.password" clearable="true"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="姓名" prop="name">
                    <el-col :span="20">
                        <el-input v-model="form.name" clearable="true"></el-input>
                    </el-col>
                </el-form-item>

                <el-form-item label="年龄" prop="age">
                    <el-col :span="20">
                        <el-input v-model="form.age" clearable="true"></el-input>
                        <br/>
                    </el-col>
                </el-form-item>
                <el-form-item label="电话" prop="phone">
                    <el-col :span="20">
                        <el-input v-model="form.phone" clearable="true"></el-input>
                    </el-col>
                </el-form-item>
                <!--                <el-form-item label="角色" prop="roleId">-->
                <!--                <el-radio-group v-model="form.roleId">-->
                <!--                    <el-radio label="0">超级管理员</el-radio>-->
                <!--                    <el-radio label="1">管理员</el-radio>-->
                <!--                    <el-radio label="2">用户</el-radio>-->
                <!--                </el-radio-group>-->
                <!--            </el-form-item>-->
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
        <el-button type="primary" @click="save">确 定</el-button>
      </span>
        </el-dialog>
    </div>
</template>

<script>
    import "@/assets/css/table.css";

    export default {
        name: "AdminManage",
        components: {},
        data() {
            let checkDuplicate = (rule, value, callback) => {
                if (this.form.id) {
                    return callback();
                }
                this.$axios
                    .get(
                        this.$httpUrl + "/user/findByUserName?userName=" + this.form.userName)
                    .then((res) => res.data)
                    .then((res) => {
                        if (res.code != 200) {
                            callback();
                        } else {
                            callback(new Error("用户名已经存在"));
                        }
                    });
            };

            return {
                tableData: [],
                pageNum: 1,
                pageSize: 9,
                total: 0,
                userName: "",
                centerDialogVisible: false,
                form: {
                    id: '',
                    userName: '',
                    password: '',
                    name: '',
                    age: "",
                    sex: "0",
                    phone: '',
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
                            pattern: /^1[0-9][0-9]\d{8}$/,
                            message: "请输入正确的手机号码",
                            trigger: "blur",
                        },
                    ],
                    age: [{required: true, message: "年龄不能为空", trigger: "blur"}]
                },
            };
        },
        methods: {
            add() {
                this.centerDialogVisible = true;
                this.$nextTick(() => {
                    this.resetForm();
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
            doSave() {
                this.$refs.form.validate((valid) => {
                    if (valid) {
                        this.$axios
                            .post(this.$httpUrl + "/user/save", this.form)
                            .then((res) => res.data)
                            .then((res) => {
                                console.log(res);
                                if (res.code == 200) {
                                    this.$message({
                                        message: "管理员新增成功！",
                                        type: "success",
                                    });
                                    this.loadPost();
                                    this.centerDialogVisible = false;
                                    this.resetForm();
                                    // this.refresh(); //页面刷新
                                } else {
                                    this.$message.error("管理员新增失败！");
                                }
                            });
                    } else {
                        console.log("error submit!!");
                        return false;
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
            resetForm() {
                // this.form.reset();
                // this.form={}
                this.$refs.form.resetFields();
            },
            resetParam() {
                this.userName = '';
                this.loadPost()
            },
            update(row) {
                console.log(row);
                //赋值到表单
                this.form.id = row.id;
                this.form.userName = row.userName + "";
                this.form.password = row.password + "";
                this.form.name = row.name + "";
                this.form.sex = row.sex + "";
                this.form.age = row.age + "";
                this.form.phone = row.phone + "";
                this.form.roleId = row.roleId + "";
                this.centerDialogVisible = true;
            },
            del(id) {
                console.log(id);
                this.$axios
                    .get(this.$httpUrl + "/user/del?id=" + id)
                    .then((res) => res.data)
                    .then((res) => {
                        console.log(res);
                        if (res.code == 200) {
                            this.$message({
                                message: "操作成功！",
                                type: "success",
                            });
                            this.loadPost();
                        } else {
                            this.$message({
                                message: "操作失败！",
                                type: "error",
                            });
                        }
                    });
            },

            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
                this.pageNum = 1;
                this.pageSize = val;
                this.loadPost();
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
                this.pageNum = val;
                this.loadPost();
            },
            loadGet() {
                this.$axios
                    .get(this.$httpUrl + "/user/list")
                    .then((res) => res.data)
                    .then((res) => {
                        console.log(res);
                        this.tableData = res;
                    });
            },
            loadPost() {
                this.$axios
                    .post(this.$httpUrl + "/user/listPageC1", {
                        pageNum: this.pageNum,
                        pageSize: this.pageSize,
                        param: {
                            userName: this.userName,
                            roleId: "1",
                        },
                        // userName:this.userName
                    })
                    .then((res) => res.data)
                    .then((res) => {
                        this.tableData = res.data;
                        this.total = res.total;
                        console.log(res);
                    });
            },
            refresh() {
                this.form.id = null;
                // this.$router.go(0);
                window.location.reload();
            },
        },
        beforeMount() {
            // this.loadGet();
            this.loadPost();
        },
    };
</script>
<style scoped>
    .el-main [data-v-47323bf2] {
        overflow-x: hidden;
    }

    .el-table__header [data-v-552664d4] {
        width: 1271px;
    }


    .demo-table-expand {
        font-size: 0;
    }

    .demo-table-expand label {
        width: 90px;
        color: #99a9bf;
    }

    .demo-table-expand .el-form-item {
        margin-right: 0;
        margin-bottom: 0;
        width: 50%;
    }
</style>