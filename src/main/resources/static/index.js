(function () {
    angular
        .module('market-front', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .when('/orders', {
                templateUrl: 'orders/orders.html',
                controller: 'orderController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.springWebUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
        }
    }
})();







angular.module('market-front').controller('indexController', function ($scope, $rootScope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/app/api/v1';

    if(!$localStorage.cartName){
        $localStorage.cartName = "cart_" + (Math.random() * 100);
    }

    if ($localStorage.springWebUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
        $localStorage.cartName = "cart_" + $localStorage.springWebUser.username;
    }

    $scope.tryToAuth = function () {
        $http.post('http://localhost:8189/app/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.springWebUser;
        delete $localStorage.cartName;
        $http.defaults.headers.common.Authorization = '';
    };

    // $scope.addToCart = function (productId) {
    //     $http.post('http://localhost:8189/app/api/v1/carts/add/' + productId, $localStorage.cartName)
    //         .then(function (response) {
    //             $scope.loadCart();
    //         });
    // }

    //Метод для уменьшения количества
    $scope.decreaseProduct = function (productId) {
        $http.post('http://localhost:8189/app/api/v1/carts/decrease/' + productId, $localStorage.cartName)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    //Метод для полного удаления позиции из корзины
    $scope.removeProduct = function (productId) {
        $http.post('http://localhost:8189/app/api/v1/carts/remove/' + productId, $localStorage.cartName)
            .then (function (response) {
                $scope.loadCart();
            })
    }

    // $scope.loadCart = function () {
    //     $http.post('http://localhost:8189/app/api/v1/carts', $localStorage.cartName)
    //         .then(function (response) {
    //             $scope.Cart = response.data;
    //         });
    // }
    //
    // $scope.clearCart = function () {
    //     $http.post('http://localhost:8189/app/api/v1/carts/clear', $localStorage.cartName)
    //         .then(function (response) {
    //             $scope.loadCart();
    //         });
    // }

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.springWebUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.showCurrentUserInfo = function () {
        $http.get('http://localhost:8189/app/api/v1/profile')
            .then(function successCallback(response) {
                alert('MY NAME IS: ' + response.data.username);
            }, function errorCallback(response) {
                alert('UNAUTHORIZED');
            });
    }

    // $scope.checkOut = function () {
    //     $http.post(contextPath + '/orders' + $scope.orderDetails + $localStorage.cartName)
    //     .then(function (response) {
    //         $scope.loadCart();
    //         $scope.orderDetails = null;
    //     });
    // }
    //
    //
    //
    // $scope.disableCheckOut = function () {
    //     alert("Для оформления заказа необходимо войти в учетную запись");
    // }

    // $scope.loadProducts();
    // $scope.loadCart();
    // $scope.loadOrders();
});