<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="Refresh" content="0; url=${pageContext.request.contextPath}/index.html" />
		<title>Spring Food</title>

		<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon.ico">
	</head>
	<body>
		<p>Initializing...</p>

		<!-- Manually inserting tokens -->
		<input type="hidden"
			   name="${_csrf.parameterName}"
			   value="${_csrf.token}" />
	</body>
</html>