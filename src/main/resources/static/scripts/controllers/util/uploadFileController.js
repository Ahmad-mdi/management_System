// CONTROLLER UPLOAD FILE
app.controller('UploadFileController', function ($scope, $http,$cookies,$rootScope) {

    $scope.uploadResult = "";

    $scope.myForm = {
        description: "",
        files: []
    };

    $scope.showResult = false;

    $scope.doUploadFile = function () {

        let url = "/api/utils/upload/uploadMultiFiles";


        let data = new FormData();

        data.append("description", $scope.myForm.description);
        data.append("file", $scope.myForm.file);

        let token = $cookies.get("userToken");
        let config = {
            transformRequest: angular.identity,
            transformResponse: angular.identity,
            headers: {
                'Content-Type': undefined,
                'Authorization': 'Bearer ' + token
            }
        };
        $http.post(url, data, config).then(
            // Success
            function(response) {
                $scope.uploadResult =  response.data;
                $scope.showResult = true;
                $rootScope.uploadedFile = response.data;
            },
            // Error
            function(response) {
                Swal.fire("image not uploaded!!");
                $scope.uploadResult = response.data;
                $scope.showResult = false;
            });

    };

});