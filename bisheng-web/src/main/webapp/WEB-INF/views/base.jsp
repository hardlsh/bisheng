
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="basePath" value="${pageContext.request.contextPath }"></c:set>
<base href="${basePath}"/>
<script>
var basePath = "${basePath}";
</script>
<!-- BEGIN GLOBAL MANDATORY STYLES 
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/>
-->
<link href="${basePath}/resources/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${basePath}/resources/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="${basePath}/resources/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${basePath}/resources/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="${basePath}/resources/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${basePath}/resources/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css" href="${basePath}/resources/plugins/select2/select2.css"/>
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="${basePath}/resources/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="${basePath}/resources/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${basePath}/resources/css/layout.css" rel="stylesheet" type="text/css"/>
<link href="${basePath}/resources/css/themes/darkblue.css" rel="stylesheet" type="text/css" id="style_color" />
<link href="${basePath}/resources/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="${basePath}/resources/css/base.css" rel="stylesheet" type="text/css"/>
<link href="${basePath}/resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>

<link rel="stylesheet" type="text/css" href="${basePath}/resources/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/resources/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="${basePath}/resources/images/favicon.ico"/>

<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="${basePath}/resources/js/respond.min.js"></script>
<script src="${basePath}/resources/js/excanvas.min.js"></script> 
<![endif]-->
<script src="${basePath}/resources/js/jquery.min.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/jquery.json.min.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/jquery-migrate.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${basePath}/resources/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
<script src="${basePath}/resources/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${basePath}/resources/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="${basePath}/resources/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${basePath}/resources/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${basePath}/resources/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript" ></script>
<script src="${basePath}/resources/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<script src="${basePath}/resources/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript" ></script>
<script src="${basePath}/resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/password.js" type="text/javascript"></script>

<!-- END CORE PLUGINS -->

<script type="text/javascript" src="${basePath}/resources/plugins/select2/select2.min.js"></script>
<script src="${basePath}/resources/js/metronic.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/layout.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/quick-sidebar.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/table-pagination.js"  type="text/javascript"></script>
<script src="${basePath}/resources/js/bootbox.js"  type="text/javascript"></script>
<script type="text/javascript" src="${basePath}/resources/js/components-pickers.js"></script>
<script src="${basePath}/resources/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${basePath}/resources/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${basePath}/resources/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${basePath}/resources/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="${basePath}/resources/js/accounting.js"></script>
<script src="${basePath}/resources/plugins/jquery-validation/js/jquery.validate.min.js"
        type="text/javascript"></script>
<script src="${basePath}/resources/plugins/jquery-validation/js/additional-methods.min.js"
        type="text/javascript"></script>
<script src="${basePath}/resources/plugins/jquery-validation/js/localization/messages_zh.min.js"
        type="text/javascript"></script>
<script>
//将一个表单的数据返回成JSON对象  
$.fn.serializeObject = function() {  
  var o = {};  
  var a = this.serializeArray();  
  $.each(a, function() {  
    if (o[this.name]) {  
      if (!o[this.name].push) {  
        o[this.name] = [ o[this.name] ];  
      }  
      o[this.name].push(this.value || '');  
    } else {  
      o[this.name] = this.value || '';  
    }  
  });  
  return o;  
};  
</script>
<script src="${basePath}/resources/js/base.js" type="text/javascript"></script>
<!-- END JAVASCRIPTS -->