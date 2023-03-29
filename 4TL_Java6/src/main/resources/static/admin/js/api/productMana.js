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
		var url = `${host}/admin/api/product/${id}`;
		$http.get(url).then(resp => {
			$scope.form = resp.data;
			$scope.thumbnail($scope.form.thumbnail.split('-*-'));
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
						item.thumbnail = dataThumbnail.map(x=>x).join("-*-");
						item.poster = dataPoster[0] + "";
						$scope.create(item);
					})
					.catch(function(error) {
						console.log(error);
						for (let i = 0; i < dataPoster.length; i++) {
							$scope.deleteImageInFolder(dataPoster[i]);
						}
					});
			})
			.catch(function(error) {
				console.log(error);
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

	$scope.update = function() {
		var item = angular.copy($scope.form);
		var url = `${host}/students/${$scope.form.email}`;
		$http.put(url, item).then(resp => {
			var index = $scope.items.findIndex(item => item.email == $scope.form.email);
			$scope.items[index] = resp.data;
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
		}).catch(error => {
			console.log("Error", error);
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
