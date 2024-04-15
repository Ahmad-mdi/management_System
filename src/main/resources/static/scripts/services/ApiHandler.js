app.service("apiHandler", function ($http, $cookies,$q) {

    this.callPost = (url, data, onSuccess, onError, setToken) => {
        url = "/api/" + url;
        let request = {
            url: url,
            method: "POST",
            data: data,
        };
        this.checkAndSetToken(request, setToken)
        $http(request).then((response) => {
            if (response != null && response.data != null) {
                let result = response.data;
                if (result.status === "SUCCESS") {
                    onSuccess(result);
                } else if (result.hasError) {
                    Swal.fire({
                        icon: "error",
                        title: "خطای درج اطلاعات",
                        text: result.message,
                        confirmButtonText: "فهمیدم"
                    });
                } else {
                    Swal.fire({
                        icon: "error",
                        title: "خطای سرور",
                        text: "ارور نامشخص!",
                        confirmButtonText: "فهمیدم"
                    });
                }
            }
        }).catch((err) => {
            Swal.fire({
                icon: "error",
                title: "خطا",
                text: err,
                confirmButtonText: "فهمیدم"
            });
            onError(err);
        });
    }

    this.callGet = (url, onSuccess, onError, setToken) => {
        url = "/api/" + url;
        let request = {
            url: url,
            method: 'GET'
        };
        this.checkAndSetToken(request, setToken);
        $http(request).then((response) => {
            if (response != null && response.data != null) {
                let result = response.data;
                if (result.status === "SUCCESS") {
                    onSuccess(result);
                } else if (result.hasError) {
                    Swal.fire({
                        icon: "error",
                        title: "خطای دریافت اطلاعات",
                        text: result.message,
                        confirmButtonText: "فهمیدم"
                    });
                } else {
                    Swal.fire({
                        icon: "error",
                        title: "خطا",
                        text: "خطای نامشخص !",
                        confirmButtonText: "فهمیدم"
                    });
                }
            }
        }, (err) => {
            Swal.fire({
                icon: "error",
                title: "خطا",
                text: "خطای سمت سرور",
                confirmButtonText: "فهمیدم"
            });
            onError(err);
        });
    }

    this.callPut = (url, data, onSuccess, onError, setToken) => {
        url = "/api/" + url;
        let request = {
            url: url,
            method: "PUT",
            data: data
        };
        this.checkAndSetToken(request, setToken);
        $http(request).then((response) => {
            if (response != null && response.data != null) {
                let result = response.data;
                if (result.status === "SUCCESS") {
                    onSuccess(result);
                } else if (result.hasError) {
                    Swal.fire({
                        icon: "error",
                        title: "خطای اطلاعات",
                        text: result.message,
                        confirmButtonText: "فهمیدم"
                    });
                } else {
                    Swal.fire({
                        icon: "error",
                        title: "خطا",
                        text: "خطای نامشخص!",
                        confirmButtonText: "فهمیدم"
                    });
                }
            }
        }, (err) => {
            Swal.fire({
                icon: "error",
                title: "خطا",
                text: "خطای سمت سرور",
                confirmButtonText: "فهمیدم"
            });
            onError(err);
        });
    }

    this.callDelete = (url, onSuccess, onError, setToken) => {
        url = "/api/" + url;
        let request = {
            url: url,
            method: "DELETE"
        };
        this.checkAndSetToken(request, setToken);
        $http(request).then((response) => {
            if (response != null && response.data != null) {
                let result = response.data;
                if (result.status === "SUCCESS") {
                    onSuccess(result);
                } else if (result.hasError) {
                    Swal.fire({
                        icon: "error",
                        title: "خطای اطلاعات",
                        text: result.message,
                        confirmButtonText: "فهمیدم"
                    });
                } else {
                    Swal.fire({
                        icon: "error",
                        title: "خطا",
                        text: "خطای نامشخص!",
                        confirmButtonText: "فهمیدم"
                    });
                }
            }
        }, (err) => {
            Swal.fire({
                icon: "error",
                title: "خطا",
                text: "خطای سمت سرور",
                confirmButtonText: "فهمیدم"
            });
            onError(err);
        });
    }

    this.checkAndSetToken = (request, setToken) => {
        if (setToken) {
            let token = $cookies.get("userToken");
            request.headers = {
                'Authorization': 'Bearer ' + token
            };
        }
    }

    this.downloadExcel = function() {
        return $http.get('http://127.0.0.1:8080/api/user/download-excel', {responseType: 'blob'});
    };
});


