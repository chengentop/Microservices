<template>
  <div class="container">
    <div class="forms-container">
      <div class="signin-signup">
        <el-form
          ref="loginForm"
          :model="loginForm"
          :rules="loginRules"
          class="form"
        >
          <h3 class="title">后台管理系统</h3>
          <el-form-item prop="username" class="input-field">
            <el-input
              v-model="loginForm.username"
              type="text"
              auto-complete="off"
              placeholder="账号"
            >
              <svg-icon
                slot="prefix"
                icon-class="user"
                class="el-input__icon input-icon"
              />
            </el-input>
          </el-form-item>
          <el-form-item prop="password" class="input-field">
            <el-input
              v-model="loginForm.password"
              type="password"
              auto-complete="off"
              placeholder="密码"
              @keyup.enter.native="handleLogin"
            >
              <svg-icon
                slot="prefix"
                icon-class="password"
                class="el-input__icon input-icon"
              />
            </el-input>
          </el-form-item>
          <!-- <el-form-item prop="code" class="input-field-code">
            <el-input
              v-model="loginForm.code"
              auto-complete="off"
              placeholder="验证码"
              style="width: 66%"
              @keyup.enter.native="handleLogin"
            >
              <svg-icon
                slot="prefix"
                icon-class="validCode"
                class="el-input__icon input-icon"
              />
            </el-input>
            <div class="login-code">
              <img :src="codeUrl" @click="getCode" class="login-code-img" />
            </div>
          </el-form-item> -->
          <el-form-item>
            <el-checkbox
              v-model="loginForm.rememberMe"
              style="margin: 0px 0px 15px 0px"
              >记住密码</el-checkbox
            >
          </el-form-item>
          <el-form-item>
            <el-button
              :loading="loading"
              size="medium"
              type="primary"
              class="btn"
              @click.native.prevent="handleLogin"
            >
              <span v-if="!loading">登 录</span>
              <span v-else>登 录 中...</span>
            </el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>

    <div class="panels-container">
      <div class="panel left-panel">
        <div class="content">
          <h3>New here ?</h3>
          <p>
            Lorem ipsum, dolor sit amet consectetur adipisicing elit. Debitis,
            ex ratione. Aliquid!
          </p>
        </div>
        <img src="../assets/image/register.png" class="image" />
      </div>
    </div>
    <!--  底部  -->
    <div class="el-login-footer">
      <span>Copyright © 2020 chengen.work All Rights Reserved.</span>
    </div>
  </div>
</template>

<script>
import { getCodeImg } from "@/api/login";
import Cookies from "js-cookie";
import { encrypt, decrypt } from "@/utils/jsencrypt";

export default {
  name: "Login",
  data() {
    return {
      codeUrl: "",
      cookiePassword: "",
      loginForm: {
        username: "admin",
        password: "admin123",
        rememberMe: false,
        code: "",
        uuid: "",
      },
      loginRules: {
        username: [
          { required: true, trigger: "blur", message: "用户名不能为空" },
        ],
        password: [
          { required: true, trigger: "blur", message: "密码不能为空" },
        ],
        // code:[
        //   { required: true, trigger: "blur", message: "验证码不能为空" },
        // ]
      },
      loading: false,
      redirect: undefined,
    };
  },
  watch: {
    $route: {
      handler: function (route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true,
    },
  },
  created() {
    // this.getCode();
    this.getCookie();
  },
  methods: {
    getCode() {
      getCodeImg().then((res) => {
        this.codeUrl = "data:image/gif;base64," + res.data.img;
        this.loginForm.uuid = res.data.uuid;
      });
    },
    getCookie() {
      const username = Cookies.get("username");
      const password = Cookies.get("password");
      const rememberMe = Cookies.get("rememberMe");
      this.loginForm = {
        username: username === undefined ? this.loginForm.username : username,
        password:
          password === undefined ? this.loginForm.password : decrypt(password),
        rememberMe: rememberMe === undefined ? false : Boolean(rememberMe),
      };
    },
    handleLogin() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.loading = true;
          if (this.loginForm.rememberMe) {
            Cookies.set("username", this.loginForm.username, { expires: 30 });
            Cookies.set("password", encrypt(this.loginForm.password), {
              expires: 30,
            });
            Cookies.set("rememberMe", this.loginForm.rememberMe, {
              expires: 30,
            });
          } else {
            Cookies.remove("username");
            Cookies.remove("password");
            Cookies.remove("rememberMe");
          }
          this.$store
            .dispatch("Login", this.loginForm)
            .then(() => {
              this.$router.push({ path: this.redirect || "/" });
            })
            .catch(() => {
              this.loading = false;
              this.getCode();
            });
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../assets/styles/login-style";

.el-login-footer {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: rgb(7, 7, 7);
  font-family: Arial;
  font-size: 12px;
  letter-spacing: 1px;
}
.login-code {
  width: 33%;
  height: 30px;
  float: right;
  img {
    cursor: pointer;
    vertical-align: middle;
  }
}
.login-code-img {
  height: 34px;
}
</style>
