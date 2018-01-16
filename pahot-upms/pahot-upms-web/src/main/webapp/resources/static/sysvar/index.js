$(function () {


    window['grid'] =
        $("#maingrid").ligerGrid({
            url: "getSettingList.sec",
            height: '100%',
            usePager: true,// 禁用分页
            columns: [
                {display: '系统编号', name: 'sid', align: 'left', width: 100, minWidth: 60},
                {display: '变量KEY', name: 'setKey'},
                {display: '变量VALUE', name: 'setValue'},
                {display: '变量描述', name: 'setDesc'},
                {
                    display: '状态', name: 'enable', render: function (item, index, value) {
                    if (value == "01") {
                        return "启用";
                    } else if (value == "00") {
                        return "禁用"
                    }
                }
                },
                {
                    display: '编辑状态', name: 'editMode', render: function (item, index, value) {
                    if (value == "01") {
                        return "可编辑";
                    } else if (value == "00") {
                        return "不可编辑"
                    }
                }
                },
                {
                    display: '操作', name: 'caozuo', render: function (item, index, value) {
                    var str = "";
                    if (app.hasPer("EDIT")) {
                        str += '<a class="btn-link openState" onclick="editItem(' + item.id + ')">修改</a>&nbsp;&nbsp;';
                    }
                    if (item.enable == "01" && app.hasPer("DISABLE")) {
                        str += '<a class="btn-link openEdit1" onclick="disabledItem(' + item.id + ')">禁用</a>';
                    } else if (item.enable == "00" && app.hasPer("ENABLE")) {
                        str += '<a class="btn-link openEdit1" onclick="abledItem(' + item.id + ')">启用</a>';
                    }
                    return str;
                }
                }
            ],
            data: [],
            parms: {a: 1},
            pageSize: 30,
            rownumbers: true,
            toolbar: {
                items: [
                    {text: '增加', click: itemclick, icon: 'add', code: "ADD"},
                    {text: '缓存', click: loadToRedis, icon: 'add',}
                ]
            }
        });


    $("#pageloading").hide();

    function itemclick(item) {
        openWindow(1, "添加系统变量");
    }

    function loadToRedis() {
        var manager = $.ligerDialog.waitting('正提交中,请稍候...');
        $.ajax({
            url: "loadToRedis.sec",
            type: "post",
            success: function (data) {
                manager.close();
                $.ligerDialog.waitting(data.info);
                setTimeout(function () {
                    $.ligerDialog.closeWaitting();
                }, 1000);
            },
            error: function (err) {
                $.ligerDialog.error("系统变量缓存失败！");
            }

        })
    }

    //bug 暴力解决
    openWindow(1, "添加系统变量");
    dialog.hide();
});
var dialog;

function openWindow(state, tittle, settingId) {
    liger.get('setting').setVisible("editMode", false);
    if (state == 1) {
        // liger.get('setting').setVisible("editMode", false);
        liger.get('setting').setData({
            id: "",
            sid: "",
            setKey: "",
            setValue: "",
            setDesc: "",
            editMode: ""
        });
    }
    if (dialog) {
        dialog.State = state;
        dialog.settingId = settingId;
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
                var form = liger.get('setting');
                if (form.valid()) {
                    var formData = form.getData();
                    formData.state = dialog.State;
                    if (dialog.State == 2) {
                        //修改
                        formData.id = dialog.settingId;
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
    var url;
    if (formData.state == "1") {
        url = "addSetting.sec";
    } else {
        url = "updateSetting.sec";
    }
    $.ajax({
        url: url,
        type: "post",
        data: formData,
        success: function (data) {
            grid.reload();
            manager.close();
            $.ligerDialog.waitting(data.info);
            setTimeout(function () {
                $.ligerDialog.closeWaitting();
            }, 1000);
            dialog.hidden();
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

    var form = liger.get("setting");
    form.setData({
        sid: line.sid,
        setKey: line.setKey,
        setValue: line.setValue,
        setDesc: line.setDesc,
        editMode: line.editMode
    });

    openWindow(2, "修改系统变量", id);
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
function disabledItem(id) {
    if (!app.hasPer("DISABLE")) {
        $.ligerDialog.success('您暂无操作权限！')
        return;
    }
    var manager = $.ligerDialog.waitting('正提交中,请稍候...');
    $.ajax({
        url: "disable.sec",
        type: "post",
        data: {
            id: id
        },
        success: function (data) {
            grid.reload();
            manager.close();
            $.ligerDialog.waitting(data.info);
            setTimeout(function () {
                $.ligerDialog.closeWaitting();
            }, 1000);
        },
        error: function (err) {
            $.ligerDialog.error("系统变量禁用失败");
        }
    })
}

/**
 * 启用行
 * @param id
 */
function abledItem(id) {
    if (!app.hasPer("ENABLE")) {
        $.ligerDialog.success('您暂无操作权限！')
        return;
    }
    var manager = $.ligerDialog.waitting('正提交中,请稍候...');
    $.ajax({
        url: "enable.sec",
        type: "post",
        data: {
            id: id
        },
        success: function (data) {
            grid.reload();
            manager.close();
            $.ligerDialog.waitting(data.info);
            setTimeout(function () {
                $.ligerDialog.closeWaitting();
            }, 1000);
        },
        error: function (err) {
            $.ligerDialog.error("系统变量启用失败");
        }
    })
}


/**
 * 下拉选项框的数据
 * @returns {*[]}
 */
function getEditModeData() {
    return [
        {Name: '不可编辑', Code: '00'},
        {Name: '可编辑', Code: '01'}
    ];
}


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
