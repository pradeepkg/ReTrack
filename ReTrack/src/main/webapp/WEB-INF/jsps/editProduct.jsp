<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="taglibs.jsp"%>
<stripes:layout-render name="${layout}" title="Edit Product Information"
	objects="${pageContext.request.parameterMap}">
	<stripes:layout-component name="contents">
		<stripes:form
			beanclass="com.gullapu.savtrac.web.EditActionBean"
			focus="entry.product.name">
			<stripes:hidden name="entryID" />
			<table border="1" cellspacing="0" align="center" width="100%">
				<tr>
					<td align="left" class="prmt">&nbsp;<stripes:label
							name="entry.product.name" /></td>
					<td align="left">&nbsp;<stripes:text name="entry.product.name"
							class="text_field" size="50" />
					</td>
				</tr>
				<tr>
					<td align="left" class="prmt">&nbsp;<stripes:label
							name="entry.product.upc" /></td>
					<td align="left">&nbsp;<stripes:text name="entry.product.upc"
							class="text_field" size="50" />
					</td>
				</tr>	
				<tr>
					<td align="left" class="prmt">&nbsp;<stripes:label
							name="entry.product.price" /></td>
					<td align="left">&nbsp;<stripes:text name="entry.product.price"
							class="text_field" size="50" />
					</td>
				</tr>							
				<tr>
					<td align="left" colspan="2">&nbsp;&nbsp;<stripes:submit
							class="submit_btn" name="editProduct" value="Continue ..." />
					</td>
				</tr>
			</table>
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>