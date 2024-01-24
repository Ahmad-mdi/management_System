<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="libs/angular.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <script src="scripts/app.js"></script>
    <script src="scripts/controllers/PanelController.js"></script>
    <title>پنل مدیریت</title>
</head>
<body dir="rtl">
<div ng-app="myapp">
    <div ng-controller="panelCtrl">
        <nav class="navbar navbar-expand-lg" style="background-color: #e3f2fd;" data-bs-theme="light">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">پنل مدیریت</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor03" aria-controls="navbarColor03" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarColor03">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    </ul>
                    <form class="d-flex" role="search">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-primary" type="submit">جستجو</button>
                    </form>
                </div>
            </div>
        </nav>
        <div class="container-fluid">
            <div class="row">
                <div class="col col-2">
                    <div class="list-group">
                        <br>
                        <button type="button" class="list-group-item list-group-item-action active" aria-current="true" ng-class="{'active':templateGroup == 'dashboard'}" ng-click="changeMenu('dashboard')">
                            داشبورد
                        </button>
                        <button type="button" class="list-group-item list-group-item-action" ng-class="{'active' : templateGroup == 'user'}" ng-click="changeMenu('user-list')">لیست کاربران</button>
<%--                        <button type="button" class="list-group-item list-group-item-action" ng-class="{'active' : templateName == 'settings'}" ng-click="changeMenu('settings')">settings</button>--%>
                        <!-- <button type="button" class="list-group-item list-group-item-action">A fourth button item</button> -->
                        <button type="button" class="list-group-item list-group-item-action" disabled>logout</button>
                    </div>
                </div>
                <div class="col col-10">
                    <br>
                    <!-- include by angular -->
                    <div ng-include="template"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>