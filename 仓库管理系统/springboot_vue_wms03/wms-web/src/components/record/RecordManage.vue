<template>
    <div style="margin: 5px">
        <div style="margin: 0 0 5px 0;text-align: center">
            <el-input v-model="name" placeholder="请输入物品名" prefix-icon="el-icon-search" style="width: 150px; "
                      @keyup.enter.native="loadPost" clearable></el-input>
            <el-select v-model="storage" placeholder="请选择仓库" clearable>
                <el-option
                        v-for="item in storageData"
                        :key="item.id"
                        :label="item.name"
                        :value="item.name">
                </el-option>
            </el-select>
            <el-select prefix-icon="el-icon-search" v-model="goodsType" placeholder="请选择类型" clearable>
                <el-option
                        v-for="item in goodsTypeData"
                        :key="item.id"
                        :label="item.name"
                        :value="item.name">
                </el-option>
            </el-select>
            <el-select v-model="remark" placeholder="请选择出入库" clearable>
                <el-option
                        v-for="item in remarkData"
                        :key="item.value"
                        :value="item.value">
                </el-option>
            </el-select>
            <el-tooltip content="搜索" placement="top" effect="light">
                <el-button icon="el-icon-search" circle type="primary" @click="loadPost"></el-button>
            </el-tooltip>
            <el-tooltip content="清空" placement="top" effect="light">
                <el-button icon="el-icon-refresh" circle type="info" @click="resetParam"></el-button>
            </el-tooltip>
        </div>
        <el-table :data="tableData"
                  :header-cell-style="{ background: '#dceaf8', color: '#2c3745' }"
                  height="590"
                  border
                  :row-class-name="tableRowClassName">
            <el-table-column prop="id" label="记录ID" width="100" sortable fixed>
            </el-table-column>
            <el-table-column prop="goodsName" label="物品名" sortable fixed>
            </el-table-column>
            <el-table-column prop="storageName" label="仓库" sortable fixed>
            </el-table-column>
            <el-table-column prop="goodsTypeName" label="分类" sortable fixed>
            </el-table-column>
            <el-table-column prop="userName" label="申请人" sortable fixed>
            </el-table-column>
            <el-table-column prop="adminName" label="操作人" sortable fixed>
            </el-table-column>
            <el-table-column prop="count" label="数量" sortable fixed>
            </el-table-column>
            <el-table-column prop="createTime" label="操作时间" width="200" sortable fixed>
                <template slot-scope="scope">
                    <i class="el-icon-time"></i>
                    {{scope.row.createTime}}
                </template>
            </el-table-column>
            <el-table-column prop="remark" label="备注">{{remark}}
            </el-table-column>
            <el-table-column prop="operate" label="操作" width="120" v-if="this.user.roleId===0">
                <template slot-scope="scope">
                    <el-tooltip class="item" effect="light" content="修改" placement="top-start">
                        <el-button style="padding: 7px" icon="el-icon-edit" circle type="primary"
                                   @click="mod(scope.row)">
                        </el-button>
                    </el-tooltip>
                    <el-tooltip class="item" effect="light" content="删除" placement="top-start">
                        <el-popconfirm
                                confirm-button-text="确认"
                                cancel-button-text="取消"
                                icon="el-icon-info"
                                icon-color="red"
                                title="确定删除吗？"
                                @confirm="del(scope.row.id)"
                                style="margin-left: 5px">
                            <el-button style="padding: 7px" icon="el-icon-delete" circle slot="reference" type="danger"
                                       @click="mod1(scope.row)">
                            </el-button>
                        </el-popconfirm>
                    </el-tooltip>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pageNum"
                :page-sizes="[2,5,10,20,30]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
        </el-pagination>

        <el-dialog
                title="记录修改"
                :visible.sync="dialogVisible"
                width="26%"
                center>
            <el-dialog
                    width="60%"
                    title="用户选择"
                    :visible.sync="innerVisible"
                    append-to-body>
                <SelectUser @doSelectUser="doSelectUser"></SelectUser>
                <span slot="footer" class="dialog-footer">
            <el-button @click="innerVisible = false">取 消</el-button>
            <el-button type="primary" @click="confirmUser">确 定</el-button>
          </span>
            </el-dialog>
            <el-form ref="form" :model="form" label-width="80px">
                <el-form-item label="货品名称">
                    <el-col :span="20">
                        <el-input v-model="form.goodsName" readonly></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="申请人" prop="userName">
                    <el-col :span="20">
                        <el-input v-model="form.userName" placeholder="请选择用户" readonly
                                  @click.native="selectUser"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="数量" prop="count">
                    <el-col :span="20">
                        <el-input v-model="form.count"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="备注" prop="remark">
                    <el-select v-model="form.remark" clearable>
                        <el-option
                                v-for="item in remarkData"
                                :key="item.value"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="update(form)">确 定</el-button>
        </span>
        </el-dialog>
    </div>
</template>

