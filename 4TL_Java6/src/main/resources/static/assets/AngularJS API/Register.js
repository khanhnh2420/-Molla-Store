// Angular js
app.controller("Register", function($scope, $http, $location) {
	$scope.form = {};
	
	$scope.Register = function() {
		$scope.messageSuccess = "";
		$scope.messageFailed = "";
		if (($scope.form.password != null) && ($scope.form.password == $scope.confirmPassword)) {
			if ($scope.form.username == null) {
				$scope.messageFailed = "username không được để trống!";
			} else if ($scope.form.fullname == null) {
				$scope.messageFailed = "fullname không được để trống!";
			} else if ($scope.form.email == null) {
				$scope.messageFailed = "email không đúng định dạng!";
			} else if ($scope.form.address == null) {
				$scope.messageFailed = "address không được để trống!";
			} else {
				var item = angular.copy($scope.form);
				var url = `${host}/register`;
				$http.post(url, item).then(resp => {
					$scope.messageSuccess = "Đăng ký thành công!";
				}).catch(error => {
					$scope.messageFailed = "Đăng ký thất bại!";
					console.log("Error", error);
				});
			}
		} else {
			$scope.messageFailed = "Sai Confirm Password!";
		}
	}
});