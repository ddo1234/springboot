<template>
    <div>
        <div style="margin-bottom: 10px">
            <el-input
                    v-model="userName"
                    style="width: 350px; text-align: left"
                    suffix-icon="el-icon-search"
                    @keyup.enter.native="loadPost"
                    clearable="true"
                    placeholder="输入用户名"
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
            <!--重置-->
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

        <el-table
                class="table"
                :data="tableData"
                style="width: 100%; margin-top: 0"
                max-height="570px"
                :header-cell-style="{ background: '#f2f5fc', color: '#555555' }"
                border
                highlight-current-row
                @current-change="selectCurrentChange"
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
              scope.row.roleId === 1
                ? 'primary'
                : scope.row.roleId === 0
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

        </el-table>

        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pageNum"
                :page-sizes="[ 6, 10, 12,18]"
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


    </div>
</template>

<script>
    import "@/assets/css/table.css";

    export default {
        name: "SelectUser",
        components: {},
        data() {
            let checkDuplicate = (rule, value, callback) => {
                if (this.form.id) {
                    return callback();
                }
                this.$axios
                    .get(
                        this.$httpUrl + "/user/findByUserName?UserName=" + this.form.userName
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
                tableData: [],
                pageNum: 1,
                pageSize: 10,
                total: 0,
                userName: "",
                centerDialogVisible: false,
                currentRow: '',
                // rules:{
                //   // userName:[{validator: checkDuplicate()}]
                // }

            };
        },
        methods: {

            selectCurrentChange(val) {
                this.$emit("doSelectUser", val)
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