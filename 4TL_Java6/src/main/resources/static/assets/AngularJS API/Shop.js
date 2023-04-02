
app.controller("Shop-ctrl", function ($scope, $http) {

	$scope.products = {};

	$scope.get_all = function () {
		var url = `${host}/category/getAll`;
		var get_allBysize = localStorage.getItem('getAllBySize');
		if (get_allBysize) {
			url += `?size=${get_allBysize}`;
		}
		$http.get(url).then(resp => {
			$scope.products = resp.data;
		}).catch(error => {
			console.log("Error", error)
		})
	}
	$scope.get_all();

	$scope.get_ByPage = function (page) {
		$scope.currentPage = page;
		var url = `${host}/category/getAll?page=${page}`;
		var get_allBysize = localStorage.getItem('getAllBySize');
		if (get_allBysize) {
			url += `&size=${get_allBysize}`;
		}
		$http.get(url).then(resp => {
			$scope.products = resp.data;
		}).catch(error => {
			console.log("Error", error)
		})
	}

	$scope.get_ByCategory = function (event, categoryId, page, size) {
		if (event.target.checked) {
			var url = `${host}/category/findByCategoryId/${categoryId}`;
			if (page) {
				url += `?page=${page}&size=${size}`;
			}
			$http.get(url).then(resp => {
				$scope.products = resp.data;

			}).catch(error => {
				console.log("Error", error)
			})
		} else {
			$scope.get_all();
		}
	}

	$scope.currentPage = 0;

	$scope.getNextPage = function () {
		if ($scope.currentPage < $scope.products.totalPages) {
			$scope.currentPage++;
			$scope.get_ByPage($scope.currentPage);
		}
	};

	$scope.getPrevPage = function () {
		if ($scope.currentPage > 0) {
			$scope.currentPage--;
			$scope.get_ByPage($scope.currentPage);
		}
	};

	$scope.goToCategoryPage = function (event, size) {
		var href = event.currentTarget.getAttribute('link'); // Lấy đường dẫn trang
		localStorage.setItem('getAllBySize', size); // Lưu số lượng sản phẩm cần hiển thị
		event.preventDefault(); // Ngăn chặn hành động mặc định của liên kết
		window.location.href = href; // Chuyển đổi trang
		$scope.$apply(); // Đồng bộ hoá các thay đổi với mô hình dữ liệu của bạn
	}
})