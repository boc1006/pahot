$(function () {
  $("#layout").ligerLayout({leftWidth: 200});

  function initTree(selector, data, selectCallback) {
    //树
    $(selector).ligerTree({
      data: data,
      checkbox: false,
      slide: false,
      nodeWidth: 150,
      attribute: ['text', 'id'],
      render: function (a) {
        return a.text;
      },
      onSelect: function (node) {
        selectCallback && selectCallback(node);
        if (dialog.paramsData) {
          dialog.paramsData.sid = node.data.id;
        } else {
          dialog.paramsData = {
            sid: node.data.id,
          }
        }
      },
      onContextmenu: function (node, e) {
        e.preventDefault();
        sysRmenu.show({top: e.pageY, left: e.pageX});
        if (dialog.paramsData) {
          dialog.paramsData.sid = node.data.id;
          dialog.paramsData.parentid = 0;
          dialog.paramsData.level = 0;
        } else {
          dialog.paramsData = {
            sid: node.data.id,
            parentid: 0,
            level: 0
          };
        }
        $("#selectPName").val(node.data.text);
      }
    });
    initSysRClic();
    initMenuRClic();
    initRightRClick();
  }

  /**
   * 系统列表的右键菜单
   */
  function initSysRClic() {
    if (window.sysRmenu) {
      return;
    }
    window.sysRmenu = $.ligerMenu({
      top: 100, left: 100, width: 120, items:
        [
          {
            text: '增加', click: function () {
              openAddMenuWin();
            }, icon: 'add', id: 3
          },
        ]
    });
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
            text: '新增菜单', click: function () {
              openAddMenuWin();
            }, icon: 'add', id: 8
          },
          {
            text: '新增功能', click: function () {
              openAddFunWin();
            }, icon: 'add', id: 7
          },
          {
            text: '修改', click: function () {
              openWindow(dialog.editData);
            }, icon: 'add', id: 6
          },
          {
            text: '禁用', click: function () {
              updateMenuState(dialog.paramsData.parentid, "00");
            }, icon: 'add', id: 5
          }, {
          text: '启用', click: function () {
            updateMenuState(dialog.paramsData.parentid, "01");
          }, icon: 'add', disable: true, id: 4
        },
        ]
    });
  }

  /**
   * 权限的右键菜单
   */
  function initRightRClick() {
    if (window.RrightRc) {
      return;
    }
    window.RrightRc = $.ligerMenu({
      top: 100, left: 100, width: 120, items:
        [
          {
            text: '修改', click: function () {
              console.log(fundlg.editData);
              openAddFunWin(fundlg.editData);
            }, icon: 'add', id: 9
          },
          {
            text: '禁用', click: function () {
              updateFunState(fundlg.editData.id, "00");
            }, icon: 'add', id: 10
          },
          {
            text: '启用', click: function () {
              updateFunState(fundlg.editData.id, "01")
            }, icon: 'add', disable: true, id: 11
          },
        ]
    });
  }


  function getSystemList() {
    $.post("../sys/getSysList.sec", {state: ""}, function (data) {
      var list = [];
      for (var i = 0; i < data.Rows.length; i++) {
        var temp = {
          text: data.Rows[i].name,
          id: data.Rows[i].id,
        };
        list.push(temp);
      }
      initTree("#systemList", list, function (node) {
        //显示菜单信息
        getMenuList(node.data.id);
      });
    });
  }

  getSystemList();


  function getMenuList(sysid) {
    $.post("getMenuList.sec", {sysid: sysid}, function (data) {
      var tree = app.buildTreeData(data.data);
      initMenuTree("#menuListTree", tree, function (node) {
        //显示操作信息
        showRadioList(node.data.id)
      })
    });
  }

  /**
   * 初始化菜单树
   * @param selector
   * @param data
   * @param selectCallback
   */
  function initMenuTree(selector, data, selectCallback) {
    $(selector).ligerTree({
      data: data,
      checkbox: false,
      slide: false,
      nodeWidth: 150,
      attribute: ['name', 'id'],
      render: function (a) {
        if(a.state == "00"){
          return a.name+"(已禁用)";
        }
        return a.name;
      },
      onSelect: function (node) {
        selectCallback && selectCallback(node);
      },
      onContextmenu: function (node, e) {
        e.preventDefault();

        if (node.data.state == "00") {
          menuRright.setDisabled(5);
          menuRright.setEnabled(4);
        } else {
          menuRright.setDisabled(4);
          menuRright.setEnabled(5);
        }

        menuRright.show({top: e.pageY, left: e.pageX});
        if (dialog.paramsData) {
          dialog.paramsData.sid = node.data.sid;
          dialog.paramsData.parentid = node.data.id;
          dialog.paramsData.level = node.data.level;
        } else {
          dialog.paramsData = {
            sid: node.data.sid,
            parentid: node.data.id,
            level: node.data.level
          };
        }
        dialog.editData = node.data;
        dialog.editData.url = node.data.uri;
        $("#selectPName").val(node.data.name);
      }
    });
  }

  /**
   * 右侧权限列表的获取数据 和 初始化方法
   * @param menuid 菜单id
   */
  function showRadioList(menuid) {
    $.post("getRightList.sec", {menuid: menuid}, function (data) {
      var list = data.data;
      $("#rightList").ligerTree({
        data: list,
        checkbox: false,
        slide: false,
        nodeWidth: 150,
        attribute: ['name', 'id'],
        render: function (a) {
          return a.name;
        },
        onSelect: function (node) {

        },
        onContextmenu: function (node, e) {
          e.preventDefault();

          // 设置按钮的启用 或者警用
          if (node.data.state == "00") {
            RrightRc.setDisabled(10);
            RrightRc.setEnabled(11);
          } else {
            RrightRc.setDisabled(11);
            RrightRc.setEnabled(10);
          }
          RrightRc.show({top: e.pageY, left: e.pageX});
          fundlg.editData = node.data;
        }
      });
    })
  }


  /**
   * 打开新增菜单的弹窗
   */
  function openAddMenuWin() {
    openWindow()
  }

  //bug 暴力解决
  openWindow();
  dialog.hide();


  var dialog;

  function openWindow(data) {
    //重设变淡数据
    if(data){
      liger.get('ff').setData(data);
    }else{
      liger.get('ff').setData({
        name: "",
        url: "",
        mtype: "",
        btype: "",
        jump: "",
        sort: ""
      });
    }
    if (dialog) {
      if(data){
        dialog.winDataState = 1;
      }else{
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
      title: "新增菜单",
      width: 400,
      buttons: [{
        text: '确定', onclick: function (item, dialog) {
          var form = liger.get('ff');
          if (form.valid()) {
            var formData = form.getData();
            formData.sid = dialog.paramsData.sid;
            formData.parentid = dialog.paramsData.parentid;
            formData.level = dialog.paramsData.level;
            console.log(data)
            if(dialog.winDataState){
              formData.mid = dialog.paramsData.parentid;
              editMenuAubmitData(formData);
            }else{
              addMenuAubmitData(formData);
            }
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
    if(data){
      dialog.winDataState = 1;
    }else{
      dialog.winDataState = 0;
    }
  }

  var fundlg;
  /**
   * 打开新增功能的弹窗
   * @param formData
   */
  //bug 暴力解决
  openAddFunWin();
  fundlg.hide();

  function openAddFunWin(data) {
    if(data){
      liger.get('addfunFrom').setData(data);
    }else{
      liger.get('addfunFrom').setData({
        name: "",
        code: "",
        type: "",
        sort: ""
      });
    }
    if (fundlg) {
      if(data){
        fundlg.winDataState = 1;
      }else{
        fundlg.winDataState = 0;
      }
      fundlg.show();
      return;
    }
    fundlg = $.ligerDialog.open({
      target: $(".addfunContent"),
      isHidden: true, // 关闭是只影藏对话框
      isDrag: false, // 禁用拖动
      allowClose: true,
      title: "新增功能",
      width: 400,
      buttons: [{
        text: '确定', onclick: function (item, flog) {
          var form = liger.get('addfunFrom');
          if (form.valid()) {
            var formData = form.getData();
            formData.sid = dialog.paramsData.sid;
            formData.mid = dialog.paramsData.parentid;
            console.log(fundlg.winDataState);
            if(fundlg.winDataState){
              formData.id = fundlg.editData.id;
              formData.mid = fundlg.editData.mid;
              editFunbmitData(formData);
            }else{
              addFunbmitData(formData);
            }
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
    if(data){
      fundlg.winDataState = 1;
    }else{
      fundlg.winDataState = 0;
    }
  }


  function addMenuAubmitData(formData) {
    var manager = $.ligerDialog.waitting('正提交中,请稍候...');
    $.ajax({
      url: "addmenu.sec",
      type: "post",
      data: formData,
      success: function (data) {
        //grid.reload();
        if (data.status) {
          manager.close();
          dialog.hide();
          getMenuList(formData.sid);
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
  function editMenuAubmitData(formData) {
    var manager = $.ligerDialog.waitting('正提交中,请稍候...');
    $.ajax({
      url: "updatmenu.sec",
      type: "post",
      data: formData,
      success: function (data) {
        //grid.reload();
        if (data.status) {
          manager.close();
          dialog.hide();
          getMenuList(formData.sid);
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

  function addFunbmitData(formData) {
    var manager = $.ligerDialog.waitting('正提交中,请稍候...');
    $.ajax({
      url: "addfun.sec",
      type: "post",
      data: formData,
      success: function (data) {
        //grid.reload();
        if (data.status) {
          manager.close();
          fundlg.hide();
          showRadioList(formData.mid)
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
  function editFunbmitData(formData) {
    var manager = $.ligerDialog.waitting('正提交中,请稍候...');
    $.ajax({
      url: "updatefun.sec",
      type: "post",
      data: formData,
      success: function (data) {
        //grid.reload();
        if (data.status) {
          manager.close();
          fundlg.hide();
          showRadioList(formData.mid)
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

  function updateMenuState(id, state) {
    //updatestate
    var manager = $.ligerDialog.waitting('正提交中,请稍候...');
    $.ajax({
      url: "updatestate.sec",
      type: "post",
      data: {
        id: id,
        state: state
      },
      success: function (data) {
        //grid.reload();
        if (data.status) {
          manager.close();
          getMenuList(dialog.paramsData.sid);
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
   * 启用或禁用菜单权限
   * @param id
   * @param state
   */
  function updateFunState(id, state) {
    //updatestate
    var manager = $.ligerDialog.waitting('正提交中,请稍候...');
    $.ajax({
      url: "updatefunstate.sec",
      type: "post",
      data: {
        id: id,
        state: state
      },
      success: function (data) {
        //grid.reload();
        if (data.status) {
          manager.close();
          showRadioList(fundlg.editData.mid);
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

});


/**
 * 下拉选项框的数据
 * @returns {*[]}
 */
function getCountryData(v) {
  if (v == 1) {
    return [
      {Name: '结构型', Code: '01'},
      {Name: '功能型', Code: '02'}
    ];
  } else if (v == 2) {
    return [{
      Name: "系统管理型", Code: "01"
    }, {
      Name: "业务操作型", Code: "02"
    }, {
      Name: "综合类型", Code: "03"
    },];//01系统管理型,02业务操作型,03综合类型',
  } else if (v == 3) {
    return [
      {Name: '主窗口', Code: '01'},
      {Name: '弹出窗口', Code: '02'},
      {Name: '新开窗口', Code: '03'}
    ];
  } else if (v == 4) {
    return [
      {Name: '功能操作', Code: '01'},
      {Name: '数据访问(列)', Code: '02'},
      {Name: '数据访问(行)', Code: '03'}
    ];
    //01功能操作,02数据访问(列),03数据访问(行),默认值=01
  }

}


