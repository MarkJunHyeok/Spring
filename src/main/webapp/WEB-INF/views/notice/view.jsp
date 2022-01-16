<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container">
    <main class="form-signin">
        <form action="/noticeCreate" method="post">
            <h3 class="d-flex justify-content-between align-items-center mb-3">
                <span class="text-primary">글 보기</span>
            </h3>

            <div>
                <h6 style="text-align: left">
                    <span class="text-muted">작성자 : ${noticeView.mi_name}</span>
                </h6>
                <h6 style="text-align: right">
                    <span class="text-muted">조회수 : ${noticeView.ni_views}</span><br>
                    <fmt:parseDate value="${noticeView.ni_updatedt}" var="dateValue" pattern="yyyyMMddHHmmss"/>
                    <span class="text-muted">최종 수정일 : <fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd / HH:mm"/></span>
                </h6>
            </div>

            <div class="form-floating">
                <h4 class="d-flex justify-content-between align-items-center mb-3">
                    <span class="text-primary">제목</span>
                </h4>
                <input type="text" style="height: 20px" class="form-control" id="ni_title" name="ni_title" value="${noticeView.ni_title}" readonly>
            </div>

            <br/>

            <h4 class="d-flex justify-content-between align-items-center mb-3">
                <span class="text-primary">내용</span>
            </h4>
            <div class="form-floating">
                <textarea type="password" style="height: 200px" class="form-control" id="ni_text" name="ni_text" readonly>${noticeView.ni_text}</textarea>
            </div>

            <c:choose>
                <c:when test="${loginMember.mi_name eq noticeView.mi_name}">
                    <a class="w-100 btn btn-lg btn-primary" href="/noticeUpdatePage">게시글 수정</a>
                    <a class="w-100 btn btn-lg btn-primary" href="/noticeDelete?ni_num=${noticeView.ni_num}">게시글 삭제</a>
                </c:when>
            </c:choose>
            <p class="mt-5 mb-3 text-muted">&copy; 게시판의-수난시대</p>
        </form>
    </main>
</div>>