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

            <tbody>
                <c:forEach items="${notices}" var="list">
                    <tr>
                        <td>${list.ni_num}</td>
                        <td><a href="/noticeView?ni_num=${list.ni_num}">${list.ni_title}</a></td>
                        <td>${list.mi_name}</td>
                        <fmt:parseDate value="${list.ni_updatedt}" var="dateValue" pattern="yyyyMMddHHmmss"/>
                        <td><fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd"/></td>
                        <td>${list.ni_views}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <c:choose>
            <c:when test="${loginMember eq null}"></c:when>
            <c:otherwise>
                <div style="text-align: right">
                    <button type="button" class="btn btn-info" onclick="location.href='/noticeCreatePage'">글쓰기</button>
                </div>
            </c:otherwise>
        </c:choose>
    </main>


    <div style="text-align: center">
        <c:if test="${page.curRange ne 1 }">
            <a href="/notice?num=1">[처음]</a>
        </c:if>

        <c:if test="${page.curPage ne 1}">
            <a href="/notice?num=${page.prevPage }" >[이전]</a>
        </c:if>

        <c:forEach var="pageNum" begin="${page.startPage }" end="${page.endPage}">
            <c:choose>
                <c:when test="${pageNum eq  page.curPage}">
                    <span style="font-weight: bold;"><a href="/notice?num=${pageNum}">${pageNum}</a></span>
                </c:when>
                <c:otherwise>
                    <a href="/notice?num=${pageNum}">${pageNum }</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${page.curPage ne page.pageCnt}">
            <a href="/notice?num=${page.nextPage }">[다음]</a>
        </c:if>

        <c:if test="${page.curRange ne page.rangeCnt}">
            <a href="/notice?num=${page.pageCnt }">[끝]</a>
        </c:if>
    </div>

    <div>
        총 게시글 수 : ${page.listCnt } /    총 페이지 수 : ${page.pageCnt } / 현재 페이지 : ${page.curPage } / 현재 블럭 : ${page.curRange } / 총 블럭 수 : ${page.rangeCnt }
    </div>

</div>

<script type="text/javascript" src="lib.js"></script>
<script>


    function noticeView(data){

        $.ajax({
            url: "/noticeView", //Controller에서 인식할 주소
            type:'GET', //GET 방식으로 전달
            data:{ "ni_title" : data },

        });

    }

</script>