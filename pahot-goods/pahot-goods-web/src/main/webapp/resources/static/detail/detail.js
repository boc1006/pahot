$(function () {
    $("#layout").ligerLayout({leftWidth: 200});
    initGoodsMenu();
    initAddGoodsDialog();
    addGoodsDialog.hide();
    initUpdataGoodsDialog();
    updataGoodsDialog.hide();
    initGoodsTree("#goodsTree");
    goodsTreeManager = $("#goodsTree").ligerGetTreeManager();
    //商品列表
    $.ajax({
        type: "POST",
        data: {
            getListType: 0
        },
        url: "../goodType/getList.sec",
        success: function (res) {
            if(res.status){
                goodsData = res.data;
                goodsTreeData = app.buildTreeData(res.data);
                goodsTreeManager.setData(goodsTreeData);
            }
            else{
                $.ligerDialog.warn(res.info);
            }
        },
        error: function (err) {
            var tip = $.ligerDialog.waitting("商品分类列表获取失败!");
            setTimeout(function () {
                tip.close();
            }, 2000);
        }
    });
    $(".l-layout-left").not("#goods").bind("contextmenu", function (e) {
        treeNodeData = null;
        goodsMenu.setEnabled('add');
        goodsMenu.setDisabled('updata');
        goodsMenu.setDisabled('active');
        goodsMenu.setDisabled('disable');
        goodsMenu.setDisabled('delete');
        goodsMenu.show(
            {
                top: e.pageY,
                left: e.pageX
            }
        );
        return false;
    });
})

var goodsTreeManager = null;
var goodsMenu = null;
var addGoodsDialog = null;
var updataGoodsDialog = null;
var addCombox = null;
var updataCombox = null;
var treeNodeData = null;
var goodsTreeData = null;
var goodsData = null;
var id = null;
//初始化商品树
function initGoodsTree(element) {
    var tree = $(element).ligerTree({
        isExpand: false,
        checkbox: false,
        //parentIDFieldName :'pid',
        render: function (data) {
            return data.name;
        },
        onClick: function (node) {
            console.log(node);
            reloadAttr(node.data.id);
        },
        onContextmenu: function (node, e) {  //右键事件
            treeNodeData = node.data;
            console.log("节点数据：", node.data);
            switch (node.data.state){
                case "01":
                    goodsMenu.setEnabled('add');
                    goodsMenu.setEnabled('updata');
                    goodsMenu.setDisabled('active');
                    goodsMenu.setEnabled('disable');
                    goodsMenu.setEnabled('delete');
                    goodsMenu.show({top: e.pageY, left: e.pageX});
                    return false;
                    break;
                case "02":
                    goodsMenu.setDisabled('add');
                    goodsMenu.setEnabled('updata');
                    goodsMenu.setEnabled('active');
                    goodsMenu.setDisabled('disable');
                    goodsMenu.setEnabled('delete');
                    goodsMenu.show({top: e.pageY, left: e.pageX});
                    return false;
                    break;
            }
        }
    });

}

