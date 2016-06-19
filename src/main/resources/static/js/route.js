console.log('route.js');
(function($) {
	var app = $.sammy('#body-main', function(){

		this.get('#/', function(){
			console.log('sammy app-info.html');
			this.$element().load('templates/app-info.html');
		});

		this.get('#/trade-board', function(){
			console.log('sammy trade-board.html');
			this.$element().load('templates/trade-board.html');
		});
		
		this.get('#/etc', function(){
			console.log('sammy etc.html');
			this.$element().load('templates/etc.html');
		});
	});

	$(function() {
		app.run('#/');
	});

})(jQuery);