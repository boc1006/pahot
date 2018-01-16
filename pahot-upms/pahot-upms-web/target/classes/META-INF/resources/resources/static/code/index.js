$(function () {
 // hrightsCodes = ["ADD", "EDIT", "DISABLE", "ENABLE", "DELETE"];

  /**
   * 初始化，grid
   * @type {*|jQuery}
   */
  window['grid'] =
    $("#maingrid").ligerGrid({
      url: "getCodeList.sec",
      height: '100%',
      width: "auto",
      usePager: true,// 启用分页
      columns: [
        {display: '系统编号', name: 'sid', width: 80,},
        {display: '对照字段', name: 'field',},
        {display: '对照字段名称', name: 'fieldname',},
        {display: '代码', name: 'code'},
        {display: '代码描述', name: 'codedesc'},
        {
          display: '状态', name: 'enabled', width: 60, render: function (item, index, value) {
            if (value == "01") {
              return "正常";
            } else if (value == "00") {
              return "禁用"
            }
          }
        }, {
          display: '编辑模式', name: 'editmode', width: 60, render: function (item, index, value) {
            if (value == "01") {
              return "可编辑";
            } else if (value == "00") {
              return "禁止编辑"
            }
          }
        },
        {display: '排序', name: 'sort', width: 60,},
        {display: '备注', name: 'remark',},
        {
          display: '操作', name: 'caozuo', minWidth: 150, render: function (item, index, value) {
            if (item.editmode == "00") {
              //如果已经禁用编辑则不可操作
              return;
            }
            var str = "";
            if (app.hasPer("EDIT")) {
              str += '<a class="btn-link openState" onclick="editItem(' + item.id + ')">修改</a>&nbsp;&nbsp;';
            }
            if (item.enabled == "01" && app.hasPer("DISABLE")) {
              str += '<a class="btn-link openEdit1" onclick="updateItemState(' + item.id + ',' + 1 + ')">禁用</a>&nbsp;&nbsp;';
            } else if (item.enabled == "00" && app.hasPer("ENABLE")) {
              str += '<a class="btn-link openEdit1" onclick="updateItemState(' + item.id + ',' + 2 + ')">启用</a>&nbsp;&nbsp;';
            }
            if (item.editmode == "01" && app.hasPer("DISABLE")) {
              str += '<a class="btn-link openEdit1" onclick="updateItemState(' + item.id + ',' + 3 + ')">禁用编辑</a>&nbsp;&nbsp;';
            }
            // } else if (item.editmode == "00" && app.hasPer("ENABLE")) {
            //   str += '<a class="btn-link openEdit1" onclick="updateItemState(' + item.id + ',' + 4 + ')">启用编辑</a>&nbsp;&nbsp;';
            // }
            // if (app.hasPer("DELETE")) {
            //   str += '<a class="btn-link openEdit1" onclick="updateItemState(' + item.id + ',' + 5 + ')">删除</a>';
            // }
            return str;
          }
        }
      ],
      data: [],
      pageSize: 30,
      rownumbers: true,
      parms: {
        sysid: "",
        key: ""
      },
      toolbar: {
        items: [
          {text: '增加', click: itemclick, icon: 'add', code: "ADD"}
        ]
      }
    });

  $("#pageloading").hide();

  function itemclick(item) {
    var form = liger.get("form1");
    form.setData({
      type: "",
      field: "",
      fieldname: "",
      code: "",
      codedesc: "",
      remark: "",
      sort: "",
    });
    openWindow(1, "添加系统");
  }

  //bug 暴力解决
  openWindow(1, "添加系统");
  dialog.hide();
  getDataAndInitSelect();
  initKeyInput();
});

/**
 * 获取数据并初始化select
 * 系统选择下拉框
 */
var sysComboBox;

function getDataAndInitSelect() {

  $.post("../sys/getSysList.sec", {state: "01"}, function (data) {
    var list = [{text: "请选择", id: 0}];
    for (var i = 0; i < data.Rows.length; i++) {
      var temp = {
        text: data.Rows[i].name,
        id: data.Rows[i].id,
      };
      list.push(temp);
    }
    sysComboBox = $("#systemList").ligerComboBox({
      width: 300,
      data: list,
      value: '0',
      initIsTriggerEvent: false,
      onSelected: function (value) {

      }
    });
  });
}

/**
 * 初始化系统变量KEY 的输入框
 */
function initKeyInput() {
  window.varKeyTextBox = $("#txtKey").ligerTextBox({nullText: '对照字段', currency: false, width: 300});
}

