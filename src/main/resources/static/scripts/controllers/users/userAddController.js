app.controller('userAddCtrl', function ($scope, apiHandler) {
    $scope.data = {};

    // Function to validate password
    $scope.validatePassword = function() {
        const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

        if (!passwordPattern.test($scope.data.password)) {
            Swal.fire({
                title: "خطا",
                text: "کلمه عبور باید حداقل 8 کاراکتر باشد و شامل حروف بزرگ و کوچک، اعداد و نمادهای خاص باشد",
                icon: "error"
            });
            return false;
        }
        return true;
    };
    function validateIranianNationalCode(code) {
        if (!/^\d{10}$/.test(code)) return false;

        let check = parseInt(code[9]);
        let sum = Array(9).fill().map((_, i) => parseInt(code[i]) * (10 - i)).reduce((x, y) => x + y) % 11;

        return sum < 2 && check === sum || sum >= 2 && check + sum === 11;
    }

    $scope.addData = () => {
        if ($scope.data.firstname === undefined || $scope.data.firstname === "" || $scope.data.firstname == null) {
            Swal.fire({
                title: "خطا",
                text: "نام نمی تواند خالی باشد",
                icon: "error",
                confirmButtonText: "فهمیدم"
            });
            return;
        }

        if ($scope.data.lastname === undefined || $scope.data.lastname === "" || $scope.data.lastname == null) {
            Swal.fire({
                title: "خطا",
                text: "نام خانوادگی نمی تواند خالی باشد",
                icon: "error",
                confirmButtonText: "فهمیدم"
            });
            return;
        }
        if ($scope.data.username === undefined || $scope.data.username === "" || $scope.data.username == null) {
            Swal.fire({
                title: "خطا",
                text: "نام کاربری نمی تواند خالی باشد",
                icon: "error",
                confirmButtonText: "فهمیدم"
            });
            return;
        }
        if ($scope.data.password === undefined || $scope.data.password === "" || $scope.data.password == null) {
            Swal.fire({
                title: "خطا",
                text: "کلمه عبور نمی تواند خالی باشد",
                icon: "error",
                confirmButtonText: "فهمیدم"
            });
            return;
        }
        if(!$scope.validatePassword())
            return;
        if ($scope.data.nationalCode === undefined || $scope.data.nationalCode === "" || $scope.data.nationalCode == null) {
            Swal.fire({
                title: "خطا",
                text: "کدملی نمی تواند خالی باشد",
                icon: "error",
                confirmButtonText: "فهمیدم"
            });
            return;
        }
        const nationalCodePattern = /^([0-9]{10})$/;
        if (!nationalCodePattern.test($scope.data.nationalCode)) {
            Swal.fire({
                title: "خطا",
                text: "فرمت کدملی صحیح نیست",
                icon: "error",
                confirmButtonText: "فهمیدم"
            });
            return;
        }
        if (!validateIranianNationalCode($scope.data.nationalCode)){
            Swal.fire({
                title: "خطا",
                text: "کد ملی وارد شده نامعتبر میباشد",
                icon: "error",
                confirmButtonText: "فهمیدم"
            });
            return;
        }
        if ($scope.data.enable === undefined || $scope.data.enable == null) {
            Swal.fire({
                title: "خطا",
                text: "لطفا فعال / غیرفعال کاربر را تعیین کنید!",
                icon: "error",
                confirmButtonText: "فهمیدم"
            });
            return;
        }

        apiHandler.callPost('user/add', $scope.data, (response) => {
            $scope.changeMenu('user-list');//after set switch in nav list page
            Swal.fire({
                title: "موفق",
                text: "ایجاد کاربر با موفقیت به ثبت رسید",
                icon: "success",
                confirmButtonText: "فهمیدم"
            });
        }, (error) => {
        }, true)
    }
});