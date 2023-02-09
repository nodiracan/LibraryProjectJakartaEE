<%--
  Created by IntelliJ IDEA.
  User: lady
  Date: 08/02/23
  Time: 02:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
</head>
<body>
<div class="row">
    <div class="col-md-10 offset-1">
        <h1 class="text-center">Book Create Page</h1>
        <form method="post">
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
            <div class="mb-3">
                <label for="categoryId" class="form-label">Category</label>
                <input type="text" class="form-controlId" id="categoryId" name="categoryId">
            </div>
            <div class="mb-3">
                <label for="publishedYear" class="form-label">Published Year</label>
                <input type="number" class="form-control" id="publishedYear" name="publishedYear">
            </div>
            <button type="submit" class="btn btn-primary">Save Book</button>
        </form>
    </div>
</div>

<script src="/resources/js/popper.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>
