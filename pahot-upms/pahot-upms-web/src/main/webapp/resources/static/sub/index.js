var ope;
$(function () {
    // hrightsCodes = ["ADD", "EDIT", "DISABLE", "ENABLE", "DELETE"];
    /**ope
     * 初始化，grid
     * @type {*|jQuery}
     */
    window['grid'] =
        $("#maingrid").ligerGrid({
            url: "getSubList.sec",
            height: '100%',
            width: "auto",
            usePager: false,// 启用分页
            columns: [
                {display: '订阅编号', name: 'id', width: 80},
                {display: '订阅名称', name: 'subname'},
                {
                    display: '消息数据类型', name: 'type', render: function (item, index, value) {
                    if (value == "1") {
                        return "json";
                    } else if (value == "2") {
                        return "xml"
                    }
                }
                },
                {display: '消息来源编号', name: 'source'},
                {display: '消息源id', name: 'dbid', width: 80},
                {display: '消息队列名称', name: 'queuename'},
                {display: '消息队列延迟发送分钟数', name: 'delays'},
                {display: '备注', name: 'remark'},
                {
                    display: '状态', name: 'state', width: 60, render: function (item, index, value) {
                    if (value == "1") {
                        return "正常";
                    } else if (value == "2") {
                        return "停用"
                    }
                    else if (value == "0") {
                        return "删除"
                    }
                }
                },
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
                    if (item.state == "1" && app.hasPer("DISABLE")) {
                        str += '<a class="btn-link openEdit1" onclick="updateItemState(' + item.id + ',' + 2 + ')">停用</a>&nbsp;&nbsp;';
                    } else if (item.state == "2" && app.hasPer("ENABLE")) {
                        str += '<a class="btn-link openEdit1" onclick="updateItemState(' + item.id + ',' + 1 + ')">启用</a>&nbsp;&nbsp;';
                    }
                    /*  if (app.hasPer("DELETE")) {
                          str += '<a class="btn-link openEdit1" onclick="updateItemState(' + item.id + ',' + 0 + ')">删除</a>&nbsp;&nbsp;';
                      }*/
                    return str;
                }
                }
            ],
            data: [],
            //  pageSize: 30,
            rownumbers: true,
            parms: {
                id: "",
                subname: "",
                queuename: ""
            },
            toolbar: {
                items: [
                    {text: '增加', click: itemclick, icon: 'add', code: "ADD"}
                ]
            }
        });

    $("#pageloading").hide();

    function itemclick(item) {
        ope = "add";
        var form = liger.get("form1");
        form.setData({
            id1: "", subname: "", type: "", source: "", queuename: "", delays: "", dbid: "", remark: ""
        });
        // disabled="disabled"
        //   $("#id1").attr("disabled", "");
        //  alert($("#id1"));
        form.setEnabled("id1", true);
        openWindow(1, "添加分布式事务消息订阅");
    }

    //bug 暴力解决
    openWindow(1, "添加分布式事务消息订阅");
    dialog.hide();
    //getDataAndInitSelect();
    initKeyInput();
});

/*function getDataAndInitSelect() {
    var list =
    return list;
}*/
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
                        formData.id = dialog.id1;
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
    var queuename = formData.queuename;
    var delays = formData.delays;
    var queueArr = queuename.split(",");
    var delaysArr = delays.split(",");
    if (queueArr.length != delaysArr.length) {
        layer.msg("队列名称必需和延迟(分钟)一一对应");
        return;
    }
    // var pattern = /^\s*\d+(\s*,\s*\d)*?\s*$/;
    //alert(delays.replace("  ", ""));
    //delays = delays.replace(" ", "");
    //queuename = queuename.replace(" ", "");
    //formData.delays = delays;
    //formData.queuename = delays;
    // alert(delays);
    var pattern = /^\s*\d+((\s*,\s*\d+)+){0,}\s*$/;
    if (!pattern.test(delays)) {
        layer.msg("延迟(分钟)必需是数字,多个数字以逗号分隔.");
        return;
    }
    var manager = $.ligerDialog.waitting('正提交中,请稍候...');
    //alert(queueSize + "," + delaysSize);
    var url;
    if (ope == "add")
        url = "addSubMessage.sec";
    if (ope == "edit")
        url = "updateSubMessage.sec";
    $.ajax({
        url: url,
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
    ope = "edit";
    if (!app.hasPer("EDIT")) {
        $.ligerDialog.success('您暂无操作权限！')
        return;
    }

    /* $("#id1").attr("disabled", "disabled");
     alert($("#id1").attr("disabled"));*/
    var line = getLineForId(id);
    var form = liger.get("form1");
    form.setEnabled("id1", false);
    form.setData({
        id1: line.id,
        subname: line.subname,
        type: line.type,
        source: line.source,
        queuename: line.queuename,
        delays: line.delays,
        dbid: line.dbid,
        remark: line.remark
    });

    openWindow(2, "修改分布式事务订阅消息", id);
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
// function getCountryData() {
//     var rstdata;
//     $.ajax({
//         url: '../sys/getSysList.sec',
//         //data:{name:value},
//         cache: false,
//         async: false,
//         type: "post",
//         dataType: 'json/xml/html',
//         success: function (result) {
//
//         },
//         error: function (err) {
//             if (err.readyState == 4 && err.status == 200) {
//                 var rst = JSON.parse(err.responseText);
//                 rstdata = rst.Rows;
//             }
//         }
//     });
//     for (var i = 0; i < rstdata.length; i++) {
//         rstdata[i].sid = rstdata[i].id;
//     }
//     return rstdata;
//
//     //
//     //
//     //
//     // return [
//     //   {Name: '管理系统', Code: '01'},
//     //   {Name: '业务系统', Code: '02'},
//     //   {Name: '运维系统', Code: '03'}
//     // ];
// }

/**
 * 表头搜索方法
 */
function f_search() {
    var id = $("#s_id").val();
    var subname = $("#s_subname").val();
    var queuename = $("#s_queuename").val();
    //alert(id + ":" + subname + ":" + queuename);
    //设置grid的ajax参数
    grid.setParm("id", id);
    grid.setParm("subname", subname);
    grid.setParm("queuename", queuename);
    grid.reload();
}