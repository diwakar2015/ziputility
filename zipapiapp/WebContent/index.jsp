<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<h3>Export as ZIP example</h3>



<form name ="downloadZip" method="post" action="ZipDownloadServlet">
Enter some content: <textarea name="content" rows="6" cols="50">Enter text here to export as ZIP file...</textarea>
<br></br>
<br></br>
<input type="submit" name="submit" value="Export this content as ZIP"/>

</form>
</center>
</body>
</html>