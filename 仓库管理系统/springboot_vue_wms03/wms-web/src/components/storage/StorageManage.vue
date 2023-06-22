<template>
    <!--页面搜索框 -->
    <div style="position: relative;padding: 0px;border-radius: 20%;margin: 5px">
        <div class="second" style="margin: 0px">
            <!--仓库搜索-->
            <el-input
                    v-model="name"
                    placeholder="请输入仓库名字"
                    suffix-icon="el-icon-search"
                    style="width: 200px;height: 40px;margin-right:5px "
                    @keyup.enter.native="loadPost">

            </el-input>
            <!--v-model="person"-->
            <!--姓名搜索-->
            <el-input
                    v-model="person"
                    placeholder="请输入负责人姓名"
                    suffix-icon="el-icon-search"
                    style="width: 200px;height: 40px"
                    @keyup.enter.native="loadPost">
            </el-input>
            <!--性别搜索-->
            <el-select
                    v-model="sex"
                    filterable
                    placeholder="请选择性别"
                    style="margin-left: 5px;height: 40px"
                    @keyup.enter.native="loadPost"
            >
                <el-option
                        v-for="item in sexs"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                </el-option>

            </el-select>
            <!--查询功能-->
            <el-tooltip
                    class="item"
                    effect="dark"
                    content="查询"
                    placement="top-start">
                <el-button type="" style="margin-left: 5px;border-radius: 50%;width: 44px;height: 41px"
                           @click="loadPost">
                    <i class="iconfont icon-chaxun" style="margin-left: -7px;"></i>
                </el-button>
            </el-tooltip>
            <!--重置功能-->
            <el-tooltip
                    class="item"
                    effect="dark"
                    content="重置"
                    placement="top-start">
                <el-button type="" style="margin-left: 5px;border-radius: 50%;width: 44px;height: 41px"
                           @click="resetParam">
                    <i class="iconfont icon-zhongzhi" style="margin-left: -7px;"></i>
                </el-button>
            </el-tooltip>
            <!--新增功能-->
            <el-tooltip
                    class="item"
                    effect="dark"
                    content="新增"
                    placement="top-start">
                <el-button type="" style="margin-left: 5px;border-radius: 50%;width: 44px;height: 41px" @click="add">
                    <i class="iconfont icon-xinzeng" style="margin-left: -7px;"></i>
                </el-button>
            </el-tooltip>
        </div>

        <!--页面数据-->
        <div class="storageTable">
            <!--表单数据 :header-cell-style="{background:'#615c5c'}" color: #99a9bf;-->
            <!--:header-cell-style="{background:'rgb(198 198 198)'}"-->
            <el-table :data="tableData" border>
                <el-table-column prop="id" label="编号" width="180">
                </el-table-column>

                <el-table-column prop="name" label="仓库名" width="180">
                </el-table-column>

                <el-table-column prop="person" label="负责人" width="180">
                </el-table-column>

                <el-table-column prop="createtime" label="创建时间" width="180">
                </el-table-column>

                <el-table-column prop="remark" label="备注" width="180">
                </el-table-column>

                <el-table-column prop="sex" label="性别" width="180">
                    <template slot-scope="scope">
                        <el-tag
                                :type="scope.row.sex === '1' ? 'primary' : 'success'"
                                disable-transitions
                        >{{scope.row.sex === '1' ? '男' : '女'}}
                        </el-tag
                        >
                    </template>
                </el-table-column>

                <el-table-column prop="operate" label="操作" width="231">
                    <template slot-scope="scope">
                        <!--  修改功能-->
                        <el-tooltip
                                class="item"
                                effect="dark"
                                content="修改"
                                placement="top-start">
                            <!--slot-scope=“scope”,将scope当作一个对象去存储插槽上绑定的数据，即当前行的数据对象-->
                            <el-button size="small" icon="el-icon-edit" circle type="success" @click="mod(scope.row)">
                            </el-button>
                        </el-tooltip>
                        <!--删除功能-->
                        <el-tooltip
                                class="item"
                                effect="dark"
                                content="删除"
                                placement="top-start">
                            <!--删除提示-->
                            <el-popconfirm
                                    confirm-button-text="好的"
                                    cancel-button-text="不用了"
                                    icon="el-icon-info"
                                    icon-color="red"
                                    title="确定删除吗？"
                                    @confirm="del(scope.row.id)"
                                    style="margin-left: 5px">
                                <el-button
                                        icon="el-icon-close"
                                        circle
                                        slot="reference"
                                        size="small"
                                        type="danger">
                                </el-button>
                            </el-popconfirm>
                            <!--  <el-button size="small" type="danger" @click="del(scope.row.id)">删除</el-button>-->
                        </el-tooltip>
                    </template>
                </el-table-column>
                <el-switch v-model="value"></el-switch>
            </el-table>
        </div>


        <!--页面分页-->
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pageNum"
                :page-sizes="[ 6, 9, 12,15]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total"
                style="text-align: center"
        >
        </el-pagination>
        <!--对话框-->
        <el-dialog
                title="提示"
                :visible.sync="centerDialogVisible"
                width="30%"
                center
                :before-close="handleClose"
                @close="closeEvent">
            <!--页面添加 -->
            <el-form ref="form" :rules="rules" :model="form" label-width="80px">
                <el-form-item label="编号" prop="id">
                    <el-col :span="20">
                        <el-input v-model="form.id" :disabled="true"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="仓库名" prop="name">
                    <el-col :span="20">
                        <el-input v-model="form.name"></el-input>
                    </el-col>
                </el-form-item>
                <!--原来-->
                <!--                <el-form-item label="负责人" prop="person">-->
                <!--                    <el-col :span="20">-->
                <!--                        <el-input v-model="form.person"></el-input>-->
                <!--                    </el-col>-->
                <!--                </el-form-item>-->
                <!--下拉选择负责人-->
                <el-form-item label="负责人" prop="person">
                    <el-col :span="18">
                        <el-select v-model="form.user" placeholder="请选择负责人">
                            <el-option v-for="item in userData" :key="item" :value="item">
                            </el-option>
                        </el-select>
                    </el-col>
                </el-form-item>


                <el-form-item label="性别">
                    <el-radio-group v-model="form.sex">
                        <el-radio label="1">男</el-radio>
                        <el-radio label="0">女</el-radio>
                    </el-radio-group>
                </el-form-item>

                <el-form-item label="创建时间">
                    <el-col :span="11">
                        <el-date-picker
                                type="date"
                                placeholder="选择日期"
                                v-model="form.createtime"
                                style="width: 100%"
                        ></el-date-picker>
                    </el-col>
                </el-form-item>
                <el-form-item label="备注" prop="remark">
                    <el-col :span="20">
                        <el-input v-model="form.remark"></el-input>
                    </el-col>
                </el-form-item>
            </el-form>
            <!--<span>需要注意的是内容是默认不居中的</span>-->
            <span slot="footer" class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        // computed:{
        //     breadCrumbList(){
        //         return this.$route.matched;
        //     }
        // },
        // mounted() {
        //   console.log(this.$route);
        // },
        name: "StorageManage",
        data() {
            return {
                tableData: [],
                value: false,
                pageSize: 9,
                pageNum: 1,
                total: 0,
                name: '',
                person: '',
                user: '',
                userData: [],
                sex: '',
                sexs: [{
                    value: '1',
                    label: '男'
                }, {
                    value: '0',
                    label: '女'
                }],
                centerDialogVisible: false,
                form: {
                    name: '',
                    id: '',
                    person: '',
                    createtime: '',
                    remark: '',
                    sex: '1',
                },
                //在前端显示必填项
                rules: {
                    name: [
                        {required: true, message: '请输入仓库名', trigger: 'blur'},
                        {min: 3, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur'}
                    ],
                    // person: [
                    //     {required: true, message: '请输入姓名', trigger: 'blur'},
                    //     {min: 1, max: 5, message: '长度在 1 到 5 个字符', trigger: 'blur' }
                    // ],
                    remark: [
                        {required: true, message: '请输入备注', trigger: 'blur'}
                        // {min: 3, max: 5, message: '长度在 3 到 50 个字符', trigger: 'blur' }
                    ]
                },
            }
        },
        methods: {
            //表单重置
            resetForm() {
                this.$refs.form.resetFields();
            }
            ,
            //打开添加页面
            add() {
                this.centerDialogVisible = true
                //采用异步，使其重置需要创建完成后
                this.$nextTick(() => {
                    this.resetForm();
                })
            },
            // 删除
            del(id) {
                //在校验的时候假如不通过则无法进行报存操作，只需返回结果的data数据
                this.$axios.get(this.$httpUrl + '/storage2/del?id=' + id).then(res => res.data).then(res => {
                    console.log(res)
                    if (res.code == 200) {
                        // alert('获取数据成功' )
                        this.$message({
                            message: '操作成功',
                            type: 'success'
                        });
                        this.loadPost()
                    } else {
                        this.$message({
                            message: '操作失败',
                            type: 'error'
                        });
                    }
                })
            },
            //先获取数据，再修改。
            mod(row) {
                //赋值到表单上，让用户可以在现有基础上修改
                this.form.id = row.id;
                this.form.name = row.name;
                this.form.person = row.person;
                // this.form.user=row.user;
                this.form.createtime = row.createtime;
                this.form.remark = row.remark;
                this.form.sex = row.sex + '';
                //展示
                this.centerDialogVisible = true
            },
            //添加功能
            doSave() {
                this.$axios.post(this.$httpUrl + '/storage2/save', this.form).then(res => res.data).then(res => {
                    // console.log(res)
                    if (res.code == 200) {
                        this.$message({
                            message: '操作成功！',
                            type: 'success'
                        });
                        this.centerDialogVisible = false
                        //保存后再拿一次数据，然后重置表单
                        this.loadPost()
                        this.resetForm()
                    } else {
                        this.$message({
                            message: '操作失败！',
                            type: 'error'
                        });
                    }

                })
            },
            doMod() {
                this.$axios.post(this.$httpUrl + '/storage2/update', this.form).then(res => res.data).then(res => {
                    console.log(res)
                    if (res.code == 200) {
                        this.$message({
                            message: '操作成功！',
                            type: 'success'
                        });
                        this.centerDialogVisible = false
                        this.loadPost()
                        this.resetForm()
                    } else {
                        this.$message({
                            message: '操作失败！',
                            type: 'error'
                        });
                    }

                })
            },
            save() {
                //在校验的时候，假如不通过则无法进行保存操作
                this.$refs.form.validate((valid) => {
                    if (valid) {
                        if (this.form.id) {
                            this.doMod();
                        } else {
                            this.doSave();
                        }
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            //提示框，在选择关闭时候会出现
            handleClose(done) {
                this.$confirm('确认关闭？')
                    .then(() => {
                        done();
                    })
                    .catch(() => {
                    });
            },
            loadUser() {
                this.$axios.get(this.$httpUrl + '/storage2/getUser').then(res => res.data).then(res => {
                    console.log(res)
                    this.userData = res
                    console.log(this.userData)
                })
            },
            loadGet() {
                this.$axios.get(this.$httpUrl + '/storage2/list').then(res => res.data).then(res => {
                    console.log(res)
                })
            },
            closeEvent() {
                console.log("关闭弹窗触发");
            },
            //清除搜索框
            resetParam() {
                this.name = '',
                    this.person = '',
                    this.sex = '',
                    //重置完再搜索一次全部数据，达到搜索状态是无
                    this.loadPost()
            },
            loadPost() {
                this.$axios.post(this.$httpUrl + '/storage2/listPageC1', {
                    pageSize: this.pageSize,
                    pageNum: this.pageNum,
                    param: {
                        name: this.name,
                        person: this.person,
                        sex: this.sex
                    }
                }).then(res => res.data).then(res => {
                    console.log(res)
                    if (res.code == 200) {
                        this.tableData = res.data
                        this.total = res.total;
                    } else {
                        alert('获取数据失败' + res.code)
                    }
                })
            },
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
                this.pageNum = 1
                this.pageSize = val
                this.loadPost()
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
                this.pageNum = val
                this.loadPost()
            }
        },
        beforeMount() {
            // this.loadGet();
            this.loadPost();
            this.loadUser()
        }
    }
</script>

<style scoped>
    .el-input >>> .el-input__inner {
        width: 200px;
        border-radius: 20px;
    }

    .el-select >>> .el-input__inner {
        border-radius: 20px;
        width: 180px;
    }
</style>

<style>
    .el-table thead {
        color: #2b354a;
    }

    .storageTable {
        margin-left: 4px;
        margin-top: 7px;
    }

    .el-table--scrollable-x .el-table__body-wrapper {
        overflow-x: hidden;
        overflow-y: hidden;
    }
</style>