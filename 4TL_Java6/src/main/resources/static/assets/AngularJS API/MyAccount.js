// Angular js
app.controller("MyAccount", function ($scope, $http, $location) {
	$scope.form = {}
    $scope.items = []
    
    $scope.getOne = function (username) {
        var url = `${host}/account/${username}`;
        $http.get(url).then(resp => {
            $scope.form = resp.data;
            console.log("Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    }
    var param1 = $location.search().username;
    console.log("abc");
    console.log(param1);
    // Lấy ra user có username được truyền vào
//    $scope.getOne();
});