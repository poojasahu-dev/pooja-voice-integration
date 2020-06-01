ACC.common = {
	currentCurrency: $("main").data('currencyIsoCode') || "USD",
	processingMessage: $("<img>").attr("src", ACC.config.commonResourcePath + "/images/spinner.gif"),


	blockFormAndShowProcessingMessage: function (submitButton)
	{
		var form = submitButton.parents('form:first');
		form.block({ message: ACC.common.processingMessage });
	},

	refreshScreenReaderBuffer: function ()
	{
		// changes a value in a hidden form field in order
		// to trigger a buffer update in a screen reader
		$('#accesibility_refreshScreenReaderBufferField').attr('value', new Date().getTime());
	},

	checkAuthenticationStatusBeforeAction: function (actionCallback)
	{
		$.ajax({
			url: ACC.config.authenticationStatusUrl,
			statusCode: {
				401: function () {
					location.href = ACC.config.loginUrl;
				}
			},
			dataType: 'json',
			success: function (data) {
				if (data == "authenticated") {
					actionCallback();
				}
			}
		});
	},

	encodeHtml: function (rawText)
	{
		return rawText.toString()
				.replace(/&/g, '&amp;')
				.replace(/</g, '&lt;')
				.replace(/>/g, '&gt;')
				.replace(/"/g, '&quot;')
				.replace(/'/g, '&#39;')
				.replace(/\//g, '&#47;');
	}
};





/* Extend jquery with a postJSON method */
jQuery.extend({
	postJSON: function (url, data, callback)
	{
		return jQuery.post(url, data, callback, "json");
	}
});

// add a CSRF request token to POST ajax request if its not available
$.ajaxPrefilter(function (options, originalOptions, jqXHR)
{
	// Modify options, control originalOptions, store jqXHR, etc
	if (options.type === "post" || options.type === "POST")
	{
		var noData = (typeof options.data === "undefined");
		if (noData)
		{
			options.data = "CSRFToken=" + ACC.config.CSRFToken;
		}
		else
		{
			var patt1 = /application\/json/i;
			if (options.data instanceof window.FormData)
			{
				options.data.append("CSRFToken", ACC.config.CSRFToken);
			}
			// if its a json post, then append CSRF to the header. 
			else if (patt1.test(options.contentType))
			{
				jqXHR.setRequestHeader('CSRFToken', ACC.config.CSRFToken);
			}
			else if (options.data.indexOf("CSRFToken") === -1)
			{
				options.data = options.data + "&" + "CSRFToken=" + ACC.config.CSRFToken;
			}
		}
		
	}
});

/*
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
});*/

/*$(document).ready(function ()
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
*/


