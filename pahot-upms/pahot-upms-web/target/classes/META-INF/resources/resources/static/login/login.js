if (window.parent != window) {
  //是子页面
  var thisHref = window.location.href;
  if (thisHref.indexOf("login.ak") != -1) {
    //是登录页面
    window.parent.location.reload();
  }
}
$(document).ready(function () {
  $("#submitButton").click(function () {
    login()
  });
  $("#loginform .input-text").keydown(function (e) {
    if(e.keyCode != 13) return;
    if($("#username").val() == ""){
      return;
    }
    if($("#password").val() == ""){
      return;
    }
    login();
  })

  function login() {
    var username = $("#username").val();
    var password = $("#password").val();
    $.ajax({
      type: "POST",
      url: "doLogin.ak",
      data: {
        username: username,
        password: password
      },
      success: function (data) {
        console.log(data);
        if(data.status){
          window.location.href = data.url;
        } else {
          var msg = data.info;
          msg = msg == null ? "登录失败" : msg;
          layer.msg(msg);
        }
      },
      error: function (err) {
        layer.msg("登录失败，请检查网络！");
      }
    })
  }
})