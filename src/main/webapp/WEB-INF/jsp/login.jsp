<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    <script src="libs/jquery-3.5.1.min.js"></script>
    <script src="libs/angular.min.js"></script>
    <script src="libs/angular-cookies.js"></script>
    <script src="libs/sweetalert2/dist/sweetalert2.all.min.js"></script>
    <link href="libs/sweetalert2/dist/sweetalert2.min.css" rel="stylesheet"/>
    <link href="libs/textAngular-1.5.16/dist/textAngular.css" rel="stylesheet"/>
    <script src="libs/textAngular-1.5.16/dist/textAngular-rangy.min.js"></script>
    <script src="libs/textAngular-1.5.16/dist/textAngular-sanitize.min.js"></script>
    <script src="libs/textAngular-1.5.16/dist/textAngular.min.js"></script>
    <script src="scripts/app.js"></script>
    <script src="scripts/services/ApiHandler.js"></script>
    <script src="scripts/controllers/loginController.js"></script>
    <link href="styles/login.css" rel="stylesheet"/>
</head>
<body ng-app="onlineShopApp">

<div class="container-fluid" ng-controller="loginCtrl">
    <div class="row justify-content-center">
        <div class="col-md-4 login-box-holder">
            <h3 class="text-center">ورود به سامانه</h3>
            <form ng-submit="doLogin()">
                <div class="form-group">
                    <label for="username">نام کاربری</label>
                    <input type="text" class="form-control" id="username" ng-model="user.username" required>
                </div>
                <div class="form-group">
                    <label for="password">کلمه عبور</label>
                    <input type="password" class="form-control" id="password" ng-model="user.password" required>
                </div>
                <button type="submit" class="btn btn-primary btn-block">ورود</button>
            </form>
        </div>
    </div>
</div>


</body>
</html>