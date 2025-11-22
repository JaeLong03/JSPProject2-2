<%--
  Created by IntelliJ IDEA.
  User: jlee23
  Date: 25. 11. 21.
  Time: 오전 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import ="org.example.project22.dao.BookDAO" %>
<%
    int s = Integer.parseInt(request.getParameter("id"));
    BookDAO bookdao = new BookDAO();
    int i = bookdao.deleteBook(s);
    String msg = "데이터 삭제 성공!";
    if(i != 1) msg = "데이터 삭제 실패 (에러)";
%>

<script>
    alert('<%=msg%>');
    location.href = 'list.jsp';
</script>