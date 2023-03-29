// Angular js
app.controller("productMana", function($scope, $http) {
	$scope.form = {};
	$scope.items = [];
	$scope.categories = [];
	$scope.pageTotal = 1;
	$scope.currentPage = 1;
	$scope.minQuantityProduct = 0;
	$scope.maxQuantityProduct = 9; // show 9 product on 1 page

	$scope.reset = function() {
		$scope.form = {};
		$scope.key = null;
		$scope.form = {
			category: {
				"id": "CT  ",
				"name": "Chiến Thuật"
			}, available : true
		};
	}
	$scope.load_all = function() {
		var url = `${host}/admin/api/product`;
		$http.get(url).then(resp => {
			$scope.items = resp.data;
			$scope.pageTotal = Math.ceil($scope.items.length / 9);
			console.log("Success", resp);
		}).catch(error => {
			console.log("Error", error);
		});
	}
	$scope.load_all_categories = function() {
		var url = `${host}/admin/api/product/categories`;
		$http.get(url).then(resp => {
			$scope.categories = resp.data;
			console.log("Success", resp);
		}).catch(error => {
			console.log("Error", error);
		});
	}
	$scope.edit = function(id) {
		var url = `${host}/admin/api/product/${id}`;
		$http.get(url).then(resp => {
			$scope.form = resp.data;
			console.log("Success", resp);
		}).catch(error => {
			console.log("Error", error);
		});
	}
	$scope.create = function() {
		var item = angular.copy($scope.form);
		var url = `${host}/students`;
		$http.post(url, item).then(resp => {
			$scope.items.push(item);
			$scope.reset();
			$scope.pageTotal = Math.ceil($scope.items.length / 9);
			console.log("Success", resp);
		}).catch(error => {
			console.log("Error", error);
		});
	}
	$scope.update = function() {
		var item = angular.copy($scope.form);
		var url = `${host}/students/${$scope.form.email}`;
		$http.put(url, item).then(resp => {
			var index = $scope.items.findIndex(item => item.email == $scope.form.email);
			$scope.items[index] = resp.data;
			console.log("Success", resp);
		}).catch(error => {
			console.log("Error", error);
		});
	}
	$scope.delete = function(email) {
		var url = `${host}/students/${email}`;
		$http.delete(url).then(resp => {
			var index = $scope.items.findIndex(item => item.email == email);
			$scope.items.splice(index, 1);
			$scope.reset();
			$scope.pageTotal = Math.ceil($scope.items.length / 9);
			console.log("Success", resp);
		}).catch(error => {
			console.log("Error", error);
		});
	}
	// Thực hiện tải toàn bộ student
	$scope.load_all();
	$scope.load_all_categories();
	$scope.reset();

	// format curency --> VND: 1,234.012
	$scope.formatCurrency = function(n) {
		var parts = n.toString().split(".");
		const numberPart = parts[0];
		const decimalPart = parts[1];
		const thousands = /\B(?=(\d{3})+(?!\d))/g;
		return numberPart.replace(thousands, ".")
			+ (decimalPart ? "," + decimalPart : "");
	}

	// change page
	$scope.changePage = function(index) {
		if (index == 1) {
			$scope.minQuantityProduct = 0;

		} else {
			$scope.minQuantityProduct = 9 * (index - 1);
		}
		$scope.maxQuantityProduct = 9 * index;
		$scope.currentPage = index;
	}

	// nextPage and PreviousPage
	$scope.nextPage = function() {
		if ($scope.currentPage < $scope.pageTotal) {
			$scope.currentPage++;
			$scope.changePage($scope.currentPage);
		}
	}

	$scope.prevPage = function() {
		if ($scope.currentPage > 1) {
			$scope.currentPage--;
			$scope.changePage($scope.currentPage);
		}
	}
});
