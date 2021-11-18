<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML>
<html>
<head>
<style>
body {
	width: 60%;
}

.alt {
	background-color: black;
}
</style>

<title> Reversi Game</title>
<h1 style=""> 
Reversi </h1>
</head>
  <body>
  <form action="<c:url value='moveDisk'/>"></form>
  	<table style="background-color:Green; border:Black; height: 500px; width: 500px">
  		<tr>
  		<c:forEach items="${game.disks}" var="space" varStatus="stat">
  			<td> 
  			<c:choose>
  			<c:when test="${empty space }">
  				<button tyoe="submit" name="loc">${stat.index }</button>
  			</c:when>
  			</c:choose>
  			
  			<td>
  			<c:if test="${stat.count % 8 == 0 }">
  			</tr>
  			<tr>
  			</c:if>
  		</c:forEach>
  		<td style="background-color:Black; color: white;">
  		<ul>
  		<li>${game.currentPlayer }</li>
  		</ul>
  		</td>
    	</tr>
    </table>
  </body>
</html>
