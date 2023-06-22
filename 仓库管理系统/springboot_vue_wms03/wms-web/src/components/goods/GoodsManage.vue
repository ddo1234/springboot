<template>
    <div>
        <!--progress开始-->
        <h1 style="display:inline-block;position: absolute;margin-top: 25px;margin-left: 20px">仓<br>库<br>情<br>况</h1>
        <div style="background-color: #E0FFFF;text-align: center">
            <div style="text-align: center;width: 350px;display: inline-block" v-for="item in numData">
                <h3>{{ item.storage }}</h3>
                <el-progress type="circle" :percentage="(100 - (item.sum / 500)) | numFilter"></el-progress>
            </div>
        </div>
        <!--progress结束-->

        <div style="margin-bottom: 50px;background-color: #FFF0F5">
            <!--tableHeader开始-->
            <div style="margin-bottom: 5px;text-align: center">
                <el-input v-model="input" placeholder="请输入货品名称" suffix-icon="el-icon-search" style="width:200px;"
                          @keyup.enter.native="loadPost"></el-input>
                <el-select v-model="goodstype" clearable placeholder="请选择类型" style="margin-left: 5px"
                           @change="loadPost">
                    <el-option v-for="item in goodstypeData" :key="item" :value="item">
                    </el-option>

                </el-select>
                <el-select v-model="storage" clearable placeholder="请选择仓库" style="margin-left: 5px" @change="loadPost">
                    <el-option v-for="item in storageData" :key="item" :value="item">
                    </el-option>

                </el-select>
                <el-tooltip class="item" effect="dark" content="搜索" placement="top">
                    <el-button icon="el-icon-search" circle type="primary" style="margin-left: 5px"
                               @click="loadPost"></el-button>
                </el-tooltip>
                <el-tooltip class="item" effect="dark" content="重置" placement="top">
                    <el-button icon="el-icon-refresh-right" circle type="success" style="margin-left: 5px"
                               @click="resetParam">
                    </el-button>
                </el-tooltip>
                <el-tooltip class="item" effect="dark" content="新增" placement="top">
                    <el-button icon="el-icon-plus" circle type="primary" style="margin-left: 5px"
                               @click="add"></el-button>
                </el-tooltip>
                <el-button type="danger" icon="el-icon-delete" @click="del" :disabled="multipleSelection.length === 0"
                           style="margin-left:15px;">批量删除
                </el-button>
                <el-badge :value="num" style="margin-left: 20px">
                    <el-button icon="el-icon-warning-outline" @click="drawer = true" type="warning"
                               style="text-align: right;">
                        存货告急
                    </el-button>
                </el-badge>
                <el-drawer title="我是标题" size="25%" :visible.sync="drawer" :with-header="false">
                    <el-table :data="tableData1" stripe
                              :header-cell-style="{ background: '#f2f5fc', color: '#555555' } " style="width: 100%">
                        <el-table-column prop="goodstype" label="类型">
                        </el-table-column>
                        <el-table-column prop="storage" label="所属仓库">
                        </el-table-column>
                        <el-table-column prop="name" label="货品名称">
                        </el-table-column>
                        <el-table-column prop="count" label="数量">
                        </el-table-column>
                    </el-table>
                </el-drawer>
            </div>
            <!--tableHeader结束-->

            <!--table开始-->
            <div>
                <el-table :data="tableData" :header-cell-style="{ background: '#f2f5fc', color: '#555555' }"
                          @selection-change="handleSelectionChange" ref="handSelectTest_multipleTable" row-key="id"
                          style="width: 100%"
                          max-height="600px">
                    <el-table-column type="expand">
                        <template slot-scope="props">
                            <el-form label-position="left" inline class="demo-table-expand">
                                <el-form-item label="货品ID">
                                    <span>{{ props.row.id }}</span>
                                </el-form-item>
                                <el-form-item label="货品名称">
                                    <span>{{ props.row.name }}</span>
                                </el-form-item>
                                <el-form-item label="所属仓库">
                                    <span>{{ props.row.storage }}</span>
                                </el-form-item>
                                <el-form-item label="类型">
                                    <span>{{ props.row.goodstype }}</span>
                                </el-form-item>
                                <el-form-item label="数量">
                                    <span>{{ props.row.count }}</span>
                                </el-form-item>
                                <el-form-item label="备注">
                                    <span>{{ props.row.mark }}</span>
                                </el-form-item>
                            </el-form>
                        </template>
                    </el-table-column>
                    <el-table-column type="selection" label="全选" width="55" :reserve-selection="true">
                    </el-table-column>
                    <el-table-column label="所属仓库" prop="storage">
                    </el-table-column>
                    <el-table-column label="货品名称" prop="name">
                    </el-table-column>
                    <el-table-column label="货品数量" prop="count">
                    </el-table-column>
                    <el-table-column label="操作">
                        <template slot-scope="props">
                            <el-tooltip class="item" effect="dark" content="编辑" placement="top">
                                <el-button type="primary" icon="el-icon-edit" circle
                                           @click="edit(props.row)"></el-button>
                            </el-tooltip>
                            <el-tooltip class="item" effect="dark" content="入库" placement="top">
                                <el-button icon="el-icon-circle-plus-outline" circle type="info"
                                           style="margin-left: 5px"
                                           @click="inGoods(props.row)"></el-button>
                            </el-tooltip>
                            <el-tooltip class="item" effect="dark" content="出库" placement="top">
                                <el-button icon="el-icon-remove-outline" circle type="warning" style="margin-left: 5px"
                                           @click="outGoods(props.row)"></el-button>
                            </el-tooltip>


                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <!--table结束-->

            <!--分页开始-->
            <div style="text-align: center">
                <el-pagination @current-change="handleCurrentChange" :current-page="pageNum" :page-size="pageSize"
                               background
                               layout="total,  prev, pager, next, jumper" :total="total">
                </el-pagination>
            </div>
            <!--分页结束-->

            <!--货品维护dialog开始-->
            <el-dialog title="货品维护" :visible.sync="centerDialogVisible" width="26%" center>
                <el-form :rules="rules" ref="form" :model="form" label-width="80px">
                    <el-form-item label="货品名称" prop="name">
                        <el-col :span="18">
                            <el-input v-model="form.name"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="类型" prop="goodstype">
                        <el-col :span="18">
                            <el-select v-model="form.goodstype" placeholder="请选择分类">
                                <el-option v-for="item in goodstypeData" :key="item" :value="item">
                                </el-option>
                            </el-select>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="所属仓库" prop="storage">
                        <el-col :span="18">
                            <el-select v-model="form.storage" placeholder="请选择仓库">
                                <el-option v-for="item in storageData" :key="item" :value="item">
                                </el-option>
                            </el-select>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="数量" prop="count">
                        <el-col :span="18">
                            <el-input v-model="form.count"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="备注" prop="mark">
                        <el-col :span="18">
                            <el-input type="textarea" v-model="form.mark"></el-input>
                        </el-col>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
          <el-button @click="centerDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="save">确 定</el-button>
        </span>
            </el-dialog>
            <!--货品维护dialog结束-->

            <!--出入库dialog开始-->
            <el-dialog title="出入库管理" :visible.sync="inGoodsDialogVisible" width="26%" center>
                <el-dialog width="45%" title="用户选择" :visible.sync="innerVisible" append-to-body center>
                    <SelectUser @doSelectUser="doSelectUser"></SelectUser>
                    <span slot="footer" class="dialog-footer">
            <el-button @click="innerVisible = false">取 消</el-button>
            <el-button type="primary" @click="confirmUser">确 定</el-button>
          </span>
                </el-dialog>
                <el-form :rules="rules1" ref="form1" :model="form1" label-width="80px">
                    <el-form-item label="货品名称">
                        <el-col :span="20">
                            <el-input v-model="form1.goodsName" readonly></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="申请人" prop="userName">
                        <el-col :span="20">
                            <el-input v-model="form1.userName" placeholder="请选择用户" readonly
                                      @click.native="selectUser"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="数量" prop="count">
                        <el-col :span="20">
                            <el-input v-model="form1.count"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="备注" prop="mark">
                        <el-col :span="20">
                            <span>{{ form1.remark }}</span>
                        </el-col>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
          <el-button @click="inGoodsDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="doInGoods">确 定</el-button>
        </span>
            </el-dialog>
            <!--出入库dialog开始-->
        </div>
    </div>
