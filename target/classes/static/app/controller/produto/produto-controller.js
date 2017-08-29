app.controller("produtoController", function($scope,$http) {
	
	
	
	$scope.buscarProdutos = function(){
		if(table!=undefined){
			table.fnDestroy()
			table = undefined

		}
		
		$http.get("produto/buscarTodos")
		.success(function(response){
			$scope.produtos = response;


		})
		.error(function(error){


		});
	}
	
	$scope.buscarProdutos();
	var table
	$scope.$on('ngRepeatFinished', function(ngRepeatFinishedEvent) {
		
		table = $('.dataTable').dataTable( {
		} );

	});
	
	
	$scope.cadastrarProduto = function() {

		$scope.salvar = function(produto){
			$http.post("produto/salvar", produto)
			.success(function(response){
				$('#modal_produto').modal('hide')
				$('body').removeClass('modal-open');
				$('.modal-backdrop').remove();
				
				$scope.buscarProdutos()
			})
			.error(function(error){


			});
			
		}
		
	}
	
	
    
});