/**
 * 这是一个公共的工具js
 * @type {Object}
 */


var app = new Object();
(function () {
  checkIsLongin();

  function checkIsLongin() {
    if (window.parent != window) {
      //是子页面
      var thisHref = window.location.href;
      if (thisHref.indexOf("login.ak") != -1) {
        //是登录页面
        window.parent.location.reload();
      }
    }
  }


  /**
   * 递归构建菜单列表树
   * @param list
   * @returns {Array}
   */
  function buildTreeData(list) {
    if (!Array.isArray(list)) {
      return [];
    }
    var tree = [];
    for (var i = 0; i < list.length; i++) {
      if (list[i].parentId == 0) {
        var temp = list[i];
        temp = $.extend({}, temp);
        _buildTreeChild(temp, list);
        tree.push(temp);
      }
    }
    app.sortListDesc(tree, "sort");
    return tree;
  }

  function _buildTreeChild(pObj, list) {
    if (!Array.isArray(list)) {
      return [];
    }
    var tree = [];
    for (var i = 0; i < list.length; i++) {
      if (list[i].parentId == pObj.id) {
        var temp = list[i];
        temp = $.extend({}, temp);
        _buildTreeChild(temp, list);
        tree.push(temp);
      }
    }
    app.sortListDesc(tree, "sort");
    if (tree.length > 0) {
      pObj.children = tree;
    }
  }

  /**
   * 排序 倒叙
   */
  app.sortListDesc = function (list, key) {
    if (!Array.isArray(list)) {
      console.warn("排序对象不是一个数组")
      return;
    }
    list.sort(function (a, b) {
      if (typeof a === "object" && typeof b === "object") return b[key] - a[key];
      return b - a;
    })
  }
  app.sortList = function (list, key) {
    if (!Array.isArray(list)) {
      console.warn("排序对象不是一个数组")
      return;
    }
    list.sort(function (a, b) {
      if (typeof a === "object" && typeof b === "object") return a[key] - b[key];
      return a - b;
    })
  }

  app.buildTreeData = buildTreeData;

  /**
   * 判断变量是否为空和空字符串
   * @param value
   * @returns {boolean}
   */
  app.isNull = function (value) {
    if (value == null) {
      return true;
    }
    return false;
  }

  /**
   * 判断变量是否是null 或 空字符串
   * @param value
   * @returns {boolean}
   */
  app.isEmpty = function (value) {
    if (this.isNull(value)) return true;
    if (value == "") return true;
    return false;
  }


  /**
   * 自动执行按钮权限检查
   */
  window.addEventListener("load", function () {
    // 检查当前也是否是登录页
    checkIsLongin();

    if (window["hrightsCodes"] == null) {
      if (window == window.parent) {
        return;
      }
      console.log("此页面没有权限数组，请在jsp页面上添加 var hrightsCodes = ${hrightsCodes};");
      return;
    }
    setTimeout(function () {
      app.checkPermissions();

      setInterval(function () {
        app.checkPermissions();
      }, 1000);

    }, 100);
  })
  /**
   * 检查页面上的 权限
   */
  app.checkPermissions = function checkPermissions() {
    var nodes = $("[data-rights]");
    for (var i = 0; i < nodes.length; i++) {
      var temp = $(nodes[i]);
      var ri = temp.attr("data-rights");
      if (!app.hasPer(ri)) {
        temp.remove();
      }
    }
  }

  /**
   * 判断页面是否具有特定的权限
   * @param per
   * @returns {boolean}
   */
  app.hasPer = function (per) {
    if(window.is_supper_user == 1){
      return true;
    } else if(window.is_supper_user == 0){

    } else {
      var supper = $.cookie("isSupper");
      if(supper == 1){
        window.is_supper_user = 1;
        return true;
      }else{
        window.is_supper_user == 0;
      }
    }
    if (app.isEmpty(window["hrightsCodes"])) {
      console.log("此页面没有权限数组，请在jsp页面上添加 var hrightsCodes = ${hrightsCodes};");
      return false;
    }
    for (var i = 0; i < hrightsCodes.length; i++) {
      if (hrightsCodes[i].toUpperCase() == per.toUpperCase()) {
        return true;
      }
    }
    return false;
  }


  /**
   * ajax 全局拦截器， 主要拦截一步请求时 session失效，返回meta标签，进行处理
   */
  $(document).ajaxComplete(function (event, xhr, options) {
    var str_Text = xhr.responseText;
    // mate 标签解析
    if (app.isEmpty(str_Text)) {
      return;
    }
    if (str_Text.indexOf("<meta") != -1 && str_Text.indexOf('http-equiv="refresh"') != -1) {
      window.parent.location.reload();
    }
  });


  // 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
  Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
      "M+": this.getMonth() + 1, //月份
      "d+": this.getDate(), //日
      "h+": this.getHours(), //小时
      "m+": this.getMinutes(), //分
      "s+": this.getSeconds(), //秒
      "q+": Math.floor((this.getMonth() + 3) / 3), //季度
      "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
      if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
  }

  /**
   * 日期格式化
   */
  app.dateFormat = function (value) {
    // console.log(value, typeof value);
    if (typeof value == "number") {
      if (value == 0) {
        return "";
      }
      value = value.toString();
      return value.slice(0, 4) + "-" + value.slice(4, 6) + "-" + value.slice(6, 8);
    }
    if (typeof value == "object") {
      return new Date(value).Format("yyyyMMddhhmmss");
    }
    return value;
  }

  /*************对layer的封装start*****************/

  app.msg = function (msg) {
    if (layer) {
      layer.msg(msg);
    } else {
      $.ligerDialog.waitting(data.info);
      setTimeout(function () {
        $.ligerDialog.closeWaitting();
      }, 1000);
    }
  };

  app.open = function (option) {
    var height = $(window).height();
    if(height < 600){
      height = height * 0.8;
    }
    var defaultOption = {
      type:1,
      area: ["400px", height +"px"],
    };
    var layerOption = $.extend(defaultOption,option);
    if(option.area){
      if(option.area[0]){
        layerOption.area[0] = option.area[0];
      }else{
        layerOption.area[0] ="400px";
      }
      if(option.area[1]){
        layerOption.area[1] = option.area[1];
      }else{
        layerOption.area[1] = height +"px";
      }
    }
    layer.open(layerOption);
  }

  /*************对layer的封装end*****************/

  /***
   * cookie 的操作方法
   * @param name
   * @param value
   * @param options
   * @returns {*}
   */
  jQuery.cookie = function(name, value, options) {
    if (typeof value != 'undefined') { // name and value given, set cookie
      options = options || {};
      if (value === null) {
        value = '';
        options.expires = -1;
      }
      var expires = '';
      if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
        var date;
        if (typeof options.expires == 'number') {
          date = new Date();
          date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
        } else {
          date = options.expires;
        }
        expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE
      }
      var path = options.path ? '; path=' + options.path : '';
      var domain = options.domain ? '; domain=' + options.domain : '';
      var secure = options.secure ? '; secure' : '';
      document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
    } else { // only name given, get cookie
      var cookieValue = null;
      if (document.cookie && document.cookie != '') {
        var cookies = document.cookie.split(';');
        for (var i = 0; i < cookies.length; i++) {
          var cookie = jQuery.trim(cookies[i]);
          // Does this cookie string begin with the name we want?
          if (cookie.substring(0, name.length + 1) == (name + '=')) {
            cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
            break;
          }
        }
      }
      return cookieValue;
    }
  };



  app.deepClone = function (obj){
    var result;
    var oClass=isClass(obj);
    if(oClass==="Object"){
      result={};
    }else if(oClass==="Array"){
      result=[];
    }else{
      return obj;
    }
    for(var key in obj){
      var copy=obj[key];
      if(isClass(copy)=="Object"){
        result[key]=arguments.callee(copy);//递归调用
      }else if(isClass(copy)=="Array"){
        result[key]=arguments.callee(copy);
      }else{
        result[key]=obj[key];
      }
    }
    return result;
  }

  //判断对象的数据类型
  function isClass(o){
    if(o===null) return "Null";
    if(o===undefined) return "Undefined";
    return Object.prototype.toString.call(o).slice(8,-1);
  }


})()


