<%-- 
    Document   : gabarit
    Created on : 2013-12-10, 19:32:22
    Author     : Marc-Antoine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Twitface client 2000!</title>
    </head>
    <body>
        <h1>Affichage des utilisateurs</h1>
        
        <c:forEach var="enreg" items="${lesMembres}">
          ${enreg.nom}<br />
        </c:forEach>
          
        <c:if test="${requestScope['precedent']}">
            <input type="button" value="Précédent" />
        </c:if>

        <c:if test="${requestScope['suivant']}">
            <input type="button" value="Suivant" />
        </c:if>
            
    </body>
</html>
