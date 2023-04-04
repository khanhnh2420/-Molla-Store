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

	// Load danh sách theo tìm kiếm
	$scope.search = function() {
		if ($scope.searchInput == "" || $scope.searchInput == null || $scope.searchInput == undefined) {
			$scope.load_all();
		} else {
			var url = `${host}/admin/api/order/search/${$scope.searchInput}`;
			$http.get(url).then(resp => {
				$scope.items = resp.data;
				$scope.pageTotal = Math.ceil($scope.items.length / 9);
				document.getElementsByClassName("pageNumber")[0].classList.add("active");
			}).catch(error => {
				console.log("Error", error);
			});
		}
	}
	var input = document.getElementById("search");

	// Lắng nghe sự kiện keydown trên thanh search
	input.addEventListener("keydown", function(event) {
		// Kiểm tra nếu phím được nhấn là phím Enter
		if (event.key === "Enter") {
			// Ngăn chặn hành động mặc định của phím Enter (submit form)
			event.preventDefault();
			// Thực hiện click button
			$scope.search();
		}
	});

	// senmail
	$scope.sendEmail = function(email, id) {
		
		var url = `${host}/admin/api/order/orderdetail/${id}`;
		$http.get(url).then(resp => {
			var source = "";
			for(let i=0; i < resp.data.length; i++){
				source+=resp.data[i].product.link;		
			}
			var emailData = {
			"from": "4Tl",
			"to":email,
			"cc": [
				"",
				""
			],
			"bcc": [
				"",
				""
			],
			"subject": "XÁC NHẬN THANH TOÁN !",
			"source":source
		};
		console.log(emailData.source)
		$http.post(`${host}/admin/api/order/sendEmail`, emailData)
			.then(function(response) {
				console.log('Đã gửi Email thành công!');
			})
			.catch(function(error) {
				console.error('Rất tiếc! Email gửi không thành công:', error);
			});
		}).catch(error => {
			console.log("Error", error);
		});
			
	};

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
