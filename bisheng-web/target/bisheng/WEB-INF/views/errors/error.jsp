<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- isErrorPage的作用设置当前页面为错误页面，当在别的页面设置errorPage="error.jsp" 后，在设置页面出现问题后就自动会跳转到当前的错误页面 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8"/>
		<title>字聖工坊</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8">
		<meta content="" name="description"/>
		<meta content="" name="author"/>
		<style type="text/css">
		table tr td { word-break: keep-all;/*必须*/ font-size: 12px;}
		</style>
	</head>
	<body bgcolor="#FFFFFF">
		<div align="center">
			<br>
			<br>
			<h1>
				错误信息
			</h1>
			<hr>
			<p>
			<h3><%=exception.toString()%></h3>
			<br>
			<br>
			<br>
			<a href="javascript: history.back();">返回</a>
		</div>
	</body>
</html>