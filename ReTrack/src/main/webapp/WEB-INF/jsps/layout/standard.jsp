<%@ include file="../taglibs.jsp"%>

<c:set var="req" value="${pageContext.request}" />
<c:set var="scheme" value="${req.scheme}" />
<c:set var="server" value="${req.serverName}" />
<c:set var="port" value="${req.serverPort}" />
<c:set var="baseUrl" value="${scheme}://${server}:${port}" />

<stripes:layout-definition>
	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
	<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="-1">
<title>ReTrack- ${title}</title>
<style type="text/css">
body {
	margin: 5px;
	background-color: #ffffff;
	font-family: tahoma, verdana, geneva, arial, lucida, sans-serif;
	font-size: 11px;
	color: #000000;
	overflow: hidden;
}

img {
	border: none;
}

.body_sm {
	font-family: tahoma, verdana, geneva, arial, lucida, sans-serif;
	font-size: 10px;
	color: #003896
}

.rule {
	background-color: #6699CC
}

.hdline {
	font-family: tahoma, verdana, geneva, arial, lucida, sans-serif;
	font-size: 12px;
	color: red;
	font-weight: bold
}

.hdline_error {
	font-family: tahoma, verdana, geneva, arial, lucida, sans-serif;
	font-size: 12px;
	color: #003896;
	font-weight: bold
}

form input {
	margin: none;
	padding: none;
}

input.submit_btn {
	color: navy;
	font: bold 84% tahoma, verdana, geneva, arial, lucida, sans-serif;
}

input.text_field {
	color: navy;
	font: bold 84% tahoma, verdana, geneva, arial, lucida, sans-serif;
	background-color: #eeeeee;
	margin: 5px;
}

input.submit {
	background-color: #ffffff;
	font-family: tahoma, verdana, geneva, arial, lucida, sans-serif;
	font-size: 11px;
	font-weight: bold;
	color: #003896;
	border: none;
	cursor: pointer;
}

select {
	border-right: thin dotted;
	border-top: thin dotted;
	border-left: thin dotted;
	border-bottom: thin dotted;
	background-color: #f0f0f0;
	font-family: tahoma, verdana, geneva, arial, lucida, sans-serif;
	font-size: 11px;
	margin: 5px;
}

#custom {
	font-family: tahoma, verdana, geneva, arial, lucida, sans-serif;
	font-size: 11px;
	color: navy;
	text-align: center;
	border-collapse: collapse;
	border-right: 1px dotted #c3c3c3;
	border-left: 1px dotted #c3c3c3;
}

#custom td,#custom th {
	font-size: 1em;
}

#custom th {
	font-size: 1.1em;
	text-align: left;
	padding-top: 5px;
	padding-bottom: 4px;
	color: #ffffff;
}

#custom tr.footer td {
	color: #AE0000;
	font-size: 9px;
}

tr.heading td {
	background-color: #003896;
	color: white;
	font-size: 8px;
	height: 1px;
	font-weight: bold;
	padding: 1px;
	text-align: center;
}

.headline {
	color: #003896;
	font-size: 14px;
	height: 1px;
	font-weight: bold;
	padding: 1px;
	text-align: left;
}
</style>
<stripes:layout-component name="html-head" />
</head>
<body>
	<BR>
	<table width="80%" border="0" cellpadding="0" align="center"
		id="custom">
		<tbody>
			<tr align="left">
				<td height="47" valign="middle">
				<stripes:link href="/">
					<img src="resources/thunder.png" />
				</stripes:link>
				<h1>&nbsp;&nbsp;&nbsp;ReTrack</h1>
				</td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr align="center">
				<td width="100%" align="center">
					<table border="0" cellpadding="0" width="100%" align="center">
						<tr>
							<td width="100%" align="center"><stripes:messages /> <stripes:errors />
							</td>
						</tr>
						<tr>
							<td width="100%" colspan="2">
								<table border="0" cellpadding="0" width="100%">
									<tr>
										<td><stripes:layout-component name="contents" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" cellpadding="0">
						<tr class="footer">
							<td valign="middle">Copyright &copy; 2012-2013 Pradeep Kadambar. All rights reserved.</td>
						</tr>
					</table></td>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</tbody>
	</table>

</body>
	</html>
</stripes:layout-definition>
