var app = angular.module("courierApp", ['ngRoute']);


app.config(['$routeProvider',
    function($routeProvider) {

      $routeProvider
      .when('/', {
          templateUrl: 'app/view/login/login.html',
          controller: 'loginController'
        })
        //para acessar as páginas da rota do angular deve conter /#/ na url, exemplo: "localhost:8084/courier/#/admin/produto"
         .when('/admin/produto', {
          templateUrl: 'app/view/produto/listar-produto.html',
          controller: 'produtoController'
        })
        .otherwise({
    		redirectTo: "/"
    	});
        

    }]
);  

