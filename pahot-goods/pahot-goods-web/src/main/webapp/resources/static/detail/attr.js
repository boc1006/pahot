(function (win, $) {
  win.global = {};
  $(document).ready(function () {
    getAttrSpect();

    // 双击启用编辑
    $(".attrEditContent").on("dblclick", ".text", function (e) {
      e.stopPropagation();
      beginEditAttr($(e.target).parent().parent().find(".edit")[0]);
    });

    $(".attrEditContent").on("click", ".edit", function (e) {
      var target = e.target;
      e.stopPropagation();
      if ($(target).hasClass("fy-icon-edit")) {
        // 编辑分类属性
        beginEditAttr(target);

      } else {
        // 提交销售属性
        var root = $(target).parent().parent();
        if (!root.hasClass("attr_child_name")) {
          var index = root.parent().parent().parent().attr("data-id");
          var row = app.deepClone(global.rows[index]);
          console.log(row);
          var val = root.find(".edit_input").val();
          if (!row.hasAttr) {
            global["typename_" + index] = val;
            showAttrData();
            endEditAttr(target);
            // layer.msg("请先添加一个属性");
            return;
          }
          if(val.length > 6){
            layer.msg("您提交的属性名太长");
            return;
          }
          if (app.isEmpty(val)) {
            endEditAttr(target);
            return;
          }
          editSpecType({
            goodsTypeId: global.goodsTypeId,
            skuSpecType: row.type,
            skuSpecTypeName: val
          });
        } else {
          var id = root.find(".text").attr("data-id");
          var ids = id.split("_");
          var row = app.deepClone(global.rows[ids[0]]["child"][ids[1]]);
          var val = $(target).parent().parent().find(".edit_input").val();
          if (app.isEmpty(val)) {
            $(target).removeClass("fy-icon-yes").addClass("fy-icon-edit");
            var tent = $(target).parent().parent().find(".tent");
            tent.find(".text").removeClass("heid");
            tent.find(".edit_input").addClass("heid");
            return;
          }
          row.name = val;

          // if (global.rows[ids[0]].hasAttr) {
          //   row.skuSpecTypeName = global.rows[ids[0]].name;
          // }
          if(global["typename_" + ids[0]]){
            row.skuSpecTypeName = global["typename_" + ids[0]];
          }
          if (global.rows[ids[0]].hasAttr) {
            row.skuSpecTypeName = global.rows[ids[0]].name;
          }


          submitEdit(row);
          endEditAttr(target);
        }
      }
    })
    $(".attrEditContent").on("click", ".fy-icon-close", function (e) {
      e.stopPropagation();
      var target = e.target;
      var root = $(target).parent().parent();
      if (!root.hasClass("attr_child_name")) {

      } else {
        var id = root.find(".text").attr("data-id");
        var ids = id.split("_");
        var row = app.deepClone(global.rows[ids[0]]["child"][ids[1]]);
        row.name = "";
        submitEdit(row);
      }

    });

    $(".l-layout-center").click(function (e) {
      var target = e.target;
      if ($(target).closest(".attrEditContent").length == 0) {
        endEditAttr();
      }
    })
  });

  function endEditAttr() {
    var content = $(".attrEditContent");
    var target = content.find(".fy-icon-yes");
    target.removeClass("fy-icon-yes").addClass("fy-icon-edit");
    var tent = $(target).parent().parent().find(".tent");
    tent.find(".text").removeClass("heid");
    tent.find(".edit_input").addClass("heid");
    $(target).next().removeClass("heid");
  }

  // 启用编辑
  function beginEditAttr(target) {

    if(!app.hasPer("EDIT")){
      return;
    }

    // 重置之前的编辑
    resetEdit();

    var root = $(target).parent().parent();

    $(target).removeClass("fy-icon-edit").addClass("fy-icon-yes");
    var tent = $(target).parent().parent().find(".tent");
    var val = tent.find(".text").addClass("heid").text();
    $(target).next().addClass("heid");
    tent.find(".edit_input").removeClass("heid").val(val).focus();
  }

  // 重置之前的编辑
  function resetEdit() {
    var content = $(".attrEditContent");
    content.find(".text").removeClass("heid");
    content.find(".edit_input").addClass("heid").val("");
    content.find(".icons .edit").removeClass("fy-icon-yes").addClass("fy-icon-edit");
  }

  function editSpecType(data) {
    $.ajax({
      url: "../goodsSkuAttr/updateSkuTypeNaeByGoodsTypeIdAndSkuSpecType.sec",
      type: "POST",
      data: data,
      success: function (rst) {
        if (rst.status) {
          initGrid(global.defaultData);
        } else {

        }
      },
      error: function () {

      }
    })
  }


  function initGrid(defaultData) {
    $(".attrEditContent").show();
    $.ajax({
      url: "../goodsSkuAttr/getGoodsSkuAttrList.sec",
      type: "POST",
      data: {
        goodsTypeId: global.goodsTypeId
      },
      success: function (rst) {
        if (rst.status) {
          var data = app.deepClone(defaultData);

          var Rows = buildShowAttrArr(data, rst.Rows);
          global.rows = Rows;
          resetEdit();
          showAttrData();

        } else {

        }
      },
      error: function () {

      }
    })
  }

  function showAttrData() {
    var content = $(".attrEditContent");
    var Rows = global.rows;
    for (var i = 0; i < Rows.length; i++) {
      var temp = Rows[i];
      var $el = content.find(".attr_" + (i + 1));
      $el.attr("data-id", i);
      var $text = $el.find(".attr_typename .text");
      var typename = temp.name;

      if (!temp.hasAttr) {
        if(global["typename_" + i]){
          typename = global["typename_" + i];
        }
        $text.addClass("disabled");
      } else {
        $text.removeClass("disabled");
      }
      $text.text(typename);

      for (var j = 0; j < temp.child.length; j++) {
        var tempchild = temp.child[j];
        var attName = $el.find(".attr_" + (i + 1) + "_child_" + (j + 1) + " .text");
        attName.attr("data-id", i + "_" + j).text(tempchild.name);
        if (!tempchild.hasAttr) {
          attName.addClass("disabled");
        } else {
          attName.removeClass("disabled");
        }
      }
    }
  }

  function getAttrSpect() {
    $.ajax({
      url: "../goodsSkuAttr/getGoodsSkuSpect.sec",
      type: "POST",
      success: function (rst) {
        if (rst.status) {
          global.defaultData = rst.data;
          // initGrid(global.defaultData);
        } else {

        }
      },
      error: function () {

      }
    })
  }

  function buildShowAttrArr(defaultData, attrData) {
    var tree = buildDefaultTree(defaultData);


    var lenj = attrData.length;
    for (var k = 0; k < tree.length; k++) {
      var len = tree[k]["child"].length;
      var defaultData = tree[k]["child"];
      for (var i = 0; i < len; i++) {
        defaultData[i].defaultname = defaultData[i].name;
        for (var j = 0; j < lenj; j++) {
          if (defaultData[i].id == attrData[j].skuSpecId && defaultData[i].type == attrData[j].skuSpecType) {
            defaultData[i].defaultSkuSpecTypeName = defaultData[i].skuSpecTypeName;
            defaultData[i].defaultname = attrData[j].skuSpecName;
            defaultData[i].hasAttr = true;
            tree[k].hasAttr = true;
            tree[k].name = attrData[j].skuSpecTypeName;

            defaultData[i].name = attrData[j].skuSpecName;
            defaultData[i].skuSpecId = attrData[j].skuSpecId;
            defaultData[i].skuSpecType = attrData[j].skuSpecType;
            defaultData[i].attrId = attrData[j].id;
            defaultData[i].goodsTypeId = attrData[j].goodsTypeId;
            defaultData[i].skuSpecTypeName = attrData[j].skuSpecTypeName;
            defaultData[i].skuSpecVal = attrData[j].skuSpecVal;
          }
        }
      }
    }


    return tree;
  }

  function buildDefaultTree(def) {
    var len = def.length;
    var lastType = "1";
    var attrTree = [];
    var tempChil = [];
    for (var i = 0; i < len; i++) {
      if (lastType == def[i].type) {
        tempChil.push(def[i]);
      } else {
        attrTree.push({
          type: lastType,
          child: tempChil,
          name: tempChil[tempChil.length - 1].typename
        });
        tempChil = [];
        tempChil.push(def[i]);
      }
      lastType = def[i].type;
    }
    if (tempChil.length > 0) {
      attrTree.push({
        type: lastType,
        child: tempChil,
        name: tempChil[tempChil.length - 1].typename
      });
    }
    return attrTree;
  }

  function submitEdit(data) {
    var ajaxUrl;
    var submitData;
    if (!app.isEmpty(data.name)) {
      if (data.defaultname == data.name) {
        return;
      }
      ajaxUrl = "../goodsSkuAttr/addOrUpdateGoodsSkuAttr.sec";
      submitData = JSON.stringify([{
        id: data.attrId,
        skuSpecName: data.name,
        goodsTypeId: data.goodsTypeId || global.goodsTypeId,
        skuSpecId: data.skuSpecId || data.id,
        skuSpecType: data.skuSpecType || data.type,
        skuSpecTypeName: data.skuSpecTypeName || data.typename,
        skuSpecVal: data.skuSpecVal || data.val,
        sort: data.sort
      }]);
      $.ajax({
        url: ajaxUrl,
        type: "POST",
        // cache: false,
        // async: false,
        dataType: "json",
        contentType: "application/json",
        data: submitData,
        success: function (rst) {
          if (rst.status) {
            initGrid(global.defaultData);
          } else {
            layer.msg(rst.info);
          }
        },
        error: function () {
          layer.msg("网络异常");
        }
      })
    } else {
      if (!app.isEmpty(data.attrId) && !app.isEmpty(data.goodsTypeId) && !app.isEmpty(data.skuSpecId)) {
        ajaxUrl = "../goodsSkuAttr/delGoodsSkuAttr.sec";
        submitData = {
          id: data.attrId
        };
        $.ajax({
          url: ajaxUrl,
          type: "POST",
          // cache: false,
          // async: false,
          data: submitData,
          success: function (rst) {
            if (rst.status) {
              initGrid(global.defaultData);
            } else {
              layer.msg(rst.info);
            }
          },
          error: function () {
            layer.msg("网络异常");
          }
        })
      } else {
        return false;
      }
    }

  }


  function editGoodsTypeSpecName(submitData) {
    submitData.goodsTypeId = global.goodsTypeId;
    $.ajax({
      url: "../goodsSkuAttr/delGoodsSkuAttr.sec",
      type: "POST",
      cache: false,
      async: false,
      data: submitData,
      success: function (rst) {
        if (rst.status) {

        } else {
          layer.msg(rst.info);
        }
      },
      error: function () {
        layer.msg("网络异常");
      }
    })
    global.grid.reload();
  }

  window.reloadAttr = function (typeId) {
    if (app.isEmpty(typeId)) {
      throw new Error("商品分类不能为空");
    }
    global.goodsTypeId = typeId;
    global.typename_1 = "";
    global.typename_2 = "";
    global.typename_3 = "";
    initGrid(global.defaultData);
  }
  window.hideTypeAttr = function () {
    $(".attrEditContent").hide();
  }

})(window, jQuery)