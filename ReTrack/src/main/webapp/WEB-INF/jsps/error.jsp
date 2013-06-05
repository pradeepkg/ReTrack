<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="taglibs.jsp"%>
<stripes:layout-render name="${layout}" title="${title}">
	<stripes:layout-component name="htmlhead">
		<META HTTP-EQUIV="Refresh" CONTENT="30;URL=${param.redirect}">
	</stripes:layout-component>

	<stripes:layout-component name="title">
	${pagetitle}
	</stripes:layout-component>
	<stripes:layout-component name="contents">
		<table border="0" width="100%" border="0" cellspacing="0"
			align="center">
			<tr class="heading">
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>

		</table>

	</stripes:layout-component>
</stripes:layout-render>