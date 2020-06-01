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



$(document).on("click","#mylogout",function(){ 
	
	$.ajax({
		url : ACC.config.encodedContextPath + "/resetFlagIsActive",
		type : 'GET',
		success : function(data) {
		if(data !== "Server Error")
		{
		//alert("hi");
		window.location.href = ACC.config.encodedContextPath + "/logout";
		}
	}

  });
	
});
