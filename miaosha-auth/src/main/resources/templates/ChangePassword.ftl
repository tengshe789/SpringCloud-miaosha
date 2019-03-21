<!doctype html>
<html lang="">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>修改密码</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link href="http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/ionicons.min.css">
        <link rel="stylesheet" href="css/AdminLTE.min.css">
        <link rel="stylesheet" href="css/blue.css">
        <link rel="stylesheet" href="css/style.css">
        <style>
            .login-page {
                background: rgba(27, 53, 65, 0.97);
            }
            .login-page h1 {
                font-weight: 300;
                margin-top: 20px;
                margin-bottom: 10px;
                font-size: 36px;
                color: #fff;
            }
        </style>
    </head>
    <body class="login-page">

        <div class="row">
            <div class="col-lg-4 col-lg-offset-4">
                <img src="img/logo2.png" style="width:200px;height: 200px; cursor:pointer;">
                <h1>统一认证</h1>

                <form method="post" action="api/users/change-password">
                    <div class="form-group">
                        <input  id="username" name="username" type="text" placeholder="用户名" required class="form-control input-underline input-lg"/>
                    </div>
                    <div class="form-group">
                        <input type="password" placeholder="旧密码" class="form-control input-underline input-lg" id="oldPwd" name="oldPwd"  required="" />
                    </div>
                    <div class="form-group">
                        <input type="password" placeholder="新密码" class="form-control input-underline input-lg" id="newPwd" name="newPwd"  required="" />
                    </div>
                    <a class="btn rounded-btn" onclick="changePassword()"> 修改密码 </a>

                    <div class="row">
                        <div class="alert alert-success fade" style="display: none">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            <strong>修改成功!</strong>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <script src="js/vendor/jquery-2.2.3.min.js"></script>
        <script src="js/vendor/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
        <script src="js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>

        <script>
            function changePassword() {
                var user = {
                    "username": $('#username').val(),
                    "oldPwd": $('#oldPwd').val(),
                    "newPwd": $('#newPwd').val()

                };
                $.ajax({
                    url: "/auth/api/users/change-password",
                    type: 'POST',
                    data: JSON.stringify(user),
                    contentType: 'application/json',
                    success: function (data) {
                        //alert(data);
                        $(".alert-success").removeClass("in").show();
                        $(".alert-success").delay(200).addClass("in").fadeOut(2000);

                        setTimeout(function () {
                            window.location.href = "/";
                        }, 3000);

                    }
                });
            };
        </script>
    </body>
</html>