<%-- 
    Document   : bookingOffline
    Created on : Aug 1, 2022, 1:51:20 AM
    Author     : NhatTan
--%>

<%@page import="sample.DTO.PackageDTO"%>
<%@page import="sample.DTO.BookDTO"%>
<%@page import="sample.DTO.AccountDTO"%>
<%@page import="sample.DTO.BookError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width" , initial-scale="1" />
        <title>Staff page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" />
        <link href="https://use.fontawesome.com/releases/v5.0.4/css/all.css" rel="stylesheet" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="CSS/style1.css" />
        <link rel="stylesheet" href="CSS/login.css" />

        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet" />
    </head>

    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <style>
                body {
                    background-image: url('./image/background.jpg');
                    background-repeat: no-repeat;
                    background-size: cover;
                    background-attachment: fixed;
                    background-size: 100% 100%;
                }
            </style>
        <%
            AccountDTO acc = (AccountDTO) session.getAttribute("LOGIN_ACCOUNT");
            if (acc == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            if (acc.getRoleID() != 2) {
                response.sendRedirect("javascript:history.back()");
                return;
            }
        %>
        <div class="container">
            <div class="row">
                <div class="col-md-5 mx-auto">
                    <div id="first">
                        <div class="myform form ">
                            <div class="logo mb-3">
                                <div class="col-md-12 text-center">
                                    <h3>Borrow book</h3>
                                </div>
                            </div>
                            <%
                                BookDTO book = (BookDTO) request.getAttribute("book");
                                AccountDTO student = (AccountDTO) request.getAttribute("student");
                                String userID = (String) request.getAttribute("userID");
                                String bookID = (String) request.getAttribute("bookID");
                                if (userID == null) {
                                    userID = "";
                                }
                                if (bookID == null) {
                                    bookID = "";
                                }

                                if (student != null && book != null) {
                            %>
                                <form action="BookingOfflineController">
                            <%
                                } else {
                            %>
                                <form action="LoadBookingOffController">
                            <%
                                }
                            %>
                                    <div class="form-group">
                                        <label>Student ID:</label>
                                        <input name="userID" type="text" placeholder="Enter Student ID"  class="form-control" value="<%=userID%>" <%=student != null ? "readonly" : ""%>>
                                    </div>
                                    <%
                                        String userIdError = (String) request.getAttribute("userIdError");
                                        if (userIdError == null) {
                                            userIdError = "";
                                        }
                                        out.print("<p style='color: #dc3545;'>" + userIdError + "</p>");
                                    %>
                                    <div class="form-group">
                                        <label>Book ID</label>
                                        <input name="bookID" type="text" placeholder="Enter Book ID"  class="form-control" required="" value="<%=bookID%>" <%=book != null ? "readonly" : ""%>>
                                    </div>
                                    <%
                                        String bookIdError = (String) request.getAttribute("bookIdError");
                                        if (bookIdError == null) {
                                            bookIdError = "";
                                        }
                                        out.print("<p style='color: #dc3545;'>" + bookIdError + "</p>");
                                    %>
                                    <div class="col-md-12 text-center mb-3">
                                        <button class=" btn btn-block mybtn btn-warning tx-tfm" type="submit"><%=(student != null && book != null) ? "Borrow" : "Check"%></button>
                                    </div>
                                </form>
                        </div>
                    </div>
                </div>
                <%
                    if (student != null || book != null) {
                %>
                <div class="content mx-auto" style="border-radius: 10px; padding: 10px;">
                    <%
                        if (student != null) {
                    %>
                    <div class="container-fluid">
                        <table width="100%">
                            <dl>
                                <strong>Student ID: </strong>&emsp;&emsp;<%= student.getAccountID()%>
                            </dl>
                            <dl>
                                <strong>Full Name: </strong>&emsp;&emsp;<%= student.getFullName()%>
                            </dl>
                            <dl>
                                <strong>Mail: </strong>&emsp;&emsp;<%=student.getEmail()%>
                            </dl>
                        </table>
                    </div>
                    <%
                        }
                        if (book != null) {
                    %>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="bookDetailImage text-center container-fluid">
                                <img style="width: 80%;height: 90%;" src="<%= book.getImage()%>"/>
                            </div>
                        </div>
                        <div class="col-md-8 container-fluid">
                            <h5><strong><%= book.getBookName()%></strong></h5>
                            <table width="100%">
                                <dl>
                                    <dt><strong>&emsp;&emsp;Authors </strong></dt>
                                    <dd>&emsp;&emsp;<%= book.getAuthor()%></dd>
                                </dl>
                                <dl>
                                    <dt><strong>&emsp;&emsp;Publisher </strong></dt>
                                    <dd>&emsp;&emsp;<%= book.getPublisher()%></dd>
                                </dl>
                                <dl>
                                    <dt><strong>&emsp;&emsp;Publication Year </strong></dt>
                                    <dd>&emsp;&emsp;<%= book.getPublishYear()%></dd>
                                </dl>
                                <dl>
                                    <dt><strong>&emsp;&emsp;Language </strong></dt>
                                    <dd>&emsp;&emsp;<%= book.getLanguage()%></dd>
                                </dl>
                                <dl>
                                    <dt><strong>&emsp;&emsp;DDC </strong></dt>
                                    <dd>&emsp;&emsp;<%= book.getDDC()%></dd>
                                </dl>
                            </table>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>
                <%
                    }
                %>
            </div>
        </div>
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {
        %>
        <script>
            Swal.fire({
                title: 'Success!',
                text: '<%=message%> successful.',
                confirmButtonColor: '#F5D98F',
                timer: 2000,
                timerProgressBar: true,
                icon: 'success'
            });
        </script>
        <%
            }
        %>
        <style>
            .container{
                padding-top: 5rem;
                padding-top: 6rem;
            }
            footer{
                margin: 0 10px;
            }
            footer .container{
                padding-top: 0;
            }
        </style>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>

