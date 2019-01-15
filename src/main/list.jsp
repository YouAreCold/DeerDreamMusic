<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="designerList" id="10box"><!-- start designerList -->  
         <ul class="layout_designer_list" id="layout_designer_list">  
             <c:forEach var="mentity" items="${list}">  
                 <li class="designer_list_details"><!-- start designer_list_details-->  
                     <div class="user-info-banner"><!-- start user-info-banner-->  
                         <div class="designer_list_details_info"><!-- start designer_list_details_info -->  
                             <p>粉丝/${mentity.fans} &nbsp;作品：80</p>  
                         </div><!-- end designer_list_details_info -->  
                     </div><!-- end user-info-banner-->  
                 </li><!-- end designer_list_details-->  
             </c:forEach>  
         </ul>  
     </div><!--end designerList -->  
</body>
</html>