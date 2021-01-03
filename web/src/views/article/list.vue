<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-form
          :model="queryParams"
          ref="queryForm"
          :inline="true"
          v-show="showSearch"
          label-width="68px"
        >
          <el-form-item label="标题" prop="username">
            <el-input
              v-model="queryParams.username"
              placeholder="请输入资讯标题"
              clearable
              size="small"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="作者" prop="phonenumber">
            <el-input
              v-model="queryParams.phonenumber"
              placeholder="请输入作者名称"
              clearable
              size="small"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-select
              v-model="queryParams.status"
              placeholder="用户状态"
              clearable
              size="small"
              style="width: 240px"
            >
              <el-option
                v-for="dict in statusOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="时间">
            <el-date-picker
              v-model="dateRange"
              size="small"
              style="width: 240px"
              value-format="yyyy-MM-dd"
              type="daterange"
              range-separator="-"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            ></el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button
              type="cyan"
              icon="el-icon-search"
              size="mini"
              @click="handleQuery"
              >搜索</el-button
            >
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
              >重置</el-button
            >
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:user:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:user:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:user:remove']"
          >删除</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>
    <el-table
      v-loading="listLoading"
      :data="list"
      border
      highlight-current-row
      style="width: 100%"
    >
      <el-table-column
        min-width="300px"
        label="时间"
        prop="intime"
        align="center"
      />
      <el-table-column
        min-width="300px"
        label="作者"
        prop="author"
        align="center"
      />
      <el-table-column
        min-width="300px"
        label="状态"
        prop="state"
        align="center"
      >
        <template slot-scope="scope">
          <el-tag
            :type="
              scope.row.state == 2
                ? 'info'
                : scope.row.state == 3
                ? 'success'
                : ''
            "
          >
            {{ scope.row.state | statusFilter }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column min-width="300px" label="标题">
        <template slot-scope="{ row }">
          <router-link :to="'/article/edit/' + row.articleid" class="link-type">
            <span>{{ row.title }}</span>
          </router-link>
        </template>
      </el-table-column>

      <el-table-column align="center" label="操作" width="120">
        <template slot-scope="scope">
          <router-link :to="'/article/edit/' + scope.row.id">
            <el-button type="primary" size="small" icon="el-icon-edit">
              修改
            </el-button>
          </router-link>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.currentPage"
      :limit.sync="queryParams.totalSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import { listArticle } from "@/api/article/article";

export default {
  filters: {
    statusFilter(status) {
      switch (status) {
        case 2:
          return "草稿";
          break;
        case 3:
          return "已发布";
          break;
        default:
          return "状态异常";
          break;
      }
    },
  },
  data() {
    return {
      list: [],
      total: 0,
      // 显示搜索条件
      showSearch: true,
      listLoading: false,
      queryParams: {
        currentPage: 1,
        totalSize: 10,
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      this.listLoading = true;
      listArticle(this.queryParams)
        .then((res) => {
          this.list = res.data.data.records;
          this.total = res.data.data.total;
          this.listLoading = false;
        })
        .catch((e) => {
          this.listLoading = false;
        });
    },
    handleAdd(){
      
    }
  },
};
</script>

<style scoped>
.edit-input {
  padding-right: 100px;
}
.cancel-btn {
  position: absolute;
  right: 15px;
  top: 10px;
}
</style>
