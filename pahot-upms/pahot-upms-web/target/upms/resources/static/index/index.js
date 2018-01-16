var tab = null;
var accordion = null;
var tree = null;
var tabItems = [];
var pswForm = null;
var pswDialog = null;
$(function () {
  //布局
  $("#layout1").ligerLayout({
    leftWidth: 190,
    height: '100%',
    heightDiff: -34,
    space: 4,
    onHeightChanged: f_heightChanged,
    onLeftToggle: function () {
      tab && tab.trigger('sysWidthChange');
    },
    onRightToggle: function () {
      tab && tab.trigger('sysWidthChange');
    }
  });

  var height = $(".l-layout-center").height();

  //Tab
  tab = $("#framecenter").ligerTab({
    height: height,
    showSwitchInTab: true,
    showSwitch: true,
    onAfterAddTabItem: function (tabdata) {
      tabItems.push(tabdata);
      saveTabStatus();
    },
    onAfterRemoveTabItem: function (tabid) {
      for (var i = 0; i < tabItems.length; i++) {
        var o = tabItems[i];
        if (o.tabid == tabid) {
          tabItems.splice(i, 1);
          saveTabStatus();
          break;
        }
      }
    },
    onReload: function (tabdata) {
      var tabid = tabdata.tabid;
      addFrameSkinLink(tabid);
    }
  });

  //面板
  $("#accordion1").ligerAccordion({
    height: height - 24, speed: null
  });

  $(".l-link").hover(function () {
    $(this).addClass("l-link-over");
  }, function () {
    $(this).removeClass("l-link-over");
  });

  for (var i = 0; i < menuData.length; i++) {
    if (menuData[i].list == null || menuData[i].list.length == 0) continue;
    initTree(menuData[i].id, app.buildTreeData(menuData[i].list), menuData[i].url);
  }


  function initTree(id, data, host) {
    //树
    $("#menu" + id).ligerTree({
      data: data,
      checkbox: false,
      slide: false,
      nodeWidth: 120,
      attribute: ['name', 'uri'],
      render: function (a) {
        if (a.jump == "03") {
          return '<a href="' + a.uri + '" target="_blank">' + a.name + '</a>';
        }
        return a.name;
      },
      onSelect: function (node) {
        if (node.target.nodeName == "A") {
          return;
        }
        if (node.data.jump == "02") {
          layer.open({
            type: 2,
            title: node.data.name,
            content: node.data.uri,
            area: ["800px", "600px"]
          });
          return;
        }
        if (!node.data.uri) return;
        if (node.data.uri == "#") return;
        if (node.data.isNew) return;
        var tabid = $(node.target).attr("tabid");
        if (!tabid) {
          tabid = node.data.id;
          $(node.target).attr("tabid", tabid)
        }
        f_addTab(tabid, node.data.name, host + node.data.uri);
      }
    });
    // var treeManager = $("#menu" + id).ligerGetTreeManager();
  }


  function openNew(url) {
    var jform = $('#opennew_form');
    if (jform.length == 0) {
      jform = $('<form method="post" />').attr('id', 'opennew_form').hide().appendTo('body');
    } else {
      jform.empty();
    }
    jform.attr('action', url);
    jform.attr('target', '_blank');
    jform.trigger('submit');
  };


  tab = liger.get("framecenter");
  accordion = liger.get("accordion1");
  tree = liger.get("tree1");
  $("#pageloading").hide();

  css_init();
  pages_init();

  buildMenuList();
});

function f_heightChanged(options) {
  if (tab)
    tab.addHeight(options.diff);
  if (accordion && options.middleHeight - 24 > 0)
    accordion.setHeight(options.middleHeight - 24);
}

function f_addTab(tabid, text, url) {
  tab.addTabItem({
    tabid: tabid,
    text: text,
    url: url,
    callback: function () {
      addFrameSkinLink(tabid);
    }
  });
}


function showCodeView(src) {
  $.ligerDialog.open({
    title: '源码预览',
    url: 'dotnetdemos/codeView.aspx?src=' + src,
    width: $(window).width() * 0.9,
    height: $(window).height() * 0.9
  });

}

function addFrameSkinLink(tabid) {
  var prevHref = getLinkPrevHref(tabid) || "";
  var skin = getQueryString("skin");
  if (!skin) return;
  skin = skin.toLowerCase();
  attachLinkToFrame(tabid, prevHref + skin_links[skin]);
}

var skin_links = {
  "aqua": "lib/ligerUI/skins/Aqua/css/ligerui-all.css",
  "gray": "lib/ligerUI/skins/Gray/css/all.css",
  "silvery": "lib/ligerUI/skins/Silvery/css/style.css",
  "gray2014": "lib/ligerUI/skins/gray2014/css/all.css"
};

function pages_init() {
  var tabJson = $.cookie('site-index-tab');
  if (tabJson) {
    var tabitems = JSON2.parse(tabJson);
    // for (var i = 0; tabitems && tabitems[i]; i++) {
    //   f_addTab(tabitems[i].tabid, tabitems[i].text, tabitems[i].url);
    // }
    if (!Array.isArray(tabitems) || tabitems.length == 0) {
      return;
    }
    f_addTab(tabitems[tabitems.length - 1].tabid, tabitems[tabitems.length - 1].text, tabitems[tabitems.length - 1].url);
  }
}

