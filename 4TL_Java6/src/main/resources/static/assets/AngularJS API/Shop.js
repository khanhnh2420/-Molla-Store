
app.controller("Shop-ctrl", function($scope, $http) {

	$scope.products = {};

	$scope.get_all = function() {
		var url = `${host}/restShop/getAll`;
		
		$http.get(url).then(resp => {
			$scope.products = resp.data;
		}).catch(error => {
			console.log("Error", error)
		})
	}
	$scope.get_all();
})