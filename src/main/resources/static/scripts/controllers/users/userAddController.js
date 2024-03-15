app.controller('userAddCtrl', function ($scope, apiHandler) {
    $scope.data = {};

    // Function to validate password
    /*$scope.validatePassword = function() {
        const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

        if (!passwordPattern.test($scope.data.password)) {
            Swal.fire("کلمه عبور باید حداقل 8 کاراکتر باشد و شامل حروف بزرگ و کوچک، اعداد و نمادهای خاص باشد");
            return false;
        }
        return true;
    };*/

    $scope.addData = () => {
        if ($scope.data.firstname === undefined || $scope.data.firstname === "" || $scope.data.firstname == null) {
            Swal.fire("نام نمی تواند خالی باشد");
            return;
        }
        if ($scope.data.lastname === undefined || $scope.data.lastname === "" || $scope.data.lastname == null) {
            Swal.fire("نام خانوادگی نمی تواند خالی باشد");
            return;
        }
        if ($scope.data.username === undefined || $scope.data.username === "" || $scope.data.username == null) {
            Swal.fire("نام کاربری نمی تواند خالی باشد");
            return;
        }
        if ($scope.data.password === undefined || $scope.data.password === "" || $scope.data.password == null) {
            Swal.fire("کلمه عبور نمی تواند خالی باشد");
            return;
        }
        /*if(!$scope.validatePassword())
            return;*/
        if ($scope.data.nationalCode === undefined || $scope.data.nationalCode === "" || $scope.data.nationalCode == null) {
            Swal.fire("کدملی نمی تواند خالی باشد");
            return;
        }
        if ($scope.data.enable === undefined || $scope.data.enable == null) {
            Swal.fire("لطفا فعال / غیرفعال کاربر را تعیین کنید!");
            return;
        }

        apiHandler.callPost('user/add', $scope.data, (response) => {
            $scope.changeMenu('user-list');//after set switch in nav list page
            Swal.fire({
                title: "موفق",
                text: "ایجاد کاربر با موفقیت به ثبت رسید",
                icon: "success"
            });
        }, (error) => {
        }, true)
    }
});