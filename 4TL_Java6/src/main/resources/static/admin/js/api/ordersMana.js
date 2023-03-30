// Angular js
app.controller("ordersMana", function($scope, $http) {
	$scope.form = {};
	$scope.items = [];
	$scope.categories = [];
	$scope.pageTotal = 1;
	$scope.currentPage = 1;
	$scope.minQuantityOrders = 0;
	$scope.maxQuantityOrders = 9; // hiển thị 9 orders trên 1 trang



	$scope.load_all = function() {
		var url = `${host}/admin/api/order`;
		$http.get(url).then(resp => {
			$scope.items = resp.data;
			$scope.pageTotal = Math.ceil($scope.items.length / 9);
		}).catch(error => {
			console.log("Error", error);
		});
	}
	$scope.load_all_categories = function() {
		var url = `${host}/admin/api/order/categories`;
		$http.get(url).then(resp => {
			$scope.categories = resp.data;
		}).catch(error => {
			console.log("Error", error);
		});
	}

	// Thực hiện tải toàn bộ order
	$scope.load_all();
	$scope.load_all_categories();



	// change page
	$scope.changePage = function(index) {
		if (index == 1) {
			$scope.minQuantityOrders = 0;

		} else {
			$scope.minQuantityOrders = 9 * (index - 1);
		}
		$scope.maxQuantityOrders = 9 * index;
		$scope.currentPage = index;

		var arrPageNum = document.getElementsByClassName("pageNumber");
		for (var i = 0; i < arrPageNum.length; i++) {
			arrPageNum[i].classList.remove("active");
		}
		arrPageNum[index - 1].classList.add("active");
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
