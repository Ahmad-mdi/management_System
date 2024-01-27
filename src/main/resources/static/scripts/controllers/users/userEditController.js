app.controller('userEditCtrl', function ($scope, apiHandler,$rootScope) {
    $scope.data = {};
    $scope.id = $rootScope.dataId;

    $scope.editData = () =>{
        if ($scope.data.firstname === undefined || $scope.data.firstname === "" || $scope.data.firstname == null){
            Swal.fire("please enter firstname!");
            return;
        }
        if ($scope.data.lastname === undefined || $scope.data.lastname === "" || $scope.data.lastname == null){
            Swal.fire("please enter lastname!");
            return;
        }

        if ($scope.data.email === undefined || $scope.data.email === "" || $scope.data.email == null){
            Swal.fire("please enter email!");
            return;
        }
        if ($scope.data.enable === undefined || $scope.data.enable == null){
            Swal.fire("please set enable yes/no!");
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