<template>
    <!--页面搜索框 -->
    <div style="position: relative">
        <div class="second" style="margin: 5px">
            <el-input
                    v-model="name"
                    placeholder="请输入分类名"
                    suffix-icon="el-icon-search"
                    style="width: 200px"
                    @keyup.enter.native="loadPost">

            </el-input>


            <!--查询-->
            <el-tooltip
                    class="item"
                    effect="dark"
                    content="查询"
                    placement="top-start">
                <el-button type="" style="margin-left: 5px;border-radius: 50%;" @click="loadPost">
                    <i class="iconfont icon-chaxun"></i>
                </el-button>
            </el-tooltip>
            <!--重置-->
            <el-tooltip
                    class="item"
                    effect="dark"
                    content="重置"
                    placement="top-start">
                <el-button type="" style="margin-left: 5px;border-radius: 50%;" @click="resetParam">
                    <i class="iconfont icon-zhongzhi"></i>
                </el-button>
            </el-tooltip>
            <!--新增-->
            <el-tooltip
                    class="item"
                    effect="dark"
                    content="新增"
                    placement="top-start">
                <el-button type="" style="margin-left: 5px;border-radius: 50%;" @click="add">
                    <i class="iconfont icon-xinzeng"></i>
                </el-button>
            </el-tooltip>
        </div>
        <!--表单数据-->
        <el-table :data="tableData" :header-cell-style="{background:'rgba(242,245,252,0.1)',color:'#555'}">
            <el-table-column prop="id" label="ID" width="200" style="margin: 20px">
            </el-table-column>

            <el-table-column prop="name" label="分类名" width="200">
            </el-table-column>

            <el-table-column prop="storage" label="仓库" width="200">
            </el-table-column>

            <el-table-column prop="remark" label="备注" width="200">
            </el-table-column>
            <el-table-column prop="operate" label="操作">
                <template slot-scope="scope">
                    <!--  编辑-->
                    <el-tooltip
                            class="item"
                            effect="dark"
                            content="编辑"
                            placement="top-start">
                        <el-button size="small" icon="el-icon-edit" circle type="success" @click="mod(scope.row)">
                        </el-button>
                    </el-tooltip>
                    <!--删除-->
                    <el-tooltip
                            class="item"
                            effect="dark"
                            content="删除"
                            placement="top-start">
                        <el-popconfirm
                                confirm-button-text="是的"
                                cancel-button-text="取消"
                                icon="el-icon-warning"
                                icon-color="red"
                                title="确定删除吗？"
                                @confirm="del(scope.row.id)"
                                style="margin-left: 5px">
                            <el-button
                                    icon="el-icon-remove-outline"
                                    circle
                                    slot="reference"
                                    size="small"
                                    type="danger">
                            </el-button>
                        </el-popconfirm>
                    </el-tooltip>
                </template>
            </el-table-column>
            <el-switch v-model="value"></el-switch>
        </el-table>
        <!--分页-->
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pageNum"
                :page-sizes="[ 5, 10, 20,30]"
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
            <!--新增页面-->
            <el-form ref="form" :rules="rules" :model="form" label-width="80px">
                <el-form-item label="分类名" prop="name">
                    <el-col :span="20">
                        <el-input v-model="form.name"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="仓库" prop="storage">
                    <el-col :span="20">
                        <el-input v-model="form.storage"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="备注" prop="remark">
                    <el-col :span="20">
                        <el-input v-model="form.remark"></el-input>
                    </el-col>
                </el-form-item>
            </el-form>
            <!--            <span>需要注意的是内容是默认不居中的</span>-->
            <span slot="footer" class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "GoodstypeManage",
        data() {

            return {
                tableData: [],
                value: false,
                pageSize: 10,
                pageNum: 1,
                total: 0,
                name: '',
                centerDialogVisible: false,//默认对话窗口是不显示的
                form: {
                    id: '',
                    name: '',
                    storage: '',
                    remark: '',
                },
                rules: {
                    name: [
                        {required: true, message: '请输入分类名', trigger: 'blur'},
                    ],
                    storage: [
                        {required: true, message: '请输入仓库', trigger: 'blur'},
                    ]
                }
            }
        },
        methods: {
            //表单重置
            resetForm() {
                this.$refs.form.resetFields();
            }
            ,
            add() {
                this.centerDialogVisible = true//显示对话框
                //采用异步，使其重置需要创建完成后
                this.$nextTick(() => {
                    this.resetForm();
                })
            },
            del(id) {
                console.log(id)
                //在校验的时候假如不通过则无法进行报存操作
                this.$axios.get(this.$httpUrl + '/goodstype/del?id=' + id).then(res => res.data).then(res => {
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
                console.log(row)
                //赋值到表单上
                this.form.id = row.id
                this.form.name = row.name
                this.form.storage = row.storage
                this.form.remark = row.remark
                //展示对话框
                this.centerDialogVisible = true
            },
            doSave() {
                this.$axios.post(this.$httpUrl + '/goodstype/save', this.form).then(res => res.data).then(res => {
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
            doMod() {
                this.$axios.post(this.$httpUrl + '/goodstype/update', this.form).then(res => res.data).then(res => {
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
                //在校验的时候假如不通过则无法进行报存操作
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
            handleClose(done) {
                this.$confirm('确认关闭？')
                    .then(() => {
                        done();
                    })
                    .catch(() => {
                    });
            },
            closeEvent() {
                console.log("关闭弹窗触发");
            },
            resetParam() {
                this.name = ''
            },
            loadPost() {
                this.$axios.post(this.$httpUrl + '/goodstype/listPage', {
                    pageSize: this.pageSize,
                    pageNum: this.pageNum,
                    param: {
                        name: this.name
                    }
                }).then(res => res.data).then(res => {
                    console.log(res)
                    if (res.code == 200) {
                        this.tableData = res.data
                        this.total = res.total;//总条数
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
            //翻页
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
                this.pageNum = val
                this.loadPost()
            }
        },
        beforeMount() {
            // this.loadGet();
            this.loadPost();
        }
    }
</script>

<style scoped>

</style>