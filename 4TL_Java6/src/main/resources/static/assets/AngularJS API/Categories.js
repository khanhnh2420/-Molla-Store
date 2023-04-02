
app.controller("Category", function($scope, $http) {

	$scope.categories = []

	$scope.load_all = function() {
		var url = `${host}/category/countProduct`;
		
		$http.get(url).then(resp => {
			$scope.categories = resp.data;
		}).catch(error => {
			console.log("Error", error)
		})
	}
	$scope.load_all();
})