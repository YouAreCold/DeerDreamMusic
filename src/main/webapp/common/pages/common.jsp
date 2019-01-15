<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//basePath = path+"/";
	String title = "-个人音乐网";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<script type="text/javascript" src="<%=path %>/common/scripts/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/common/js/jquery.JPlaceholder.js"></script>
<!-- <script type="text/javascript" charset="UTF-8" src="/welive/welive.js"></script> -->
<c:set var="ctx" value="<%=path %>"/>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?090d10be15bcf84eff931a0d02d82c81";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>