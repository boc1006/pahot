<template>
  <div id="login">
    <div class="aaa"></div>
  </div>
</template>

<script>
  export default {
    name: 'Login',
    data () {
      return {
        username: '',
        password: ''
      }
    },
    methods: {
      login (userinfo, per) {
        this.$store.dispatch('login', userinfo, per)
      },
      forget () {}
    },
    mounted () {
      this.$nextTick(() => {
        console.log(this.$store)
      })
    }
  }
</script>

<style scoped lang="less">
.container_body{
  background-color: #000;
  .aaa{
    width:500px;
  }
}
</style>
