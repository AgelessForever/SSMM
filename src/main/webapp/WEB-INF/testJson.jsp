<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.2.min.js"></script>
<body>
	<script type="text/javascript">
	 function requestJson(){
		 $.ajax({
			 type:'post',
			 url:'${pageContext.request.contextPath }/requestJson.action',
			 contentType:'application/json;charset=utf-8',
			 //请求是数据就是json格式 
			 data:'{"name":"手机","price":678}',
			 success:function(data){//返回的格式也是JSON形式  --需要用户进行配置
				 alert(data);
			 }
			 
		 });
	 }
	 
	 function responseJson(){
		 $.ajax({
			 type:'post',
			 url:'${pageContext.request.contextPath }/responseJson.action',
			 //请求是数据就是json格式 
			 data:'name=手机&price=999',
			 success:function(data){//返回的格式也是JSON形式  --需要用户进行配置
				 alert(data.name);
			 }
			 
		 });
	 }
	
	</script>
	
	<input type="button" onclick="requestJson()" value="请求是JSON，输出也是JSON">
	<input type="button" onclick="responseJson()" value="请求是key-value，输出也是JSON">

</body>
</html>