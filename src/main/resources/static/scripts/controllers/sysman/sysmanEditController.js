app.controller('sysmanEditCtrl', function ($scope, apiHandler,$rootScope) {
    $scope.data = {};
    $scope.id = $rootScope.dataId;

    $scope.editData = () =>{
        if ($scope.data.en_name === undefined || $scope.data.en_name === "" || $scope.data.en_name == null){
            Swal.fire("فیلد نام نمی تواند خالی باشد");
            return;
        }
        if ($scope.data.fa_name === undefined || $scope.data.fa_name === "" || $scope.data.fa_name == null){
            Swal.fire("فیلد نام خانوادگی نمی تواند خالی باشد");
            return;
        }
        if ($scope.data.route === undefined || $scope.data.route === "" || $scope.data.route == null){
            Swal.fire("فیلد نام کاربری نمی تواند خالی باشد");
            return;
        }

        apiHandler.callPut('sysman/update',$scope.data,(response) => {
            $scope.changeMenu('sysman-list');
            Swal.fire({
                title: "بروزرسانی سامانه",
                text: "با موفقیت بروزرسانی شد",
                icon: "success"
            });
        },(error) => {

        },true)
    }

    //send data for edit and get data:
    $scope.getData = () =>{
        apiHandler.callGet('sysman/'+$scope.id,(response) => {
            $scope.data = response.dataList[0];
        },(error) =>{},true);
    }

    $scope.getData();
});