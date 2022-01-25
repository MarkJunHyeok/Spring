<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
    <main class="form-signin">
        <h1 class="h3 mb-3 fw-normal">Notice</h1>
        <form class="d-flex" style="text-align: right">
            <select id="selectSearch" >
                <option value="all">전체 검색</option>
                <option value="title">제목 검색</option>
                <option value="text">내용 검색</option>
                <option value="writer">작성자 검색</option>
            </select>
            <input class="form-control me-2" type="search" name="inputSearch" id="inputSearch" placeholder="Search" aria-label="Search">
            <a class="btn btn-outline-success" type="button" onclick="Search()" >Search</a>
        </form>
        <table class="table table-striped">


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

            <tbody id="noticeList">
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


    <div style="text-align: center" id="noticePage">
    </div>


</div>


<script>
    $(document).ready(function (){
        var data = {};
        data.curPage = 1;
        noticeLoad(data)
    });


    function Search(){
        var data = {};
        data.selectSearch = $("#selectSearch").val();
        data.inputSearch = $("#inputSearch").val();
        data.curPage = 1;
        noticeLoad(data);
    }

    function goPage(page, select, input){
        var data = {};
        data.curPage = page;
        data.selectSearch = select;
        data.inputSearch = input;
        noticeLoad(data)
    }

    function noticeLoad(data){
        data.pageSize = 10;
        data.rangeSize = 10;
        $.ajax({
            url: "/notice/load",
            type : 'POST',
            data : data,

            error : function(error) {
                alert("Error!");
            },
            success : function(value) {
                $("#noticeList").children().remove();

                var html ='';
                for(var i = 0 ; i < value.list.length ; i++){
                    var list = value.list[i];
                    html += '<tr>';
                    html +=  '<td>' + list.ni_num + '</td>';
                    html +=  '<td><a href="/noticeView?ni_num=' + list.ni_num + '">' + list.ni_title + '</a></td>';
                    html +=  '<td>' + list.mi_name + '</td>';
                    html +=  '<td>' + list.ni_updatedt + '</td>';
                    html +=  '<td>' + list.ni_views + '</td></tr>';
                }
                $("#noticeList").append(html);
                $("#noticePage").paging({
                    inputSearch : data.inputSearch,
                    selectSearch : data.selectSearch,

                    curPage : data.curPage,
                    curRange : value.param.curRange,
                    prevPage : value.param.prevPage,
                    nextPage : value.param.nextPage,
                    startPage : value.param.startPage,
                    endPage : value.param.endPage,
                    rangeCnt : value.param.rangeCnt,
                    pageCnt : value.param.pageCnt

                })
            }

        });
    }


    function noticeView(data){

        $.ajax({
            url: "/noticeView", //Controller에서 인식할 주소
            type:'GET', //GET 방식으로 전달
            data:{ "ni_title" : data },

        });

    }

</script>