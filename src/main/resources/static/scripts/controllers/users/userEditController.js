app.controller('userEditCtrl', function ($scope, apiHandler,$rootScope) {
    $scope.data = {};
    $scope.id = $rootScope.dataId;

    $scope.editData = () =>{
        if ($scope.data.firstname === undefined || $scope.data.firstname === "" || $scope.data.firstname == null){
            Swal.fire("فیلد نام نمی تواند خالی باشد");
            return;
        }
        if ($scope.data.lastname === undefined || $scope.data.lastname === "" || $scope.data.lastname == null){
            Swal.fire("فیلد نام خانوادگی نمی تواند خالی باشد");
            return;
        }
        if ($scope.data.username === undefined || $scope.data.username === "" || $scope.data.username == null){
            Swal.fire("فیلد نام کاربری نمی تواند خالی باشد");
            return;
        }
        if ($scope.data.nationalCode === undefined || $scope.data.nationalCode === "" || $scope.data.nationalCode == null){
            Swal.fire("فیلد کد ملی نمی تواند خالی باشد");
            return;
        }
        if ($scope.data.enable === undefined || $scope.data.enable == null){
            Swal.fire("لطفا وضعیت فعال / غیر فعال بودن کاربر را مشخص کنید!");
            return;
        }
        apiHandler.callPut('user/update',$scope.data,(response) => {
            $scope.changeMenu('user-list');
            Swal.fire({
                title: "success",
                text: "your data updated successfully",
                icon: "success"
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