/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */



$( document ).ready(function() {
	/*var name = $('#username').val();
	alert(name);*/
	 if(window.sessionStorage.getItem('isVoiceStarted') == 'yes'){
		 $('#startVoice').trigger('click');
	 }
});

$(document).ready(function ()
		{
	$(".toChangeValue").each(function() {
		var productId = $(this).attr('id');
		var index1 = productId.indexOf("<");
		var index2 = productId.indexOf(">");
		var subString = productId.substring(index1, index2 + 1);
		 while (productId.includes(subString) && productId.includes("</em>") ) {
			 productId = productId.replace(subString,"");
			 productId = productId.replace("</em>","");
		    }
		productId= productId.replace(/[&\/\\#,+()$~%.'":*?<>{}]/g, '');
	    $(this).attr('id',productId);
	}); 	
});

$(document).ready(function ()
		{
	$(".product__list--thumb").each(function() {
		var showP = $(this).attr('id');
		showP = showP.toLowerCase();
		showP = showP.split(" ").join("");
		var index1 = showP.indexOf("<");
		var index2 = showP.indexOf(">");
		var subString = showP.substring(index1, index2 + 1);
		while (showP.includes(subString) && showP.includes("</em>") ) {
			 showP = showP.replace(subString,"");
			 showP = showP.replace("</em>","");
		    }
		 showP= showP.replace(/[&\/\\#,+()$~%.'":*?<>{}]/g, '');		
	    $(this).attr('id',showP);
	}); 	
});


$(document).ready(function ()
		{
	if($("#isVoiceId").val() == 1)
		{
		var resultCount = $("#noOfResult").val();
		if(resultCount != undefined)
			{
			//alert(resultCount);
			artyom.say("Your in search result page, total" +resultCount+ "result found, say add to cart product name");
			}
		}
	
	if($("#voiceId").val() == 1)
	{
	var resultCount = $("#noOfSavedCarts").val();
	if(resultCount != undefined)
		{
		//alert(resultCount);
		artyom.say("total" +resultCount+ "result found");
		}
	}
	
	if($("#dateNumberId").val() > 0)
	{
	var resultCount = $("#noOfOrdersResult").val();
	if(resultCount != undefined && $("#pageId").val() == 0)
		{
		//alert(resultCount);
		artyom.say("total" +resultCount+ "result found");
		}
	}
	
});

window.artyom = new Artyom();


    	var commandHello = {
		    indexes:["hello","good morning","hey","good evening","good afternoon"], // These spoken words will trigger the execution of the command
		    action:function(){ // Action to be executed when a index match with spoken word
		    var name = $('#username').val();
	    	if(name != 'Anonymous')
	    		{
	    		artyom.say(" Welcome Back  "+name);
	    		}
	    	else
	    		{
	    		artyom.say(" Welcome Buddy , Please login to start shopping");	    
				window.location.href=ACC.config.contextPath+"/login";
	    		}
	   		 }
		};

		artyom.addCommands(commandHello);
		
		var commandVisitPage = {
			    indexes:["home page","cart page","my account","faq"], // These spoken words will trigger the execution of the command
			    action:function(i){ 
			    	// Action to be executed when a index match with spoken word
	    	
	    	switch(i){
	    	case 0:
	    		artyom.say(" oppening home page ");
	    		window.location.href=ACC.config.contextPath;
	    		break;
	    	case 1:
	    		artyom.say(" oppening cart page ");
	    		window.location.href=ACC.config.contextPath+"/cart";
	    		break;
	    	case 2:
	    		var name = $('#username').val();
	    		if(name != 'Anonymous')
		    		{
		    			window.location.href=ACC.config.contextPath+"/my-account/update-profile";
			    		artyom.say(" oppening personal details , check other links in my account also");
		    		}
		    	else
		    		{
		    		artyom.say("please login to see my account ");
		    		window.location.href=ACC.config.contextPath+"/login";
		    		}
	    		 $('.myAccountLinksHeader').click();
	    		break;
	    	case 3:
	    		artyom.say(" oppening FAQ Page ");
	    		window.location.href=ACC.config.contextPath+"/faq";
	    		break;
	    	default:
	    		 artyom.say("Hey buddy , Please reapeat again , not able to find anything");
	    	}
	    
	    }
	};
	artyom.addCommands(commandVisitPage);
	
	var commandForUpdateEmail = {
			indexes:["update email"],// These spoken words will trigger the execution of the command
			action:function(i){ // Action to be executed when a index match with spoken word
				var name = $('#username').val();
		    	if(name != 'Anonymous')
		    		{
		    		window.location.href=ACC.config.contextPath+"/my-account/update-email";
					artyom.say(" oppening email update page");	
		    		}
		    	else
		    		{
		    		artyom.say(" Please login to update email ");	    
					window.location.href=ACC.config.contextPath+"/login";
		    		}
									    	 
				 }
			};
	   artyom.addCommands(commandForUpdateEmail);
	   
	   
	   var logoutCommand = {
				indexes:["logout","sign out","log out"],// These spoken words will trigger the execution of the command
				action:function(i){ // Action to be executed when a index match with spoken word
					 var name = $('#username').val();
				    	if(name != 'Anonymous')
				    		{
				    		window.location.href=ACC.config.contextPath+"/logout";
							 artyom.say("you are logged out");
				    		}
				    	else
				    		{
				    		artyom.say(" you are already logged out");	
				    		}
						}
						};
		  artyom.addCommands(logoutCommand);
		  
		  
		  var myOrdersCommand = {
					indexes:["my orders","order history","open order history"],// These spoken words will trigger the execution of the command
					action:function(i){ // Action to be executed when a index match with spoken word
						var name = $('#username').val();
				    	if(name != 'Anonymous')
				    		{
				    		 artyom.say("what do you want to display last 1 day orders or 7 days order or 30 days order ");	 
				    		}
				    	else
				    		{
				    		artyom.say(" Please login to see orders");	   
			                 window.location.href=ACC.config.contextPath+"/login";
				    		}						    	 
					  }
					};
		 artyom.addCommands(myOrdersCommand);
		 
		 var myReturnOrdersCommand = {
					indexes:["my return orders","return history","open return history"],// These spoken words will trigger the execution of the command
					action:function(i){ // Action to be executed when a index match with spoken word
						var name = $('#username').val();
				    	if(name != 'Anonymous')
				    		{
				    		artyom.say("return history page is opening");	
							 window.location.href=ACC.config.contextPath+"/my-account/returns";
				    		}
				    	else
				    		{
				    		artyom.say(" Please login to see returned orders");	   
			                 window.location.href=ACC.config.contextPath+"/login";
				    		}						    	 
						  }
						};
		 artyom.addCommands(myReturnOrdersCommand);
		 
		 var addressBookCommand = {
					indexes:["address book","add address","save address","delete address"],// These spoken words will trigger the execution of the command
					action:function(i){ // Action to be executed when a index match with spoken word
						switch(i)
						{
						case 0:
							var name = $('#username').val();
					    	if(name != 'Anonymous')
					    		{
					    		artyom.say("address book page is opening");	
								 window.location.href=ACC.config.contextPath+"/my-account-voice/address-book-voice";
					    		}
					    	else
					    		{
					    		 artyom.say(" Please login to see address book");	   
				                 window.location.href=ACC.config.contextPath+"/login";
					    		}
							break;
						case 1:
							window.location.href=ACC.config.contextPath+"/my-account-voice/add-address";
							artyom.say("you are in add address page,say save address to add address");	
							break;
						case 2:
							if($('#saveAddress').val() != undefined)
							{
								 artyom.say("do you want to make this default address say yes or no")
								
							}
							break;
						case 3:
							if($('#deleteAddress').val() != undefined)       // this if block is for address book
							{
							var href = $('#deleteAddress').attr('href');
							var removeAddressLink = ACC.config.contextPath+"/my-account-voice/"+href;
							window.location.href = removeAddressLink;
							artyom.say("address"+justTheNumber+"is removed successfully");
							}
							break;
						}
					   						    	 
					 }
				};
		 artyom.addCommands(addressBookCommand);
		 

	var commandChangeLang = {
		smart:true,
	    indexes:["change language to *"],// These spoken words will trigger the execution of the command
	    action:function(i,wildcard){ // Action to be executed when a index match with spoken word
	    	
	    	 switch(wildcard){
	  			    case "english":
	  			    	artyom.say(wildcard);
	  			    	  $('#lang-selector').val("en");			    	
	  					$('#lang-form').submit();		  						
	  		    		break;
	  		    	case "dutch":
	  		    		artyom.say(wildcard);
	  		    		$('#lang-selector').val("de");
	  		    		$('#lang-form').submit();
	  		    		break;
	  		    	case "chinese":
	  		    		artyom.say(wildcard);
	  		    		$('#lang-selector').val("zh");
	  		    		$('#lang-form').submit();
	  		    		break;		    	
	  		    	default:		  		    		
	  		    		 artyom.say("Hey buddy , Please reapeat again , not able to find the language");			    
	  			    }
	    }
	};
	
	artyom.addCommands(commandChangeLang);	
	
	var commandChangeCur = {
			smart:true,
		    indexes:["change currency to *"],// These spoken words will trigger the execution of the command
		    action:function(i,wildcard){ // Action to be executed when a index match with spoken word
		    	
		    	 switch(wildcard){
		  			    case "dollar":
		  			    	artyom.say(wildcard);
		  			   	   $('#currency-selector').val("USD");			    	
		  			   	$('#currency-form').submit();		  						
		  		    		break;
		  		    	case "german":
		  		    		artyom.say(wildcard);
		  		    		$('#currency-selector').val("JPY");
		  		    		$('#currency-form').submit();
		  		    		break;			  		    	    	
		  		    	default:	
		  		    		artyom.say(wildcard);
		  		    		 artyom.say("Hey buddy , Please reapeat again , not able to find the currency");			    
		  			    }
		    }
		};
		
		artyom.addCommands(commandChangeCur);
		
		 var commandSearchPage = {
					smart:true,
				    indexes:["looking for *","search for *","need *","open category *"],// These spoken words will trigger the execution of the command
				    action:function(i,wildcard){ // Action to be executed when a index match with spoken word
				    	artyom.say("you search for "+wildcard);
				    	//artyom.say("Your in search result page, say add to cart product name");
				    	var isVoice = 1;
				    	//window.location.href=ACC.config.contextPath+"/search/?text="+wildcard;
				    	window.location.href=ACC.config.contextPath+"/searchVoice/?isVoice=1"+"&text="+wildcard;
				    }
				};
			
			artyom.addCommands(commandSearchPage); 
			
			
			 var commandForPDP = {
						smart:true,
						indexes:["open product with code *","open pdp for code *","open pdp page for code *"],// These spoken words will trigger the execution of the command
						action:function(i,wildcard){ // Action to be executed when a index match with spoken word
									 artyom.say("Openning PDP for  "+wildcard);	
									var code= wildcard.replace(/ /g,'')
									if(code.includes("to"))
										{
										 code = code.replace("to","2")
										}
									else if(code.includes("for"))
										{
										 code = code.replace("for","4")
										}
									else if(code.includes("at"))
										{
										 code = code.replace("at","8")
										}
									 window.location.href=ACC.config.contextPath+"/p/"+code;
								    }
								};
					artyom.addCommands(commandForPDP);
					
					
					

					  var addToCartWildcard = {
							smart:true,
							indexes:["add to cart *","add to bag *","cart *"],
							action:function(i,wildcard){
							     wildcard = wildcard.split(" ").join("");
							     wildcard = wildcard.replace(/[&\/\\#,+()$~%.'":*?<>{}]/g, '');
								if(wildcard != null && $('#product_'+wildcard).val() != null)
									{
										var prodValue=$('#product_'+wildcard).val().trim();
										formId='addToCartForm'+prodValue;
										$('#'+formId).submit();
										artyom.say(" Product added to cart say checkout or continue shopping");
									}
								else
									{
									artyom.say(" Product name not found. please go to product detail page then add to cart.");
									}
							}
					    };
					 artyom.addCommands(addToCartWildcard); 
					 
					 
					 var placeOrderCommand = {
								indexes:["check out","next","place order","agreed"],
								action:function(i){
									 switch(i){
						  			    case 0:
						  			    	if($('.js-continue-checkout-button').val() == undefined){
						  						window.location.href = ACC.config.contextPath+'/cart';
						  						artyom.say("Your in cart page, say checkout to proceed");
						  					}else{
						  						//window.location.href = ACC.config.contextPath+'/cart/checkout';
						  						window.location.href = ACC.config.contextPath+'/checkout/multi/delivery-address-voice/add';
						  						artyom.say("Your in Address Page, say open address book to open address book");
						  						
						  					}		  						
						  		    		break;	
						  			    case 1:	
					  						window.location.href = ACC.config.contextPath+'/checkout/multi/payment-method-voice/add';
					  						artyom.say("Your in Payment Page, say saved cards to open saved cards");
						  			    	break;
						  			    case 2:
						  			    	if($("#Terms1").prop('checked') == false){
						  			    		artyom.say("please agreed term and condition to place order by saying agreed");
						  			    	} else {
						  			    		$('#placeOrderForm1').submit();
												artyom.say("your order is placed successfully, thankyou for shopping with us");
						  			    	}
						  			    	break;
						  			    case 3:
							  			     if($("#Terms1").val() != undefined){
												$("#Terms1").prop("checked", true);
												artyom.say("Thank you for agreed terms and conditions, please confirm order by saying place order");
												break;
											 }
						  		    	default:	
						  		    		 artyom.say("Hey buddy , Please reapeat again , not able to find the command");			    
						  			    }
									
								}
						};
						artyom.addCommands(placeOrderCommand);
					 

		var registerCommand = {
				indexes:["register"],
				action:function(i){
					if($('#registerBotton').val() != undefined){
						$('#registerBotton').trigger('click');
						artyom.say("your registration is submitted successfully");
					}
				}
		};
		artyom.addCommands(registerCommand);
		
		var loginCommand = {
				indexes:["login","login and check out"],
				action:function(i){
					if($('#loginCheckout').val() != undefined){
					  $('#loginCheckout').trigger('click');
					 artyom.say("you are logined to our website");
					}
				}
		};
		artyom.addCommands(loginCommand);
		
		var addToCartCommand = {
				indexes:["add to cart","add to bag"],
				action:function(i){
					if($("#addToCartButton").val() != undefined){
						artyom.say(" Product added to cart say checkout or continue shopping");
					$('#addToCartButton').trigger('click');
					}
					
				}
		};
		artyom.addCommands(addToCartCommand);

		var continueShopping = {
				indexes:["continue shopping"],
				action:function(i){
					if($('.js-continue-shopping-button').val() != undefined){
						$('.js-continue-shopping-button').trigger('click');
						artyom.say("your shopping is continue");
					}
					else{
						$('.js-mini-cart-close-button').click();
					}
				}
		};
		artyom.addCommands(continueShopping);
		
		var addressBookCommand = {
				indexes:["my address book open","select from my address","use my address", "open address book"],
				action:function(i){
					if($('.js-address-book').val() != undefined)
						{
						artyom.say("address book is opening");	
						$('.js-address-book').trigger('click');
						artyom.say("Select the address by saying select address number");
						}
					 }
		};
	 artyom.addCommands(addressBookCommand);
	 
	 var usedSavedAddressCommand = {
				indexes:["select address *","use address *","use address detail *","select from my address *","use my address *"],
				smart:true,
				action:function(i,wildcard){
						    wildcard =convertToNumber(wildcard);
							var justTheNumber = parseInt(wildcard.match(/\d+/g));
							$('#addresssNumber_'+justTheNumber).trigger('click');
							artyom.say("Address"+justTheNumber+"is used and say next to continue");	
					 }
		};
		artyom.addCommands(usedSavedAddressCommand);
		
		
		  var savedCardCommand = {
					indexes:["select from my saved card","my saved card","saved card","saved card open","open saved card","save card"],
					action:function(i){
				    	if($('.js-saved-payments').val() != undefined)
				    		{
					    	    artyom.say("saved cards details opening");	
					    		$('.js-saved-payments').trigger('click');
						    	artyom.say("Select the card by saying select card number");
				    		}
						 }
		              
			};
		 artyom.addCommands(savedCardCommand);
		 
		 var usedSavedCardsCommand = {
					indexes:["select card *","use card *","use payment detail *"],
					smart:true,
					action:function(i,wildcard){				
						wildcard =convertToNumber(wildcard);
						var justTheNumber = parseInt(wildcard.match(/\d+/g));
						$('#cardCounter_'+justTheNumber).trigger('click');
						artyom.say("Payment"+justTheNumber+"is used, please agree term and condition by saying agreed");
						 }
			};
			artyom.addCommands(usedSavedCardsCommand);

		var lastOrdersCommand = {
			    smart:true,
				indexes:["last * days order"],// These spoken words will trigger the execution of the command
				action:function(i,wildcard){ // Action to be executed when a index match with spoken word
					wildcard =convertToNumber(wildcard);
		            artyom.say("order history page is opening");
		            var link = ACC.config.contextPath+"/my-account-voice/ordersVoice?dateNumber="+wildcard;	
		            if(link != undefined)
		            	{
		            	window.location.href = link;  
		            	}
		            else
		            	{
		            	artyom.say("sorry, not able to access command, please say again");
		            	}
				}  
			};
		artyom.addCommands(lastOrdersCommand);
		

		 var paginationCommand = {
				indexes:["go to * page"],
				smart:true,
				action:function(i,wildcard){	
				  if(wildcard == 'next')
					{
						var pageLink=$('#nextPage').attr('href');
					}
					if(wildcard == 'previous')
					{
						var pageLink=$('#prePage').attr('href');
					}
					else if(wildcard != 'previous' && wildcard != 'next')
					{
						wildcard =convertToNumber(wildcard);
						var pageLink=$('#pageNumber').attr('href');
						index = pageLink.lastIndexOf('&');
						substring = pageLink.substring(index);
						pageLink = pageLink.replace(substring,'');
						pageLink = pageLink +'&page='+ (wildcard-1);
					} 
					if(pageLink != undefined)
						{
						window.location.href = pageLink;
						artyom.say("you are in"+wildcard+"page");
						}
					else
						{
						artyom.say("There are no"+wildcard+" page");
						}
					 }
		};
		artyom.addCommands(paginationCommand);
		
		var commandForPDPByName = {
				smart:true,
				indexes:["open product *"],// These spoken words will trigger the execution of the command
				action:function(i,wildcard){ // Action to be executed when a index match with spoken word
							 artyom.say("Openning PDP for  "+wildcard);	
							 wildcard = wildcard.split(" ").join("");
						     wildcard = wildcard.replace(/[&\/\\#,+()$~%.'":*?<>{}]/g, '');
						     var id='#showproduct1_'+wildcard;
						     var productLink=$(id).attr('href');
						     window.location.href = productLink;
						    }
				};
							
	    artyom.addCommands(commandForPDPByName); 
	    
	    var savedCartsCommand = {
				indexes:["saved carts","my saved carts","my shopping list"],
				action:function(i,wildcard){				
					artyom.say("opening saved carts page");
					var isVoice = 1;
					window.location.href=ACC.config.contextPath+"/my-account-voice/saved-carts-voice?isVoice=1";
					 }
		};
		artyom.addCommands(savedCartsCommand);
		
		var restoreCartCommand = {
				smart:true,
				indexes:["restore card name *","restore cart name *"],
				action:function(i,wildcard){
				     wildcard = wildcard.split(" ").join("");
					if(wildcard != null && $('#cart_'+wildcard).val() != null)
						{
							$('#cart_'+wildcard).trigger('click');
							artyom.say("do you want to keep a copy of this cart on saved list, say yes or no, by default it is yes.");
						}
				}
		    };
	artyom.addCommands(restoreCartCommand);
	
	
	var restoreCartConfirmCommand = {
			indexes:["yes","no","restore","cancel","back to saved cards","edit","save","new cart"],
			action:function(i){
				switch(i){
				case 0:
					 if($("#keepRestoredCart").val() != undefined){
							$("#keepRestoredCart").prop("checked", true);
							artyom.say("Thank you , please confirm restore your cart  by saying restore");
						 }
						 
					 if($("#defaultAddress").val() != undefined){  // this if block is for address book
						 alert("save");
						   $("#defaultAddress").prop("checked", true);
						   $('#saveAddress').trigger('click');
						   artyom.say("your address is saved successfully");
					    }
					break;
				case 1:
					if($("#keepRestoredCart").val() != undefined){
						$("#keepRestoredCart").prop("checked", false);
						artyom.say("Thank you , please confirm restore your cart  by saying restore");
					 }
					if($("#defaultAddress").val() != undefined){     // this if block is for address book
						   $('#saveAddress').trigger('click');
						   artyom.say("your address is saved successfully");
					    }
					break;
				case 2:
						var display = $('#colorbox').css('display');
						if($('.js-save-cart-restore-btn') != undefined && display == 'block')
						{
							$('.js-save-cart-restore-btn').trigger('click');
							artyom.say("Thank you ,yor cart is restored successfully.");
						}
						
						if($("#restoreButton").val() != undefined && display == 'none')
						{
							$('#restoreButton').trigger('click');
							
							artyom.say("do you want to keep a copy of this cart on saved list, say yes or no, by default it is yes.");
						}
					  
					break;
				case 3:
					if($('.js-cancel-restore-btn').val() != undefined)
						{
							$('.js-cancel-restore-btn').trigger('click');
						}
					if($('.js-savedcart_delete_confirm_cancel').val() != undefined)
					{
						$('.js-savedcart_delete_confirm_cancel').trigger('click');
					}
					if($('.closeColorBox').val() != undefined)     // this if block is for address book
					{
						$('.closeColorBox').trigger('click');
					}
					break;
	
				case 4:
					if($('.savedCartBackBtn').val() != undefined)
					{
						//$('.savedCartBackBtn').trigger('click');
						window.location.href=ACC.config.contextPath+"/my-account-voice/saved-carts-voice";
						artyom.say("Thank you ,you are in saved cart page.");
					}
					break;
				case 5:
					if($('.js-update-saved-cart').val() != undefined)
					{
						$('.js-update-saved-cart').trigger('click');
						artyom.say("you are in update saved cart page. please say save to update.");
					}
					break;
				case 6:
					if($('#saveCartButton').val() != undefined)
					{
						$('#saveCartButton').trigger('click');
						artyom.say("your cart is saved successfully");
					}
					break;
				case 7:
					if($('.js-save-cart-link').val() != undefined)
					{
						$('.js-save-cart-link').trigger('click');
						artyom.say("give a cart name to previous cart and save it saying save");
					}
					break;
				}
			    
			}
	    };
   artyom.addCommands(restoreCartConfirmCommand);
   
   
   var removeCartCommand = {
			smart:true,
			indexes:["remove card name *","remove cart name *","confirm delete *"],
			action:function(i,wildcard){
			     wildcard = wildcard.split(" ").join("");
			    // alert(wildcard);
			     switch(i)
			     {
			     case 0:
			    	 if(wildcard != null && $('#cartRemove_'+wildcard).val() != null)
						{
							$('#cartRemove_'+wildcard).trigger('click');
							artyom.say("confirm cart remove by saying confirm delete cart name");
						}
			    	 break;
			     case 1:
			    	 if(wildcard != null && $('#cartRemove_'+wildcard).val() != null)
						{
							$('#cartRemove_'+wildcard).trigger('click');
							artyom.say("confirm cart remove by saying confirm delete cart name");
						}
			    	 break;
			     case 2:
			    	 if(wildcard != null && $('#delete_'+wildcard).val() != null)
						{
							$('#delete_'+wildcard).trigger('click');
							artyom.say("confirm cart remove by saying confirm delete cart name");
						}
			    	 break;
			     }
				
			}
	    };
	artyom.addCommands(removeCartCommand);
	
	var deleteCartCommand = {
			indexes:["delete cart","delete card"],
			action:function(i){
				if($('.js-savedcart_delete_confirm').val() != null)
					{
					$('.js-savedcart_delete_confirm').trigger('click');
					artyom.say("cart is deleted successfully");
					}
			}
	    };
	artyom.addCommands(deleteCartCommand);



	var openCartCommand = {
			smart:true,
			indexes:["open card name *","open cart name *"],
			action:function(i,wildcard){
				
			     wildcard = wildcard.split(" ").join("");
			     //alert(wildcard);
				if(wildcard != null && $('#cartOpen_'+wildcard).val() != null)
					{
						var cartLink = $('#cartOpen_'+wildcard).attr('href');
						window.location.href = cartLink;
						artyom.say("Cart name" +wildcard+"is opening");
					}
			}
	    };
	artyom.addCommands(openCartCommand);
	
	
	 var openOrderDetailPageCommand = {
				indexes:["open order number *"],
				smart:true,
				action:function(i,wildcard){
					
					var code= wildcard.replace(/ /g,'')
					if(code.includes("to"))
						{
						 code = code.replace("to","2")
						}
					else if(code.includes("for"))
						{
						 code = code.replace("for","4")
						}
					else if(code.includes("at"))
						{
						 code = code.replace("at","8")
						} 
					window.location.href=ACC.config.contextPath+"/my-account-voice/order/"+code;
					artyom.say("order detail page is opened");
					}
		};
		artyom.addCommands(openOrderDetailPageCommand);
		
		
		var returnOrderCommand = {
				indexes:["back to order history","return order","return complete order","confirm return","submit request"],
				action:function(i){
					switch(i)
					{
					case 0:
						if($('#backOrderHistory').val() != undefined)
							{
								$('#backOrderHistory').trigger('click');
								artyom.say("you are in order history page");
							}
						break;
					case 1:
						if($('#returnOrderButton').val() != undefined)
							{
								$('#returnOrderButton').trigger('click');
								artyom.say("do you want to return complete order,if yes then say return complete order.");
							}
						break;
					case 2:
						if($('.js-return-complete-order-link').val() != undefined)
							{
								$('.js-return-complete-order-link').trigger('click');
								artyom.say("say confirm return to confirm return order");
							}
						break;
					case 3:
						if($('#returnOrderButtonConfirmation').val() != undefined)
							{
								$('#returnOrderButtonConfirmation').trigger('click');
								artyom.say("say submit request for return confirmation");
							}
						break;
					case 4:
						if($('#submitreturnorderformvbutton').val() != undefined)
						{
							$('#submitreturnorderformvbutton').trigger('click');
							artyom.say("your order return request is submitted successfully.");
						}
					break;
					}
				}
		};
		artyom.addCommands(returnOrderCommand);
		
		
		var cancelOrderCommand = {
				indexes:["cancel order","cancel complete order","confirm cancel","submit cancel request"],
				action:function(i){
					switch(i)
					{
					case 0:
						if($('#cancelOrderButton').val() != undefined)
							{
								$('#cancelOrderButton').trigger('click');
								artyom.say("do you want to cancel complete order,if yes then say cancel complete order.");
							}
						break;
					case 1:
						if($('.js-cancel-complete-order-link').val() != undefined)
							{
								$('.js-cancel-complete-order-link').trigger('click');
								artyom.say("say confirm cancel to confirm cancel order");
							}
						break;
					case 2:
						if($('#cancelOrderButtonConfirmation').val() != undefined)
							{
								$('#cancelOrderButtonConfirmation').trigger('click');
								artyom.say("say submit cancel request for cancel order confirmation");
							}
						break;
					case 3:
						if($('#submitcancelorderformvbutton').val() != undefined)
						{
							$('#submitcancelorderformvbutton').trigger('click');
							artyom.say("your order cancel request is submitted successfully.");
						}
					break;
					}
				}
		};
		artyom.addCommands(cancelOrderCommand);
		
		
	 
	 var addressBookOperationCommand = {
				indexes:["edit address *","remove address *","set as default address *"],
				smart:true,
				action:function(i,wildcard){
						    wildcard =convertToNumber(wildcard);
							var justTheNumber = parseInt(wildcard.match(/\d+/g));
							switch(i)
							{
							case 0:
								var href = $('#editAddress_'+justTheNumber).attr('href');
								var editAddressLink = ACC.config.contextPath+"/my-account-voice/"+href;
								window.location.href = editAddressLink;
								artyom.say("edit address page is opening");
								break;
							case 1:
								$('#removeAddress_'+justTheNumber).trigger('click');
								artyom.say("please confirm remove address by saying delete address");
								break;
							case 2:
								var href = $('#setAsDefaultAddress_'+justTheNumber).attr('href');
								var setAsDefaultAddressLink = ACC.config.contextPath+"/my-account-voice/"+href;
								window.location.href = setAsDefaultAddressLink;
								artyom.say("address"+justTheNumber+"is set as default address");
								break;
							}
					 }
		};
		artyom.addCommands(addressBookOperationCommand);
		
		
		var removeProductCommand = {
				smart:true,
				indexes:["remove product *"],
				action:function(i,wildcard){
				     wildcard = wildcard.split(" ").join("");
				 //   alert(wildcard);
					if(wildcard != null && $('.remove_'+wildcard).val() != null)
						{
							var productId = $('.remove_'+wildcard).attr('id');
							//alert(productId);
							$('#'+productId).trigger('click');
							artyom.say("product name" +wildcard+"is is removed from card");
						}
				}
		    };
		artyom.addCommands(removeProductCommand);
		
		var removeAllProductFromCartCommand = {
				indexes:["remove all product from cart","remove all product from card","empty cart","remove all"],
				action:function(i){
					if($('.js-execute-entry-action-button').val() != undefined)
						{
						 var cartLength = $('.js-execute-entry-action-button').length;
						 var i;
					//	 alert(cartLength);
						  for (i = 0; i < cartLength; i++) {
							$('#actionEntry_'+i).trigger('click');
							window.location.href = ACC.config.contextPath+"/cart";
						}  
						window.location.href = ACC.config.contextPath+"/cart";
						artyom.say("your shoping cart is empty.")
					}
				}
		    };
		artyom.addCommands(removeAllProductFromCartCommand); 
		
		
		 var commandSortCategory = {
					smart:true,
				    indexes:["sort By *","search by *"],// These spoken words will trigger the execution of the command
				    action:function(i,wildcard){
				    	// Action to be executed when a index match with spoken word
				    	artyom.say("Sort by "+wildcard);
				    	switch(wildcard){
				    	
				    	case "relevance":
				    		artyom.say("Sort by "+wildcard);	
				    		$('#sortOptions1').val("relevance");
					    		$('#sortForm1').submit();
				    		break;				    
				    	case "top rated":
				    		artyom.say("Sort by "+wildcard);
				    		$('#sortOptions1').val("topRated");
					    		$('#sortForm1').submit();
				    		break;	
				    	case "name ascending":
				    		artyom.say("Sort by "+wildcard);	
				    		$('#sortOptions1').val("name-asc");
					    		$('#sortForm1').submit();
				    		break;	
				    	case "name descending":
				    		artyom.say("Sort by "+wildcard);
				    		$('#sortOptions1').val("name-desc");
					    		$('#sortForm1').submit();
				    		break;	
				    	case "price lowest first":
				    		artyom.say("Sort by "+wildcard);
				    		$('#sortOptions1').val("price-asc");
					    		$('#sortForm1').submit();
				    		break;	
				    	case "price":
				    		artyom.say("Sort by "+wildcard);
				    		$('#sortOptions1').val("price-desc");
					    	$('#sortForm1').submit();
				    		break;	
				    	 
				      }
				    }
				};	
			artyom.addCommands(commandSortCategory);
		
		
		


		function convertToNumber(text){

			if(text.includes('one') || text.includes('first')){
				return '1'
			}
			else if(text.includes('two') || text.includes('to') || text.includes('tu') || text.includes('second')){
				return '2'
			}
			else if(text.includes('three')|| text.includes('third') ){
				return '3'
			}
			else if(text.includes('four') || text.includes('for') || text.includes('forth') || text.includes('4th')){
				return '4'
			}
			else if(text.includes('five') || text.includes('fifth') || text.includes('5th')){
				return '5'
			}
			else if(text.includes('six') || text.includes('sixth')|| text.includes('6th')){
				return '6'
			}
			else if(text.includes('seven') || text.includes('one week')|| text.includes('seventh')|| text.includes('7th')){
				return '7'
			}
			else if(text.includes('eight') || text.includes('at')|| text.includes('8th')){
				return '8'
			}
			else if(text.includes('nine')|| text.includes('9th')){
				return '9'
			}
			else if(text.includes('thirty') || text.includes('one month') || text.includes('month')){
				return '30'
			}
			else{
				return text;
			}
		}
		


function start(){
	window.sessionStorage.setItem('isVoiceStarted','yes');
	$('#startVoice').hide();
	$('#stopVoice').show();
artyom.initialize({
            lang:"en-IN",// A lot of languages are supported. Read the docs !
            continuous:true,// recognize 1 command and stop listening !
            listen:true, // Start recognizing
            debug:true, // Show everything in the console
            speed:1
        });
	
artyom.ArtyomVoicesIdentifiers["en-GB"] = ["Google UK English Female", "Google UK English Male", "en-GB", "en_GB"];		

}

function stop(){
	window.sessionStorage.setItem('isVoiceStarted','no');
	$('#startVoice').show();
	$('#stopVoice').hide();
  artyom.fatality();
} 

 

         