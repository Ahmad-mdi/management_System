app.controller('userListCtrl', function ($scope, apiHandler, $rootScope) {

    $scope.query = {
        pageSize: 10,
        pageNumber: 0
    };
    $scope.totalCount = 0;
    $scope.dataList = [];
    $scope.pageCount = 0;
    $scope.userInfo = $rootScope.userInfo;

    $scope.getDataList = () => {
        let url = 'user/getAll?pageSize=' + $scope.query.pageSize + '&pageNumber=' + $scope.query.pageNumber;
        apiHandler.callGet(url, (response) => {
            $scope.dataList = response.dataList;
            $scope.totalCount = response.totalCount;
            $scope.pageCount = $scope.totalCount / $scope.query.pageSize;
            $scope.pageCount = parseInt($scope.pageCount);
            if ($scope.totalCount % $scope.pageSize > 0)
                $scope.pageCount++;
        }, (error) => {

        }, true);
    }

    $scope.changePage = (pageNumber) => {
        $scope.query.pageNumber = pageNumber;
        $scope.getDataList();
    }

    $scope.range = (max) => {
        return new Array(max);
    }

    $scope.editItem = (id) => {
        $rootScope.dataId = id;
        $scope.changeMenu('user-edit');
    }

    $scope.deleteItem = (id) => {
        Swal.fire({
            title: "Are you sure?",
            text: "You won't be able to revert this!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes, delete it!"
        }).then((result) => {
            if (result.isConfirmed) {
                apiHandler.callDelete('user/delete/'+id,(response) =>{
                    Swal.fire({
                        title: "Deleted!",
                        text: "Your data has been deleted.",
                        icon: "success"
                    });
                    $scope.getDataList();
                },(error)=>{},true);
            }
        });
    }
    $scope.getDataList();
});