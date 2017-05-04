$(document).on({
	ajaxStart : function() {
		$('.modalWait').addClass("load");
	},
	ajaxStop : function() {
		$('.modalWait').removeClass("load");
	}
});

