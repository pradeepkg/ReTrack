<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="taglibs.jsp"%>
<stripes:layout-render name="${layout}" title="Edit Entry"
	objects="${pageContext.request.parameterMap}">
	<stripes:layout-component name="html-head">
		<link rel="stylesheet" type="text/css" href="resources/jsDatePick/jsDatePick_ltr.min.css" />
		<script src="resources/jsDatePick/jquery.1.4.2.js"></script>			
		<script type="text/javascript" src="resources/jsDatePick/jsDatePick.min.1.3.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {				
				$("#main_form").attr("disabled", true);
				$("#submit_btn").attr("disabled", true);
				$("#btn_undo").hide();
				$(".delete_button").hide();
				
				$("#btn_edit").click(function() {
					$("#main_form").attr("disabled", false);
					$("#submit_btn").attr("disabled", false);
					$(".list_button").hide();
					$(".delete_button").show();
					$("#btn_undo").show();
					$("#btn_edit").hide();
				});
				
				$("#btn_undo").click(function() {
					$("#main_form").attr("disabled", true);
					$("#submit_btn").attr("disabled", true);
					$(".list_button").show();
					$(".delete_button").hide();
					$("#btn_undo").hide();
					$("#btn_edit").show();
				});				
			});		
		
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
			beanclass="com.gullapu.savtrac.web.EditActionBean" >
			<stripes:hidden name="entry.id" />
			<table border="1" cellspacing="0" align="center" width="100%" id="main_form" >
				<tr>
					<td colspan="2">
                    	<img src="resources/screwdriver.png" alt="Edit" title="Edit" id="btn_edit"/>
                    	<img src="resources/undo.png" alt="Undo" title="Undo" id="btn_undo"/>
					</td>
				</tr>
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
							name="entry.documents" /></td>				
					<td align="left" class="prmt">&nbsp;					
						<c:forEach items="${actionBean.entry.documents}" var="document" varStatus="loop">
							<div>&nbsp;&nbsp;
							<img src="resources/list.png" class="list_button" />
							<stripes:image name="deleteAttachment" src="resources/trash_can.png" 
								class="delete_button" alt="Delete"/>
							<stripes:param name="documentID">${document.id}</stripes:param>
							&nbsp;&nbsp;${document.fileName}
							</div>
						</c:forEach>			
					
						<c:forEach var="i" begin="0" end="2">
							<div>&nbsp;&nbsp;<img src="resources/upload2.png" />&nbsp;&nbsp;
								<stripes:file name="attachments[${i}]" />
							</div>
						</c:forEach>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="left" colspan="2">&nbsp;&nbsp;<stripes:submit 
							class="submit_btn" name="editEntry" value="Finish" id="submit_btn"/>
					</td>
				</tr>
			</table>
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>