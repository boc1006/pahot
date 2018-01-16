$(function () {
  window['grid'] = $("#maingrid").ligerGrid({
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
          return "待审核";
        }
      },
      {display: '排序', name: 'sort', width: 60},
      {display: '备注', name: 'remark',},
      {
        display: '操作', name: 'caozuo', minWidth: 150, render: function (item, index, value) {
          var str = "";
          if (app.hasPer("VERIFY")) {
            str += '<a class="btn-link openState" onclick="showUserInfo(' + index + ')">审核</a>&nbsp;&nbsp;';
          }
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
    }
  });

  $("#pageloading").hide();
})

function showUserInfo(rowid) {
  // var row = grid.getRow(rowid);
  layer.open({
    area:["500px","600px"],
    btn:["通过","不通过"],
    content:$(".layer_pop_win_box")[0].innerHTML,
    yes:function () {

    },
    btn2:function(){
      return false;
    }
  })
}

function verifyUserApply(id) {
  $.ajax({
    url:"",
    type:'POST',
    data: {
      id:id
    },
    success:function (rst) {
      if(rst.status){
        grid.reload();
      }else{
        layer.msg(rst.info);
      }

    },
    error:function () {
      layer.msg("网络异常");
    }
  })
}