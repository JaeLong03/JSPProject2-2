<%--
  Created by IntelliJ IDEA.
  User: jlee23
  Date: 25. 11. 15.
  Time: 오후 1:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import ="org.example.project22.dao.BookDAO" %>

<% request.setCharacterEncoding("utf-8"); %>

<%@page import="org.example.project22.dao.BookDAO, org.example.project22.bean.BookVO,java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="top.jsp" flush="true" />

<%
    String keyword = request.getParameter("search");

    if (keyword == null) {
        keyword = "";
    }

    BookDAO bookdao = new BookDAO();
    List<BookVO> list = bookdao.loadBook(keyword);
    request.setAttribute("list", list);
%>

<html>
<head>
    <title>도서 게시판 - 목록</title>
    <style>
        table {
            border-collapse: collapse;
            width: 700px;
        }
        th, td {
            border: 1px solid #777;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #eee;
        }
        .top-btn {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4 text-primary">📚 도서 목록</h2>

    <form method="get" action="list.jsp" class="mb-3">
        <div class="d-flex justify-content-between">
            <a href="write.jsp" class="btn btn-success me-3">➕ 새 도서 등록</a>

            <div class="input-group" style="width: 300px;">
                <%-- input의 name="search" 유지 --%>
                <input type="text" class="form-control" placeholder="도서 제목 검색..." name="search"
                       value="<%= keyword.equals("") ? "" : keyword %>">
                <button class="btn btn-outline-secondary" type="submit">검색</button>
                <a href="list.jsp" class="btn btn-outline-secondary" role="button">초기화</a>
            </div>
        </div>
    </form>

    <table class="table table-striped table-hover table-bordered">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>제목</th>
            <th>저자</th>
            <th>출판사</th>
            <th>출판날짜</th>
            <th>장르</th>
            <th>조회수</th>
            <th>관리</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items = "${list}" var = "u">
            <tr>
                <td>${u.id}</td>
                <td class="text-start">${u.title}</td>
                <td>${u.author}</td>
                <td>${u.publisher}</td>
                <td>${u.published_date}</td>
                <td>${u.genre}</td>
                <td>${u.cnt}</td>
                <td>
                    <a href="view.jsp?id=${u.id}" class="btn btn-sm btn-info me-1">상세</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>

<jsp:include page="bottom.jsp" flush="true" />