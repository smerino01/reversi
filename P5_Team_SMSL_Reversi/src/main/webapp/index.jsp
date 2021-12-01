<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML>
<html>
<head>
<style>
.game {
	background-color:black;
	border: 5px black solid;
	width: 500px;
}

.LIGHT {
	background-color:white;
	color: white;
}

.DARK {
	background-color:black;
	color: black;
}

.disks {
	text-align: center;
	width: 50px;
	height: 50px;
}

</style>

<title> Reversi Game</title>
<h1> Reversi </h1>
</head>
  <body>
  <div class="game">
  <form action="<c:url value='moveDisk'/>">
  	<table style="background-color:Green; border:Black; height: 500px; width: 500px">
  		<tr>
  		<c:forEach items="${game.disks}" var="space" varStatus="stat">
  			<td> 
  			<c:choose>
  			<c:when test="${empty space }">
  				<button type="submit" name="loc" class="space" value="${stat.index }">${stat.index }</button>
  			</c:when>
  			<c:otherwise>
  				<span class="space ${space }">${space }</span>
  			</c:otherwise>
  			</c:choose>
  			
  			<td>
  			<c:if test="${stat.count % 8 == 0 }">
  			</tr>
  			<tr>
  			</c:if>
  		</c:forEach>
  		<td colspan="16">
  		<ul style="background-color:darkgreen; color:white;">
  		<li>Current Player: ${game.currentPlayer }</li>
  		<li>Current Score: Dark -> ${game.darkScore}  Light -> ${game.lightScore}</li>
  		<li>Game Over: </li>
  		<li>Winner: </li>
  		</ul>
  		<button class="submit" name="quit" >Quit</button>
  		</td>
    	</tr>
    </table>
    </form>
    </div>
  </body>
</html>
