<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
    <main class="form-signin">
        <form action="/loginGo" method="post">
            <h1 class="h3 mb-3 fw-normal">회원 로그인</h1>

            <div class="form-floating">
                <input type="text" class="form-control" id="floatingInput" name="mi_id" value="${cookie.mi_id.value}">
                <label for="floatingInput">ID</label>
            </div>
            <div class="form-floating">
                <input type="password" class="form-control" id="floatingPassword" name="mi_pw" value="${cookie.mi_pw.value}">
                <label for="floatingPassword">Password</label>
            </div>
            <c:choose>
                <c:when test="${cookie.mi_id.value == null}">
                    <input type="checkbox" name="idSave">아이디/비밀번호 저장
                </c:when>
                <c:otherwise>
                    <input type="checkbox" name="idSave" checked>아이디/비밀번호 저장
                </c:otherwise>
            </c:choose>


            <button class="w-100 btn btn-lg btn-primary" type="submit">로그인</button>
            <a class="w-100 btn btn-lg btn-primary" href="/joinPage">회원가입</a>
            <p class="mt-5 mb-3 text-muted">&copy; 쩌로님의-수강시대</p>
        </form>
    </main>
</div>>