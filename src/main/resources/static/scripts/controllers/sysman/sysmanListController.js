app.controller('sysmanListCtrl', function ($scope, apiHandler, $rootScope) {

    $scope.query = {
        pageSize: 10,
        pageNumber: 0
    };
    /*$scope.searchQuery = {
        searchPageSize: 10,
        searchPageNumber: 0
    };*/
    $scope.filter = {
        filterPageSize: 10,
        filterPageNumber: 0
    };
    $scope.dataList = [];
    $scope.getFilters = [];
    $scope.totalCount = 0;
    $scope.pageCount = 0;
    $scope.userInfo = $rootScope.userInfo;

    $scope.changePage = (pageNumber) => {
        $scope.query.pageNumber = pageNumber;
        $scope.getDataList();
        $scope.filter.filterPageNumber = pageNumber;
        $scope.filterByColumns();
    }

    $scope.range = (max) => {
        return new Array(max);
    }

    $scope.getDataList = () => {
        let url = 'sysman/get-all?pageSize=' + $scope.query.pageSize + '&pageNumber=' + $scope.query.pageNumber;
        apiHandler.callGet(url, (response) => {
            $scope.dataList = response.dataList;
            $scope.totalCount = response.totalCount;
            $scope.pageCount = Math.ceil($scope.totalCount / $scope.query.pageSize);
            if ($scope.totalCount % $scope.pageSize > 0)
                $scope.pageCount++;
        }, (error) => {

        }, true);
    }


    $scope.editItem = (id) => {
        $rootScope.dataId = id;
        $scope.changeMenu('sysman-edit');
    }

    $scope.deleteItem = (id) => {
        Swal.fire({
            title: "برای حذف مطمئن هستید؟",
            text: "اطلاعات سامانه فعلی پاک میشود!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            cancelButtonText: "لغو",
            confirmButtonText: "پاک شود"
        }).then((result) => {
            if (result.isConfirmed) {
                apiHandler.callDelete('sysman/delete/' + id, (response) => {
                    Swal.fire({
                        title: "حذف ",
                        text: "سامانه با موفقیت حذف شد",
                        icon: "success"
                    });
                    $scope.getDataList();
                }, (error) => {
                }, true);
            }
        });
    }

    $scope.reloadListAfterFilter = () => {
        $scope.en_name = '';
        $scope.fa_name = '';
        $scope.route = '';
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

    $scope.filterByColumns = () => {
        let url = 'sysman/filter?';

        if ($scope.en_name) {
            url += `en_name=${encodeURIComponent($scope.en_name)}&`;
        }
        if ($scope.fa_name) {
            url += `fa_name=${encodeURIComponent($scope.fa_name)}&`;
        }
        if ($scope.route) {
            url += `route=${encodeURIComponent($scope.route)}&`;
        }

        url += `pageSize=${$scope.filter.filterPageSize}&pageNumber=${$scope.filter.filterPageNumber}`;

        apiHandler.callGet(url, (response) => {
            $scope.getFilters = response.dataList;
            $scope.totalCount = response.totalCount;
            $scope.pageCount = Math.ceil($scope.totalCount / $scope.filter.filterPageSize);
        }, (error) => {
            console.error('Error filterd all columns of systme_management:', error);
        }, true);
    };




    $scope.getDataList();
});