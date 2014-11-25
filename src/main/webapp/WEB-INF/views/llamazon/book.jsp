<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Llamazon</title>

<%@ include file="/WEB-INF/views/llamazon/include/css.jsp" %>

</head>

<body>

	<%@ include file="/WEB-INF/views/llamazon/include/navbar.jsp" %>
    <div class="container">
        <div class="row"><br><br><br><br><br><br><br><br><br></div>  
        <div class="row clearfix">
            <div class="col-md-1 column">
            </div>
            <div class="col-md-3 column">
                <img class="img-responsive" src="<c:url value="${book.bookImage}" />" />
            </div>
            <div class="col-md-8 column">
                <h2>
                    ${book.bookTitle}
                </h2>
                <h4>
                    ${book.author}
                </h4>
                <p>
                   ${book.description}  
                </p>
                <br><br>
                <input id = "currentBook" type="hidden" value="${book.llamazonBookId}">
                <div class="row clearfix">
                    <div class="col-md-1 column">
                    </div>
                    <div class="col-md-2 column">
                        <button type="button" class="btn btn-success btn-lg">Download</button>
                    </div>
                    <div class="col-md-1 column">
                    </div>
                    <div class="col-md-2 column">
                        <a type="button" class="btn btn-lg btn-default">Return</a>
                    </div>
                    <div class="col-md-6 column">
                    </div>
                </div>
            </div>
        </div>
    </div>
	
	<%@ include file="/WEB-INF/views/llamazon/include/javascript.jsp" %>
	
</body>

</html>