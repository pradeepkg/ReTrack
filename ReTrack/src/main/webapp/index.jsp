<%@ include file="/WEB-INF/jsps/taglibs.jsp"%>
<stripes:layout-render name="/WEB-INF/jsps/${layout}"
	title="Index"
	objects="${pageContext.request.parameterMap}">
	<stripes:layout-component name="contents">
		<table border="0" width="100%" align="left" cellpadding="0">
			<tr class="heading">
				<td colspan="2">&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">
					<table align="left" border="0">
						<tr>
							<td colspan="2" align="center">
								<stripes:link beanclass="com.gullapu.savtrac.web.CreateActionBean" event="showForm">
									<img src="resources/notepad.png" title="Create a new report" />
								</stripes:link>	
								<stripes:link beanclass="com.gullapu.savtrac.web.CreateActionBean" event="showForm">
									<h2>Create</h2>
								</stripes:link>		
							</td>
							<td colspan="2" align="center">
								<stripes:link beanclass="com.gullapu.savtrac.web.GetEntriesActionBean" event="getEntries">
									<img src="resources/binoculars.png" title="View all entries"/>
								</stripes:link>
								<stripes:link beanclass="com.gullapu.savtrac.web.GetEntriesActionBean" event="getEntries" >
									<h2>View All</h2>
								</stripes:link>						
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>						
					</table>

				</td>
			</tr>
			<tr class="heading">
				<td colspan="2">&nbsp;</td>
			</tr>
		</table>
	</stripes:layout-component>
</stripes:layout-render>