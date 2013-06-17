<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="taglibs.jsp"%>
<stripes:layout-render name="${layout}" title="Create entry"
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
			beanclass="com.gullapu.savtrac.web.CreateActionBean"
			focus="entry.name">
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
					<td align="left" class="prmt">&nbsp;<stripes:label
							name="entry.product.link" /></td>
					<td align="left">&nbsp;<stripes:text name="entry.productLink"
							id="entry.product.link" class="text_field" size="50" />
					</td>
				</tr>	
				<tr>
					<td align="left" class="prmt">&nbsp;<stripes:label
							name="entry.rebate.link" /></td>
					<td align="left">&nbsp;<stripes:text name="entry.rebateLink"
							id="entry.rebate.link" class="text_field" size="50" />
					</td>
				</tr>												
				<tr>
					<td align="left" class="prmt">&nbsp;<stripes:label
							name="entry.documents" /></td>				
					<td align="left" class="prmt">&nbsp;
						<c:forEach var="i" begin="0" end="4">
							<div>&nbsp;&nbsp;<img src="resources/upload2.png" />
								<stripes:file name="attachments[${i}]" />
							</div>
						</c:forEach>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" colspan="2">&nbsp;&nbsp;<stripes:submit
							class="submit_btn" name="createEntry" value="Continue ..." />
					</td>
				</tr>
			</table>
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>