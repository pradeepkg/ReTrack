<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="taglibs.jsp"%>
<stripes:layout-render name="${layout}" title="Documents"
	objects="${pageContext.request.parameterMap}">
	<stripes:layout-component name="html-head">
		<script type="text/javascript">

		</script>
	</stripes:layout-component>
	<stripes:layout-component name="contents">
		<table border="1" cellspacing="0" align="center" width="100%">
			<tr class="heading">
				<td>ID</td>
				<td>Name</td>
				<td>Type</td>
				<td>View</td>
			</tr>
			<c:forEach items="${actionBean.documents}" var="document"
				varStatus="loop">
				<tr>
					<td>${document.id}</td>
					<td>${document.fileName}</td>
					<td>${document.mimeType}</td>
					<td><stripes:link
							beanclass="com.gullapu.savtrac.web.GetDocumentsActionBean"
							target="_blank" event="getDocument" id="getDocument" onclick="">
							<stripes:param name="documentID">${document.id}</stripes:param>
                            View
                        </stripes:link>
				</tr>
			</c:forEach>


		</table>
	</stripes:layout-component>
</stripes:layout-render>