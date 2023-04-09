// Angular js
app.controller("ThanhtoanMana", function($scope, $http) {
	$scope.form = {};
	$scope.orderDetailForm = {};

	$scope.create = function(username) {
		var url = `${host}/account/${username}`;
		$http.get(url).then(resp => {
			$scope.form.account = resp.data;
			$scope.form.createDate = new Date().toLocaleDateString('en-ca');
			$scope.form.status = "Chờ xử lý";
			var url = `${host}/api/createorders`;
			var item = angular.copy($scope.form);
			$http.post(url, item).then(resp => {
				$scope.orderDetailForm.order = resp.data;
				const arr = Object.keys($scope.cart).map((key) => [key, $scope.cart[key]]);
				for (let i = 0; i < arr.length; i++) {
					$scope.orderDetailForm.product = arr[i][1].product;
					$scope.orderDetailForm.price = arr[i][1].product.price;
					console.log($scope.orderDetailForm)
					var url = `${host}/api/createorderdetail`;
					var item = angular.copy($scope.orderDetailForm);
					$http.post(url, item).then(resp => {
						confirm('thanh toán thành công !');
					}).catch(error => {
						confirm('thanh toán thất bại!');
					});
				}
			}).catch(error => {
				confirm('thanh toán thất bại!');
			});
		}).catch(error => {
			confirm('thanh toán thất bại!');
		});


	}
});
