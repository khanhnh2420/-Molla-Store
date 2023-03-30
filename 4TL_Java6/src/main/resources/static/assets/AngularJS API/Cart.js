app.controller("Cart-ctrl", function($scope, $http){
	
	$scope.cart = []
	$scope.cartTotal;
    $scope.cartLength;

	$scope.load_all = function(){
		var url = `${host}/restCart/getCart`;
		$http.get(url).then(resp => {
			$scope.cart = resp.data;
            $scope.getCartTotal();
            $scope.getCartLength();
		}).catch(error => {
			console.log("Error", error)
		})
	}

    $scope.getCartTotal = function(){
		var url = `${host}/restCart/getCartTotal`;
		$http.get(url).then(resp => {
			$scope.cartTotal = resp.data;
		}).catch(error => {
			console.log("Error", error)
		})
	}

    $scope.getCartLength = function(){
		var url = `${host}/restCart/getCartLength`;
		$http.get(url).then(resp => {
			$scope.cartLength = resp.data;
		}).catch(error => {
			console.log("Error", error)
		})
	}
	
	$scope.addCart = function(event){
        var productId = event.currentTarget.getAttribute('data-product-id');
		var url = `${host}/restCart/addCart/${productId}`;
		$http.post(url).then(resp => {
			$scope.load_all();
		}).catch(error => {
			console.log("Error", error)
		})
	}
	
	$scope.removeCart = function(event){
        var productId = event.currentTarget.getAttribute('data-cart-id');
		var url = `${host}/restCart/removeCart/${productId}`;
		$http.post(url).then(resp => {
			$scope.load_all();
		}).catch(error => {
			console.log("Error", error)
		})
	}

    $scope.clear_all = function(){
		var url = `${host}/clearCart`;
		$http.post(url).then(resp => {
			$scope.cart = resp.data;
            $scope.load_all();
		}).catch(error => {
			console.log("Error", error)
		})
	}
	
	$scope.load_all();
})