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
				<td>Status</td>
				<td>Description</td>
				<td colspan="2" width="20%">Options</td>
			</tr>
			<c:forEach items="${actionBean.userEntries}" var="entry"
				varStatus="loop">
				<tr>
					<td>${entry.id}</td>
					<td>	
						<c:choose>
							<c:when test="${entry.status == 'VALID'}">
								<img src="resources/hand_thumbsup.png" alt="Complete" 
	                            	title="Entry Complete"/>
							</c:when>
							<c:when test="${entry.status == 'VOID'}">
								<img src="resources/delete.png" alt="Void" 
	                            	title="Void Entry"/>
							</c:when>	
							<c:when test="${entry.status == 'MAILED'}">
								<img src="resources/mail.png" alt="Mailed" 
	                            	title="Mailed"/>
							</c:when>
							<c:when test="${entry.status == 'PROCESSING'}">
								<img src="resources/hourglass.png" alt="Processing" 
	                            	title="Processing"/>
							</c:when>	
							<c:when test="${entry.status == 'APPROVED'}">
								<img src="resources/checkmark2.png" alt="Approved" 
	                            	title="Approved"/>
							</c:when>	
							<c:when test="${entry.status == 'RECEIVED'}">
								<img src="resources/money_bag.png" alt="Received" 
	                            	title="Received"/>
							</c:when>	
							<c:when test="${entry.status == 'REJECTED'}">
								<img src="resources/close.png" alt="Rejected" 
	                            	title="Rejected"/>
							</c:when>																																										
							<c:otherwise>
								<stripes:link beanclass="com.gullapu.savtrac.web.EditActionBean"
									event="completeEntry">
									<stripes:param name="entryID">${entry.id}</stripes:param>
		                            <img src="resources/emoticon_angry.png" alt="Incomplete Entry" 
		                            	title="Incomplete Entry"/>
		                        </stripes:link>	
							</c:otherwise>
						</c:choose>&nbsp;
					</td>
					<td>${entry.name}</td>
					<td>${entry.description}</td>
					<td>
						<c:if test="${entry.status == 'VALID'}">
                            <stripes:link beanclass="com.gullapu.savtrac.web.ProcessActionBean"
								event="showMailForm">
								<stripes:param name="entryID">${entry.id}</stripes:param>
                           		<img src="resources/mail.png" alt="Mark as 'Mailed'" 
                           			title="Mark as 'Mailed'"/>
                       		</stripes:link>
						</c:if>
						<c:if test="${entry.status == 'MAILED'}">
                            <stripes:link beanclass="com.gullapu.savtrac.web.ProcessActionBean"
								event="showProcessingForm">
								<stripes:param name="entryID">${entry.id}</stripes:param>
                           		<img src="resources/hourglass.png" alt="Mark as 'Processing'" 
                           			title="Mark as 'Processing'"/>
                       		</stripes:link>
						</c:if>
						<c:if test="${entry.status == 'PROCESSING'}">
                            <stripes:link beanclass="com.gullapu.savtrac.web.ProcessActionBean"
								event="showApprovedForm">
								<stripes:param name="entryID">${entry.id}</stripes:param>
                           		<img src="resources/checkmark2.png" alt="Mark as 'Approved'" 
                           			title="Mark as 'Approved'"/>
                       		</stripes:link>
						</c:if>	
						<c:if test="${entry.status == 'APPROVED'}">
                            <stripes:link beanclass="com.gullapu.savtrac.web.ProcessActionBean"
								event="showReceivedForm">
								<stripes:param name="entryID">${entry.id}</stripes:param>
                           		<img src="resources/money_bag.png" alt="Mark as 'Received'" 
                           			title="Mark as 'Received'"/>
                       		</stripes:link>
						</c:if>	
						<c:if test="${entry.status == 'REJECTED'}">
							<img src="resources/close.png" alt="Rejected" 
                            	title="Rejected"/>
						</c:if>	
						<stripes:link beanclass="com.gullapu.savtrac.web.EditActionBean"
							event="showEditForm">
							<stripes:param name="entryID">${entry.id}</stripes:param>
                            <img src="resources/screwdriver.png" alt="Edit" title="Edit"/>
                        </stripes:link>						
						<c:choose>
							<c:when test="${empty entry.documents}">
								.
							</c:when>
							<c:otherwise>
								<stripes:link beanclass="com.gullapu.savtrac.web.GetDocumentsActionBean"
									event="getDocumentsForEntry">
									<stripes:param name="entryID">${entry.id}</stripes:param>
		                            <img src="resources/clip.png" alt="View Attachments" title="View Attachments"/>
		                        </stripes:link>										
							</c:otherwise>
						</c:choose>
					</td>					
				</tr>
			</c:forEach>
		</table>
	</stripes:layout-component>
</stripes:layout-render>