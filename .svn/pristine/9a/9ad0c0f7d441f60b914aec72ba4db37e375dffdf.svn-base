/**
 * Created by april on 2017/12/7.
 */
var orgName = "";
var orgList = [];
//操作用户
function controlUser(userId,state) {
    var userState = "";
    var confirmStr = "";
    switch (state){
        case 1:
            userState = "01";
            confirmStr = "确定冻结此用户？"
            break;
        case 2:
            userState = "02";
            confirmStr = "确定解冻此用户？"
            break;
        case 3:
            userState = "03";
            confirmStr = "确定注销此用户？"
            break;
        case 4:
            userState = "04";
            confirmStr = "确定重置此用户的密码？"
            break;
    }
    $.ligerDialog.confirm(confirmStr, function (res) {
        if(res){
            $.ajax({
                type: "POST",
                data: ({
                    id: userId,
                    state: userState  //冻结01，解冻02， 注销03,04重置密码
                }),
                url: "updatestate.sec",
                success: function (res) {
                    var tipStr = "";
                    switch (state){
                        case 1:
                            tipStr = "用户已冻结";
                            break;
                        case 2:
                            tipStr = "用户已解冻";
                            break;
                        case 3:
                            tipStr = "用户已注销";
                            break;
                        case 4:
                            tipStr = "用户密码已重置";
                            break;
                    }
                    if(res.status){
                        var tip = $.ligerDialog.waitting(tipStr);
                        setTimeout(function () {
                            tip.close();
                        }, 2000);
                        userGrid.reload();
                    }
                    else{
                        var tip = $.ligerDialog.waitting(res.info);
                        setTimeout(function () {
                            tip.close();
                        }, 2000);
                    }
                },
                error: function (err) {
                    var tip = $.ligerDialog.waitting('操作用户失败'+JSON.stringify(err));
                    setTimeout(function () {
                        tip.close();
                    }, 2000);
                }
            });
        }
    });
}
//修改用户
function updataUser(data) {
    console.log(data);
    //组织机构列表
    $.ajax({
        type: "GET",
        url: "getorglist.sec",
        success: function (res) {
            //var list = [{text: "请选择", id: 0}];
            // for (var i = 0; i < data.Rows.length; i++) {
            //     var temp = {
            //         text: data.Rows[i].name,
            //         id: data.Rows[i].id,
            //     };
            //     list.push(temp);
            // }
            // sysComboBox = $("input[name='orgid']").ligerComboBox({
            //     width: 170,
            //     data: list,
            //     value: '0',
            //     initIsTriggerEvent: false,
            //     onSelected: function (value) {
            //
            //     }
            // });
            orgList = res.Rows;
            for(var i =0; i<orgList.length; i++){
                if(data.orgid==orgList[i].id){
                    var updataOrgName = orgList[i].name;
                }
            }
            console.log(updataOrgName);
            var orgData = app.buildTreeData(res.Rows);
            //创建新增人员表单
            addMemberForm = $("#addMemberForm").ligerForm({
                inputWidth: 170,
                labelWidth: 90,
                fields: [
                    {
                        display: "登录名",
                        name: "username",
                        newline: false,
                        type: "text",
                        label: "登录名",
                        validate: {
                            required:true
                        }
                    },
                    {
                        display: "姓名",
                        name: "name",
                        newline: false,
                        type: "text",
                        label: "姓名",
                        validate: {
                            required:true
                        }
                    },
                    {
                        display: "性别",
                        name: "sex",
                        newline: false,
                        type: "select",
                        label: "性别",
                        comboboxName: "sex",
                        options: {
                            data: [
                                {text: '女', sex: '0'},
                                {text: '男', sex: '1'}
                            ],
                            valueFieldID: "sex"
                        },
                        validate: {
                            required:true
                        }
                    },
                    {
                        display: "所属部门",
                        name: "orgid",
                        newline: false,
                        type: "select",
                        label: "所属部门",
                        comboboxName: "orgid",
                        options: {
                            tree: {
                                data: orgData,
                                isExpand: false,
                                checkbox: false,
                                render: function (a) {
                                    return a.name;
                                },
                                onClick: function (node) {
                                    $("input[name='orgid']").val(node.data.name).attr({"data-orgid":node.data.id,"data-state":node.data.state});
                                    console.log(node);
                                    console.log("组织机构名字:"+node.data.name+"，组织机构id:"+node.data.id);
                                }
                            }
                        },
                        validate: {
                            required:true
                        }
                    },
                    {
                        display: "生日",
                        name: "birthday",
                        newline: false,
                        type: "date",
                        label: "生日"
                    },
                    {
                        display: "备注",
                        name: "remark",
                        newline: false,
                        type: "text",
                        label: "备注"
                    },
                    {
                        display: "电话",
                        name: "phone",
                        newline: false,
                        type: "text",
                        label: "电话",
                        validate: {
                            required:true
                        }
                    }
                ],
                onRendered: function () {
                    console.log("渲染结束");
                }
            });
            //设置数据
            var addMemberData = {
                username: data.username,
                name: data.name,
                sex: data.sex,
                orgid: data.orgid,
                birthday: data.birthday,
                remark: data.remark,
                phone: data.phone,
            };
            addMemberForm.setData(addMemberData);
            switch (data.sex){
                case 1:
                    var sex = "男";
                    break;
                case 0:
                    var sex = "女";
                    break;
            }
            addMemberForm.setEnabled("username", false);
            $("input[name='sex']").val(sex);
            var on = setInterval(function () {
                $("input[name='orgid']").val(updataOrgName);
                if($("input[name='orgid']").val()){
                    clearInterval(on);
                }
            },100)
        },
        error: function (err) {
            var tip = $.ligerDialog.waitting("部门列表获取失败");
            setTimeout(function () {
                tip.close();
            }, 2000);
        }
    });
    //增加人员弹窗
    var addMember = $.ligerDialog.open({
        width: 350,
        target: $("#addMember"),
        title: "修改人员",
        isHidden: true, //关闭对话框时是否只是隐藏，还是销毁对话框
        buttons: [
            {
                text: '确定',
                onclick: function (item, dialog) {
                    var memberData = addMemberForm.getData();
                    switch ($("input[name='sex']").val()) {
                        case "男":
                            memberData.sex = 1;
                            break;
                        case "女":
                            memberData.sex = 0;
                            break;
                    }
                    memberData.id = data.id;
                    memberData.birthday = $("input[name='birthday']").val();
                    if(!($("input[name='orgid']").attr("data-orgid"))){
                        for(var i=0;i<orgList.length;i++){
                            if($("input[name='orgid']").val()==orgList[i].name){
                                memberData.orgid = orgList[i].id;
                            }
                        }
                    }
                    else{
                        memberData.orgid = $("input[name='orgid']").attr("data-orgid");
                    }
                    console.log(memberData);
                    var regName=/^[\u0391-\uFFE5]+$/;
                    var regPhone=/^[-+]?\d*$/;
                    if(regName.test((memberData.username).replace(/\s+/g, ""))){
                        $.ligerDialog.warn('登录名格式不正确');
                        return;
                    }
                    if(!regName.test((memberData.name).replace(/\s+/g, ""))){
                        $.ligerDialog.warn('用户姓名格式不正确');
                        return;
                    }
                    if(!$("input[name='sex']").val().replace(/\s+/g, "")){
                        $.ligerDialog.warn('请选择性别');
                        return;
                    }
                    if(!$("input[name='orgid']").val()){
                        $.ligerDialog.warn('请选择部门');
                        return;
                    }
                    if(!regPhone.test((memberData.phone).replace(/\s+/g, ""))||(memberData.phone).replace(/\s+/g, "").length!=11){
                        $.ligerDialog.warn('手机号格式不正确');
                        return;
                    }
                    var state = $("input[name='orgid']").attr("data-state");
                    if(state!='02'){
                        //更新人员
                        $.ajax({
                            type: "POST",
                            url: "updateuser.sec",
                            data: memberData,
                            success: function (res) {
                                console.log(res);
                                addMember.hidden();
                                //刷新
                                if(res.status){
                                    var tip = $.ligerDialog.waitting(res.info);
                                    setTimeout(function () {
                                        tip.close();
                                    }, 2000);
                                    userGrid.reload();
                                }
                                else{

                                    var tip = $.ligerDialog.waitting(res.info);
                                    setTimeout(function () {
                                        tip.close();
                                    }, 2000);
                                }
                            },
                            error: function (err) {
                                var addMemberTip = $.ligerDialog.waitting('新增人员失败'+JSON.stringify(err));
                                setTimeout(function () {
                                    addMemberTip.close();
                                }, 2000);
                            }
                        })
                    }
                    else{
                        addMemberForm.setData({
                            birthday: "",
                            id: "",
                            name: "",
                            orgid: "",
                            phone: "",
                            remark: "",
                            sex: "",
                            username: ""
                        })
                        var tip = $.ligerDialog.warn('该组织机构已被禁用');
                        setTimeout(function () {
                            tip.close();
                        }, 2000);
                    }
                }
            },
            {

                text: '取消',
                onclick: function (item, dialog) {
                    addMember.hidden();
                    var addMemberData = {
                        username: "",
                        name: "",
                        sex: "",
                        orgid: "",
                        birthday: "",
                        remark: "",
                        phone: ""
                    };
                    addMemberForm.setData(addMemberData);
                }
            }
        ]
    });
    //关闭弹窗清除数据
    $(".l-dialog-close").click(function () {
        addMember.hidden();
        var addMemberData = {
            username: "",
            name: "",
            sex: "",
            orgid: "",
            birthday: "",
            remark: "",
            phone: ""
        };
        addMemberForm.setData(addMemberData);
    })
}


