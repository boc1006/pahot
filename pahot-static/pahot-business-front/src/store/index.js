import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

let storeObj = {
  strict: process.env.NODE_ENV !== 'production',
  state: {
    user: {},
    /*
     * Int isLogin
     * -1：未知，0：未登录，1：已登录
    */
    login: -1
  },
  getters: {
    isLogin: state => state === 1,
    getUser: state => state.user
  },
  mutations: {
    logout (state) {
      state.login = 0
    },
    login (state, userinfo, permission) {
      state.login = 1
      state.user.userInfo = userinfo
      state.user.permission = permission
    }
  },
  actions: {
    login ({commit}, userinfo, permission) {
      commit('login', userinfo, permission)
    },
    logout ({commit}) {
      commit('logout')
    }
  }
}

// 子模块
storeObj.modules = {}

const store = new Vuex.Store(storeObj)

export default store
