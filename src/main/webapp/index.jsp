<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-12">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" href="/homes">Trang chủ</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="customers?act=create"> Thêm customer <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="orders?act=create">Thêm orders</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-expanded="false">
                                Sửa/Xóa
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="/customers?act=edit">Sửa customer</a>
                                <a class="dropdown-item" href="#">Another action</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#">Something else here</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link disabled">Disabled</a>
                        </li>
                    </ul>
                    <form action="/homes" class="form-inline my-2 my-lg-0">
                        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="findName">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                    </form>
                </div>
            </nav>
        </div>
    </div>

</div>

<div class="row">
    <div class="col-3">
        danh sach customer
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">NAME</th>
                <th scope="col">AGE</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${customers}" var="cs">
                <tr>
                    <td>${cs.id}</td>
                    <td>${cs.name}</td>
                    <td>${cs.age}</td>
                    <td>
                        <a class="btn btn-info">View</a>
                        <a href="/customers?act=edit&id=${cs.id}" class="btn btn-primary">Sửa</a>
                        <form action="/customers" method="post" id="delete${cs.id}" style="display: inline">
                            <input type="hidden" name="act" value="delete">
                            <input type="hidden" name="id" value="${cs.id}">
                            <a class="btn btn-danger" onclick="xacNhanDelete(${cs.id})">Xoá</a>
                        </form>
                    </td>
                    <script>
                        function xacNhanDelete(id) {
                            if (confirm("Bạn có chắc muốn xoá?")) {
                                document.getElementById("delete"+id).submit();
                            }
                        }
                    </script>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col-9">
        danh sach order
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">TIME</th>
                <th scope="col">TOTAL</th>
                <th scope="col">CustomerId</th>
                <th scope="col">CustomerName</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orders}" var="od">
                <tr>
                    <td>${od.id}</td>
                    <td>${od.time}</td>
                    <td>${od.total}</td>
                    <td>${od.customerd.id}</td>
                    <td>${od.customerd.name}</td>
                    <td>
                        <a class="btn btn-primary">Sửa</a>
                        <a class="btn btn-danger">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>