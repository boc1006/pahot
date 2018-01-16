<template>
  <div id="app" class="container_body">
    <p style="font-size: 2rem;">sdfgsdgdfsgdfg</p>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'app',
  data () {
    return {
    }
  },
  methods: {
  },
  mounted () {
    this.$nextTick(() => {
      this.$store.dispatch('logout')
      console.log(111, this.$store)
    })
    var that = this
    setTimeout(function () {
      that.$store.dispatch('login', {username: '', pwd: ''}, 1)
    }, 2000)
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
