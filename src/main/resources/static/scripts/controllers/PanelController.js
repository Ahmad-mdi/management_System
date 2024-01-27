app.controller("panelCtlr", function ($scope, apiHandler, $cookies, $rootScope) {

    $scope.template = "views/dashboard.html";//dashboard
    $scope.templateName = "dashboard";
    $scope.templateGroup = "dashboard";

    $scope.checkAccess = () => {
        let token = $cookies.get("userToken");
        debugger;
        if (token === undefined || token == null || token === "") {
            location.href = "/login";
            return;
        }
        $scope.getUserInfo();
    }
    $scope.getUserInfo = () => {
        apiHandler.callGet('user/getUserInfo', (response) => {
            debugger;
            $rootScope.userInfo = response.dataList[0];
            $scope.user = $rootScope.userInfo;
            console.log($scope.user);
        }, (error) => {

        }, true);
    }
//activation and selected menu:
    $scope.changeMenu = (templateName) => {
        $scope.templateName = templateName;
        $scope.template = $scope.getMenuPrefix(templateName);
        $scope.templateGroup = $scope.getMenuGroup(templateName);
    }
//for show page.html by angularJs:
    $scope.getMenuPrefix = (templateName) => {
        if (templateName === 'dashboard') {
            return 'views/' + templateName + '.html';
        } else if (templateName === 'nav-list' || templateName === 'nav-add' || templateName === 'nav-edit') {
            return 'views/site/nav/' + templateName + '.html';
        } else if (templateName === 'content-list' || templateName === 'content-add' || templateName === 'content-edit') {
            return 'views/site/content/' + templateName + '.html';
        } else if (templateName === 'slider-list' || templateName === 'slider-add' || templateName === 'slider-edit') {
            return 'views/site/slider/' + templateName + '.html';
        } else if (templateName === 'blog-list' || templateName === 'blog-add' || templateName === 'blog-edit') {
            return 'views/site/blog/' + templateName + '.html';
        }else if (templateName === 'user-list' || templateName === 'user-add' || templateName === 'user-edit') {
            return 'views/users/' + templateName + '.html';
        }else if (templateName === 'category-list' || templateName === 'category-add' || templateName === 'category-edit') {
            return 'views/products/category' + templateName + '.html';
        } else if (templateName === 'uploader') {
            return 'views/util/' + templateName + '.html';
        } else {
            return 'views/dashboard.html';
        }
    }
// for active item by select user:
    $scope.getMenuGroup = (templateName) => {
        if (templateName === 'dashboard') {
            return 'dashboard';
        } else if (templateName === 'nav-list' || templateName === 'nav-edit' || templateName === 'nav-add') {
            return 'nav';
        } else if (templateName === 'content-list' || templateName === 'content-edit' || templateName === 'content-add') {
            return 'content';
        } else if (templateName === 'slider-list' || templateName === 'slider-edit' || templateName === 'slider-add') {
            return 'slider';
        }else if (templateName === 'blog-list' || templateName === 'blog-edit' || templateName === 'blog-add') {
            return 'blog';
        }else if (templateName === 'user-list' || templateName === 'user-edit' || templateName === 'user-add') {
            return 'user';
        }else if (templateName === 'category-list' || templateName === 'category-edit' || templateName === 'category-add') {
            return 'category';
        } else if (templateName === 'uploader') {
            return 'uploader';
        } else {
            return 'dashboard';
        }
    }
    $scope.checkAccess();
});

