app.controller('userAddCtrl', function ($scope, apiHandler) {
    $scope.data = {};

    $scope.addData = () =>{
        if ($scope.data.firstname === undefined || $scope.data.firstname === "" || $scope.data.firstname == null){
            Swal.fire("please enter firstname!");
            return;
        }
        if ($scope.data.lastname === undefined || $scope.data.lastname === "" || $scope.data.lastname == null){
            Swal.fire("please enter lastname!");
            return;
        }
        if ($scope.data.username === undefined || $scope.data.username === "" || $scope.data.username == null){
            Swal.fire("please enter username!");
            return;
        }
        if ($scope.data.password === undefined || $scope.data.password === "" || $scope.data.password == null){
            Swal.fire("please enter password!");
            return;
        }
        if ($scope.data.nationalCode === undefined || $scope.data.nationalCode === "" || $scope.data.nationalCode == null){
            Swal.fire("please enter national-Code!");
            return;
        }
        if ($scope.data.enable === undefined || $scope.data.enable == null){
            Swal.fire("please set enable yes/no!");
            return;
        }
        /*if ($scope.data.role === undefined || $scope.data.role == null){
            Swal.fire("please set role admin/user!");
            return;
        }*/
        apiHandler.callPost('user/add',$scope.data,(response) => {
            $scope.changeMenu('user-list');//after set switch in nav list page
            Swal.fire({
                title: "success",
                text: "your data insert successfully",
                icon: "success"
            });
        },(error) => {

        },true)
    }
});