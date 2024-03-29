<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="/resources/js/plugin.page.js"></script>
    <title>fsdfs</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <style>
      .container{
        margin-top:150px;
      }
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
  </head>

  <body>
    <header>
      <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <div class="container-fluid">
          <a class="navbar-brand" href="/">Carousel</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="/notice">Notice</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/gallery">Gallery</a>
              </li>
            </ul>
            <form class="d-flex">
              <c:choose>
                <c:when test="${loginMember ne null}">
                  <a class="btn btn-outline-success" href="/joinPage" type="button"> ${loginMember.mi_name} 님</a>
                  <a class="btn btn-outline-success" href="/logout" type="button">LogOut</a>
                </c:when>
                <c:otherwise>
                  <a class="btn btn-outline-success" href="/loginPage" type="button">Login</a>
                </c:otherwise>
              </c:choose>
              <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
              <button class="btn btn-outline-success" type="submit">Search</button>
            </form>


          </div>
        </div>
      </nav>
  </header>


    <jsp:include page="${content }" />

    <script>
        let msg = "${MSG}";
        if(msg != ''){
          alert(msg)
        }
    </script>

  </body>

</html>
