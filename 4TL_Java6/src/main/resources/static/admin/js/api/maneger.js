// Angular js
app.controller("ManegerMana", function($scope, $http) {
	$scope.form = {};
	$scope.items = [];
	$scope.categories = [];
	$scope.pageTotal = 1; // tổng số trang
	$scope.currentPage = 1; // trang hiện tại
	$scope.minQuantityProduct = 0;
	$scope.maxQuantityProduct = 9; // hiển thị 9 nguoi dung trên 1 trang
	

	

	// Load danh sách nguoi dung
	$scope.load_all = function() {
		var url = `${host}/admin/api/acount`;
		$http.get(url).then(resp => {
			$scope.items = resp.data;
			$scope.pageTotal = Math.ceil($scope.items.length / 9);
			document.getElementsByClassName("pageNumber")[0].classList.add("active");
		}).catch(error => {
			console.log("Error", error);
		});
	}

	// Load danh sách thể loại
	$scope.load_all_categories = function() {
		var url = `${host}/admin/api/acount/categories`;
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
			var url = `${host}/admin/api/acount/search/${$scope.searchInput}`;
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

	// hiển thị sản phẩm lên form
	$scope.edit = function(id) {
		$scope.messageSuccess = "";
		$scope.messageFailed = "";
		var url = `${host}/admin/api/acount/${id}`;
		$http.get(url).then(resp => {
			$scope.form = resp.data;
			$scope.thumbnail($scope.form.thumbnail.split('-*-'));
			$scope.key = id;
		}).catch(error => {
			console.log("Error", error);
		});
	}

	/* hiển thị hình ảnh thumbnail khi chọn edit 
		   arrImages : mảng ảnh thumbnail
	*/
	

	
	

	

	
	// Call API delete phía server
	$scope.delete = function(id) {
		$scope.messageSuccess = "";
		$scope.messageFailed = "";
		var url = `${host}/admin/api/acount/${id}`;
		$http.delete(url).then(resp => {
			$scope.load_all();
			$scope.reset();
			$scope.pageTotal = Math.ceil($scope.items.length / 9);
			$scope.messageSuccess = "Xóa thành công!";
		}).catch(error => {
			console.log("Error", error);
			$scope.messageFailed = "Xóa thất bại!";
		});
	}

	// Thực hiện tải toàn bộ product
	$scope.load_all();
	$scope.load_all_categories();




	// change page
	$scope.changePage = function(index) {
		if (index == 1) {
			$scope.minQuantityProduct = 0;

		} else {
			$scope.minQuantityProduct = 9 * (index - 1);
		}
		$scope.maxQuantityProduct = 9 * index;
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
