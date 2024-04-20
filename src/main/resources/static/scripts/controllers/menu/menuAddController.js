app.controller('menuAddCtrl', function ($scope, apiHandler) {
    $scope.data = {};

    $scope.addData = () => {
        if ($scope.data.en_name === undefined || $scope.data.en_name === "" || $scope.data.en_name == null) {
            Swal.fire({
                title: "خطا",
                text: "نام انگلیسی نمی تواند خالی باشد",
                icon: "error",
                confirmButtonText: "فهمیدم"
            });
            return;
        }
        if ($scope.data.fa_name === undefined || $scope.data.fa_name === "" || $scope.data.fa_name == null) {
            Swal.fire({
                title: "خطا",
                text: "نام فارسی نمی تواند خالی باشد",
                icon: "error",
                confirmButtonText: "فهمیدم"
            });
            return;
        }
        if ($scope.data.route === undefined || $scope.data.route === "" || $scope.data.route == null) {
            Swal.fire({
                title: "خطا",
                text: "مسیر سامانه نمی تواند خالی باشد",
                icon: "error",
                confirmButtonText: "فهمیدم"
            });
            return;
        }

        apiHandler.callPost('menu/add', $scope.data, (response) => {
            $scope.changeMenu('sysman-list');//after set switch in nav list page
            Swal.fire({
                title: "موفق",
                text: "ایجاد سامانه با موفقیت به ثبت رسید",
                icon: "success",
                confirmButtonText: "فهمیدم"
            });
        }, (error) => {
        }, true)
    }
});