<template>
    <div class="userMain" style="margin-left: 0px;">
        <div style="margin-bottom: 10px;margin-left: 0px;">
            <el-input
                    clearable="true"
                    v-model="userName"
                    style="width: 350px; text-align: left"
                    suffix-icon="el-icon-search"
                    @keyup.enter.native="loadPost"
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
            <el-tooltip
                    class="item"
                    effect="dark"
                    content="重置"
                    placement="top-start">
                <el-button type="reset" style="margin-left: 5px;border-radius: 50%;" @click="resetParam">
                    <i class="iconfont icon-zhongzhi"></i>
                </el-button>
            </el-tooltip>
        </div>

        <!--        显示表单-->
        <el-table
                class="userTable"
                :data="tableData"
                style="width: 100%; margin-top: 0 ; padding-left: 0"
                max-height="570px"
                :header-cell-style="{ background: '#f2f5fc', color: '#555555' }"
                border
        >
            <el-table-column prop="id" label="id" width="180"></el-table-column>
            <el-table-column prop="userName" label="用户名" width="180">
                <template slot-scope="scope">
                    <el-popover trigger="hover" placement="top">
                        <p>用户名: {{ scope.row.userName }}</p>
                        <p>密码: {{ scope.row.password }}</p>
                        <p>姓名: {{ scope.row.name }}</p>
                        <p>年龄: {{ scope.row.age }}</p>
                        <div slot="reference" class="name-wrapper">
                            <el-tag size="medium">{{ scope.row.userName }}</el-tag>
                        </div>
                    </el-popover>
                </template>
            </el-table-column>

            <el-table-column prop="name" label="姓名" width="180"></el-table-column>
            <el-table-column prop="sex" label="性别" width="120">
                <template slot-scope="scope">
                    <el-tag
                            :type="scope.row.sex === 0 ? 'primary' : 'success'"
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
                ? 'danger'
                : scope.row.roleId === 1
                ? 'primary'
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
            <el-table-column fixed="right" label="操作" width="190">
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
            <!--            修改表单-->
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
                </el-form-item
                >
                <el-form-item label="年龄" prop="age">
                    <el-col :span="20">
                        <el-input v-model="form.age" clearable="true"></el-input>
                        <br/>
                    </el-col>
                </el-form-item
                >
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
        name: "UserManager",
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
                            callback(new Error("账号已经存在"));
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
                    age: '',
                    sex: "0",
                    phone: '',
                    roleId: "2",
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
                                        message: "用户新增成功！",
                                        type: "success",
                                    });
                                    this.loadPost();
                                    this.centerDialogVisible = false;
                                    this.resetForm();
                                    // this.refresh(); //页面刷新
                                } else {
                                    this.$message.error("用户新增失败！");
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
                                        message: "用户修改成功！",
                                        type: "success",
                                    });
                                    this.loadPost();
                                    this.centerDialogVisible = false;
                                    this.resetForm();
                                    // this.refresh(); //页面刷新
                                } else this.$message.error("用户修改失败！");
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
                this.form.userName = row.userName;
                this.form.password = row.password;
                this.form.name = row.name;
                this.form.sex = row.sex + "";
                this.form.age = row.age + "";
                this.form.phone = row.phone;
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
                            roleId: "2",
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

    .el-table_8_column_50 is-leaf el-table__cell {
        padding-left: 70px;
    }

    .userName {
        left: 70px;
    }

</style>
<template>
    <div class="userMain" style="margin-left: 0px;">
        <div style="margin-bottom: 10px;margin-left: 0px;">
            <el-input
                    clearable="true"
                    v-model="userName"
                    style="width: 350px; text-align: left"
                    suffix-icon="el-icon-search"
                    @keyup.enter.native="loadPost"
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
            <el-tooltip
                    class="item"
                    effect="dark"
                    content="重置"
                    placement="top-start">
                <el-button type="reset" style="margin-left: 5px;border-radius: 50%;" @click="resetParam">
                    <i class="iconfont icon-zhongzhi"></i>
                </el-button>
            </el-tooltip>
        </div>

        <!--        显示表单-->
        <el-table
                class="userTable"
                :data="tableData"
                style="width: 100%; margin-top: 0 ; padding-left: 0"
                max-height="570px"
                :header-cell-style="{ background: '#f2f5fc', color: '#555555' }"
                border
        >
            <el-table-column prop="id" label="id" width="180"></el-table-column>
            <el-table-column prop="userName" label="用户名" width="180">
                <template slot-scope="scope">
                    <el-popover trigger="hover" placement="top">
                        <p>用户名: {{ scope.row.userName }}</p>
                        <p>密码: {{ scope.row.password }}</p>
                        <p>姓名: {{ scope.row.name }}</p>
                        <p>年龄: {{ scope.row.age }}</p>
                        <div slot="reference" class="name-wrapper">
                            <el-tag size="medium">{{ scope.row.userName }}</el-tag>
                        </div>
                    </el-popover>
                </template>
            </el-table-column>

            <el-table-column prop="name" label="姓名" width="180"></el-table-column>
            <el-table-column prop="sex" label="性别" width="120">
                <template slot-scope="scope">
                    <el-tag
                            :type="scope.row.sex === 0 ? 'primary' : 'success'"
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
                ? 'danger'
                : scope.row.roleId === 1
                ? 'primary'
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
            <el-table-column fixed="right" label="操作" width="190">
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
            <!--            修改表单-->
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
                </el-form-item
                >
                <el-form-item label="年龄" prop="age">
                    <el-col :span="20">
                        <el-input v-model="form.age" clearable="true"></el-input>
                        <br/>
                    </el-col>
                </el-form-item
                >
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
        name: "UserManager",
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
                            callback(new Error("账号已经存在"));
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
                    age: '',
                    sex: "0",
                    phone: '',
                    roleId: "2",
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
                                        message: "用户新增成功！",
                                        type: "success",
                                    });
                                    this.loadPost();
                                    this.centerDialogVisible = false;
                                    this.resetForm();
                                    // this.refresh(); //页面刷新
                                } else {
                                    this.$message.error("用户新增失败！");
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
                                        message: "用户修改成功！",
                                        type: "success",
                                    });
                                    this.loadPost();
                                    this.centerDialogVisible = false;
                                    this.resetForm();
                                    // this.refresh(); //页面刷新
                                } else this.$message.error("用户修改失败！");
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
                this.form.userName = row.userName;
                this.form.password = row.password;
                this.form.name = row.name;
                this.form.sex = row.sex + "";
                this.form.age = row.age + "";
                this.form.phone = row.phone;
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
                            roleId: "2",
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

    .el-table_8_column_50 is-leaf el-table__cell {
        padding-left: 70px;
    }

    .userName {
        left: 70px;
    }

</style>