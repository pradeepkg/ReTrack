<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="taglibs.jsp"%>
<stripes:layout-render name="${layout}" title="User entries"
	objects="${pageContext.request.parameterMap}">
	<stripes:layout-component name="contents">
		<table border="1" cellspacing="0" align="cente" width="100%">
			<tr class="heading">
				<td>ID</td>
				<td>Name</td>
				<td>Description</td>
				<td colspan="2" width="20%">Options</td>
			</tr>
			<c:forEach items="${actionBean.userEntries}" var="entry"
				varStatus="loop">
				<tr>
					<td>${entry.id}</td>
					<td>
						<c:if test="true">ssssssssssssssssss</c:if>
						
							<stripes:link beanclass="com.gullapu.savtrac.web.EditActionBean"
								event="completeEntry">
								<stripes:param name="entryID">${entry.id}</stripes:param>
	                            <img src="resources/emoticon_angry.png" alt="Incomplete Entry" 
	                            	title="Incomplete Entry"/>
	                        </stripes:link>	
						
						${entry.name}
					</td>
					<td>${entry.description}</td>
					<td>
						<stripes:link beanclass="com.gullapu.savtrac.web.EditActionBean"
							event="showEditForm">
							<stripes:param name="entryID">${entry.id}</stripes:param>
                            <img src="resources/screwdriver.png" alt="Edit" title="Edit"/>
                        </stripes:link>						
					<c:choose>
						<c:when test="${empty entry.documents}">
							.</td>
						</c:when>
						<c:otherwise>
							<stripes:link beanclass="com.gullapu.savtrac.web.GetDocumentsActionBean"
								event="getDocumentsForEntry">
								<stripes:param name="entryID">${entry.id}</stripes:param>
	                            <img src="resources/clip.png" alt="View Attachments" title="View Attachments"/>
	                        </stripes:link>										
							</td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</table>
	</stripes:layout-component>
</stripes:layout-render>