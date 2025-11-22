<%--
  Created by IntelliJ IDEA.
  User: jlee23
  Date: 25. 11. 20.
  Time: 오후 8:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import ="org.example.project22.dao.BookDAO" %>

<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id = "u" class = "org.example.project22.bean.BookVO"/>
<jsp:setProperty name="u" property="*"/>

<%
    BookDAO bookdao = new BookDAO();
    int i = bookdao.insertBook(u);
    String msg = "데이터 추가 성공!";
    if(i != 1) msg = "데이터 추가 실패 (에러)";
%>

<script>
    alert('<%=msg%>');
    location.href = 'list.jsp';
</script>

