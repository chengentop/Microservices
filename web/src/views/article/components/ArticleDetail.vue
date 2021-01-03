<template>
  <div class="createPost-container">
    <el-form
      ref="postForm"
      :model="postForm"
      :rules="rules"
      class="form-container"
    >
      <sticky :z-index="10" :class-name="'sub-navbar ' + postForm.status">
        <el-button
          v-loading="loading"
          style="margin-left: 10px"
          type="success"
          @click="submitForm"
        >
          发布
        </el-button>
        <el-button v-loading="loading" type="warning" @click="draftForm">
          保存
        </el-button>
      </sticky>

      <div class="createPost-main-container">
        <el-row>
          <el-col :span="24">
            <el-form-item style="margin-bottom: 40px" prop="title">
              <MDinput
                v-model="postForm.title"
                :maxlength="100"
                name="name"
                required
              >
                标题
              </MDinput>
            </el-form-item>

            <div class="postInfo-container">
              <el-row>
                <el-col :span="8">
                  <el-form-item
                    label-width="60px"
                    label="作者:"
                    class="postInfo-container-item"
                  >
                    <el-select
                      v-model="postForm.author"
                      :remote-method="getRemoteUserList"
                      filterable
                      default-first-option
                      remote
                      placeholder="Search user"
                    >
                      <el-option
                        v-for="(item, index) in userListOptions"
                        :key="item + index"
                        :label="item"
                        :value="item"
                      />
                    </el-select>
                  </el-form-item>
                </el-col>

                <el-col :span="10">
                  <el-form-item
                    label-width="60px"
                    label="日期:"
                    class="postInfo-container-item"
                  >
                    <el-date-picker
                      v-model="displayTime"
                      type="datetime"
                      format="yyyy-MM-dd HH:mm:ss"
                      placeholder="Select date and time"
                    />
                  </el-form-item>
                </el-col>
              </el-row>
            </div>
          </el-col>
        </el-row>
        <el-form-item
          label="缩略图:"
          prop="image_uri"
          style="margin-bottom: 30px"
        >
          <Upload v-model="postForm.image_uri" />
        </el-form-item>
        <el-form-item
          style="margin-bottom: 40px"
          label-width="60px"
          label="概述:"
        >
          <el-input
            v-model="postForm.summary"
            :rows="1"
            type="textarea"
            class="article-textarea"
            autosize
            placeholder="Please enter the content"
          />
          <span v-show="contentShortLength" class="word-counter"
            >{{ contentShortLength }}words</span
          >
        </el-form-item>

        <el-form-item label="关键字:" style="margin-bottom: 30px">
          <el-tag
            :key="tag"
            v-for="tag in dynamicTags"
            closable
            :disable-transitions="false"
            @close="handleClose(tag)"
          >
            {{ tag }}
          </el-tag>
          <el-input
            class="input-new-tag"
            v-if="inputVisible"
            v-model="inputValue"
            ref="saveTagInput"
            size="small"
            @keyup.enter.native="handleInputConfirm"
            @blur="handleInputConfirm"
          >
          </el-input>
          <el-button
            v-else
            class="button-new-tag"
            size="small"
            @click="showInput"
            >添加关键字</el-button
          >
        </el-form-item>

        <el-form-item prop="content" style="margin-bottom: 30px">
          <Tinymce ref="editor" v-model="postForm.content" :height="400" />
        </el-form-item>
      </div>
    </el-form>
  </div>
</template>

<script>
//组件
import Tinymce from "@/components/Tinymce";
import MDinput from "@/components/MDinput";
import Sticky from "@/components/Sticky"; // 粘性header组件
import Upload from "@/components/Upload/SingleImage3";
// 接口
import { addArticle, getArticle, updateArticle } from "@/api/article/article";
const defaultForm = {
  status: "draft",
  title: "", // 文章题目
  content: "", // 文章内容
  summary: "", // 文章摘要
  image_uri: "", // 文章图片
  pubtime: undefined, // 前台展示时间
  id: undefined,
  comment_disabled: false,
  importance: 0,
};