//初始化商品菜单
function initGoodsMenu() {
    goodsMenu = $.ligerMenu({
        top: 100,
        left: 200,
        width: 120,
        items: [
            {
                text: '新增',
                id: "add",
                click: function () {
                    getAddGoodsTree();
                    var addGoodsTreeManager=$("ul[ligeruiid='ligerui1005']").ligerGetTreeManager();
                    addGoodsTreeManager.setData(goodsTreeData);
                    if(treeNodeData){
                        addCombox.setValue(treeNodeData.id);
                        var goon = setInterval(function () {
                            addCombox.setText(treeNodeData.name);
                            if(addCombox.getText()){
                                clearInterval(goon);
                            }
                        },100)
                    }
                    else {
                        addCombox.setValue("");
                        var goon = setInterval(function () {
                            addCombox.setText("");
                            if(!addCombox.getText()){
                                clearInterval(goon);
                            }
                        },100)
                    }
                    addGoodsDialog.show();
                    var goodsForm = liger.get("addGoodsform");
                    $(".l-dialog-close").click(function () {
                        goodsForm.setData({
                            name: "",
                            sort: ""
                        });
                        addCombox.setText("");
                    })
                }
            },
            {
                text: '修改',
                id: "updata",
                click: function () {
                    getUpdataGoodsTree();
                    var goodsForm = liger.get("updataGoodsform");
                    goodsForm.setData({
                        name: treeNodeData.name,
                        sort: treeNodeData.sort
                    });
                    updataCombox.setValue(
                        JSON.stringify({
                            id: treeNodeData.id,
                            parentId: treeNodeData.parentId
                        })
                    );
                    updataCombox.setDisabled();
                    for(var i=0;i<goodsData.length;i++){
                        if(treeNodeData.parentId==goodsData[i].id){
                            console.log(i,goodsData[i].name);
                            var name = goodsData[i].name;
                            var goon = setInterval(function () {
                                updataCombox.setText(name);
                                if(updataCombox.getText()){
                                    clearInterval(goon);
                                }
                            },100)
                            break;
                        }
                    }
                    setTimeout(function () {
                        console.log(updataCombox.getText());
                        if(updataCombox.getText()=="null"){
                            var goon1 = setInterval(function () {
                                updataCombox.setText("无");
                                if(updataCombox.getText()){
                                    clearInterval(goon1);
                                }
                            },100)
                        }
                    },200)
                    $("#updataGoodsform .l-trigger-cancel").remove();
                    updataGoodsDialog.show();
                    $(".l-dialog-close").click(function () {
                        goodsForm.setData({
                            name: "",
                            sort: ""
                        });
                    })
                }
            },
            {
                text: '启用',
                id: "active",
                disable: true,
                click: function () {
                    $.ligerDialog.confirm("确定启用此商品分类？", function (res) {
                        if(res){
                            //改变商品分类状态
                            $.ajax({
                                type: "POST",
                                url: "../goodType/changeState.sec",
                                data: {
                                    id: treeNodeData.id,
                                    state: "01"
                                },
                                success: function (res) {
                                    if(res.status){
                                        var tip = $.ligerDialog.waitting("启用成功");
                                        setTimeout(function () {
                                            tip.close();
                                        }, 2000);
                                        //商品列表
                                        $.ajax({
                                            type: "POST",
                                            data: {
                                                getListType: 0
                                            },
                                            url: "../goodType/getList.sec",
                                            success: function (res) {
                                                if(res.status) {
                                                    goodsData = res.data;
                                                    goodsTreeData = app.buildTreeData(res.data);
                                                    goodsTreeManager.setData(goodsTreeData);
                                                }
                                                else{
                                                    $.ligerDialog.warn(res.info);
                                                }
                                            },
                                            error: function (err) {
                                                var tip = $.ligerDialog.waitting("商品分类列表获取失败!");
                                                setTimeout(function () {
                                                    tip.close();
                                                }, 2000);
                                            }
                                        });
                                    }
                                    else{
                                        $.ligerDialog.warn(res.info);
                                    }
                                },
                                error: function (err) {
                                    var tip = $.ligerDialog.waitting("启用失败！");
                                    setTimeout(function () {
                                        tip.close();
                                    }, 2000);
                                }
                            });
                        }
                    })
                }
            },
            {
                text: '禁用',
                id: "disable",
                click: function () {
                    $.ligerDialog.confirm("确定禁用此商品分类？", function (res) {
                        if(res){
                            //改变商品分类状态
                            $.ajax({
                                type: "POST",
                                url: "../goodType/changeState.sec",
                                data: {
                                    id: treeNodeData.id,
                                    state: "02"
                                },
                                success: function (res) {
                                    if(res.status){
                                        var tip = $.ligerDialog.waitting("禁用成功");
                                        setTimeout(function () {
                                            tip.close();
                                        }, 2000);
                                        //商品列表
                                        $.ajax({
                                            type: "POST",
                                            data: {
                                                getListType: 0
                                            },
                                            url: "../goodType/getList.sec",
                                            success: function (res) {
                                                if(res.status) {
                                                    goodsData = res.data;
                                                    goodsTreeData = app.buildTreeData(res.data);
                                                    goodsTreeManager.setData(goodsTreeData);
                                                }
                                                else{
                                                    $.ligerDialog.warn(res.info);
                                                }
                                            },
                                            error: function (err) {
                                                var tip = $.ligerDialog.waitting("商品分类列表获取失败!");
                                                setTimeout(function () {
                                                    tip.close();
                                                }, 2000);
                                            }
                                        });
                                    }
                                    else{
                                        $.ligerDialog.warn(res.info);
                                    }
                                },
                                error: function (err) {
                                    var tip = $.ligerDialog.waitting("禁用失败！");
                                    setTimeout(function () {
                                        tip.close();
                                    }, 2000);
                                }
                            });
                        }
                    })
                }
            },
            {
                text: '删除',
                id: "delete",
                click: function () {
                    $.ligerDialog.confirm("确定删除此商品分类？", function (res) {
                        if(res){
                            //改变商品分类状态
                            $.ajax({
                                type: "POST",
                                url: "../goodType/changeState.sec",
                                data: {
                                    id: treeNodeData.id,
                                    state: "00"
                                },
                                success: function (res) {
                                    if(res.status){
                                        var tip = $.ligerDialog.waitting("删除成功");
                                        setTimeout(function () {
                                            tip.close();
                                        }, 2000);
                                        //商品列表
                                        $.ajax({
                                            type: "POST",
                                            data: {
                                                getListType: 0
                                            },
                                            url: "../goodType/getList.sec",
                                            success: function (res) {
                                                if(res.status) {
                                                    goodsData = res.data;
                                                    goodsTreeData = app.buildTreeData(res.data);
                                                    goodsTreeManager.setData(goodsTreeData);
                                                    hideTypeAttr();
                                                }
                                                else{
                                                    $.ligerDialog.warn(res.info);
                                                }
                                            },
                                            error: function (err) {
                                                var tip = $.ligerDialog.waitting("商品分类列表获取失败!");
                                                setTimeout(function () {
                                                    tip.close();
                                                }, 2000);
                                            }
                                        });
                                    }
                                    else{
                                        $.ligerDialog.warn(res.info);
                                    }
                                },
                                error: function (err) {
                                    var tip = $.ligerDialog.waitting("删除失败！");
                                    setTimeout(function () {
                                        tip.close();
                                    }, 2000);
                                }
                            });
                        }
                    })
                }
            }
        ]
    });
}

