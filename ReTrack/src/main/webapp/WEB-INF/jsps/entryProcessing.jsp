<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="taglibs.jsp"%>
<stripes:layout-render name="${layout}" title="Entry Processed"
	objects="${pageContext.request.parameterMap}">
	<stripes:layout-component name="html-head">
		<link rel="stylesheet" type="text/css" media="all"
			href="resources/jsDatePick/jsDatePick_ltr.min.css" />
		<script type="text/javascript"
			src="resources/jsDatePick/jsDatePick.min.1.3.js"></script>
		<script type="text/javascript">
			window.onload = function() {
				new JsDatePick({
					useMode : 2,
					target : "processing.date"
				});
			};
		</script>
	</stripes:layout-component>	
	<stripes:layout-component name="contents">
		<stripes:form
			beanclass="com.gullapu.savtrac.web.ProcessActionBean"
			focus="date">
			<stripes:hidden name="entryID" />
			<table border="1" cellspacing="0" align="center" width="100%">
				<tr>
					<td align="left" class="prmt">&nbsp;
						<stripes:label name="entry.processing.date" /></td>
					<td align="left">&nbsp;
						<stripes:text name="date" id="processing.date" class="text_field" size="20" />
					</td>
				</tr>
				<tr>
					<td align="left" colspan="2">&nbsp;&nbsp;<stripes:submit
							class="submit_btn" name="entryProcessing" value="Mark as 'Processing'" />
					</td>
				</tr>
			</table>
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>