</template>

<style>
    .demo-table-expand {
        font-size: 0;
    }

    .demo-table-expand label {
        width: 90px;
        color: #99a9bf;
    }

    .demo-table-expand .el-form-item {

        margin-left: 15px;

        width: 34%;
    }
</style>

<script>
    import SelectUser from "@/components/user/SelectUser";

    export default {
        name: "GoodsManage",
        components: {SelectUser},
        data() {
            return {
                user: JSON.parse(sessionStorage.getItem('CurUser')),
                drawer: false,
                num: '',
                numData: [],
                tableData: [],
                tableData1: [],
                pageNum: 1,
                pageSize: 8,
                total: 0,
                input: '',
                centerDialogVisible: false,
                inGoodsDialogVisible: false,
                innerVisible: false,
                storage: '',
                goodstype: '',
                currentRow: {},
                storageData: [],
                goodstypeData: [],
                multipleSelection: [],
                tempUser: '',
                form: {
                    id: '',
                    name: '',
                    goodstype: '',
                    count: '',
                    mark: '',
                    storage: '',
                },
                form1: {
                    goodsId: '',
                    goodsName: '',
                    count: '',
                    remark: '',
                    adminId: '',
                    userId: '',
                    userName: '',
                    action: '1'
                },
                rules: {
                    name: [
                        {required: true, message: '请输入货品名称', trigger: 'blur'}
                    ],
                    goodstype: [
                        {required: true, message: '请选择类型', trigger: 'change'}
                    ],
                    storage: [
                        {required: true, message: '请选择仓库', trigger: 'change'}
                    ],
                    count: [
                        {required: true, message: '请输⼊数量', trigger: 'blur'},
                        {pattern: /^([1-9][0-9]*){1,4}$/, message: '数量必须为正整数字', trigger: "blur"},

                    ]
                },
                rules1: {
                    userName: [
                        {required: true, message: '请选择申请人', trigger: 'change'}
                    ],
                    count: [
                        {required: true, message: '请输⼊数量', trigger: 'blur'},
                        {pattern: /^([1-9][0-9]*){1,4}$/, message: '数量必须为正整数字', trigger: "blur"},

                    ]
                }

            }
        },
        filters: {
            numFilter(value) {
                let realVal = ''
                if (!isNaN(value) && value !== '') {
                    // 截取当前数据到小数点后两位
                    realVal = parseFloat(value).toFixed(2)
                } else {
                    realVal = '-'
                }
                return realVal
            }
        },

        methods: {
            resetForm() {
                this.$refs.form.resetFields();
            },
            resetForm1() {
                this.$refs.form1.resetFields();
            },

            handleSelectionChange(val) {
                console.log(val)  //打印选中的行集合
                this.multipleSelection = val;
            },
            selectUser() {
                this.innerVisible = true
            },
            confirmUser() {
                this.form1.userName = this.tempUser.name
                this.form1.userId = this.tempUser.id
                this.innerVisible = false

            },
            doSelectUser(val) {
                console.log(val)
                this.tempUser = val
            },
            inGoods(row) {
                this.inGoodsDialogVisible = true
                this.form1.goodsId = row.id
                this.form1.goodsName = row.name
                this.form1.adminId = this.user.id
                this.form1.action = '1'
                this.form1.remark = '入库'
                this.$nextTick(() => {
                    this.resetForm1()

                })
            },
            doInGoods() {
                this.$axios.post(this.$httpUrl + '/record/save', this.form1).then(res => res.data).then(res => {
                    console.log(res)
                    if (res.code == 200) {
                        this.$message({
                            showClose: true,
                            message: '操作成功！',
                            type: 'success'
                        });
                        this.inGoodsDialogVisible = false
                        this.loadPost()
                        this.resetForm1()
                    } else {
                        this.$message({
                            showClose: true,
                            message: '操作失败！',
                            type: 'error'
                        });
                    }
                })

            },


            outGoods(row) {
                this.inGoodsDialogVisible = true
                this.form1.goodsId = row.id
                this.form1.goodsName = row.name
                this.form1.adminId = this.user.id
                this.form1.action = '2'
                this.form1.remark = '出库'
                this.$nextTick(() => {
                    this.resetForm1()
                })
            },

            del() {
                let arr = []
                //遍历点击选择的对象集合，拿到每一个对象的id添加到新的集合中
                this.multipleSelection.forEach(row => arr.push(row.id))
                this.$confirm('确定删除吗', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'success',
                    callback: action => {
                        if (action === 'confirm') {
                            //批量删除
                            this.$axios.post(this.$httpUrl + '/goods/delAll', arr).then(res => res.data).then(res => {
                                console.log(res)
                                if (res.code == 200) {
                                    this.$message({
                                        type: "success",
                                        message: "删除成功！"
                                    })
                                    this.loadPost();

                                } else {

                                    this.$message.error("删除失败！")
                                }
                            }).catch(() => {
                                console.log('error submit')
                            })
                        }
                    }
                })
            },


            doEdit() {
                this.$axios.post(this.$httpUrl + '/goods/update', this.form).then(res => res.data).then(res => {
                    console.log(res)
                    if (res.code == 200) {
                        this.$message({
                            showClose: true,
                            message: '操作成功！',
                            type: 'success'
                        });
                        this.centerDialogVisible = false
                        this.loadPost()
                        this.resetForm()
                    } else {
                        this.$message({
                            showClose: true,
                            message: '操作失败！',
                            type: 'error'
                        });
                    }
                })
            },
            doSave() {
                this.$axios.post(this.$httpUrl + '/goods/save', this.form).then(res => res.data).then(res => {
                    console.log(res)
                    if (res.code == 200) {
                        this.$message({
                            showClose: true,
                            message: '操作成功！',
                            type: 'success'
                        });
                        this.centerDialogVisible = false
                        this.loadPost()
                        this.resetForm()
                    } else {
                        this.$message({
                            showClose: true,
                            message: '操作失败！',
                            type: 'error'
                        });
                    }
                })
            },

            save() {
                this.$refs.form.validate((valid) => {
                    if (valid) {
                        if (this.form.id) {
                            this.doEdit();
                        } else {
                            this.doSave();
                        }
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            add() {
                this.centerDialogVisible = true
                this.$nextTick(() => {
                    this.resetForm()
                    this.form.id = ''
                })
            },
            edit(row) {
                this.$nextTick(() => {
                    this.centerDialogVisible = true
                    this.form.id = row.id;
                    this.form.name = row.name;
                    this.form.goodstype = row.goodstype;
                    this.form.storage = row.storage;
                    this.form.mark = row.mark;
                    this.form.count = row.count;
                })
            },

            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
                this.pageNum = val
                this.loadPost()
            },
            resetParam() {
                this.input = '';
                this.goodstype = '';
                this.storage = '';
                this.loadPost()
            },
            getCount() {
                this.$axios.get(this.$httpUrl + '/goods/listTotal').then(res => res.data).then(res => {
                    console.log(res)
                    this.numData = res
                    console.log(this.numData)
                })
            },
            getLessCount() {
                this.$axios.get(this.$httpUrl + '/goods/getCount').then(res => res.data).then(res => {
                    console.log(res)
                    this.num = res
                    console.log(this.num)
                })
            },
            getLess() {
                this.$axios.get(this.$httpUrl + '/goods/listLess').then(res => res.data).then(res => {
                    console.log(res)
                    this.tableData1 = res
                    console.log(this.tableData1)
                })
            },

            loadStorage() {
                this.$axios.get(this.$httpUrl + '/goods/listStorage').then(res => res.data).then(res => {
                    console.log(res)
                    this.storageData = res
                    console.log(this.storageData)
                })
            },
            loadGoodsType() {
                this.$axios.get(this.$httpUrl + '/goods/listType').then(res => res.data).then(res => {
                    console.log(res)
                    this.goodstypeData = res
                    console.log(this.goodstypeData)
                })
            },
            loadPost() {
                this.$axios.post(this.$httpUrl + '/goods/listPage', {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    param: {name: this.input, storage: this.storage, goodstype: this.goodstype}
                }).then(res => res.data).then(res => {
                    console.log(res)
                    if (res.code == 200) {
                        this.tableData = res.data;
                        this.total = res.total;
                    } else {
                        alert('获取数据失败')
                    }
                })
                this.getCount();
                this.getLess();
                this.getLessCount()
            }
        },
        beforeMount() {
            this.loadStorage();
            this.loadPost();
            this.loadGoodsType();
            this.getCount();
            this.getLess();
            this.getLessCount()
        }
    }
</script>
<style>
    .el-table [data-v-47323bf2] {
        width: 100%;
    }
</style>