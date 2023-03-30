// Angular js
app.controller("productMana", function($scope, $http) {
	$scope.form = {};
	$scope.items = [];
	$scope.categories = [];
	$scope.pageTotal = 1;
	$scope.currentPage = 1;
	$scope.minQuantityProduct = 0;
	$scope.maxQuantityProduct = 9; // hiển thị 9 product trên 1 trang
	$scope.filePoster = null;
	$scope.fileThumbnails = null;

	$scope.reset = function() {
		$scope.form = {};
		$scope.key = null;
		$scope.form = {
			category: {
				"id": "CT  ",
				"name": "Chiến Thuật"
			}, available: true
		};
		document.getElementById("selectedThumbnails").innerHTML = "";
		document.getElementById("selectedImage").setAttribute('src', '');
;
		$scope.filePoster = null;
		$scope.fileThumbnails = null;
		$scope.messageSuccess = "";
		$scope.messageFailed = "";
		$scope.messageSuccess = "";
		$scope.messageFailed = "";
	}
	$scope.load_all = function() {
		var url = `${host}/admin/api/product`;
		$http.get(url).then(resp => {
			$scope.items = resp.data;
			$scope.pageTotal = Math.ceil($scope.items.length / 9);
			document.getElementsByClassName("pageNumber")[0].classList.add("active");
		}).catch(error => {
			console.log("Error", error);
		});
	}
	$scope.load_all_categories = function() {
		var url = `${host}/admin/api/product/categories`;
		$http.get(url).then(resp => {
			$scope.categories = resp.data;
		}).catch(error => {
			console.log("Error", error);
		});
	}
	$scope.edit = function(id) {
		$scope.messageSuccess = "";
		$scope.messageFailed = "";
		var url = `${host}/admin/api/product/${id}`;
		$http.get(url).then(resp => {
			$scope.form = resp.data;
			$scope.thumbnail($scope.form.thumbnail.split('-*-'));
			$scope.key = id;
		}).catch(error => {
			console.log("Error", error);
		});
	}
	// hiển thị hình ảnh thumbnail khi chọn edit
	$scope.thumbnail = function(arrImages) {
		let row = document.createElement("div");
		row.classList.add("row");
		document.getElementById("selectedThumbnails").innerHTML = "";
		for (let i = 0; i < arrImages.length; i++) {
			let url = "/assets/images/products/" + arrImages[i];
			let img = document.createElement("img");
			img.src = url;
			img.classList.add("img-thumbnail");
			let div = document.createElement("div");
			div.classList.add("col-md-4", "mb-3");
			div.appendChild(img);
			row.appendChild(div);

			// Thêm một hàng mới sau mỗi 3 hình ảnh
			if ((i + 1) % 3 == 0) {
				document.getElementById("selectedThumbnails")
					.appendChild(row);
				row = document.createElement("div");
				row.classList.add("row");
			}
		}

		// Nếu số hình ảnh không chia hết cho 3, thêm hàng cuối cùng
		if (arrImages.length % 3 != 0) {
			document.getElementById("selectedThumbnails")
				.appendChild(row);
		}
	}
	$scope.create = function(item) {
		var url = `${host}/admin/api/product`;
		$http.post(url, item).then(resp => {
			$scope.items.push(item);
			$scope.load_all();
			$scope.reset();
			$scope.messageSuccess = "Thêm sản phẩm thành công!";
			$scope.pageTotal = Math.ceil($scope.items.length / 9);

		}).catch(error => {
			console.log("Error", error);
			$scope.messageFailed = "Thêm sản phẩm thất bại!";
		});
	}

	$scope.create_btn = function() {
		$scope.messageSuccess = "";
		$scope.messageFailed = "";
		$scope.createImageInFolder($scope.filePoster)
			.then(function(dataPoster) {
				$scope.createImageInFolder($scope.fileThumbnails)
					.then(function(dataThumbnail) {
						var item = angular.copy($scope.form);
						item.thumbnail = dataThumbnail.map(x => x).join("-*-");
						item.poster = dataPoster[0] + "";
						$scope.create(item);
					})
					.catch(function(error) {
						console.log(error);
						for (let i = 0; i < dataPoster.length; i++) {
							$scope.deleteImageInFolder(dataPoster[i]);
						}
						$scope.messageFailed = "Vui lòng chọn 3 tấm hình thumbnails!";
					});
			})
			.catch(function(error) {
				console.log(error);
				$scope.messageFailed = "Vui lòng chọn poster!";
			});
	}

	$scope.createImageInFolder = function(files) {
		return new Promise(function(resolve, reject) {
			var form = new FormData();
			for (var i = 0; i < files.length; i++) {
				form.append("files", files[i]);
			}

			// Check create image in folder
			var url = `${host}/files/products`;
			$http
				.post(url, form, {
					transformRequest: angular.identity,
					headers: { "Content-Type": undefined },
				})
				.then(function(resp) {
					resolve(resp.data);
				})
				.catch(function(error) {
					reject(error);
				});
		});
	};
	// delete image in folder products
	$scope.deleteImageInFolder = function(filename) {
		$http.delete(`${host}/files/products/${filename}`).then(resp => {
			console.log("success");
		}).catch(error => {
			console.log("Error", error);
		});
	}

	$scope.update_btn = function() {
		$scope.messageSuccess = "";
		$scope.messageFailed = "";
		var item = angular.copy($scope.form);

		$scope.createImageInFolder($scope.filePoster)
			.then(function(dataPoster) {
				item.poster = dataPoster[0] + "";
				$scope.createImageInFolder($scope.fileThumbnails)
					.then(function(dataThumbnail) {
						item.thumbnail = dataThumbnail.map(x => x).join("-*-");
						$scope.update(item);
					})
					.catch(function(error) {
						console.log(error);
						for (let i = 0; i < $scope.items.length; i++) {
							if ($scope.items[i].id == $scope.key) {
								item.thumbnail == $scope.items[i].thumbnail;
							}
						}
						$scope.update(item);
					});
			})
			.catch(function(error) {
				console.log(error);
				for (let i = 0; i < $scope.items.length; i++) {
					if ($scope.items[i].id == $scope.key) {
						item.poster == $scope.items[i].poster;
					}
				}
				$scope.createImageInFolder($scope.fileThumbnails)
					.then(function(dataThumbnail) {
						item.thumbnail = dataThumbnail.map(x => x).join("-*-");
						$scope.update(item);
					})
					.catch(function(error) {
						console.log(error);
						for (let i = 0; i < $scope.items.length; i++) {
							if ($scope.items[i].id == $scope.key) {
								item.thumbnail == $scope.items[i].thumbnail;
							}
						}
						$scope.update(item);
					});
			});
	}

	$scope.update = function(item) {
		var url = `${host}/admin/api/product/${$scope.key}`;
		$http.put(url, item).then(resp => {
			var index = $scope.items.findIndex(item => item.id == $scope.key);
			$scope.items[index] = resp.data;
			$scope.messageSuccess = "Cập nhật thành công!";
			$scope.load_all();
		}).catch(error => {
			console.log("Error", error);
			$scope.messageFailed = "Cập nhật thất bại!";
		});
	}

	$scope.delete = function(id) {
		$scope.messageSuccess = "";
		$scope.messageFailed = "";
		var url = `${host}/admin/api/product/${id}`;
		$http.delete(url).then(resp => {
			var index = $scope.items.findIndex(item => item.id == id);
			$scope.items[index].poster;
			$scope.deleteImageInFolder($scope.items[index].poster);
			$scope.arrThumbnails = $scope.form.thumbnail.split('-*-');
			for (let i = 0; i < $scope.arrThumbnails.length; i++) {
				$scope.deleteImageInFolder($scope.arrThumbnails[i]);
			}
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

	// Xử lý sự kiện khi chọn poster
	document
		.getElementById("imageInput")
		.addEventListener(
			"change",
			function(event) {
				// Lấy thông tin file đã chọn
				var selectedFile = event.target.files[0];
				$scope.filePoster = event.target.files;
				// Tạo đường dẫn cho hình ảnh đã chọn
				var selectedFileUrl = URL
					.createObjectURL(selectedFile);
				// Hiển thị hình ảnh đã chọn lên div card
				document.getElementById("selectedImage").src = selectedFileUrl;
			});

	// Xử lý sự kiện khi chọn Thumbnails
	document.getElementById("imageThumbnails").addEventListener(
		"change",
		function(event) {
			let files = event.target.files;
			$scope.fileThumbnails = files;
			let row = document.createElement("div");
			row.classList.add("row");

			document.getElementById("selectedThumbnails").innerHTML = "";
			for (let i = 0; i < files.length; i++) {
				let file = files[i];
				let url = URL.createObjectURL(file);
				let img = document.createElement("img");
				img.src = url;
				img.classList.add("img-thumbnail");
				let div = document.createElement("div");
				div.classList.add("col-md-4", "mb-3");
				div.appendChild(img);
				row.appendChild(div);

				// Thêm một hàng mới sau mỗi 3 hình ảnh
				if ((i + 1) % 3 == 0) {
					document.getElementById("selectedThumbnails")
						.appendChild(row);
					row = document.createElement("div");
					row.classList.add("row");
				}
			}

			// Nếu số hình ảnh không chia hết cho 3, thêm hàng cuối cùng
			if (files.length % 3 != 0) {
				document.getElementById("selectedThumbnails")
					.appendChild(row);
			}
		});
});
