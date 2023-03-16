<%@page import="sample.DTO.BookDTO"%>
<%@page import="sample.DTO.AccountDTO"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width" , initial-scale="1" />
        <title>Manager Book</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"/>
        <link href="https://use.fontawesome.com/releases/v5.0.4/css/all.css" rel="stylesheet"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="CSS/style1.css" />
        <link rel="stylesheet" href="CSS/advanceSearch.css" />

        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />

    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <%
            AccountDTO accLog = (AccountDTO) session.getAttribute("LOGIN_ACCOUNT");
            if (accLog == null || accLog.getRoleID() != 2) {
                response.sendRedirect("javascript:history.back()");
                return;
            }
        %>
        <div class="main">
            <div class="row  contens ">
                <div class="col-md-2"></div>
                <div class="col-md-8 content">
                    <div class="content table-container">
                        <div>
                            <div class="table-title">
                                <div class="row">
                                    <div class="col-sm-10">
                                        <h2>Manage <b>Book</b></h2>
                                    </div>
                                    <div class="col-sm-2">
                                        <a href="addBook.jsp"  class="btn btn-warning btn-sm" ><span>+ New Book</span></a>
                                    </div>
                                </div>
                            </div>
                            <%
                                List<BookDTO> list = (List<BookDTO>) request.getAttribute("listBook");
                                if (list != null) {
                                    if (!list.isEmpty()) {
                            %>
                            <table class="tableStyle book">
                                <thead>
                                    <tr>
                                        <th>Image</th>
                                        <th>Book</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        for (BookDTO book : list) {
                                    %>
                                    <tr class="tb">
                                        <td class="tb"><a href="MainController?action=Detail&bookID=<%=book.getBookID()%>"><img src="<%= book.getImage()%>" /></a></td>
                                        <td class="tb">
                                            <div class="noBorder">
                                                <table>
                                                    <tr>
                                                        <td>Name:</td>
                                                        <td>
                                                            <a href="MainController?action=Detail&bookID=<%=book.getBookID()%>">
                                                                <%= book.getBookName()%>
                                                            </a>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Author:</td>
                                                        <td>
                                                            <%= book.getAuthor()%>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Publisher:</td>
                                                        <td>
                                                            <%= book.getPublisher()%>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Publish Year:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                        <td><%= book.getPublishYear()%></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Language:</td>
                                                        <td><%= book.getLanguage()%></td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </td>
                                        <td>
                                            <a href="LoadBookController?bookID=<%=book.getBookID()%>"  class="edit" ><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                            <a onclick="delbook('<%=book.getBookID()%>')" class="delete" href="#"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                        </td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                </tbody>
                            </table>
                            <%
                                    }
                                }
                            %>
                        </div>
                        <ul class="pagination" id="pagination">
                            <li class="page-item first disabled"><a href="#" class="page-link">&lt;&lt;&lt;</a></li>
                            <li class="page-item prev disabled"><a href="#" class="page-link">&lt;-</a></li>
                            <li class="page-item active"><a href="#" class="page-link">1</a></li>
                            <li class="page-item next disabled"><a href="#" class="page-link">-&gt;</a></li>
                            <li class="page-item last disabled"><a href="#" class="page-link">&gt;&gt;&gt;</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-2"></div>
            </div>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
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
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script>
                                                function delbook(id) {
                                                    Swal.fire({
                                                        title: 'Are you sure?',
                                                        text: "You want to delete this book.",
                                                        icon: 'question',
                                                        showCancelButton: true,
                                                        confirmButtonColor: '#F5D98F',
                                                        cancelButtonColor: '#F7E5D7',
                                                        confirmButtonText: 'Yes, delete it!'
                                                    }).then((result) => {
                                                        if (result.isConfirmed) {
                                                            location.href = "DeleteBookController?bookID=" + id;
                                                        }
                                                    })
                                                }
        </script>
    </body>
</html>
