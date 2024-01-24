<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en-US">
<head>
    <title>Panel</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="libs/css/style.css">
    <link rel="stylesheet" href="libs/css/responsive_991.css" media="(max-width:991px)">
    <link rel="stylesheet" href="libs/css/responsive_768.css" media="(max-width:768px)">
    <link rel="stylesheet" href="libs/css/font.css">
    <%--angularJS--%>
    <script src="libs/js/angular.min.js"></script>
    <script src="libs/js/angular-cookies.js"></script>
    <link href="libs/js/textAngular-1.5.16/dist/textAngular.css" rel="stylesheet"/>
    <script src="libs/js/textAngular-1.5.16/dist/textAngular-rangy.min.js"></script>
    <script src="libs/js/textAngular-1.5.16/dist/textAngular-sanitize.min.js"></script>
    <script src="libs/js/textAngular-1.5.16/dist/textAngular.min.js"></script>
</head>
<body>
<div class="sidebar__nav border-top border-left  ">
    <span class="bars d-none padding-0-18"></span>
    <div class="profile__info border cursor-pointer text-center">
        <div class="avatar__img">
            <input type="file" accept="image/*" class="hidden avatar-img__input">
            <div class="v-dialog__container" style="display: block;"></div>
            <div class="box__camera default__avatar"></div>
        </div>
        <span class="profile__name">user: ahmad</span></div>
    <ul>
        <li class="active item-li i-users">
            <a href="#">کاربران</a>
        </li>
        <li ng-class="{'side-nav-active':templateGroup == 'dashboard'}">
            <a href="#" ng-click="changeMenu('dashboard')">
                <i class="fa fa-solar-panel"></i>
                <span>Dashboard</span>
            </a>
        </li>
        <%--<li class="item-li i-courses "><a href="courses.html">دوره ها</a></li>
        <li class="item-li i-users"><a href="users.html"> کاربران</a></li>
        <li class="item-li i-categories"><a href="categories.html">دسته بندی ها</a></li>
        <li class="item-li i-slideshow"><a href="slideshow.html">اسلایدشو</a></li>
        <li class="item-li i-banners"><a href="banners.html">بنر ها</a></li>
        <li class="item-li i-articles"><a href="articles.html">مقالات</a></li>
        <li class="item-li i-ads"><a href="ads.html">تبلیغات</a></li>
        <li class="item-li i-comments"><a href="comments.html"> نظرات</a></li>
        <li class="item-li i-tickets"><a href="tickets.html"> تیکت ها</a></li>
        <li class="item-li i-discounts"><a href="discounts.html">تخفیف ها</a></li>
        <li class="item-li i-transactions"><a href="transactions.html">تراکنش ها</a></li>
        <li class="item-li i-checkouts"><a href="checkouts.html">تسویه حساب ها</a></li>
        <li class="item-li i-checkout__request "><a href="checkout-request.html">درخواست تسویه </a></li>
        <li class="item-li i-my__purchases"><a href="mypurchases.html">خرید های من</a></li>
        <li class="item-li i-notification__management"><a href="notification-management.html">مدیریت اطلاع رسانی</a>
        </li>
        <li class="item-li i-user__inforamtion"><a href="user-information.html">اطلاعات کاربری</a></li>--%>
    </ul>

</div>
<div class="content">
    <div class="header d-flex item-center bg-white width-100 border-bottom padding-12-30">
        <div class="header__right d-flex flex-grow-1 item-center">
            <span class="bars"></span>
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
    <div class="breadcrumb">
        <ul>
            <li><a href="index.html" title="پیشخوان">پیشخوان</a></li>
        </ul>
    </div>

    <div class="main-content">
        <div class="row no-gutters font-size-13 margin-bottom-10">
            <%--<div class="col-3 padding-20 border-radius-3 bg-white margin-left-10 margin-bottom-10">
                <p> موجودی حساب فعلی </p>
                <p>2,500,000 تومان</p>
            </div>
            <div class="col-3 padding-20 border-radius-3 bg-white margin-left-10 margin-bottom-10">
                <p> کل فروش دوره ها</p>
                <p>2,500,000 تومان</p>
            </div>
            <div class="col-3 padding-20 border-radius-3 bg-white margin-left-10 margin-bottom-10">
                <p> کارمزد کسر شده </p>
                <p>2,500,000 تومان</p>
            </div>
            <div class="col-3 padding-20 border-radius-3 bg-white margin-bottom-10">
                <p> درآمد خالص </p>
                <p>2,500,000 تومان</p>
            </div>--%>
        </div>
    </div>
</div>
</body>
<script src="libs/js/jquery-3.5.1.min.js"></script>
<script src="libs/js/js.js"></script>
</html>