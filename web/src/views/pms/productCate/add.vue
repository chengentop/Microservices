<template>
  <div class="app-container home">
    <product-cate-detail></product-cate-detail>
  </div>
</template>

<script>
import ProductCateDetail from "./components/ProductCateDetail";

export default {
  components: { ProductCateDetail },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 总条数
      total: 0,
    };
  },
  created() {},
  methods: {},
};
</script>
<style scoped lang="scss">
.home {
  overflow-y: hidden;
}
</style>

