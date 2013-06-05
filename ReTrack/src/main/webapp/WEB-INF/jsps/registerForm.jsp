<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="taglibs.jsp"%>
<stripes:layout-render name="${layout}" title="Create entry"
	objects="${pageContext.request.parameterMap}">
	<stripes:layout-component name="html-head">
		<link rel="stylesheet" type="text/css" media="all"
			href="resources/jsDatePick_ltr.min.css" />
		<script type="text/javascript" src="resources/jsDatePick.min.1.3.js"></script>
		<script type="text/javascript">
			window.onload = function() {
				new JsDatePick({
					useMode : 2,
					target : "entry.date.start"
				});

				new JsDatePick({
					useMode : 2,
					target : "entry.date.end"
				});
			};
		</script>
	</stripes:layout-component>
	<stripes:layout-component name="contents">
		<stripes:form
			beanclass="com.gullapu.savtrac.web.CreateActionBean">
			<table border="1" cellspacing="0" align="center" width="100%">
				<tr>
					<td align="left" class="prmt">&nbsp;<stripes:label
							name="entry.name" /></td>
					<td align="left">&nbsp;<stripes:text name="entry.name"
							class="text_field" size="50" />
					</td>
				</tr>
				<tr>
					<td align="left" class="prmt">&nbsp;<stripes:label
							name="entry.description" /></td>
					<td align="left">&nbsp;<stripes:text name="entry.description"
							class="text_field" size="50" />
					</td>
				</tr>
				<tr>
					<td align="left" class="prmt">&nbsp;<stripes:label
							name="entry.date.start" /></td>
					<td align="left">&nbsp;<stripes:text name="entry.startDate"
							id="entry.date.start" class="text_field" size="50" />
					</td>
				</tr>
				<tr>
					<td align="left" class="prmt">&nbsp;<stripes:label
							name="entry.date.end" /></td>
					<td align="left">&nbsp;<stripes:text name="entry.endDate"
							id="entry.date.end" class="text_field" size="50" />
					</td>
				</tr>
				<tr>
					<td align="left" class="prmt">&nbsp; <stripes:file
							name="attachment" />
					</td>
				</tr>
				<tr>
					<td align="left">&nbsp;&nbsp;<stripes:submit
							class="submit_btn" name="submit" value="Create entry" />
					</td>
				</tr>
			</table>
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>