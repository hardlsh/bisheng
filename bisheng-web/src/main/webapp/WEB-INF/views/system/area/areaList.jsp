<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../basepath.jsp"%>

<link rel="stylesheet" href="${basePath}/resources/js/ztree/zTreeStyle/zTreeStyle.css" type="text/css" />
<link rel="stylesheet" href="${basePath}/resources/js/artDialog/skins/idialog.css" type="text/css" />
<style type="text/css">
.ztree li span.button.add {margin-right:2px; background-position:-143px 0px; vertical-align:top; *vertical-align:middle}  
</style>
<script type="text/javascript" src="${basePath}/resources/js/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${basePath}/resources/js/ztree/jquery.ztree.exedit-3.5.min.js"></script>
<script type="text/javascript" src="${basePath}/resources/js/artDialog/jquery.artDialog.js"></script>
<script type="text/javascript" src="${basePath}/resources/js/customized/areaList.js"></script>

<ol class="breadcrumb">
  <li class="active">地区管理</li>
</ol>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->	
		<div class="portlet box grey-cascade">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>地区列表
				</div>
				<div class="actions">
					<button id="addArea" class="btn btn-sm blue" type="button" onclick="return areaManager.addTopNode();">新增省级地区</button>
				</div>
			</div>
			<div class="portlet-body" style="float:left;">
				<div class="model-box-h" style="margin-bottom:15px;">
					<div class="tube-table" id="manageTable">
						<%-- 地区树列表 --%>
						<ul id="areaTree" class="ztree"></ul>
					</div>
				</div>
			</div>
			
		</div>
	</div>
</div>
