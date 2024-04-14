<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en-US">
<head>
    <title>پنل مدیریت</title>
    <link href="libs/bootstrap-4.5.2-dist/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="libs/jquery-3.5.1.min.js"></script>
    <script src="libs/bootstrap-4.5.2-dist/js/bootstrap.min.js"></script>
    <script src="libs/angular.min.js"></script>
    <script src="libs/angular-cookies.js"></script>
    <script src="libs/sweetalert2/dist/sweetalert2.all.min.js"></script>
    <link href="libs/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet"/>
    <link href="libs/sweetalert2/dist/sweetalert2.min.css" rel="stylesheet"/>
    <link href="libs/textAngular-1.5.16/dist/textAngular.css" rel="stylesheet"/>
    <script src="libs/textAngular-1.5.16/dist/textAngular-rangy.min.js"></script>
    <script src="libs/textAngular-1.5.16/dist/textAngular-sanitize.min.js"></script>
    <script src="libs/textAngular-1.5.16/dist/textAngular.min.js"></script>
    <script src="scripts/app.js"></script>
    <script src="scripts/services/ApiHandler.js"></script>
    <script src="scripts/directives/fileModel.js"></script>
    <script src="scripts/controllers/util/uploadFileController.js"></script>
    <script src="scripts/controllers/util/getFilesCtrl.js"></script>
    <script src="scripts/controllers/PanelController.js"></script>
    <script src="scripts/controllers/users/userListController.js"></script>
    <script src="scripts/controllers/users/userAddController.js"></script>
    <script src="scripts/controllers/users/userEditController.js"></script>
    <link rel="stylesheet" href="styles/panel.css">

</head>
<body ng-app="onlineShopApp">

<div class="container-fluid" ng-controller="panelCtlr">
    <div class="col p-0">
        <div class="panel-header">
            <a href="/logout" class="btn btn-danger btn-sysman">Logout</a>
        </div>
    </div>
    <div class="row">
        <div class="col-2 p-0">
            <div class="side-nav">
                <div class="text-center p-3">
                    <img src="images/useravatar.webp" width="100" alt="avatar">
                    <br>
                    <span>{{user.fullName}}</span> <!--call by angular api-->
                </div>
                <ul>
                    <li ng-class="{'side-nav-active':templateGroup == 'dashboard'}">
                        <a href="#" ng-click="changeMenu('dashboard')">
                            <i class="fa fa-solar-panel"></i>
                            <span>Dashboard</span>
                        </a>
                    </li>
                    <li ng-class="{'side-nav-active':templateGroup == 'user'}">
                        <a href="#" ng-click="changeMenu('user-list')">
                            <i class="fa fa-users"></i>
                            <span>Users</span>
                        </a>
                    </li>
                    <li ng-class="{'side-nav-active':templateGroup == 'uploader'}">
                        <a href="#" ng-click="changeMenu('uploader')">
                            <i class="fa fa-file-import"></i>
                            <span>File Manager</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="col p-0">
            <div class="main-container" ng-include="template">

            </div>
        </div>
    </div>
</div>
</body>
</html>