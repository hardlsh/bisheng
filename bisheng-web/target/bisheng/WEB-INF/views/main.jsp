<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<!--<![endif]-->
<!-- BEGIN HEAD -->
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
<!-- END HEAD -->
<%@include file="base.jsp"%>
<!-- BEGIN BODY -->
<body class="page-header-fixed page-quick-sidebar-over-content ">
<!-- BEGIN HEADER -->
<div class="page-header navbar navbar-fixed-top">
	<!-- BEGIN HEADER INNER -->
	<div class="page-header-inner">
		<!-- BEGIN LOGO -->
		<div class="page-logo">
			<a href="index.html">
			<img src="${basePath}/resources/img/logo.jpg" alt="logo" class="logo-default" width="108" height="26"/>
			</a>
			<div class="menu-toggler sidebar-toggler">
			</div>
		</div>
		<!-- END LOGO -->
		<!-- BEGIN RESPONSIVE MENU TOGGLER -->
		<a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
		</a>
		<!-- END RESPONSIVE MENU TOGGLER -->
		<!-- BEGIN TOP NAVIGATION MENU -->
		<div class="top-menu">
			<ul class="nav navbar-nav pull-right">
				
				<!-- BEGIN USER LOGIN DROPDOWN -->
				<li class="dropdown dropdown-user">
					<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
					<img alt="" class="img-circle" src="${basePath}/resources/img/avatar_small.jpg"/>
					<span class="username username-hide-on-mobile"></span>
					<i class="fa fa-angle-down"></i>
					</a>
					<ul class="dropdown-menu dropdown-menu-default">
						<li>
							<a href="javascript:showPasswordModel();" >
							<i class="icon-key"></i> 修改密码 </a>
						</li>
						<li>
							<a href="${basePath}/index/logout.do">
							<i class="icon-key"></i> 退出 </a>
						</li>
					</ul>
				</li>
				<!-- END USER LOGIN DROPDOWN -->
				<!-- BEGIN QUICK SIDEBAR TOGGLER -->
				<li class="dropdown dropdown-quick-sidebar-toggler">
					<a href="javascript:;" class="dropdown-toggle">
					<i class="icon-logout"></i>
					</a>
				</li>
				<!-- END QUICK SIDEBAR TOGGLER -->
			</ul>
		</div>
		<!-- END TOP NAVIGATION MENU -->
	</div>
	<!-- END HEADER INNER -->
