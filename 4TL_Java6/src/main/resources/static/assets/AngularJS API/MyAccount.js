// Angular js
app.controller("MyAccount", function($scope, $http, $location) {
	$scope.form = {};
	$scope.orderDetail = [];
	$scope.totalPrice = 0;

	$scope.showModal = function(orderId) {
		$('#modalDetailOrder').modal('show');
		var url = `${host}/orders/${orderId}`;
		$http.get(url).then(resp => {
			$scope.orderDetail = resp.data;
			$scope.totalPrice = 0;
			for (let i = 0; i < $scope.orderDetail.length; i++) {
				$scope.totalPrice += $scope.orderDetail[i].price;
			}
		}).catch(error => {
			console.log("Error", error);
		});
	}

	// format curency --> VND: 1,234.012
	$scope.formatCurrency = function(n) {
		var parts = n.toString().split(".");
		const numberPart = parts[0];
		const decimalPart = parts[1];
		const thousands = /\B(?=(\d{3})+(?!\d))/g;
		return numberPart.replace(thousands, ".")
			+ (decimalPart ? "," + decimalPart : "");
	}
});