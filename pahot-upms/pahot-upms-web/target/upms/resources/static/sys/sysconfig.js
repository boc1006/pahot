$(function () {

  window['grid'] =
    $("#maingrid").ligerGrid({
      url: "getSysList.sec",
      height: '100%',
      usePager: false,// 启用分页
      columns: [
        {display: '名称', name: 'name', align: 'left', width: 100, minWidth: 60},
        {display: '类型', name: 'type',},
        // {display: 'LOGO', name: 'logo'},
        {display: 'URL信息', name: 'url'},
        {display: '排序', name: 'sort'},
        {
          display: '状态', name: 'state', render: function (item, index, value) {
            if (value == "01") {
              return "正常";
            } else if (value == "00"){
              return "禁用"
            }
          }
        },
        {display: '备注', name: 'remark',},
        {
          display: '操作', name: 'caozuo', render: function (item, index, value) {
            var str = "";
            if(app.hasPer("EDIT")){
              str += '<a class="btn-link openState" onclick="editItem('+item.id+')">修改</a>&nbsp;&nbsp;';
            }
            if(item.state == "01" && app.hasPer("DISABLE")){

              str += '<a class="btn-link openEdit1" onclick="disabledItem('+item.id+')">禁用</a>';
            }else if(item.state == "00"  && app.hasPer("ENABLE")){
              str += '<a class="btn-link openEdit1" onclick="abledItem('+item.id+')">启用</a>';
            }
            return str;
          }
        }
      ],
      data: [],
      pageSize: 30,
      rownumbers: true,
      toolbar: {
        items: [
          {text: '增加', click: itemclick, icon: 'add',code: "ADD"}
        ]
      }
    });

  $("#pageloading").hide();

  function itemclick(item) {
    var form = liger.get('form1');
    form.setData({
      sysid:"",
      name: "",
      url: "",
      type: "",
      sort: "",
      remark: ""
    })
    openWindow(1, "添加系统");
  }
  //bug 暴力解决
  openWindow(1, "添加系统");
  dialog.hide();
});
var dialog;
function openWindow(state , tittle, editSysid) {
  if(dialog){
    dialog.State = state;
    dialog.show();
    return;
  }
  dialog = $.ligerDialog.open({
    target: $("#target1"),
    isHidden: true, // 关闭是只影藏对话框
    isDrag: false, // 禁用拖动
    allowClose:true,
    title: tittle,
    width: 400,
    buttons: [{
      text: '确定', onclick: function (item, dialog) {
        var form = liger.get('form1');
        if (form.valid()) {
          var formData = form.getData();
          formData.state = dialog.State;
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
    url:"addoredit.sec",
    type:"post",
    data:formData,
    success: function (data) {
      grid.reload();
      manager.close();
      $.ligerDialog.waitting(data.info);
      setTimeout(function () { $.ligerDialog.closeWaitting(); }, 1000);
      dialog.hidden();
    },
    error:function (err) {
      $.ligerDialog.waitting("提交失败");
      setTimeout(function () { $.ligerDialog.closeWaitting(); }, 1000);
    }
  })
}

/**
 * 编辑行
 * @param id
 */
function editItem(id) {
  if(!app.hasPer("EDIT")){
    $.ligerDialog.success('您暂无操作权限！')
    return;
  }

  var line = getLineForId(id);

  var form = liger.get("form1");
  form.setData({
    sysid: line.id,
    type: line.type,
    name: line.name,
    url: line.url,
    remark: line.remark,
    sort: line.sort,
  });

  openWindow(2,"修改系统", id);
}

function getLineForId(id) {
  var dataArr = grid.getData();
  for(var i = 0 ; i<dataArr.length;i++){
    if(dataArr[i].id == id){
      return dataArr[i];
    }
  }
  return null;
}

/**
 * 禁用行   ENABLE
 * @param id
 */
function disabledItem(id) {
  if(!app.hasPer("DISABLE")){
    $.ligerDialog.success('您暂无操作权限！')
    return;
  }
  var manager = $.ligerDialog.waitting('正提交中,请稍候...');
  $.ajax({
    url:"disabled.sec",
    type:"post",
    data: {
      id:id
    },
    success: function (data) {
      grid.reload();
      manager.close();
      $.ligerDialog.waitting(data.info);
      setTimeout(function () { $.ligerDialog.closeWaitting(); }, 1000);
    },
    error:function (err) {
      $.ligerDialog.error("系统禁用失败");
    }
  })
}

/**
 * 启用行
 * @param id
 */
function abledItem(id) {
  if(!app.hasPer("ENABLE")){
    $.ligerDialog.success('您暂无操作权限！')
    return;
  }
  var manager = $.ligerDialog.waitting('正提交中,请稍候...');
  $.ajax({
    url:"abled.sec",
    type:"post",
    data: {
      id:id
    },
    success: function (data) {
      grid.reload();
      manager.close();
      $.ligerDialog.waitting(data.info);
      setTimeout(function () { $.ligerDialog.closeWaitting(); }, 1000);
    },
    error:function (err) {
      $.ligerDialog.error("系统启用失败");
    }
  })
}


/**
 * 下拉选项框的数据
 * @returns {*[]}
 */
function getCountryData() {
  return [
    { Name: '管理系统', Code: '01' },
    { Name: '业务系统', Code: '02' },
    { Name: '运维系统', Code: '03' }
  ];
}