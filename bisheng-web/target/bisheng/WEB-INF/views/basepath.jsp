<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="hz" uri="/WEB-INF/hz" %>
<c:set var="basePath" value="${pageContext.request.contextPath }"></c:set>
<script type="text/javascript">
    var environment = {
        basePath : '${basePath}',
        globalPath : '${basePath}'
    };
</script>
<base href="${basePath}"/>