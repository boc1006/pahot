var zTreeObj;
$(function () {
  $("#layout").ligerLayout({leftWidth: 200});


  function initTree(selector, data, selectCallback) {
    //树
    $(selector).ligerTree({
      data: data,
      checkbox: false,
      slide: false,
      nodeWidth: 150,
      attribute: ['name', 'sysId'],
      render: function (a) {
        var str = a.name;
        if (a.state == "00") {
          str += "(已禁用)";
        }
        if (a.outdate == 1 && a.validity != 0) {
          str += "(已过期)";
        }
        return str;
      },
      onSelect: function (node) {
        selectCallback && selectCallback(node);
        // if (dialog.paramsData) {
        //   dialog.paramsData.sid = node.data.id;
        // } else {
        //   dialog.paramsData = {
        //     sid: node.data.id,
        //   }
        // }
      },
      onContextmenu: function (node, e) {
        e.preventDefault();
        menuRright.paramsData = {};
        menuRright.editData = node.data;

        // console.log(node.data);
        if (app.isEmpty(node.data.sysId)) {
          menuRright.paramsData.sysId = node.data.sid;
        } else {
          menuRright.paramsData.sysId = node.data.sysId;

        }
        if (node.data.isMenu != null) {
          menuRright.hide();
          menuAdd.show({top: e.pageY, left: e.pageX});
          return;
        }

        // 设置按钮的启用 或者警用
        if (node.data.state == "00") {
          menuRright.setDisabled(5);
          menuRright.setEnabled(4);
        } else {
          menuRright.setDisabled(4);
          menuRright.setEnabled(5);
        }
        menuAdd.hide();
        menuRright.show({top: e.pageY, left: e.pageX});
      }
    });
    initMenuRClic();
  }

  function initRightsTree(selector, data, isSupper) {

    var setting = {
      check: {
        enable: !isSupper
      },
      callback: {
        beforeExpand: function (treeId, treeNodeJSON) {
          // console.log(arguments)
        },
        onClick: function (event, treeId, treeNode) {
          // if(treeNode.isEnd == true || treeNode.isLoad == true){
          //   return;
          // }
          // $.post("../menu/getRightList.sec", {menuid:treeNode.id}, function (rst) {
          //   for(var i= 0; i< rst.data.length;i++){
          //     rst.data[i].isEnd = true;
          //   }
          //   treeNode.children = rst.data;
          //   treeNode.isLoad = true;
          //   zTreeObj.refresh();
          // })
        }
      }
    };

    zTreeObj = $.fn.zTree.init($(selector), setting, data);
  }


  function getSystemList() {
    $.post("./sysrole.sec", function (data) {
      if (!data.status) {
        layer.msg(data.info);
        return;
      }
      initTree("#systemMenuList", data.data, function (node) {
        //显示菜单信息
        if (node.data.isMenu == 1) {
          return;
        }
        var roleId = node.data.id;
        getMenuList(roleId);
      });
    });
  }

  getSystemList();

  window.getMenuList = function getMenuList(roleId) {
    $.post("./menu_role.sec", {id: roleId}, function (rst) {
      if (!rst.status) {
        layer.msg(rst.info);
        return;
      }
      if (rst.isSupper) {
        $("#submitData").hide();
      } else {
        $("#submitData").attr("data-role-id", roleId);
        $("#submitData").show();
      }
      initRightsTree("#rightList", rst.data, rst.isSupper);
    })
  }

  /**
   * 菜单树的右键菜单
   */
  function initMenuRClic() {
    if (window.menuRright) {
      return;
    }
    window.menuRright = $.ligerMenu({
      top: 100, left: 100, width: 120, items:
        [
          {
            text: '新增', click: function () {
              openWindow();
            }, icon: 'add', id: 8
          },
          {
            text: '修改', click: function () {
              openWindow(menuRright.editData);
            }, icon: 'add', id: 6
          },
          {
            text: '禁用', click: function () {
              updateRoleState(menuRright.editData.id, "00");
            }, icon: 'add', id: 5
          },
          {
            text: '启用', click: function () {
              updateRoleState(menuRright.editData.id, "01");
            }, icon: 'add', disable: true, id: 4
          },
        ]
    });
    window.menuAdd = $.ligerMenu({
      top: 100, left: 100, width: 120, items:
        [
          {
            text: '新增', click: function () {
              openWindow();
            }, icon: 'add', id: 8
          }
        ]
    });
  }

  /**
   * 启用或禁用role
   * @param id
   * @param state
   */
  function updateRoleState(id, state) {
    //updatestate
    var manager = $.ligerDialog.waitting('正提交中,请稍候...');
    $.ajax({
      url: "updatestate.sec",
      type: "post",
      data: {
        roleId: id,
        state: state
      },
      success: function (data) {
        //grid.reload();
        if (data.status) {
          manager.close();
          getSystemList();
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


  //bug 暴力解决
  openWindow();
  dialog.hide();
  var dialog;

  function openWindow(data) {
    //重设变淡数据
    if (data) {
      data.validity = app.dateFormat(data.validity);
      liger.get('ff').setData(data);
    } else {
      liger.get('ff').setData({
        name: "",
        validity: "",
        sort: ""
      });
    }
    if (dialog) {
      if (data) {
        dialog.winDataState = 1;
      } else {
        dialog.winDataState = 0;
      }
      dialog.show();
      return;
    }
    dialog = $.ligerDialog.open({
      target: $(".addMenuContent"),
      isHidden: true, // 关闭是只影藏对话框
      isDrag: false, // 禁用拖动
      allowClose: true,
      title: "角色",
      width: 400,
      buttons: [{
        text: '确定', onclick: function (item, dialog) {
          var form = liger.get('ff');
          if (form.valid()) {
            var formData = form.getData();
            formData.state = dialog.winDataState;
            formData.sid = menuRright.paramsData.sysId;
            if (dialog.winDataState) {
              formData.id = menuRright.editData.id;
            }
            if (app.isEmpty(formData.validity)) {
              formData.validity = 0;
            }
            submitRoleData(formData);
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
    if (data) {
      dialog.winDataState = 1;
    } else {
      dialog.winDataState = 0;
    }
  }

  function submitRoleData(data) {
    if (data.validity != 0) {
      if (typeof data.validity == "string" && data.validity.indexOf("_" != -1)) {
        if (data.validity.length == 10) {
          data.validity = data.validity.split("_").join("") + "000000";
        } else {
          $.ligerDialog.waitting("您的日期格式不正确");
          setTimeout(function () {
            $.ligerDialog.closeWaitting();
          }, 1000);
          return;
        }
      } else if (typeof data.validity == "object") {
        data.validity = app.dateFormat(data.validity);
      } else {
        $.ligerDialog.waitting("您的日期格式不正确");
        setTimeout(function () {
          $.ligerDialog.closeWaitting();
        }, 1000);
        return;
      }
    }
    var manager = $.ligerDialog.waitting('正提交中,请稍候...');
    $.ajax({
      url: "addoreditrole.sec",
      type: "post",
      data: data,
      success: function (rst) {
        if (rst.status) {
          manager.close();
          dialog.hide();
          getSystemList();
        } else {
          manager.close();
          $.ligerDialog.waitting(rst.info);
          setTimeout(function () {
            $.ligerDialog.closeWaitting();
          }, 1000);
        }
      },
      error: function () {
        $.ligerDialog.waitting("提交失败");
        setTimeout(function () {
          $.ligerDialog.closeWaitting();
        }, 1000);
      }
    })
  }

})

/**
 * 保存用户的授权信息
 */
function f_submit() {
  var nodes = zTreeObj.getCheckedNodes();
  // 分离操作权限，和才单权限
  if (nodes.length <= 0) {
    layer.msg("请先选择需要授权的菜单！");
    return;
  }
  //菜单权限
  var aRights = "";
  // 操作权限
  var hRights = "";

  // 分离菜单权限和操作权限
  nodes.map(function (value, index, all) {
    // console.log(arguments);
    if (value.isMenu == 1) {
      // 菜单权限
      aRights += "," + value.id;
    } else {
      hRights += "," + value.id;
    }
  });
  var roleId = $("#submitData").attr("data-role-id");
  $.post("./addrights.sec", {roleId: roleId, aRights: aRights.slice(1), hRights: hRights.slice(1)}, function (rst) {
    if (rst.status) {
      getMenuList(roleId);
      layer.msg("授权成功！");
    } else {
      layer.msg(rst.info);
    }
  })

}
