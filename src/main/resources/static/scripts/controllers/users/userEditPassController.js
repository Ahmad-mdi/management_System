app.controller('userEditPassCtrl', function ($scope, apiHandler,$rootScope) {
    $scope.data = {};
    $scope.id = $rootScope.dataId;

    $scope.editData = () =>{
        if ($scope.data.password === undefined || $scope.data.password === "" || $scope.data.password == null){
            Swal.fire("فیلد کلمه عبور فعلی نمی تواند خالی باشد");
            return;
        }
        if ($scope.data.newPassword === undefined || $scope.data.newPassword === "" || $scope.data.newPassword == null){
            Swal.fire("فیلد کلمه عبور جدید نمی تواند خالی باشد");
            return;
        }
        if ($scope.data.newPassword !== $scope.data.c_password) {
            Swal.fire("کلمه عبور جدید و تکرار آن باید یکسان باشند");
            return;
        }


        apiHandler.callPut(`user/change-password?id=${$scope.id}&password=${encodeURIComponent($scope.data.password)}&newPassword=${encodeURIComponent($scope.data.newPassword)}`,$scope.data,(response) => {
            $scope.changeMenu('user-list');
            Swal.fire({
                title: "تغییر کلمه عبور",
                text: "کلمه عبور جدید با موفقیت بروزرسانی شد",
                icon: "warning",
                confirmButtonText: "فهمیدم",
            });
        },(error) => {

        },true)
    }

    //send data for edit and get data:
    $scope.getData = () =>{
        apiHandler.callGet('user/'+$scope.id,(response) => {
            $scope.data = response.dataList[0];
        },(error) =>{},true);
    }

    $scope.getData();
});