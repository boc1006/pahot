$(function () {
  // hrightsCodes = ["ADD", "EDIT", "DISABLE", "ENABLE", "DELETE"];

  /**
   * 初始化，grid
   * @type {*|jQuery}
   */
  window['grid'] =
    $("#maingrid").ligerGrid({
      url: "messagelist.sec",
      height: '100%',
      width: "auto",
      usePager: true,// 启用分页
      columns: [
        {display: '编号', name: 'id', width: 80,},
        {
          display: '消息内容', name: 'content', render: function (item, index, value) {
            return "<div style='text-decoration: underline;color:#1786ef;cursor: pointer;' onclick='openmsg(" + item.id + ")'>" + value + "</div>";
          }
        },
        {
          display: '来源编号', name: 'source',
        },
        {
          display: '队列名称', name: 'queuename'
        },
        {
          display: '重发次数', name: 'repeatcount'
        },
        {
          display: '是否死亡', name: 'isdead', width: 60, render: function (item, index, value) {
            if (value == "01") {
              return "未死亡";
            } else if (value == "02") {
              return "已死亡"
            }
          }
        }
        ,
        {
          display: '状态', name: 'state', width: 60, render: function (item, index, value) {
            // 01待确认,02已确认,03发送中,04已完成',
            if (value == "01") {
              return "待确认";
            } else if (value == "02") {
              return "已确认"
            } else if (value == "03") {
              return "发送中"
            } else if (value == "04") {
              return "已完成"
            }
          }
        },
        {
          display: '延时时间', name: 'delaytime', width: 60,
        },
        {
          display: '备注', name: 'remark',
        },
        {
          display: '操作', name: 'caozuo', minWidth: 150, render:

            function (item, index, value) {
              var str = "";
              if (item.state == "03" && app.hasPer("RESEND")) {
                str += '<a class="btn-link openState" onclick="reSend(' + item.id + ')">重发</a>';
              }
              return str;
            }
        }
      ],
      data: [],
      pageSize:
        30,
      rownumbers: true,
      parms: {
        id: "",
        source: "",
        queuename: "",
        isdead: "",
        state: ""
      },
    })
  ;

  $("#pageloading").hide();

});


function reSend(id) {
  var manager = $.ligerDialog.waitting('正提交中,请稍候...');
  $.post("./resend.sec", {msgid: id}, function (rst) {
    if (rst.status) {
      grid.reload();
      manager.close();
    } else {
      manager.close();
      $.ligerDialog.waitting(data.info);
      setTimeout(function () {
        $.ligerDialog.closeWaitting();
      }, 1000);
    }
  })
}

function openmsg(id) {
  var arr = grid.getData();
  for (var i = 0; i < arr.length; i++) {
    if (arr[i].id == id) {
      layer.open({
        type: 0,
        content: "<div>" + arr[i].content + "</div>",
        title: "消息【" + id + "】的内容",
        btn: [],
        shadeClose: true
      })
      break;
    }
  }
}


/**
 * 下拉选项框的数据
 *
 * ，没有找到一步设置值的方法，故用ajax发起同步请求
 *
 * @returns {*[]}
 */
function getCountryData(val) {
  if (val == 1) {
    //01未死亡,02已死亡
    return [
      {Name: '请选择', Code: '0'},
      {Name: '未死亡', Code: '01'},
      {Name: '已死亡', Code: '02'}
    ];
  } else if (val == 2) {
    // 01待确认,02已确认,03发送中,04已完成
    return [
      {Name: '请选择', Code: '0'},
      {Name: '待确认', Code: '01'},
      {Name: '已确认', Code: '02'},
      {Name: '发送中', Code: '03'},
      {Name: '已完成', Code: '04'}
    ];
  } else if (val == 3) {
    return [
      {Name: '100', Code: '100'},
      {Name: '300', Code: '300'},
      {Name: '600', Code: '600'},
      {Name: '900', Code: '900'}
    ];
  }

}

/**
 * 表头搜索方法
 */
function f_search(val) {

  if (val == 1) {
    //获取用户输入的KEY
    var keyText = liger.get("form1").getData();
    //设置grid的ajax参数
    grid.setParm("id", keyText.mesageid);
    grid.setParm("source", keyText.source);
    grid.setParm("queuename", keyText.queuename);
    grid.setParm("isdead", (keyText.isdead == 0 ? "" : keyText.isdead));
    grid.setParm("state", (keyText.state == 0 ? "" : keyText.state));
    grid.reload();
    return;
  } else if (val == 2) {
    //获取用户输入的KEY
    var form = liger.get("search2");
    if (!form.valid()) {
      form.showInvalid();
      return;
    }
    var keyText = form.getData();
    if (app.isEmpty(keyText.queuename)) {
      layer.msg("请输入队列名称");
      return;
    }
    if (app.isEmpty(keyText.size)) {
      layer.msg("请选择数量");
      return;
    }
    var manager = $.ligerDialog.waitting('正提交中,请稍候...');
    $.ajax({
      url: "./resendall.sec",
      data: {queuename: keyText.queuename, size: keyText.size},
      type: "post",
      success: function (data) {
        if (data.status) {
          grid.reload();
          manager.close();
          layer.msg("重发成功！");
        } else {
          manager.close();
          $.ligerDialog.waitting(data.info);
          setTimeout(function () {
            $.ligerDialog.closeWaitting();
          }, 1000);
        }
      },
      error: function () {
        manager.close();
        $.ligerDialog.waitting("网络异常");
        setTimeout(function () {
          $.ligerDialog.closeWaitting();
        }, 1000);
      }
    })

    return;
  }


}