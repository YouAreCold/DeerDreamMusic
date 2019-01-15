<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!-- 在线人员列表  -->
<thead>
	<tr>
		<th>在线人员列表</th>
	</tr>
</thead>
<tbody id="doctorInfos" height="560px">
	<c:forEach var="entry" items="${ userMap }">
	  	<tr style="height:50px;">
		    <td align="center" style="height:50px;" id="online">
		    	<a href="javascript:void();" color="#0000" onclick="set('${ entry.key.userName }')">${ entry.key.userName }</a> 
				<c:if test="${entry.key.userName != user.userName }">
					<a href="${path}/kick?userId=${ entry.key.userId }" style="color:#999">踢下线</a>
				</c:if>
			</td>
	  	</tr>
	</c:forEach>
</tbody>