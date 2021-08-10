<template>
  <el-card class="form-container">
    <el-form ref="form" :model="form" label-width="150px">
      <el-form-item label="分类名称">
        <el-input size="mini" v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="上级分类">
        <el-select
          size="mini"
          v-model="form.region"
          placeholder="请选择活动区域"
        >
          <el-option label="区域一" value="shanghai"></el-option>
          <el-option label="区域二" value="beijing"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="数量单位">
        <el-input size="mini" type="text" v-model="form.desc"></el-input>
      </el-form-item>
      <el-form-item label="排序">
        <el-input size="mini" type="text" v-model="form.desc"></el-input>
      </el-form-item>
      <el-form-item label="是否显示">
        <el-radio-group size="mini" v-model="form.resource">
          <el-radio label="线上品牌商赞助"></el-radio>
          <el-radio label="线下场地免费"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="是否显示在导航栏">
        <el-radio-group size="mini" v-model="form.resource">
          <el-radio label="线上品牌商赞助"></el-radio>
          <el-radio label="线下场地免费"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="分类图标">
        <ImageUpload
          size="mini"
          :fileType="file.fileType"
          :limit="file.limit"
          :fileSize="file.fileSize"
        ></ImageUpload>
      </el-form-item>
      <el-form-item
        v-for="(filterProductAttr, index) in filterProductAttrList"
        :label="index | filterLabelFilter"
        :key="filterProductAttr.key"
      >
        <el-cascader
          size="mini"
          clearable
          v-model="filterProductAttr.value"
          :options="filterAttrs"
        >
        </el-cascader>
        <!-- <el-button size="mini" style="margin-left: 20px">删除</el-button> -->
      </el-form-item>
      <el-form-item label="关键词">
        <el-input size="mini" type="text" v-model="form.desc"></el-input>
      </el-form-item>
      <el-form-item label="描述">
        <el-input size="mini" type="textarea" v-model="form.desc"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button size="mini" type="primary" @click="onSubmit"
          >立即创建</el-button
        >
        <el-button size="mini">取消</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
import ImageUpload from "@/components/ImageUpload/index";
export default {
  components: {
    ImageUpload,
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 总条数
      total: 0,
      form: {
        name: "",
        region: "",
        date1: "",
        date2: "",
        delivery: false,
        type: [],
        resource: "",
        desc: "",
      },
      file: {
        // 数量限制
        limit: 1,
        // 大小限制(MB)
        fileSize: 5,
      },
      filterProductAttrList: [
        {
          value: [],
        },
      ],
    };
  },
  filters: {
    filterLabelFilter(index) {
      if (index === 0) {
        return "筛选属性：";
      } else {
        return "";
      }
    },
  },
  created() {},
  methods: {
    onSubmit() {
      console.log("submit!");
    },
  },
};
</script>

<style scoped lang="scss">
.form-container {
  width: 800px;
  left: 0;
  right: 0;
  padding: 35px 35px 15px;
  margin: 20px auto;
}
::v-deep .el-form-item {
  margin-bottom: 15px;
}
</style>

