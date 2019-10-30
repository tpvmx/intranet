/*------------------------------------------------------
    Author : www.webthemez.com
    License: Commons Attribution 3.0
    http://creativecommons.org/licenses/by/3.0/
---------------------------------------------------------  */

(function ($) {
    "use strict";
    var mainApp = {

        initFunction: function () {
            /*MENU 
            ------------------------------------*/
            $('#main-menu').metisMenu();
			
            $(window).bind("load resize", function () {
                if ($(this).width() < 768) {
                    $('div.sidebar-collapse').addClass('collapse')
                } else {
                    $('div.sidebar-collapse').removeClass('collapse')
                }
            });
        },
        initialization: function () {
            mainApp.initFunction();
        }
    }
    // Initializing ///

    $(document).ready(function () {
        mainApp.initFunction(); 
		$("#sideNav").click(function(){
			if($(this).hasClass('closed')){
				$('.navbar-side').animate({left: '0px'});
				$(this).removeClass('closed');
				$('#page-wrapper').animate({'margin-left' : '230px'});
				
			}
			else{
			    $(this).addClass('closed');
				$('.navbar-side').animate({left: '-230px'});
				$('#page-wrapper').animate({'margin-left' : '0px'}); 
			}
		});
		
		var navListItems = $('div.setup-panel div a'),
          allWells = $('.setup-content'),
          allNextBtn = $('.nextBtn'),
  		  allPrevBtn = $('.prevBtn');

		  allWells.hide();
		
		  navListItems.click(function (e) {
		      e.preventDefault();
		      var $target = $($(this).attr('href')),
		              $item = $(this);
		
		      if (!$item.hasClass('disabled')) {
		          navListItems.removeClass('btn-primary').addClass('btn-default');
		          $item.addClass('btn-primary');
		          allWells.hide();
		          $target.show();
		          $target.find('input:eq(0)').focus();
		      }
		  });
		  
		  allPrevBtn.click(function(){
		      var curStep = $(this).closest(".setup-content"),
		          curStepBtn = curStep.attr("id"),
		          prevStepWizard = $('div.setup-panel div a[href="#' + curStepBtn + '"]').parent().prev().children("a");
		
		          prevStepWizard.removeAttr('disabled').trigger('click');
		  });
		
		  allNextBtn.click(function(){
		      var curStep = $(this).closest(".setup-content"),
		          curStepBtn = curStep.attr("id"),
		          nextStepWizard = $('div.setup-panel div a[href="#' + curStepBtn + '"]').parent().next().children("a"),
		          curInputs = curStep.find("input[type='text'],input[type='url']"),
		          isValid = true;
		
		      $(".form-group").removeClass("has-error");
		      for(var i=0; i<curInputs.length; i++){
		          if (!curInputs[i].validity.valid){
		              isValid = false;
		              $(curInputs[i]).closest(".form-group").addClass("has-error");
		          }
		      }
		
		      if (isValid)
		          nextStepWizard.removeAttr('disabled').trigger('click');
		  });
		
		  $('div.setup-panel div a.btn-primary').trigger('click');
    });

}(jQuery));