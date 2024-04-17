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
            apiHandler.callGet('user/get-user-info', (response) => {
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
            } else if (templateName === 'user-list' || templateName === 'user-add' || templateName === 'user-edit' || templateName ==='user-edit-pass') {
                return 'views/users/' + templateName + '.html';
            } else if (templateName === 'sysman-list' || templateName === 'sysman-add' || templateName === 'sysman-edit') {
                return 'views/sysman/' + templateName + '.html';
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
            } else if (templateName === 'user-list' || templateName === 'user-edit' || templateName === 'user-add' || templateName === 'user-edit-pass') {
                return 'user';

            } else if (templateName === 'sysman-list' || templateName === 'sysman-edit' || templateName === 'sysman-add') {
                return 'sysman';

            } else if (templateName === 'uploader') {
                return 'uploader';
            } else {
                return 'dashboard';
            }
        }
        $scope.checkAccess();
    }
)
;

