<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container">
    <main class="form-signin">
        <h1 class="h3 mb-3 fw-normal">Notice</h1>

        <table class ="table table-striped">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>날짜</th>
                    <th>조회수</th>
                </tr>
            </thead>
        </table>
        <tbody>
        <c:forEach items="${list}" var="list">
            <tr>
                <td>${list.seq}</td>
                <td>${list.subject}</td>
                <td>${list.content}</td>
                <td>${list.name}</td>
                <fmt:parseDate value="${list.reg_date}" var="dateValue" pattern="yyyyMMddHHmmss"/>
                <td><fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd"/></td>
                <td>${list.readCount}</td>
            </tr>
        </c:forEach>
        </tbody>
        </table>
        <button type="button" onclick="location.href='/noticeText'">글쓰기</button>
    </main>
</div>