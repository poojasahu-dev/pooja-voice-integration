<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/responsive/storepickup" %>

<template:page pageTitle="${pageTitle}">

	<div class="row">
		<div class="col-xs-3">
			<cms:pageSlot position="ProductLeftRefinements" var="feature" element="div" class="search-list-page-left-refinements-slot">
				<cms:component component="${feature}" element="div" class="search-list-page-left-refinements-component"/>
			</cms:pageSlot>
		</div>
		<div class="col-sm-12 col-md-9">
			<cms:pageSlot position="SearchResultsListSlotVoice" var="feature" element="div" class="search-list-page-right-result-list-slot">
				<cms:component component="${feature}" element="div" class="search-list-page-right-result-list-component"/>
			</cms:pageSlot>
        </div>
	</div>

	<storepickup:pickupStorePopup />

</template:page>

<script>

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



</script>