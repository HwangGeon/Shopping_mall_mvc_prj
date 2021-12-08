<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String protocol=application.getInitParameter("protocol");
String serverName=application.getInitParameter("serverName");
String serverPort=application.getInitParameter("serverPort");
String contextRoot=application.getInitParameter("contextRoot");
String commonCssPath=application.getInitParameter("commonCss");

String configUrl=protocol+serverName+serverPort+"/"+contextRoot;
pageContext.setAttribute("configUrl",configUrl);
pageContext.setAttribute("commonCssPath",commonCssPath);

%>