var dialog;

function openWindow(state, tittle, editSysid) {
  if (dialog) {
    dialog.State = state;
    dialog.editSysid = editSysid;
    dialog.show();
    return;
  }
  dialog = $.ligerDialog.open({
    target: $("#target1"),
    isHidden: true, // 关闭是只影藏对话框
    isDrag: false, // 禁用拖动
    allowClose: true,
    title: tittle,
    width: 400,
    buttons: [{
      text: '确定', onclick: function (item, dialog) {
        var form = liger.get('form1');
        if (form.valid()) {
          var formData = form.getData();
          formData.state = dialog.State;
          if (dialog.State == 2) {
            //编辑
            formData.id = dialog.editSysid;
          }
          submitAddOrEditData(formData);
        } else {
          form.showInvalid();
        }
      }
    }, {
      text: '取消', onclick: function (item, dialog) {
        dialog.hidden();
      }
    }]
  });
}


/**
 * 提交编辑或新增的数据
 */
function submitAddOrEditData(formData) {
  var manager = $.ligerDialog.waitting('正提交中,请稍候...');
  $.ajax({
    url: "addoredit.sec",
    type: "post",
    data: formData,
    success: function (data) {
      if (data.status) {
        manager.close();
        grid.reload();
        dialog.hidden();
      } else {
        manager.close();
        $.ligerDialog.waitting(data.info);
        setTimeout(function () {
          $.ligerDialog.closeWaitting();
        }, 1000);
      }
    },
    error: function (err) {
      $.ligerDialog.waitting("提交失败");
      setTimeout(function () {
        $.ligerDialog.closeWaitting();
      }, 1000);
    }
  })
}

/**
 * 编辑行
 * @param id
 */
function editItem(id) {
  if (!app.hasPer("EDIT")) {
    $.ligerDialog.success('您暂无操作权限！')
    return;
  }

  var line = getLineForId(id);

  var form = liger.get("form1");
  form.setData({
    type: line.sid,
    field: line.field,
    fieldname: line.fieldname,
    code: line.code,
    codedesc: line.codedesc,
    remark: line.remark,
    sort: line.sort,
  });

  openWindow(2, "修改系统", id);
}

function getLineForId(id) {
  var dataArr = grid.getData();
  for (var i = 0; i < dataArr.length; i++) {
    if (dataArr[i].id == id) {
      return dataArr[i];
    }
  }
  return null;
}

/**
 * 禁用行   ENABLE
 * @param id
 */
function updateItemState(id, state) {
  var manager = $.ligerDialog.waitting('正提交中,请稍候...');
  $.ajax({
    url: "updatestate.sec",
    type: "post",
    data: {
      id: id,
      state: state
    },
    success: function (data) {
      if (data.status) {
        grid.reload();
        manager.close();
      } else {
        manager.close();
        $.ligerDialog.waitting(data.info);
        setTimeout(function () {
          $.ligerDialog.closeWaitting();
        }, 1000);
      }
    },
    error: function (err) {
      $.ligerDialog.error("修改失败");
    }
  })
}


/**
 * 下拉选项框的数据
 *
 * ，没有找到一步设置值的方法，故用ajax发起同步请求
 *
 * @returns {*[]}
 */
function getCountryData() {
  var rstdata;
  $.ajax({
    url: '../sys/getSysList.sec',
    //data:{name:value},
    cache: false,
    async: false,
    type: "post",
    dataType: 'json/xml/html',
    success: function (result) {

    },
    error: function (err) {
      if (err.readyState == 4 && err.status == 200) {
        var rst = JSON.parse(err.responseText);
        rstdata = rst.Rows;
      }
    }
  });
  for (var i = 0; i < rstdata.length; i++) {
    rstdata[i].sid = rstdata[i].id;
  }
  return rstdata;

  //
  //
  //
  // return [
  //   {Name: '管理系统', Code: '01'},
  //   {Name: '业务系统', Code: '02'},
  //   {Name: '运维系统', Code: '03'}
  // ];
}

/**
 * 表头搜索方法
 */
function f_search() {
  //获取选择的系统id
  var sysid = sysComboBox.getValue();
  if (sysid == 0) {
    sysid = "";
  }
  //获取用户输入的KEY
  var keyText = varKeyTextBox.getValue();

  //设置grid的ajax参数
  grid.setParm("sysid", sysid);
  grid.setParm("key", keyText);
  grid.reload();
}