app.controller('userListCtrl', function ($scope, apiHandler, $rootScope) {

    $scope.query = {
        pageSize: 10,
        pageNumber: 0
    };
    $scope.searchQuery = {
        searchPageSize: 10,
        searchPageNumber: 0
    };
    $scope.filter = {
        filterPageSize: 10,
        filterPageNumber: 0
    };
    $scope.dataList = [];
    $scope.getSearch = [];
    $scope.getFilters = [];
    $scope.totalCount = 0;
    $scope.pageCount = 0;
    $scope.userInfo = $rootScope.userInfo;

    $scope.changePage = (pageNumber) => {
        $scope.query.pageNumber = pageNumber;
        $scope.getDataList();
        $scope.searchQuery.searchPageNumber = pageNumber;
        $scope.searchByUsername();
        $scope.filter.filterPageNumber = pageNumber;
        $scope.filterByColumns();
    }

    $scope.range = (max) => {
        return new Array(max);
    }

    $scope.getDataList = () => {
        let url = 'user/get-all?pageSize=' + $scope.query.pageSize + '&pageNumber=' + $scope.query.pageNumber;
        apiHandler.callGet(url, (response) => {
            $scope.dataList = response.dataList;
            $scope.totalCount = response.totalCount;
            $scope.pageCount = Math.ceil($scope.totalCount / $scope.query.pageSize);
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

    $scope.editItem = (id) => {
        $rootScope.dataId = id;
        $scope.changeMenu('user-edit');
    }
    $scope.editPassword = (id) => {
        $rootScope.dataId = id;
        $scope.changeMenu('user-edit-pass');
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

    $scope.reloadListAfterSearch = () => {
        $scope.username = '';
        $scope.searchQuery.searchPageNumber = 0;
        $scope.getSearch = [];
        $scope.totalCount = 0;
        $scope.pageCount = 0;
        $scope.getDataList();
    };

    $scope.reloadListAfterFilter = () => {
        $scope.usernameFilter = '';
        $scope.firstname = '';
        $scope.lastname = '';
        $scope.nationalCode = '';
        $scope.filter.filterPageNumber = 0;
        $scope.getFilters = [];
        $scope.totalCount = 0;
        $scope.pageCount = 0;
        $scope.getDataList();
    };

    $scope.showInfoTextForSearch = () => {
        Swal.fire({
            title: "راهنمایی",
            html: `
                <p style="text-align: justify;">              
                      نکته اول: گزینه "بازگردانی لیست" برای شما به نمایش گذاشته شده است لطفا برای جلوگیری از مشکلات احتمالی، بعد از اینکه جستجوی شما به اتمام رسید روی این گزینه کلیک نمایید!
                </p>
                <br>                      
                <p style="text-align: justify;">              
                     نکته دوم: اگر جستجوی شما مطابقت نداشته باشد صفحه بندی روی صفحه یک قفل میشود! برای حل این مشکل لطفا بر روی "بازگردانی لیست" کلیک کرده و جستجو را دوباره آغاز کنید!
                </p>
                <br>
                <p style="text-align: justify;">              
                    نکته سوم: اگر مطمئن هستید در هنگام جستجو کاربر مورد نظر وجود دارد ولی اطلاعاتی برای شما  نمایش داده نمیشود لطفا یکبار روی گزینه "بازگردانی لیست" کلیک کنید و دوباره جستجو را تکرار کنید!
                </p>
            `,
            icon: "info",
            confirmButtonText: "فهمیدم"
        });
    }

    $scope.searchByUsername = () => {
        let url = `user/search?username=${encodeURIComponent($scope.username)}&pageSize=${$scope.searchQuery.searchPageSize}&pageNumber=${$scope.searchQuery.searchPageNumber}`;
        apiHandler.callGet(url, (response) => {
            $scope.getSearch = response.dataList;
            $scope.totalCount = response.totalCount;
            $scope.pageCount = Math.ceil($scope.totalCount / $scope.searchQuery.searchPageSize);
        }, (error) => {
            console.error('Error searching users:', error);
        }, true);
    };

    $scope.filterByColumns = () => {
        let url = 'user/filter?';

        if ($scope.usernameFilter) {
            url += `username=${encodeURIComponent($scope.usernameFilter)}&`;
        }
        if ($scope.firstname) {
            url += `firstname=${encodeURIComponent($scope.firstname)}&`;
        }
        if ($scope.lastname) {
            url += `lastname=${encodeURIComponent($scope.lastname)}&`;
        }
        if ($scope.nationalCode) {
            url += `nationalCode=${encodeURIComponent($scope.nationalCode)}&`;
        }

        url += `pageSize=${$scope.filter.filterPageSize}&pageNumber=${$scope.filter.filterPageNumber}`;

        apiHandler.callGet(url, (response) => {
            $scope.getFilters = response.dataList;
            $scope.totalCount = response.totalCount;
            $scope.pageCount = Math.ceil($scope.totalCount / $scope.filter.filterPageSize);
        }, (error) => {
            console.error('Error filterd all columns of users:', error);
        }, true);
    };


    $scope.getDataList();
});