//初始化新增商品表单弹窗
function initAddGoodsDialog() {
    addGoodsDialog = $.ligerDialog.open({
        width: 400,
        target: $("#addGoodsform"),
        title: "新增商品分类",
        isHidden: true, //关闭对话框时是否只是隐藏，还是销毁对话框
        buttons: [
            {
                text: '确定',
                onclick: function (item, dialog) {
                    var goodsForm = liger.get("addGoodsform");
                    var data = goodsForm.getData();
                    console.log(addCombox.getValue());
                    if(!addCombox.getValue()){
                        data.parentId = 0;
                    }
                    else{
                        data.parentId = addCombox.getValue();
                    }
                    var regName=/^[\u0391-\uFFE5]+$/;
                    // if(!regName.test((data.name).replace(/\s+/g, ""))){
                    //     $.ligerDialog.warn('名称格式不正确');
                    //     return;
                    // }
                    if(!(data.name).replace(/\s+/g, "")){
                        $.ligerDialog.warn('名称格式不正确');
                        return;
                    }
                    if(data.sort==0){
                        $.ligerDialog.warn('请输入排序');
                        return;
                    }
                    console.log(data);
                    addGoodsDialog.hide();
                    //新增商品分类
                    $.ajax({
                        type: "POST",
                        data: data,
                        url: "../goodType/addItem.sec",
                        success: function (res) {
                            if(res.status){
                                var goodsForm = liger.get("addGoodsform");
                                goodsForm.setData({
                                    name: "",
                                    sort: ""
                                });
                                addCombox.setText("");
                                var tip = $.ligerDialog.waitting(res.info);
                                setTimeout(function () {
                                    tip.close();
                                }, 2000);
                                //商品列表
                                $.ajax({
                                    type: "POST",
                                    data: {
                                        getListType: 0
                                    },
                                    url: "../goodType/getList.sec",
                                    success: function (res) {
                                        if(res.status) {
                                            goodsData = res.data;
                                            goodsTreeData = app.buildTreeData(res.data);
                                            goodsTreeManager.setData(goodsTreeData);
                                        }
                                        else{
                                            $.ligerDialog.warn(res.info);
                                        }
                                    },
                                    error: function (err) {
                                        var tip = $.ligerDialog.waitting("商品分类列表获取失败!");
                                        setTimeout(function () {
                                            tip.close();
                                        }, 2000);
                                    }
                                });
                            }
                            else{
                                var goodsForm = liger.get("addGoodsform");
                                goodsForm.setData({
                                    name: "",
                                    sort: ""
                                });
                                addCombox.setText("");
                                $.ligerDialog.warn(res.info);
                            }
                        },
                        error: function (err) {
                            var goodsForm = liger.get("addGoodsform");
                            goodsForm.setData({
                                name: "",
                                sort: ""
                            });
                            addCombox.setText("");
                            var tip = $.ligerDialog.waitting("新增商品分类失败！");
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
                    addGoodsDialog.hide();
                    var goodsForm = liger.get("addGoodsform");
                    goodsForm.setData({
                        name: "",
                        parentId: "",
                        sort: ""
                    });
                }
            }
        ]
    });
}

//初始化修改商品表单弹窗
function initUpdataGoodsDialog() {
    updataGoodsDialog = $.ligerDialog.open({
        width: 400,
        target: $("#updataGoodsform"),
        title: "修改商品分类",
        isHidden: true, //关闭对话框时是否只是隐藏，还是销毁对话框
        buttons: [
            {
                text: '确定',
                onclick: function (item, dialog) {
                    var goodsForm = liger.get("updataGoodsform");
                    var data = goodsForm.getData();
                    if(updataCombox.getValue()){
                        if(JSON.parse(updataCombox.getValue()).id){
                            id = JSON.parse(updataCombox.getValue()).id;
                            data.id = JSON.parse(updataCombox.getValue()).id;
                        }
                        else{
                            data.id = id;
                        }
                        data.parentId = JSON.parse(updataCombox.getValue()).parentId;
                    }
                    else{
                        $.ligerDialog.warn('请选择所属分类');
                        return;
                    }
                    var regName=/^[\u0391-\uFFE5]+$/;
                    // if(!regName.test((data.name).replace(/\s+/g, ""))){
                    //     $.ligerDialog.warn('名称格式不正确');
                    //     return;
                    // }
                    if(!(data.name).replace(/\s+/g, "")){
                        $.ligerDialog.warn('名称格式不正确');
                        return;
                    }
                    if(data.sort==0){
                        $.ligerDialog.warn('请输入排序');
                        return;
                    }
                    console.log(data);
                    updataGoodsDialog.hidden();
                    //更新商品分类
                    $.ajax({
                        type: "POST",
                        data: data,
                        url: "../goodType/update.sec",
                        success: function (res) {
                            if(res.status){
                                // var goodsForm = liger.get("addGoodsform");
                                // goodsForm.setData({
                                //     name: "",
                                //     sort: ""
                                // });
                                // addCombox.setText("");
                                var tip = $.ligerDialog.waitting(res.info);
                                setTimeout(function () {
                                    tip.close();
                                }, 2000);
                                //商品列表
                                $.ajax({
                                    type: "POST",
                                    data: {
                                        getListType: 0
                                    },
                                    url: "../goodType/getList.sec",
                                    success: function (res) {
                                        if(res.status) {
                                            goodsData = res.data;
                                            goodsTreeData = app.buildTreeData(res.data);
                                            goodsTreeManager.setData(goodsTreeData);
                                        }
                                        else{
                                            $.ligerDialog.warn(res.info);
                                        }
                                    },
                                    error: function (err) {
                                        var tip = $.ligerDialog.waitting("商品分类列表获取失败!");
                                        setTimeout(function () {
                                            tip.close();
                                        }, 2000);
                                    }
                                });
                            }
                            else{
                                $.ligerDialog.warn(res.info);
                            }
                        },
                        error: function (err) {
                            var goodsForm = liger.get("addGoodsform");
                            goodsForm.setData({
                                name: "",
                                sort: ""
                            });
                            addCombox.setText("");
                            var tip = $.ligerDialog.waitting("新增商品分类失败！");
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
                    updataGoodsDialog.hide();
                    var goodsForm = liger.get("updataGoodsform");
                    goodsForm.setData({
                        name: "",
                        parentId: "",
                        sort: ""
                    });
                }
            }
        ]
    });
}

//新增分类商品下拉树
function getAddGoodsTree() {
    //console.log(goodsTreeData);
    addCombox = $("#addParentId").ligerComboBox({
        width : 180,
        selectBoxWidth: 180,
        selectBoxHeight: 200,
        tree: {
            //data: goodsTreeData,
            isExpand: false,
            checkbox: false,
            render: function (data) {
                return data.name;
            },
            onClick: function (node) {
                console.log(node.data);
                addCombox.setValue(node.data.id);
                var goon = setInterval(function () {
                    addCombox.setText(node.data.name);
                    if(addCombox.getText()){
                        clearInterval(goon);
                    }
                },100)
            }
        }
    });
    $("#addParentId").hide();
}

//修改分类商品下拉树
function getUpdataGoodsTree() {
    console.log(goodsTreeData);
    updataCombox = $("#updataParentId").ligerComboBox({
        width : 180,
        selectBoxWidth: 180,
        selectBoxHeight: 200,
        tree: {
            data: goodsTreeData,
            isExpand: false,
            checkbox: false,
            render: function (data) {
                return data.name;
            },
            onClick: function (node) {
                console.log(node.data);
                updataCombox.setValue(
                    JSON.stringify({
                        //id: node.data.id,
                        parentId: node.data.id
                    })
                );
                var goon = setInterval(function () {
                    updataCombox.setText(node.data.name);
                    if(updataCombox.getText()){
                        clearInterval(goon);
                    }
                },100)
            }
        }
    });
    $("#updataParentId").hide();
}