export default {
  name: "ArticleDetail",
  components: { Tinymce, MDinput, Sticky, Upload },
  props: {
    isEdit: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    const validateRequire = (rule, value, callback) => {
      if (value === "") {
        this.$message({
          message: rule.field + "为必传项",
          type: "error",
        });
        callback(new Error(rule.field + "为必传项"));
      } else {
        callback();
      }
    };
    
    return {
      postForm: Object.assign({}, defaultForm),
      loading: false,
      userListOptions: ["admin"],
      rules: {
        image_uri: [{ validator: validateRequire }],
        title: [{ validator: validateRequire }],
        content: [{ validator: validateRequire }],
      },
      dynamicTags: [],
      inputVisible: false,
      inputValue: "",
    };
  },
  computed: {
    contentShortLength() {
      return this.postForm.summary.length;
    },
    displayTime: {
      get() {
        return +new Date(this.postForm.pubtime);
      },
      set(val) {
        this.postForm.pubtime = new Date(val);
      },
    },
  },
  created() {
    if (this.isEdit) {
      const articleid = this.$route.params && this.$route.params.articleid;
      this.fetchData(articleid);
    }
  },
  methods: {
    fetchData(id) {
      getArticle(id)
        .then((res) => {
          console.log(res);
          this.postForm = res.data.article;
          this.displayTime = this.postForm.pubtime;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    submitForm() {
      this.$refs.postForm.validate((valid) => {
        if (valid) {
          this.loading = true;
          if (this.postForm.articleid == undefined) {
            addArticle(this.postForm)
              .then((res) => {
                if (res.code == 200) {
                  this.msgSuccess("发布文章成功");
                  this.loading = false;
                } else {
                  this.msgError("发布失败");
                  this.loading = false;
                }
              })
              .catch((e) => {
                this.loading = false;
              });
          } else {
            updateArticle(this.postForm)
              .then((res) => {
                if (res.code == 200) {
                  this.msgSuccess("发布文章成功");
                  this.loading = false;
                } else {
                  this.msgError("发布失败");
                  this.loading = false;
                }
              })
              .catch((e) => {
                this.loading = false;
              });
          }
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    draftForm() {
      console.log(this.postForm);
      if (
        this.postForm.content.length === 0 ||
        this.postForm.title.length === 0
      ) {
        this.$message({
          message: "请填写必要的标题和内容",
          type: "warning",
        });
        return;
      }
      this.$message({
        message: "保存成功",
        type: "success",
        showClose: true,
        duration: 1000,
      });
      this.postForm.status = "draft";
    },
    getRemoteUserList(query) {
      searchUser(query).then((response) => {
        if (!response.data.items) return;
        this.userListOptions = response.data.items.map((v) => v.name);
      });
    },
    handleClose(tag) {
      this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
    },

    showInput() {
      this.inputVisible = true;
      this.$nextTick((_) => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },

    handleInputConfirm() {
      let inputValue = this.inputValue;
      if (inputValue) {
        this.dynamicTags.push(inputValue);
      }
      this.inputVisible = false;
      this.inputValue = "";
    },
  },
};
</script>

<style lang="scss" scoped>
@import "~@/assets/styles/mixin.scss";

.createPost-container {
  position: relative;

  .createPost-main-container {
    padding: 40px 45px 20px 50px;

    .postInfo-container {
      position: relative;
      @include clearfix;
      margin-bottom: 10px;

      .postInfo-container-item {
        float: left;
      }
    }
  }

  .word-counter {
    width: 40px;
    position: absolute;
    right: 10px;
    top: 0px;
  }
}

.article-textarea ::v-deep {
  textarea {
    padding-right: 40px;
    resize: none;
    border: none;
    border-radius: 0px;
    border-bottom: 1px solid #bfcbd9;
  }
}
.el-tag + .el-tag {
  margin-left: 10px;
}
.button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}
.input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}
</style>
