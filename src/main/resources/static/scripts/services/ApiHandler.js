app.service("apiHandler", function ($http, $cookies) {

    this.callPost = (url, data, onSuccess, onError, setToken) => {
        url = "/api/" + url;
        let request = {
            url: url,
            method: "POST",
            data: data
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
                        title: "Error",
                        text: result.message
                    });
                } else {
                    Swal.fire({
                        icon: "error",
                        title: "Error",
                        text: "unknown error!"
                    });
                }
            }
        }, (err) => {
            Swal.fire({
                icon: "error",
                title: "Error",
                text: "Exception on server"
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
                        title: "Error",
                        text: result.message
                    });
                } else {
                    Swal.fire({
                        icon: "error",
                        title: "Error",
                        text: "unknown error!"
                    });
                }
            }
        }, (err) => {
            Swal.fire({
                icon: "error",
                title: "Error",
                text: "Exception on server"
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
                        title: "Error",
                        text: result.message
                    });
                } else {
                    Swal.fire({
                        icon: "error",
                        title: "Error",
                        text: "unknown error!"
                    });
                }
            }
        }, (err) => {
            Swal.fire({
                icon: "error",
                title: "Error",
                text: "Exception on server"
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
                        title: "Error",
                        text: result.message
                    });
                } else {
                    Swal.fire({
                        icon: "error",
                        title: "Error",
                        text: "unknown error!"
                    });
                }
            }
        }, (err) => {
            Swal.fire({
                icon: "error",
                title: "Error",
                text: "Exception on server"
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
});


