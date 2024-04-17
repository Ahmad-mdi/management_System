<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fa">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0;">
    <title>پنل مدیریت</title>
    <script src="libs/jquery-3.5.1.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
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
    <script src="scripts/directives/fileModel.js"></script>
    <script src="scripts/controllers/util/uploadFileController.js"></script>
    <script src="scripts/controllers/util/getFilesCtrl.js"></script>
    <script src="scripts/controllers/PanelController.js"></script>
    <script src="scripts/controllers/users/userListController.js"></script>
    <script src="scripts/controllers/users/userAddController.js"></script>
    <script src="scripts/controllers/users/userEditController.js"></script>
    <script src="scripts/controllers/users/userEditPassController.js"></script>
    <script src="scripts/controllers/sysman/sysmanEditController.js"></script>
    <script src="scripts/controllers/sysman/sysmanAddController.js"></script>
    <script src="scripts/controllers/sysman/sysmanListController.js"></script>
    <link rel="stylesheet" href="styles/css/style.css">
    <link rel="stylesheet" href="styles/css/responsive_991.css" media="(max-width:991px)">
    <link rel="stylesheet" href="styles/css/responsive_768.css" media="(max-width:768px)">
    <link rel="stylesheet" href="styles/css/font.css">
</head>

<body dir="rtl" ng-app="onlineShopApp"  ng-controller="panelCtlr">
<div class="sidebar__nav border-top border-left">
    <span class="bars d-none padding-0-18"></span>
    <a class="header__logo  d-none" href=""></a>
    <div class="profile__info border cursor-pointer text-center">
        <div class="avatar__img"><img src="/img/pro.jpg" class="avatar___img">
            <input type="file" accept="image/*" class="hidden avatar-img__input">
            <div class="v-dialog__container" style="display: block;"></div>
            <div class="box__camera default__avatar"></div>
        </div>
        <span class="profile__name">{{user.fullName}}</span>
    </div>


    <ul>
        <li class="item-li i-dashboard" ng-class="{'is-active':templateGroup == 'dashboard'}">
            <a href="#" ng-click="changeMenu('dashboard')">
                <i class="fa fa-dashboard"></i>
                <span>داشبورد</span>
            </a>
        </li>
        <li class="item-li i-users" ng-class="{'is-active':templateGroup == 'user'}">
            <a href="#" ng-click="changeMenu('user-list')">
                <i class="fa fa-users"></i>
                <span>مدیریت کاربران</span>
            </a>
        </li>
        <li class="item-li i-checkout__request" ng-class="{'is-active':templateGroup == 'sysman'}">
            <a href="#" ng-click="changeMenu('sysman-list')">
                <i class="fa fa-users"></i>
                <span>مدیریت سامانه ها</span>
            </a>
        </li>
    </ul>

</div>
<div class="content">
    <div class="header d-flex item-center bg-white width-100 border-bottom padding-12-30">
        <div class="header__right d-flex flex-grow-1 item-center">
            <span class="bars"></span>
            <a class="header__logo" href=""></a>
        </div>
        <div class="header__left d-flex flex-end item-center margin-top-2">
            <span class="account-balance font-size-12"></span>
            <div class="notification margin-15">
                <a class="notification__icon"></a>
                <div class="dropdown__notification">
                    <div class="content__notification">
                        <span class="font-size-13">موردی برای نمایش وجود ندارد</span>
                    </div>
                </div>
            </div>
            <a href="" class="logout" title="خروج"></a>
        </div>
    </div>
    <%--<div class="breadcrumb">
        <ul>
            <li><a href="index.html" title="پیشخوان">پیشخوان</a></li>
        </ul>
    </div>--%>

    <div class="main-content" ng-include="template">

    </div>
</div>
<script src="scripts/js/js.js"></script>
</body>
</html>
