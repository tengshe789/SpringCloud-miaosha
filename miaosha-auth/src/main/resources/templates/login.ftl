<!doctype html>
<html lang="">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>登录</title>
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

        <form method="post" action="login" role="login" id="loginForm">
            <div class="form-group">
                <input  id="username" name="username" type="text" placeholder="用户名" class="form-control input-underline input-lg"/>
            </div>
            <div class="form-group">
                <input type="password" placeholder="密码" class="form-control input-underline input-lg" id="password" name="password"/>
            </div>
            <div class="checkbox pull-right" style="display: none;">
                <input type="checkbox" name="remember-me" checked/> 下次自动登录
            </div>
            <button type="submit" class="btn rounded-btn" > 登录 </button>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <a class="btn rounded-btn" type="submit" href="/auth/register" style="display: none;"> 注册 </a>
        </form>
    </div>
</div>
<script src="js/vendor/jquery-2.2.3.min.js"></script>
<script src="js/vendor/bootstrap.min.js"></script>
<script src="js/main.js"></script>
<script src="js/vendor/jquery.validate.min.js"></script>
<script>
    /*表单验证*/
    $("#loginForm").validate({
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            username: {
                required: "请输入用户名！"
            },
            password: {
                required: "请输入密码！"
            }
        }
    });
</script>
</body>
</html>