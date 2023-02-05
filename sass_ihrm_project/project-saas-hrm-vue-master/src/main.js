import Vue from 'vue'
import 'normalize.css/normalize.css'// A modern alternative to CSS resets
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import '@/styles/index.scss' // global css
import App from './App'
import router from './router'
import store from './store'
import i18n from './lang' // Internationalization
import './icons' // icon
import './errorLog'// error log
import * as filters from './filters' // global filters
import moment from 'moment'
// font-awesome
import 'font-awesome/css/font-awesome.css'
// import './mock' // simulation data
import VueBus from 'vue-bus';
/*
* 注册 - 业务模块
*/
import dashboard from '@/module-dashboard/' // 面板
import saasClients from '@/module-saas-clients/' // 企业管理
import departments from '@/module-departments/' // 企业管理
import employees from '@/module-employees/' // 企业管理
import settings from '@/module-settings/' 
import permissions from '@/module-permissions/' 
import socialSecuritys from '@/module-social-securitys/' // 社保
import attendances from '@/module-attendances/' // 考勤
import salarys from '@/module-salarys/' // 工资
import users from '@/module-users/' // 员工页面


import tools from './utils/common.js'
Vue.prototype.$tools = tools
Vue.use(VueBus);
Vue.use(tools)
Vue.use(dashboard, store)
Vue.use(saasClients,store)
Vue.use(departments,store)
Vue.use(employees,store)
Vue.use(settings,store)
Vue.use(permissions,store)
Vue.use(socialSecuritys,store)
Vue.use(attendances,store)
Vue.use(salarys, store)
Vue.use(users, store)

/*
* 注册 - 组件
*/

// 饿了么
Vue.use(Element, {
  size: 'medium', // set element-ui default size
  i18n: (key, value) => i18n.t(key, value)
})
// 过滤器
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

Vue.config.productionTip = false

Vue.filter('dateformat', function(dataStr, pattern = 'YYYY-MM-DD HH:mm:ss') {
  return moment(dataStr).format(pattern)
})

Vue.prototype.dateFormat = function (row,column,cellValue){
  var date = row[column.property];
  if (date == undefined) {
    return "----";
  }
  return moment(date).format("YYYY-MM-DD");
}


/* eslint-disable */
new Vue({
  el: '#app',
  router,
  store,
  i18n,
  template: '<App/>',
  components: { App }
})