$(function () {
    $("#layout").ligerLayout({leftWidth: 200});
    //菜单
    var orgMenu = $.ligerMenu({
        top: 100,
        left: 200,
        width: 120,
        items: [
            {
                text: '新增',
                id: "add",
                click: function () {
                    //创建新增组织机构表单
                    addOrgForm = $("#addOrgForm").ligerForm({
                        inputWidth: 170,
                        labelWidth: 90,
                        space: 100,
                        fields: [
                            {
                                display: "部门名称",
                                name: "name",
                                newline: false,
                                type: "text",
                                validate: {
                                    required:true
                                }
                            },
                            {
                                display: "排序",
                                name: "sort",
                                newline: false,
                                type: "int",
                                validate: {
                                    required:true
                                }
                            },
                            {
                                display: "电话",
                                name: "tel",
                                newline: false,
                                type: "int",
                                validate: {
                                    required:true
                                }
                            },
                            {
                                display: "传真",
                                name: "fax",
                                newline: false,
                                type: "text"
                            },
                            {
                                display: "地址",
                                name: "address",
                                newline: false,
                                type: "text",
                                validate: {
                                    required:true
                                }
                            },
                            {
                                display: "备注",
                                name: "remark",
                                newline: false,
                                type: "text"
                            }
                        ]
                    });
                    //增加组织机构弹窗
                    var addOrg = $.ligerDialog.open({
                        width: 400,
                        target: $("#addOrg"),
                        title: "新增组织机构",
                        isHidden: true, //关闭对话框时是否只是隐藏，还是销毁对话框
                        buttons: [
                            {
                                text: '确定',
                                onclick: function (item, dialog) {
                                    var addOrgData = addOrgForm.getData();
                                    addOrgData.parentid = nodeData.id;
                                    console.log(addOrgData);
                                    var regName=/^[\u0391-\uFFE5]+$/;
                                    var regPhone=/^[-+]?\d*$/;
                                    //alert(regPhone.test((addOrgData.sort).replace(/\s+/g, "")));
                                    if(!regName.test((addOrgData.name).replace(/\s+/g, ""))){
                                        $.ligerDialog.warn('部门名称格式不正确');
                                        return;
                                    }
                                    if(addOrgData.sort==0){
                                        $.ligerDialog.warn('请输入排序');
                                        return;
                                    }
                                    if(addOrgData.tel==0){
                                        $.ligerDialog.warn('请输入电话');
                                        return;
                                    }
                                    if(!addOrgData.address.replace(/\s+/g, "")){
                                        $.ligerDialog.warn('请输入地址');
                                        return;
                                    }
                                    addOrg.hidden();
                                    //添加部门
                                    $.ajax({
                                        type: "POST",
                                        url: "addorg.sec",
                                        data: addOrgData,
                                        success: function (res) {
                                            if(res.status){
                                                //组织机构列表
                                                $.ajax({
                                                    type: "GET",
                                                    url: "getorglist.sec",
                                                    success: function (data) {
                                                        console.log(data);
                                                        var orgData = app.buildTreeData(data.Rows);
                                                        initTree("#orgData", orgData);
                                                        tree.expandAll();
                                                    }
                                                });
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
                                                var addOrgTip = $.ligerDialog.waitting(res.info);
                                                setTimeout(function () {
                                                    addOrgTip.close();
                                                }, 2000);
                                            }
                                            else{
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
                                                var tip = $.ligerDialog.waitting(res.info);
                                                setTimeout(function () {
                                                    tip.close();
                                                }, 2000);
                                            }
                                        },
                                        error: function (err) {
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
                                    addOrg.hidden();
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
                    //关闭弹窗清除数据
                    $(".l-dialog-close").click(function () {
                        addOrg.hidden();
                        var orgData = {
                            name: "",
                            parentid: "",
                            tel: "",
                            fax: "",
                            address: "",
                            //uid: "",
                            remark: ""
                            //levels: "",
                            //sort: ""
                        };
                        addOrgForm.setData(orgData);
                    })
                }
            },
            {
                text: '修改',
                id: "updata",
                click: function () {
                    //创建新增组织机构表单
                    addOrgForm = $("#addOrgForm").ligerForm({
                        inputWidth: 170,
                        labelWidth: 90,
                        space: 100,
                        fields: [
                            {
                                display: "部门名称",
                                name: "name",
                                newline: false,
                                type: "text",
                                validate: {
                                    required:true
                                }
                            },
                            {
                                display: "排序",
                                name: "sort",
                                newline: false,
                                type: "int",
                                validate: {
                                    required:true
                                }
                            },
                            {
                                display: "电话",
                                name: "tel",
                                newline: false,
                                type: "int",
                                validate: {
                                    required:true
                                }
                            },
                            {
                                display: "传真",
                                name: "fax",
                                newline: false,
                                type: "text"
                            },
                            {
                                display: "地址",
                                name: "address",
                                newline: false,
                                type: "text",
                                validate: {
                                    required:true
                                }
                            },
                            {
                                display: "备注",
                                name: "remark",
                                newline: false,
                                type: "text"
                            }
                        ]
                    });
                    //增加组织机构弹窗
                    var addOrg = $.ligerDialog.open({
                        width: 400,
                        target: $("#addOrg"),
                        title: "新增组织机构",
                        isHidden: true, //关闭对话框时是否只是隐藏，还是销毁对话框
                        buttons: [
                            {
                                text: '确定',
                                onclick: function (item, dialog) {
                                    var regName=/^[\u0391-\uFFE5]+$/;
                                    var regPhone=/^[-+]?\d*$/;
                                    var addOrgData = addOrgForm.getData();
                                    addOrgData.parentid = nodeData.parentid;
                                    addOrgData.id = nodeData.id;
                                    if(!regName.test((addOrgData.name).replace(/\s+/g, ""))){
                                        $.ligerDialog.warn('部门名称格式不正确');
                                        return;
                                    }
                                    if(addOrgData.sort==0){
                                        $.ligerDialog.warn('请输入排序');
                                        return;
                                    }
                                    if(addOrgData.tel==0){
                                        $.ligerDialog.warn('请输入电话');
                                        return;
                                    }
                                    if(!addOrgData.address.replace(/\s+/g, "")){
                                        $.ligerDialog.warn('请输入地址');
                                        return;
                                    }
                                    addOrg.hidden();
                                    //更新部门
                                    $.ajax({
                                        type: "POST",
                                        url: "updateorg.sec",
                                        data: addOrgData,
                                        success: function (res) {
                                            if(res.status){
                                                //组织机构列表
                                                $.ajax({
                                                    type: "GET",
                                                    url: "getorglist.sec",
                                                    success: function (data) {
                                                        console.log(data);
                                                        var orgData = app.buildTreeData(data.Rows);
                                                        initTree("#orgData", orgData);
                                                        tree.expandAll();
                                                    }
                                                });
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
                                                var addOrgTip = $.ligerDialog.waitting(res.info);
                                                setTimeout(function () {
                                                    addOrgTip.close();
                                                }, 2000);
                                            }
                                            else{
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
                                                var tip = $.ligerDialog.waitting(res.info);
                                                setTimeout(function () {
                                                    tip.close();
                                                }, 2000);
                                            }
                                        },
                                        error: function (err) {
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
                                    addOrg.hidden();
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
                    //关闭弹窗清除数据
                    $(".l-dialog-close").click(function () {
                        addOrg.hidden();
                        var orgData = {
                            name: "",
                            parentid: "",
                            tel: "",
                            fax: "",
                            address: "",
                            //uid: "",
                            remark: ""
                            //levels: "",
                            //sort: ""
                        };
                        addOrgForm.setData(orgData);
                    })
                    //设置数据
                    var orgData = {
                        name: nodeData.name,
                        //parentid: nodeData.id,
                        tel: nodeData.tel,
                        fax: nodeData.fax,
                        //id: nodeData.id,
                        address: nodeData.address,
                        //uid: "",
                        remark: nodeData.remark,
                        //levels: "",
                        sort: nodeData.sort
                    };
                    addOrgForm.setData(orgData);
                    addOrg.show();
                }
            },
            {
                text: '启用',
                id: "active",
                disable: true,
                click: function () {
                    $.ligerDialog.confirm("确定启用此组织机构？", function (res) {
                        if(res){
                            //更新部门
                            $.ajax({
                                type: "POST",
                                url: "updateorgstate.sec",
                                data: {
                                    id: nodeData.id,
                                    state: "01"
                                },
                                success: function (res) {
                                    if(res.status){
                                        //组织机构列表
                                        $.ajax({
                                            type: "GET",
                                            url: "getorglist.sec",
                                            success: function (data) {
                                                console.log(data);
                                                var orgData = app.buildTreeData(data.Rows);
                                                initTree("#orgData", orgData);
                                                tree.expandAll();
                                            }
                                        });
                                        var tip = $.ligerDialog.waitting(res.info);
                                        setTimeout(function () {
                                            tip.close();
                                        }, 2000);
                                    }
                                    else{
                                        var tip = $.ligerDialog.waitting(res.info);
                                        setTimeout(function () {
                                            tip.close();
                                        }, 2000);
                                    }
                                },
                                error: function (err) {
                                    var tip = $.ligerDialog.waitting(res.info);
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
                    $.ligerDialog.confirm("确定禁用此组织机构？", function (res) {
                        if(res){
                            //更新部门
                            $.ajax({
                                type: "POST",
                                url: "updateorgstate.sec",
                                data: {
                                    id: nodeData.id,
                                    state: "02"
                                },
                                success: function (res) {
                                    if(res.status){
                                        //组织机构列表
                                        $.ajax({
                                            type: "GET",
                                            url: "getorglist.sec",
                                            success: function (data) {
                                                console.log(data);
                                                var orgData = app.buildTreeData(data.Rows);
                                                initTree("#orgData", orgData);
                                                tree.expandAll();
                                            }
                                        });
                                        var tip = $.ligerDialog.waitting(res.info);
                                        setTimeout(function () {
                                            tip.close();
                                        }, 2000);
                                    }
                                    else{
                                        var tip = $.ligerDialog.waitting(res.info);
                                        setTimeout(function () {
                                            tip.close();
                                        }, 2000);
                                    }
                                },
                                error: function (err) {
                                    var tip = $.ligerDialog.waitting(res.info);
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
                    $.ligerDialog.confirm("确定删除此组织机构？", function (res) {
                        if(res){
                            //更新部门
                            $.ajax({
                                type: "POST",
                                url: "updateorgstate.sec",
                                data: {
                                    id: nodeData.id,
                                    state: "03"
                                },
                                success: function (res) {
                                    if(res.status){
                                        //组织机构列表
                                        $.ajax({
                                            type: "GET",
                                            url: "getorglist.sec",
                                            success: function (data) {
                                                console.log(data);
                                                var orgData = app.buildTreeData(data.Rows);
                                                initTree("#orgData", orgData);
                                                tree.expandAll();
                                            }
                                        });
                                        var tip = $.ligerDialog.waitting(res.info);
                                        setTimeout(function () {
                                            tip.close();
                                        }, 2000);
                                    }
                                    else{
                                        var tip = $.ligerDialog.waitting(res.info);
                                        setTimeout(function () {
                                            tip.close();
                                        }, 2000);
                                    }
                                },
                                error: function (err) {
                                    var tip = $.ligerDialog.waitting(res.info);
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

    //初始化树节点
    function initTree(element, data) {
        tree = $(element).ligerTree({
            data: data,
            isExpand: false,
            checkbox: false,
            render: function (data) {
                return data.name;
            },
            onClick: function (node) {
                orgName = node.data.name;
                var orgId = node.data.id;
                // console.log(node);
                // console.log(orgId);
                loadUser(orgId);
            },
            onContextmenu: function (node, e) {  //右键事件
                nodeData = node.data;
                console.log("节点数据：", node.data);
                if(node.data.parentid!=0){
                    switch (node.data.state){
                        case "01":
                            orgMenu.setDisabled('active');
                            orgMenu.setEnabled('add');
                            orgMenu.setEnabled('updata');
                            orgMenu.setEnabled('disable');
                            orgMenu.setEnabled('delete');
                            orgMenu.show({top: e.pageY, left: e.pageX});
                            return false;
                            break;
                        case "02":
                            orgMenu.setDisabled('add');
                            orgMenu.setDisabled('updata');
                            orgMenu.setDisabled('disable');
                            orgMenu.setDisabled('delete');
                            orgMenu.setEnabled('active');
                            orgMenu.show({top: e.pageY, left: e.pageX});
                            return false;
                            break;
                    }
                }
                else{
                    orgMenu.setDisabled('updata');
                    orgMenu.setDisabled('active');
                    orgMenu.setDisabled('disable');
                    orgMenu.setDisabled('delete');
                    orgMenu.setEnabled('add');
                    orgMenu.show({top: e.pageY, left: e.pageX});
                    return false;
                }
            }
        });
    }

    //组织机构列表
    $.ajax({
        type: "GET",
        url: "getorglist.sec",
        success: function (data) {
            console.log(data);
            var orgData = app.buildTreeData(data.Rows);
            console.log(orgData);
            ///  console.log(orgData);
            //初始化树节点
            initTree("#orgData", orgData);
        },
        error: function (err) {
            layer.msg("部门列表获取失败!");
        }
    });

    //初始化表格
    // function initTable(element, data) {
    userGrid = $("#usersData").ligerGrid({
        //checkbox: true,
        url: "getuserlist.sec",
        height: '100%',
        // width: '100%',
        usePager: true,// 启用分页
        columns: [
            {
                display: '登录名',
                name: 'username',
                width: 80
            },
            {
                display: '姓名',
                name: 'name',
                width: 80
            },
            {
                display: '性别',
                name: 'sex',
                width: 80,
                render: function (rowdata) {
                    if (parseInt(rowdata.sex) == 1) return '男';
                    return '女';
                }
            },
            {
                display: '手机号',
                name: 'phone',
                width: 110
            },
            {
                display: '状态',
                name: 'state',
                width: 80,
                render: function (rowdata) {
                    switch (parseInt(rowdata.state)){
                        case 01:
                            return '正常';
                            break;
                        case 02:
                            return '冻结';
                            break;
                        case 03:
                            return '注销';
                            break;
                    }
                }
            },
            {
                display: '操作',
                minWidth:250,
                width: "auto",
                render: function (rowdata, rowindex, value) {
                    //console.log("行数据：",rowdata);
                    // console.log("行号：", rowindex);
                    var h = "";
                    if(app.hasPer("EDIT")){
                        h += "<a class='control' data-rights='EDIT' onclick='updataUser(" + JSON.stringify(rowdata) + ")'>修改</a>";
                    }
                    if(rowdata.state=="01" && app.hasPer("FREEZE")){
                        h += "<a class='control' data-rights='FREEZE' onclick='controlUser(" + rowdata.id + ",1)'>冻结</a> ";
                    }else if(rowdata.state=="02" && app.hasPer("THAW")){
                        h += "<a class='control' data-rights='THAW' onclick='controlUser(" + rowdata.id + ",2)'>解冻</a> ";
                    }
                    if(app.hasPer("LOGOUT")){
                        h += "<a class='control' data-rights='LOGOUT' onclick='controlUser(" + rowdata.id + ",3)'>注销</a>";
                    }
                    if(app.hasPer("PWD")){
                        h += "<a class='control' data-rights='PWD' onclick='controlUser(" + rowdata.id + ",4)'>初始化密码</a>";
                    }
                    if(app.hasPer("AUTH")){
                        h += "<a class='control' data-rights='AUTH' onclick='openWinUserRole(" + rowdata.id + ")'>用户授权</a>";
                    }
                    return h;
                }
            },
            {
                display: '备注',
                name: 'remark',
                width: 100
            }
            ],
        data: [],
        pageSize: 10,
        rownumbers: true
    });
    // }

    //查询用户列表
    function loadUser(orgId) {
        userGrid.setParm("orgId", orgId);
        userGrid.reload();
    }

    //工具条
    $("#toolBar").ligerToolBar({
        items: [
            {
                text: '增加',
                code:"ADD",
                click: function () {
                    //组织机构列表
                    $.ajax({
                        type: "GET",
                        url: "getorglist.sec",
                        success: function (data) {
                            //var list = [{text: "请选择", id: 0}];
                            // for (var i = 0; i < data.Rows.length; i++) {
                            //     var temp = {
                            //         text: data.Rows[i].name,
                            //         id: data.Rows[i].id,
                            //     };
                            //     list.push(temp);
                            // }
                            // sysComboBox = $("input[name='orgid']").ligerComboBox({
                            //     width: 170,
                            //     data: list,
                            //     value: '0',
                            //     initIsTriggerEvent: false,
                            //     onSelected: function (value) {
                            //
                            //     }
                            // });
                            orgList = data.Rows;
                            var orgData = app.buildTreeData(data.Rows);
                            //创建新增人员表单
                            addMemberForm = $("#addMemberForm").ligerForm({
                                inputWidth: 170,
                                labelWidth: 90,
                                fields: [
                                    {
                                        display: "登录名",
                                        name: "username",
                                        newline: false,
                                        type: "text",
                                        label: "登录名",
                                        validate: {
                                            required:true
                                        }
                                    },
                                    {
                                        display: "姓名",
                                        name: "name",
                                        newline: false,
                                        type: "text",
                                        label: "姓名",
                                        validate: {
                                            required:true
                                        }
                                    },
                                    {
                                        display: "性别",
                                        name: "sex",
                                        newline: false,
                                        type: "select",
                                        label: "性别",
                                        comboboxName: "sex",
                                        options: {
                                            data: [
                                                {text: '女', sex: '0'},
                                                {text: '男', sex: '1'}
                                            ],
                                            valueFieldID: "sex"
                                        },
                                        validate: {
                                            required:true
                                        }
                                    },
                                    {
                                        display: "所属部门",
                                        name: "orgid",
                                        newline: false,
                                        type: "select",
                                        label: "所属部门",
                                        comboboxName: "orgid",
                                        options: {
                                            tree: {
                                                data: orgData,
                                                isExpand: false,
                                                checkbox: false,
                                                render: function (a) {
                                                    return a.name;
                                                },
                                                onClick: function (node) {
                                                    $("input[name='orgid']").val(node.data.name).attr({"data-orgid":node.data.id,"data-state":node.data.state});
                                                    console.log(node);
                                                    console.log("组织机构名字:"+node.data.name+"，组织机构id:"+node.data.id);
                                                }
                                            }
                                        },
                                        validate: {
                                            required:true
                                        }
                                    },
                                    {
                                        display: "生日",
                                        name: "birthday",
                                        newline: false,
                                        type: "date",
                                        label: "生日"
                                    },
                                    {
                                        display: "备注",
                                        name: "remark",
                                        newline: false,
                                        type: "text",
                                        label: "备注"
                                    },
                                    {
                                        display: "电话",
                                        name: "phone",
                                        newline: false,
                                        type: "text",
                                        label: "电话",
                                        validate: {
                                            required:true
                                        }
                                    }
                                ],
                                onRendered: function () {
                                    console.log("渲染结束");
                                }
                            });
                        },
                        error: function (err) {
                            var tip = $.ligerDialog.waitting("部门列表获取失败");
                            setTimeout(function () {
                                tip.close();
                            }, 2000);
                        }
                    });

                    //增加人员弹窗
                    addMember = $.ligerDialog.open({
                        width: 350,
                        target: $("#addMember"),
                        title: "新增人员",
                        isHidden: true, //关闭对话框时是否只是隐藏，还是销毁对话框
                        buttons: [
                            {
                                text: '确定',
                                onclick: function (item, dialog) {
                                    var memberData = addMemberForm.getData();
                                    if(!$("input[name='orgid']").attr("data-orgid")){
                                        if(orgName) {
                                            for (var i = 0; i < orgList.length; i++) {
                                                if (orgName == orgList[i].name) {
                                                    memberData.orgid = orgList[i].id;
                                                }
                                            }
                                        }
                                    }
                                    else{
                                        memberData.orgid = $("input[name='orgid']").attr("data-orgid");
                                    }
                                    switch ($("input[name='sex']").val()) {
                                        case "男":
                                            memberData.sex = 1;
                                            break;
                                        case "女":
                                            memberData.sex = 0;
                                            break;
                                    }
                                    memberData.birthday = $("input[name='birthday']").val();
                                    console.log(memberData);
                                    var regName=/^[\u0391-\uFFE5]+$/;
                                    var regPhone=/^[-+]?\d*$/;
                                    if(regName.test((memberData.username).replace(/\s+/g, ""))){
                                        $.ligerDialog.warn('登录名格式不正确');
                                        return;
                                    }
                                    if(!regName.test((memberData.name).replace(/\s+/g, ""))){
                                        $.ligerDialog.warn('用户姓名格式不正确');
                                        return;
                                    }
                                    if(!$("input[name='sex']").val().replace(/\s+/g, "")){
                                        $.ligerDialog.warn('请选择性别');
                                        return;
                                    }
                                    if(!$("input[name='orgid']").val().replace(/\s+/g, "")){
                                        $.ligerDialog.warn('请选择部门');
                                        return;
                                    }
                                    if(!regPhone.test((memberData.phone).replace(/\s+/g, ""))||(memberData.phone).replace(/\s+/g, "").length!=11){
                                        $.ligerDialog.warn('手机号格式不正确');
                                        return;
                                    }
                                    var state = $("input[name='orgid']").attr("data-state");
                                    if(state!='02'){
                                        //新增人员
                                        $.ajax({
                                            type: "POST",
                                            url: "adduser.sec",
                                            data: memberData,
                                            success: function (res) {
                                                console.log(res);
                                                addMember.hidden();
                                                //刷新
                                                if(res.status){
                                                    var tip = $.ligerDialog.waitting(res.info);
                                                    setTimeout(function () {
                                                        tip.close();
                                                    }, 2000);
                                                    userGrid.reload();
                                                }
                                                else{
                                                    var tip = $.ligerDialog.waitting(res.info);
                                                    setTimeout(function () {
                                                        tip.close();
                                                    }, 2000);
                                                }
                                            },
                                            error: function (err) {
                                                var addMemberTip = $.ligerDialog.waitting('新增人员失败'+JSON.stringify(err));
                                                setTimeout(function () {
                                                    addMemberTip.close();
                                                }, 2000);
                                            }
                                        })
                                    }
                                    else{
                                        var tip = $.ligerDialog.warn('该组织机构已被禁用');
                                        setTimeout(function () {
                                            tip.close();
                                        }, 2000);
                                    }
                                }
                            },
                            {

                                text: '取消',
                                onclick: function (item, dialog) {
                                addMember.hidden();
                                var addMemberData = {
                                    username: "",
                                    name: "",
                                    sex: "",
                                    orgid: "",
                                    birthday: "",
                                    remark: "",
                                    phone: ""
                                };
                                addMemberForm.setData(addMemberData);
                            }
                            }
                        ]
                    });
                    setTimeout(function () {
                        $("input[name='orgid']").val(orgName);
                    },500)
                    //关闭弹窗清除数据
                    $(".l-dialog-close").click(function () {
                        addMember.hidden();
                        var addMemberData = {
                            username: "",
                            name: "",
                            sex: "",
                            orgid: "",
                            birthday: "",
                            remark: "",
                            phone: ""
                        };
                        addMemberForm.setData(addMemberData);
                    })
                },
                icon: 'add'
            },
            // {
            //     text: '删除',
            //     click: function () {
            //         var row = usersTable.getSelectedRow();
            //         if (!row) {
            //             $.ligerDialog.warn('请选择行');
            //             return;
            //         }
            //         console.log("行数据：",JSON.stringify(row));
            //         //删除复选框选中的行
            //         usersTable.deleteSelectedRow();
            //     } ,
            //     icon:'add'
            // }
        ]
    });
});
