<%--
  Created by IntelliJ IDEA.
  User: lady
  Date: 08/02/23
  Time: 02:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Welcome to Library</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">

</head>
<body>
<h1>Welcome to Library</h1>
<nav aria-label="...">
    <ul class="pagination mt-2">
        <c:if test="${hasPrevious}">
            <li class="page-item">
                <a class="page-link" href="?page=${previous}">Previous</a>
            </li>
        </c:if>
        <c:set value="${currentPage}" var="cur"/>
        <c:forEach begin="0" end="${pageCount}" var="i">
            <li class="page-item ${cur == i ? "active":""}">
                <a class="page-link" href="?page=${i}">${i+1}</a>
            </li>
        </c:forEach>
        <c:if test="${hasNext}">
            <li class="page-item">
                <a class="page-link" href="?page=${next}">Next</a>
            </li>
        </c:if>
    </ul>
</nav>

<p>  </p>
<div>
    <label for="browser" style="background: #a3cfbb">Search Book:</label>
    <input list="browsers" name="browser" id="browser">

    <datalist id="browsers">
            <c:forEach items="${books}" var="book">
                <option value="${book.getTitle()}">
             </c:forEach>
    </datalist>
</div>
<p> </p>
<div class="d-grid gap-2 d-md-block">
    <button class="btn btn-success"data-bs-target="#exampleModalToggle" data-bs-toggle="modal">+ Add New Book</button>
</div>
<p> </p>
<div class="row">
<c:forEach items="${books}" var="book">
    <div class="card col-3 mx-3" style="width: 15rem; height: 30rex" >
        <img src="https://m.media-amazon.com/images/I/71+zS0Q30hS.jpg" class="card-img-top" alt="..." width="120" height="250">
        <div class="card-body">
            <h5 class="card-title">${book.getTitle()}</h5>
            <p class="card-text">${book.getDescription()}</p>
        </div>
        <ul class="list-group list-group-flush">
            <li class="list-group-item">Author: <h5>${book.getAuthor()}</h5></li>
                <li class="list-group-item">Cost: <h5>${book.getCost()}</h5></li>
                <li class="list-group-item">Published year: <h5>${book.getPublishedYear()}</h5></li>
            <li class="list-group-item">Category: <h5>${book.getCategoryId()}</h5></li>
            <li class="list-group-item"><fmt:formatDate type="date" value="${book.getCreatedAt()}" var="fd"/>
                    ${fd}</li>
        </ul>
        <button type="button" class="btn btn-outline-warning" data-bs-target="#updateStudentModal" data-bs-toggle="modal" onclick="update(${book.getId()})">Update</button>
        <a type="button" class="btn btn-outline-danger" href="/books/delete/${book.getId()}">Delete</a>

    </div>
</c:forEach>
</div>

<div class="modal fade" id="exampleModalToggle" aria-hidden="true" aria-labelledby="exampleModalToggleLabel"
     tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body">
                <form method="post" action="/books/add">
                    <div class="mb-3" >
                        <label for="title" class="form-label">Title</label>
                        <input type="text" class="form-control" id="title" name="title">
                    </div>
                    <div class="mb-3">
                        <label for="author" class="form-label">Author</label>
                        <input type="text" class="form-control" id="author" name="author">
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Description</label>
                        <input type="text" class="form-control" id="description" name="description">
                    </div>
                    <div class="mb-3">
                        <label for="cost" class="form-label">Cost</label>
                        <input type="text" class="form-control" id="cost" name="cost">
                    </div>
                    <div class="form-floating">
                        <select class="form-select" id="floatingSelect" name="categoryName" aria-label="Floating label select example">
                            <option selected>Select category</option>
                            <c:forEach items="${categories}" var="category">
                            <option value="${category.getName()}">
                                </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="publishedYear" class="form-label">Published Year</label>
                        <input type="number" class="form-control" id="publishedYear" name="publishedYear">
                    </div>
                    <button type="submit" class="btn btn-success">Save Book</button>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="updateStudentModal" aria-hidden="true" aria-labelledby="UpdateStudentModalLabel"
     tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="UpdateStudentModalLabel">Update Student</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form method="post" action="/books/update/">
                    <input type="hidden" id="u_id" name="id" />
                    <div class="mb-3">
                        <label for="u_firstName" class="form-label">Student First Name</label>
                        <input type="text" class="form-control" id="u_firstName" name="firstName">
                    </div>
                    <div class="mb-3">
                        <label for="u_lastName" class="form-label">Student Last Name</label>
                        <input type="text" class="form-control" id="u_lastName" name="lastName">
                    </div>

                    <div class="mb-3">
                        <label for="u_age" class="form-label">Student Age</label>
                        <input type="number" class="form-control" id="u_age" name="age">
                    </div>
                    <button type="submit" class="btn btn-success">Update Student</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="/resources/js/main.js"></script>
<script src="/resources/js/popper.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>