</div>
<!-- END HEADER -->
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<div class="page-container">
	<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
	<div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title">Modal title</h4>
				</div>
				<div class="modal-body">
					 Widget settings form goes here
				</div>
				<div class="modal-footer">
					<button type="button" class="btn blue">Save changes</button>
					<button type="button" class="btn default" data-dismiss="modal">Close</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
	<!-- BEGIN SIDEBAR1 -->
	<div class="page-sidebar-wrapper">
		<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
		<!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
		<div class="page-sidebar navbar-collapse collapse">
			<!-- BEGIN SIDEBAR MENU1 -->
			<ul class="page-sidebar-menu" data-slide-speed="200" data-auto-scroll="true" data-auto-scroll="true" data-slide-speed="200">
				<!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element
				<li class="sidebar-search-wrapper">
					BEGIN RESPONSIVE QUICK SEARCH FORM
					DOC: Apply "sidebar-search-bordered" class the below search form to have bordered search box
					DOC: Apply "sidebar-search-bordered sidebar-search-solid" class the below search form to have bordered & solid search box
					<form class="sidebar-search sidebar-search-bordered" action="extra_search.html" method="POST">
						<a href="javascript:;" class="remove">
						<i class="icon-close"></i>
						</a>
						<div class="input-group">
							<input type="text" class="form-control" disabled="disabled" placeholder="Search...">
							<span class="input-group-btn">
							<a href="javascript:;" class="btn submit"><i class="icon-magnifier"></i></a>
							</span>
						</div>
					</form>
					END RESPONSIVE QUICK SEARCH FORM
				</li> -->
				<c:forEach items="${menus }" var="item" varStatus="status">
					<c:if test="${status.index==0}">
						<li class="start active open">
							<a href="javascript:;">
							<i class="icon-credit-card"></i>
							<span class="title">${item.menu.getResourceName() }</span>
							<span class="selected"></span>
							<span class="arrow open"></span>
							</a>
					</c:if>
					<c:if test="${status.index!=0}">
						<li>
							<a href="javascript:;">
							<i class="icon-credit-card"></i>
							<span class="title">${item.menu.getResourceName() }</span>
							<span class="arrow"></span>
							</a>
					</c:if>
					<c:if test="${fn:length(item.getChilds())>0}">
						<ul class="sub-menu">
						<c:forEach items="${item.getChilds() }" var="child" varStatus="stat">
							<c:if test="${stat.index==0 && status.index==0}">
								<li class="active">
									<a class="ajaxify start" href="${basePath}/${child.getResourcePath() }">
									<i class="icon-credit-card"></i>
									${child.getResourceName() }</a>
								</li>
							</c:if>
							<c:if test="${stat.index!=0 || status.index!=0}">
								<li>
									<a class="ajaxify" href="${basePath}/${child.getResourcePath() }">
									<i class="icon-credit-card"></i>
									${child.getResourceName()}</a>
								</li>
							</c:if>
						</c:forEach>
						</ul>
					</c:if>
					</li>
				</c:forEach>
			</ul>
			<!-- END SIDEBAR MENU1 -->
		</div>
	</div>
	<!-- END SIDEBAR1 -->
	<!-- BEGIN CONTENT -->
	<div class="page-content-wrapper">
		<div class="page-content" id = "body1">
			<div class="page-content-body" id = "body2">
			
			</div>
		</div>
		<!-- BEGIN CONTENT -->
	</div>
	<!-- END CONTENT -->
	<!-- BEGIN QUICK SIDEBAR -->
	<a href="javascript:;" class="page-quick-sidebar-toggler"><i class="icon-close"></i></a>
	<div class="page-quick-sidebar-wrapper">
		<div class="page-quick-sidebar">
			<div class="nav-justified">
				
				<div class="tab-content">
					<div class="tab-pane active page-quick-sidebar-chat" id="quick_sidebar_tab_1">
						<div class="page-quick-sidebar-chat-users" data-rail-color="#ddd" data-wrapper-class="page-quick-sidebar-list">
							<h3 class="list-heading">Staff</h3>
							<ul class="media-list list-items">
								<li class="media">
									<div class="media-status">
										<span class="badge badge-success">8</span>
									</div>
									<img class="media-object" src="${basePath}/resources/img/avatar_small.jpg" alt="...">
									<div class="media-body">
										<h4 class="media-heading">Bob Nilson</h4>
										<div class="media-heading-sub">
											 Project Manager
										</div>
									</div>
								</li>
								
								<li class="media">
									<img class="media-object" src="${basePath}/resources/img/avatar_small.jpg" alt="...">
									<div class="media-body">
										<h4 class="media-heading">Ella Wong</h4>
										<div class="media-heading-sub">
											 CEO
										</div>
									</div>
								</li>
							</ul>
							<h3 class="list-heading">Customers</h3>
							<ul class="media-list list-items">
								<li class="media">
									<div class="media-status">
										<span class="badge badge-warning">2</span>
									</div>
									<img class="media-object" src="${basePath}/resources/img/avatar_small.jpg" alt="...">
									<div class="media-body">
										<h4 class="media-heading">Lara Kunis</h4>
										<div class="media-heading-sub">
											 CEO, Loop Inc
										</div>
										<div class="media-heading-small">
											 Last seen 03:10 AM
										</div>
									</div>
								</li>
								
							</ul>
						</div>
					</div>
					
				</div>
			</div>
		</div>
	</div>
	<!-- END QUICK SIDEBAR -->
</div>
<!-- END CONTAINER -->
<!-- add modal content -->
<div class="modal" id="passwordModal" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">修改密码</h4>
			</div>
			<div class="modal-body">
				<form action="##" class="form-horizontal form-row-sepe" id="passwordFrom">
					<div class="form-group">
						<label class="control-label col-md-3">新密码</label>
						<div class="col-md-4">
							<input type="text" name="password" class="form-control" id="password">
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn default" data-dismiss="modal" id="close">关闭</button>
				<button type="button" class="btn blue" id ="submit" onclick="updatePwd();">保存</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- BEGIN FOOTER -->
<%@include file="footer.jsp"%>
<!-- END FOOTER -->
</body>
<!-- END BODY -->
<script type="text/javascript">
	jQuery(document).ready(function() {    
		Metronic.init(); // init metronic core components
		Layout.init(); // init current layout
		QuickSidebar.init(); // init quick sidebar
		//Demo.init(); // init demo features
		//TableManaged.init();
	    $('.page-sidebar .ajaxify.start').click() // load the content for the dashboard page.
	    $body_cache = $("body");
	});
	
	function showPasswordModel(){
		$('#passwordModal').modal('show');
	}
	function updatePwd(){
		var param = $("#passwordFrom").serialize();
		$.ajax({
			url : "${basePath}/user/updatePwd.do",
			data : param,
			dataType: "json",
			type : "POST",
			success : function(data) {
				bootbox.alert(data.resultMsg);
				if(data.resultCode=='0000'){
					$("#passwordFrom")[0].reset();
					$('#passwordModal').modal('hide');
				}
			}
		});
	}
</script>
</html>