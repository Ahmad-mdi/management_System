app.controller('menuEditCtrl', function ($scope, apiHandler,$rootScope) {
    $scope.data = {};
    $scope.id = $rootScope.dataId;
    $scope.sysmans = [];

    $scope.fetchSysmans = () => {
        apiHandler.callGet('sysman/list-for-add-menu', (response) => {
            $scope.sysmans = response.dataList;
        }, (error) => {
            console.error('Error fetching sysmans:', error);
        });
    };

    $scope.editData = () =>{

        apiHandler.callPut('menu/update',$scope.data,(response) => {
            $scope.changeMenu('menu-list');
            Swal.fire({
                title: "بروزرسانی منو",
                text: "با موفقیت انجام شد",
                icon: "success"
            });
        },(error) => {

        },true)
    }

    //send data for edit and get data:
    $scope.getData = () =>{
        apiHandler.callGet('menu/'+$scope.id,(response) => {
            $scope.data = response.dataList[0];
            $scope.fetchSysmans();
        },(error) =>{},true);
    }

    $scope.getData();
});