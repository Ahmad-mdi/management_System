app.controller('userListCtrl', function ($scope, apiHandler, $rootScope) {
    $scope.query = {
        pageSize: 15,
        pageNumber: 0
    };
    $scope.totalCount = 0;
    $scope.dataList = [];
    $scope.getDataSearch = [];
    $scope.pageCount = 0;
    $scope.userInfo = $rootScope.userInfo;

    $scope.getDataList = () => {
        let url = 'user/get-all?pageSize=' + $scope.query.pageSize + '&pageNumber=' + $scope.query.pageNumber;
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


    $scope.downloadExcelUserList = function () {
        apiHandler.downloadExcel().then(function (response) {
            let file = new Blob([response.data], {type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
            let fileURL = URL.createObjectURL(file);
            let a = document.createElement('a');
            a.href = fileURL;
            a.download = 'list-of-users.xlsx';
            a.click();
        });
    };

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
            title: "برای حذف مطمئن هستید؟",
            text: "اطلاعات کاربر فعلی پاک میشود!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            cancelButtonText: "لغو",
            confirmButtonText: "پاک شود"
        }).then((result) => {
            if (result.isConfirmed) {
                apiHandler.callDelete('user/delete/' + id, (response) => {
                    Swal.fire({
                        title: "حذف ",
                        text: "کاربر با موفقیت حذف شد",
                        icon: "success"
                    });
                    $scope.getDataList();
                }, (error) => {
                }, true);
            }
        });
    }

    $scope.searchByUsername = () => {
        let url = 'user/search?usernameLike=' + encodeURIComponent($scope.usernameLike);
        apiHandler.callGet(url, (response) => {
            $scope.dataList = response.dataList;
            $scope.totalCount = response.totalCount;
            $scope.pageCount = $scope.totalCount / $scope.query.pageSize;
            $scope.pageCount = parseInt($scope.pageCount);
            if ($scope.totalCount % $scope.pageSize > 0)
                $scope.pageCount++;
        }, (error) => {
            console.error('Error searching users:', error);
        }, true);
    };


    $scope.searchByUsername();
    $scope.getDataList();
});