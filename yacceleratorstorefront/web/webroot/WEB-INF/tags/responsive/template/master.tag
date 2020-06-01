<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ attribute name="pageTitle" required="false" rtexprvalue="true" %>
<%@ attribute name="metaDescription" required="false" %>
<%@ attribute name="metaKeywords" required="false" %>
<%@ attribute name="pageCss" required="false" fragment="true" %>
<%@ attribute name="pageScripts" required="false" fragment="true" %>

<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="analytics" tagdir="/WEB-INF/tags/shared/analytics" %>
<%-- <%@ taglib prefix="addonScripts" tagdir="/WEB-INF/tags/responsive/common/header" %> --%>
<%@ taglib prefix="addonScripts" tagdir="/WEB-INF/tags/addons/hmvoiceecommerce/responsive/common/header" %>
<%@ taglib prefix="generatedVariables" tagdir="/WEB-INF/tags/shared/variables" %>
<%@ taglib prefix="debug" tagdir="/WEB-INF/tags/shared/debug" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="htmlmeta" uri="http://hybris.com/tld/htmlmeta"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<spring:htmlEscape defaultHtmlEscape="true" />

<!DOCTYPE html>
<html lang="${fn:escapeXml(currentLanguage.isocode)}">
<head>
	<title>
		${not empty pageTitle ? pageTitle : not empty cmsPage.title ? fn:escapeXml(cmsPage.title) : 'Accelerator Title'}
	</title>

	<%-- Meta Content --%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">

	<%-- Additional meta tags --%>
	<htmlmeta:meta items="${metatags}"/>

	<%-- Favourite Icon --%>
	<spring:theme code="img.favIcon" text="/" var="favIconPath"/>
	
	<c:choose>
		<%-- if empty webroot, skip originalContextPath, simply use favIconPath --%>
		<c:when test="${fn:length(originalContextPath) eq 1}" >
			<link rel="shortcut icon" type="image/x-icon" media="all" href="${favIconPath}" />
		</c:when>
		<c:otherwise>
			<link rel="shortcut icon" type="image/x-icon" media="all" href="${originalContextPath}${favIconPath}" />
		</c:otherwise>
	</c:choose>

	<%-- CSS Files Are Loaded First as they can be downloaded in parallel --%>
	<template:styleSheets/>

	<%-- Inject any additional CSS required by the page --%>
	<jsp:invoke fragment="pageCss"/>
	<analytics:analytics/>
	<generatedVariables:generatedVariables/>
</head>

<body class="${pageBodyCssClasses} ${cmsPageRequestContextData.liveEdit ? ' yCmsLiveEdit' : ''} language-${fn:escapeXml(currentLanguage.isocode)}">

	<%-- Inject the page body here --%>
	<jsp:doBody/>


	<form name="accessiblityForm">
		<input type="hidden" id="accesibility_refreshScreenReaderBufferField" name="accesibility_refreshScreenReaderBufferField" value=""/>
	</form>
	<div id="ariaStatusMsg" class="skip" role="status" aria-relevant="text" aria-live="polite"></div>

	<%-- Load JavaScript required by the site --%>
	<template:javaScript/>
	
	<%-- Inject any additional JavaScript required by the page --%>
	<jsp:invoke fragment="pageScripts"/>

	<%-- Inject CMS Components from addons using the placeholder slot--%>
	<addonScripts:addonScripts/>




	<script>
 
/*  $( document ).ready(function() {
	 
	 if(window.sessionStorage.getItem('isVoiceStarted') == 'yes'){
		 $('#startVoice').trigger('click');
	 }
	 //$("#clickd").click();	
}); */ 

 
 
         // Create a global accesible instance of artyom
       // window.artyom = new Artyom();		 
		

	
	
		
	
		
			
			
			
   
	
					
	
	
	
		

	
	
    
   
	
	

/* function start(){
	window.sessionStorage.setItem('isVoiceStarted','yes');
	$('#startVoice').hide();
	$('#stopVoice').show();
artyom.initialize({
            lang:"en-IN",// A lot of languages are supported. Read the docs !
            continuous:true,// recognize 1 command and stop listening !
            listen:true, // Start recognizing
            debug:true, // Show everything in the console
            speed:1 // talk normally
        });
	
artyom.ArtyomVoicesIdentifiers["en-GB"] = ["Google UK English Female", "Google UK English Male", "en-GB", "en_GB"];		

}

function stop(){
	window.sessionStorage.setItem('isVoiceStarted','no');
	$('#startVoice').show();
	$('#stopVoice').hide();
  artyom.fatality();
} */

    </script><!-- 

	<input type="button" id="clickd" value="Start Voice" onclick="start()">
	<input type="button" value="Stop Voice" id="stop" onclick="stop()"> -->


</body>

<debug:debugFooter/>

</html>
