<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Agregar Archivo</title>
    </head>
    <body>
        <c:if test="${addExcelFileSuccess}">
            <div>Successfully added File - id : ${savedFile.id}</div>
        </c:if>
    
        <c:url var="add_file_url" value="/demo/saveFile"/>
        <form:form action="${add_file_url}" method="post" modelAttribute="excelFile">
            <form:label path="file">Seleccione Archivo: </form:label> <form:input type="" path="file"/>
            <input type="submit" value="submit"/>
        </form:form>
    </body>
</html>