<script>
    import SelectUser from "@/components/user/SelectUser";

    export default {
        name: "RecordManage",
        components: {SelectUser},
        data() {
            return {
                user: JSON.parse(sessionStorage.getItem('CurUser')),
                storageData: [],
                goodsTypeData: [],
                tableData: [],
                remark: '',
                remarkData: [
                    {
                        value: "入库",
                        label: '入库'
                    },
                    {
                        value: "出库",
                        label: '出库'
                    }
                ],
                pageSize: 10,
                pageNum: 1,
                total: 0,
                name: '',
                storage: '',
                goodsType: '',
                dialogVisible: false,
                innerVisible: false,
                form: {
                    id: '',
                    userId: '',
                    count: '',
                    remark: ''
                }
            }
        },
        methods: {
            tableRowClassName({row}) {
                if (row.remark === '出库') {
                    return 'warning-row';
                } else if (row.remark === '入库') {
                    return 'success-row';
                }
                return '';
            },
            selectUser() {
                this.innerVisible = true
            },
            confirmUser() {
                this.form.userName = this.tempUser.name
                this.form.userId = this.tempUser.id
                this.innerVisible = false

            },
            doSelectUser(val) {
                this.tempUser = val
            },
            mod(row) {
                this.dialogVisible = true
                this.form.id = row.id
                this.form.goodsId = row.goodsId
                this.form.goodsName = row.goodsName
                this.form.userName = row.userName
                this.form.count = row.count
                this.form.remark = row.remark
            },
            mod1(row) {
                this.form.id = row.id
                this.form.goodsId = row.goodsId
                this.form.goodsName = row.goodsName
                this.form.userName = row.userName
                this.form.count = row.count
                this.form.remark = row.remark
            },
            update() {
                this.$axios.put(this.$httpUrl + '/record/', this.form).then(res => res.data).then(res => {
                    if (res.code == 200) {
                        this.$message.success('修改成功');
                        this.dialogVisible = false
                        this.loadPost()
                    } else {
                        this.$message.success('修改失败');
                    }
                })
            },
            del(id) {
                this.$axios.delete(this.$httpUrl + '/record/' + id).then(res => res.data).then(res => {
                    if (res.code == 200) {
                        this.$message.success('删除成功');
                        this.loadPost()
                        this.updateGoods()
                    } else {
                        this.$message.success('删除失败');
                    }
                })
            },
            updateGoods() {
                this.$axios.put(this.$httpUrl + '/record/updateGoods', this.form).then(res => res.data).then(res => {
                    if (res.code == 200) {
                        this.$message.success('更新物品数量成功');
                        this.dialogVisible = false
                        this.loadPost()
                    } else {
                        this.$message.success('更新物品数量失败');
                    }
                })
            },
            formatGoodsType(row) {
                let temp = this.goodsTypeData.find(item => {
                    return item.id === row.goodstype
                })
                return temp && temp.name
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
            },
            resetParam() {
                this.name = ''
                this.storage = ''
                this.goodsType = ''
                this.remark = ''
            },
            loadStorage() {
                this.$axios.get(this.$httpUrl + '/record/listStorage2').then(res => res.data).then(res => {

                    if (res.code == 200) {
                        this.storageData = res.data
                    } else {
                        this.$message.error('storage2获取数据失败')
                    }

                })
            },
            loadGoodstype() {
                this.$axios.get(this.$httpUrl + '/record/listGoodsType').then(res => res.data).then(res => {
                    console.log(res.code)
                    if (res.code == 200) {
                        this.goodsTypeData = res.data
                    } else {
                        alert()
                        this.$message.error('goodstype获取数据失败')
                    }

                })
            },
            loadPost() {
                this.$axios.post(this.$httpUrl + '/record/select', {
                    pageSize: this.pageSize,
                    pageNum: this.pageNum,
                    param: {
                        name: this.name,
                        goodsType: this.goodsType + '',
                        storage: this.storage + '',
                        roleId: this.user.roleId + '',
                        userId: this.user.id + '',
                        remark: this.remark
                    }
                }).then(res => res.data).then(res => {

                    if (res.code == 200) {
                        this.tableData = res.data
                        this.total = res.total
                    } else {
                        this.$message.error('select获取数据失败')
                    }

                })
            },
        },
        beforeMount() {
            this.loadStorage()
            this.loadGoodstype()
            this.loadPost()

        }
    }
</script>

<style scoped>
    @import "/src/assets/1.css";

    .el-table {
        border: 2px solid #d2d0d0;
        border-radius: 50px;
    }

    .el-input >>> .el-input__inner {
        width: 150px;
        border-radius: 20px;
    }

    .el-select >>> .el-input__inner {
        border-radius: 20px;
        margin-left: 10px;
        width: 150px;
    }

    .el-button {
        border: 0;
        margin-left: 10px;
        font-size: 17px;

    }

    .el-pagination {
        padding: 20px 0;
        z-index: 500;
        position: fixed;
        bottom: 0;
        width: 100%;
        color: black;
    }

</style>