function saveTabStatus() {
  $.cookie('site-index-tab', JSON2.stringify(tabItems));
}

function css_init() {
  var css = $("#mylink").get(0), skin = getQueryString("skin");
  $("#skinSelect").val(skin);
  $("#skinSelect").change(function () {
    if (this.value) {
      location.href = "index.htm?skin=" + this.value;
    } else {
      location.href = "index.htm";
    }
  });


  if (!css || !skin) return;
  skin = skin.toLowerCase();
  $('body').addClass("body-" + skin);
  $(css).attr("href", skin_links[skin]);
}

function getQueryString(name) {
  var now_url = document.location.search.slice(1), q_array = now_url.split('&');
  for (var i = 0; i < q_array.length; i++) {
    var v_array = q_array[i].split('=');
    if (v_array[0] == name) {
      return v_array[1];
    }
  }
  return false;
}

function attachLinkToFrame(iframeId, filename) {
  if (!window.frames[iframeId]) return;
  var head = window.frames[iframeId].document.getElementsByTagName('head').item(0);
  var fileref = window.frames[iframeId].document.createElement("link");
  if (!fileref) return;
  fileref.setAttribute("rel", "stylesheet");
  fileref.setAttribute("type", "text/css");
  fileref.setAttribute("href", filename);
  head.appendChild(fileref);
}

function getLinkPrevHref(iframeId) {
  if (!window.frames[iframeId]) return;
  var head = window.frames[iframeId].document.getElementsByTagName('head').item(0);
  var links = $("link:first", head);
  for (var i = 0; links[i]; i++) {
    var href = $(links[i]).attr("href");
    if (href && href.toLowerCase().indexOf("ligerui") > 0) {
      return href.substring(0, href.toLowerCase().indexOf("lib"));
    }
  }
}

var menuList = [];

function buildMenuList() {
  for (var i = 0; i < menuData.length; i++) {
    menuList = menuList.concat(menuData[i].list);
  }
}

function logout() {
  $.cookie('site-index-tab', JSON2.stringify([]));
  window.location.href = "./logout.sec";
}

//
// function getMenuPermissions(url) {
//   var temp = menuList;
//   console.log(temp);
// }

//修改密码
function updataPsw() {
  console.log(userId);
  if (!pswForm) {
    pswForm = $("#pswForm").ligerForm({
      inputWidth: 150,
      labelWidth: 80,
      space: 100,
      fields: [
        {
          display: "旧密码",
          name: "oldPwd",
          newline: false,
          type: "password",
          validate: {
            required: true
          }
        },
        {
          display: "新密码",
          name: "newPwd",
          newline: false,
          type: "password",
          validate: {
            required: true
          }
        },
        {
          display: "确认密码",
          name: "surePwd",
          newline: false,
          type: "password",
          validate: {
            required: true
          }
        }
      ]
    });
  }
  if (!pswDialog) {
    pswDialog = $.ligerDialog.open({
      show: false,
      width: 380,
      target: $("#psw"),
      title: "修改密码",
      isHidden: true, //关闭对话框时是否只是隐藏，还是销毁对话框
      buttons: [
        {
          text: '确定',
          onclick: function (item, dialog) {
            var data = pswForm.getData();
            data.id = userId;
            if (!(data.oldPwd).replace(/\s+/g, "") || !(data.newPwd).replace(/\s+/g, "") || !(data.surePwd).replace(/\s+/g, "")) {
              $.ligerDialog.warn("请输入密码");
              return;
            }
            if (data.newPwd != data.surePwd) {
              $.ligerDialog.warn("两次密码输入不一致");
              return;
            }
            //更新密码
            $.ajax({
              type: "POST",
              url: "org/updatepwd.sec",
              data: data,
              success: function (res) {
                pswDialog.hidden();
                if (res.status) {
                  var data = {
                    oldPwd: "",
                    newPwd: "",
                    surePwd: ""
                  };
                  pswForm.setData(data);
                  var tip = $.ligerDialog.waitting(res.info);
                  setTimeout(function () {
                    tip.close();
                  }, 3000);
                }
                else {
                  var data = {
                    oldPwd: "",
                    newPwd: "",
                    surePwd: ""
                  };
                  pswForm.setData(data);
                  var tip = $.ligerDialog.waitting(res.info);
                  setTimeout(function () {
                    tip.close();
                  }, 4000);
                }
              },
              error: function (err) {
                pswDialog.hidden();
                var data = {
                  oldPwd: "",
                  newPwd: "",
                  surePwd: ""
                };
                pswForm.setData(data);
                var tip = $.ligerDialog.waitting(res.info);
                setTimeout(function () {
                  tip.close();
                }, 2000);
              }
            });
          }
        },
        {
          text: '取消',
          onclick: function (item, dialog) {
            pswDialog.hidden();
            var orgData = {
              name: "",
              parentid: "",
              tel: "",
              fax: "",
              address: "",
              //uid: "",
              remark: "",
              //levels: "",
              sort: ""
            };
            addOrgForm.setData(orgData);
          }
        }
      ]
    });
  }
  else {
    pswDialog.show();
  }
}
 