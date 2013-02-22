<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript">
function checkKey(evt){
	var e=evt || event;
    var charCode = e.which || e.keyCode;
    if(charCode == 13){
    	document.getElementById('loginForm').submit();
    }
}
</script>
<style>
.error{
	color: red;
	border:2px solid red;
	padding:10px;
	
}
</style>

	<c:if test="${not empty error}">
		<div class="error">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>

<form:form name="loginForm" id="loginForm" method="post" acceptCharset="UTF-8" action="j_spring_security_check" >
<h2 align="center">Plain Java Rules Engine</h2>
<center>

<table class="ruletable" width="50%" cellspacing="25">
	<tr>
		<td class="ruletabletd" colspan=2><form:errors path="*" cssClass="error" element="div"/></td>	
	</tr>
	<tr>
		<td class="ruletabletd">User Name</td>
		<td class="ruletabletd"><input  type="text" name="j_username" id="userName" size="30" onkeypress="checkKey(event);"></input></td>		
	</tr>
	<tr>
		<td class="ruletabletd">Password</td>
		<td class="ruletabletd"><input type="password" name="j_password" id="password" size="30" onkeypress="checkKey(event);"></input></td>
	</tr>
	<tr>
		<td class="ruletabletd" colspan=2 align="center">
			<A class="button" href="#" onclick="javascript:document.getElementById('loginForm').submit();" >Login</A>		
		</td>	
	</tr>	
	
	
</table>
</center>
</form:form>