app.controller('menuAddCtrl', function ($scope, apiHandler) {
    $scope.data = {};
    $scope.sysmans = [];

    $scope.fetchSysmans = () => {
        apiHandler.callGet('sysman/list-for-menus', (response) => {
            $scope.sysmans = response.dataList;
        }, (error) => {
            console.error('Error fetching sysmans:', error);
        });
    };

    $scope.fetchSysmans();

    $scope.addData = () => {
        apiHandler.callPost('menu/add', $scope.data, (response) => {
            $scope.changeMenu('menu-list');
            Swal.fire({
                title: "موفق",
                text: "ایجاد منو با موفقیت به ثبت رسید",
                icon: "success",
                confirmButtonText: "فهمیدم"
            });
        }, (error) => {
        }, true)
    }
});