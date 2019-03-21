<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>注册</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link href="http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/ionicons.min.css">
        <link rel="stylesheet" href="css/AdminLTE.min.css">
        <link rel="stylesheet" href="css/blue.css">
        <link rel="stylesheet" href="css/style.css">
        <style>
            .register-page {
                background: rgba(27, 53, 65, 0.97);
            }
            .register-page h1 {
                font-weight: 300;
                margin-top: 20px;
                margin-bottom: 10px;
                font-size: 36px;
                color: #fff;
            }
        </style>
    </head>
    <body class="register-page login-page">

        <div class="row">
            <div class="col-lg-4 col-lg-offset-4">
                <img src="img/logo2.png" style="width:200px;height: 200px; cursor:pointer;">
                <h1>统一认证</h1>
                <form action="" id="registerForm" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control input-underline input-lg" id="username" name="username" placeholder="用户名">
                    </div>
                    <div class="form-group">
                        <input type="email" class="form-control input-underline input-lg" id="email" name="email" placeholder="邮箱">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control input-underline input-lg" id="password" name="password" placeholder="密码">
                    </div>
                    <div class="form-group">
                        <input type="password" id="passwordTwice" name="passwordTwice" class="form-control input-underline input-lg" placeholder="再次输入密码" >
                    </div>

                    <div class="row">
                        <div class="alert alert-success fade" style="display: none;">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            <strong style="color: #ffffff;"> 注册成功！</strong>
                        </div>
                        <div class="alert alert-warning fade" style="display: none;">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            <strong style="color: #ffffff;"> 注册失败！</strong>
                        </div>
                    </div>
                    <br>
                    <button class="btn rounded-btn" id="submit" type="submit"> 注册 </button>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a class="btn rounded-btn" type="submit" href="/auth/login" > 登录 </a>
                </form>
            </div>
        </div>

        <script src="js/vendor/jquery-2.2.3.min.js"></script>
        <script src="js/vendor/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
        <script src="js/vendor/jquery.validate.min.js"></script>
        <script src="js/vendor/jquery.serializejson.min.js"></script>
        <script src="js/vendor/jquery.form.min.js"></script>

        <script>
            /*表单验证*/
            $("#registerForm").validate({
                rules: {
                    username: {
                        required: true,
                        minlength: 2,
                        remote: {
                            url: '/auth/v1/users/register/name',
                            type: 'GET',
                            data: {
                                username: function () {
                                    return $('#username').val();
                                }
                            },
                            dataFilter: function (data) {
                                if(data == 'true') {
                                    return true;
                                }else {
                                    return false;
                                }
                            }
                        }
                    },
                    email: {
                        required: true,
                        email: true
                    },
                    password: {
                        required: true,
                        minlength: 5
                    },
                    passwordTwice: {
                        required: true,
                        minlength: 5,
                        equalTo: "#password"
                    }
                },
                messages: {
                    username: {
                        required: "请输入用户名！",
                        minlength: "用户名长度最少两个字母!",
                        remote: "用户名已存在！"
                    },
                    email: "请输入正确的邮箱地址！",
                    password: {
                        required: "请输入密码！",
                        minlength: "密码长度最少五个字母!"
                    },
                    passwordTwice: {
                        required: "请输入密码！",
                        minlength: "密码长度最少五个字母！",
                        equalTo: "两次密码输入不一致！"
                    }
                },
                submitHandler: function (form) {
                    var user = $('#registerForm').serializeJSON();
                    $.ajax({
                        url: "/auth/v1/users/register",
                        type: 'POST',
                        contentType: 'application/json',
                        data: JSON.stringify(user),
                        success: function (data) {
                            $(".alert-success").removeClass("in").show();
                            $(".alert-success").delay(200).addClass("in").fadeOut(2000);
                        },
                        error: function (error) {
                            $(".alert-warning").removeClass("in").show();
                            $(".alert-warning").delay(200).addClass("in").fadeOut(2000);
                        }
                    });
                }
            });
        </script>
    </body>
</html>