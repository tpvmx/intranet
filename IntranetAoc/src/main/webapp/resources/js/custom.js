/*------------------------------------------------------
    Author : www.webthemez.com
    License: Commons Attribution 3.0
    http://creativecommons.org/licenses/by/3.0/
---------------------------------------------------------  */
(function($) {

	// Navigation scrolls
	$(".navbar-nav li a").on('click', function(event) {
		$('.navbar-nav li').removeClass('active');
		$(this).closest('li').addClass('active');
		var $anchor = $(this);
		var nav = $($anchor.attr('href'));
		if (nav.length) {
			$('html, body').stop().animate({
				scrollTop : $($anchor.attr('href')).offset().top
			}, 1500, 'easeInOutExpo');

			event.preventDefault();
		}
	});
	$(".navbar-collapse a").on('click', function() {
		$(".navbar-collapse.collapse").removeClass('in');
	});

	// Add smooth scrolling to all links in navbar
	$("a.mouse-hover, a.get-quote").on('click', function(event) {
		var hash = this.hash;
		if (hash) {
			event.preventDefault();
			$('html, body').animate({
				scrollTop : $(hash).offset().top
			}, 1500, 'easeInOutExpo');
		}
	});
})(jQuery);
(function($) {
	"use strict";
	var mainApp = {

		initFunction : function() {

		},

		initialization : function() {
			mainApp.initFunction();

		}

	}
	// Initializing ///

	$(document).ready(function() {
		mainApp.initFunction();
	});

}(jQuery));
