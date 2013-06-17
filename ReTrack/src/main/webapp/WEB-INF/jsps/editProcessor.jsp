<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="taglibs.jsp"%>
<stripes:layout-render name="${layout}" title="Edit Processor Information"
	objects="${pageContext.request.parameterMap}">
	<stripes:layout-component name="contents">
		<stripes:form
			beanclass="com.gullapu.savtrac.web.EditActionBean"
			focus="entry.processor.select">
			<stripes:hidden name="entryID" />			
			<stripes:hidden name="processorID" id="processorID" />
			
			<table border="1" cellspacing="0" align="center" width="100%" id="processor" >
				<tr>
					<td align="left" class="prmt">&nbsp;<stripes:label
							name="entry.processor.name" /></td>
					<td align="left">&nbsp;<stripes:text
							name="entry.processor.name" class="text_field" size="20" />
					</td>
				</tr>
				<tr>
					<td align="left" class="prmt">&nbsp;<stripes:label
							name="entry.processor.homePage" /></td>
					<td align="left">&nbsp;<stripes:text
							name="entry.processor.homePage" class="text_field" size="50" />
					</td>
				</tr>
				<tr>
					<td align="left" class="prmt">&nbsp;<stripes:label
							name="entry.processor.email" /></td>
					<td align="left">&nbsp;<stripes:text
							name="entry.processor.email" class="text_field" size="20" />
					</td>
				</tr>
				<tr>
					<td align="left" class="prmt">&nbsp;<stripes:label
							name="entry.processor.phone" /></td>
					<td align="left">&nbsp;<stripes:text
							name="entry.processor.phone" class="text_field" size="13"
							onkeydown="javascript:backspacerDOWN(this,event);"
							onkeyup="javascript:backspacerUP(this,event);" />
					</td>
				</tr>
				<tr>
					<td align="left" class="prmt" colspan="2" >&nbsp;<stripes:label
							name="entry.processor.address" /></td>
				</tr>	
				<tr>
					<td align="left" class="prmt">&nbsp;<stripes:label
							name="entry.processor.address.line1" /></td>
					<td align="left">&nbsp;<stripes:text
							name="entry.processor.address.line1" class="text_field" size="20" />
					</td>
				</tr>	
				<tr>
					<td align="left" class="prmt">&nbsp;<stripes:label
							name="entry.processor.address.line2" /></td>
					<td align="left">&nbsp;<stripes:text
							name="entry.processor.address.line2" class="text_field" size="20" />
					</td>
				</tr>	
				<tr>
					<td align="left" class="prmt">&nbsp;<stripes:label
							name="entry.processor.address.poBox" /></td>
					<td align="left">&nbsp;<stripes:text
							name="entry.processor.address.poBox" class="text_field" size="20" />
					</td>
				</tr>				
				<tr>
					<td align="left" class="prmt">&nbsp;<stripes:label
							name="entry.processor.address.city" /></td>
					<td align="left">&nbsp;<stripes:text
							name="entry.processor.address.city" class="text_field" size="20" />
					</td>
				</tr>	
				<tr>
					<td align="left" class="prmt">&nbsp;<stripes:label
							name="entry.processor.address.state" /></td>
					<td align="left">&nbsp;<stripes:text
							name="entry.processor.address.state" class="text_field" size="20" />
					</td>
				</tr>	
				<tr>
					<td align="left" class="prmt">&nbsp;<stripes:label
							name="entry.processor.address.zipCode" /></td>
					<td align="left">&nbsp;<stripes:text
							name="entry.processor.address.zipCode" class="text_field" size="10" />
					</td>
				</tr>	
			</table>
			<table border="1" cellspacing="0" align="center" width="100%">
				<tr>
					<td align="left" colspan="2">&nbsp;&nbsp;<stripes:submit
							class="submit_btn" name="editProcessor" value="Finish" />
					</td>
				</tr>
			</table>
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>