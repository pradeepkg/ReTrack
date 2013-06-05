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
					<table align="left">
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2">
								<stripes:link beanclass="com.gullapu.savtrac.web.CreateActionBean" event="showForm">
									<img src="resources/notepad.png" />
								</stripes:link>	
								&nbsp;&nbsp;
								<stripes:link beanclass="com.gullapu.savtrac.web.CreateActionBean" event="showForm">
									Create a new report
								</stripes:link>		
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						
						<tr>
							<td colspan="2">
								<stripes:link beanclass="com.gullapu.savtrac.web.GetEntriesActionBean" event="getEntries">
									<img src="resources/binoculars.png" />
								</stripes:link>	
								&nbsp;&nbsp;
								<stripes:link beanclass="com.gullapu.savtrac.web.GetEntriesActionBean" event="getEntries">
									View all entries
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