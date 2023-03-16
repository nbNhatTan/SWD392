<%-- 
    Document   : Detail
    Created on : Jun 14, 2022, 11:09:52 AM
    Author     : NhatTan
--%>

<%@page import="sample.DTO.BookDTO"%>
<%@page import="sample.DTO.BorrowDTO"%>
<%@page import="java.util.List"%>
<%@page import="sample.DTO.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width" , initial-scale="1" />
        <title>Detail Borrow</title>
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" />
        <link href="https://use.fontawesome.com/releases/v5.0.4/css/all.css"
              rel="stylesheet" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="CSS/style1.css" />

        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />

    </head>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <%
        AccountDTO acc = (AccountDTO) session.getAttribute("LOGIN_ACCOUNT");
        if (acc != null && acc.getRoleID() == 1) {
            response.sendRedirect("javascript:history.back()");
            return;
        }
    %>
        <div class="main">
            <div class="row news">
                <div class="col-md-1"></div>
                <div class="col-md-10 contents">
                    <div class="content">
                    <%
                        BorrowDTO borrow = (BorrowDTO) request.getAttribute("DETAIL_BORROW");
                        if (borrow != null) {
                    %>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="bookDetailImage text-center container-fluid">
                                <img src="<%= borrow.getImage()%>" width="280" height="400"/>
                            </div>
                        </div>
                        <div class="col-md-6 container-fluid">
                            <h5><%= borrow.getBookName()%></h5>
                            <table width="100%">
                                <tr>
                                    <td><h6>ID: </h6></td>
                                    <td><%= borrow.getBookingTicketID()%></td>
                                </tr>
                                <tr>
                                    <td><h6>BookItemID: </h6></td>
                                    <td><%= borrow.getBookItemID()%></td>
                                </tr>
                                <tr>
                                    <td><h6>Status: </h6></td>
                                    <td><span style="color: <%=borrow.getBorrowStatus().equals("Pending")?"#ffa500":
                                                               borrow.getBorrowStatus().equals("Borrowing")?"#00b050":
                                                               borrow.getBorrowStatus().equals("Returned")?"#0008ff":
                                                               borrow.getBorrowStatus().equals("Expired")?"#f00":"#ffa500"%>">[<%= borrow.getBorrowStatus()%>]</span> </td>
                                </tr>
                                <tr>
                                    <td><h6>Borrow Date: </h6></td>
                                    <td><%= borrow.getBorrowDate()%></td>
                                </tr>
                                <tr>
                                    <td><h6>Expired Date: </h6></td>
                                    <td><%= borrow.getExpiredDate()%></td>
                                </tr>
                                <tr>
                                    <td><h6>Return Date: </h6></td>
                                    <td><%= borrow.getReturnDate() != null ? borrow.getReturnDate() : ""%></td>
                                </tr>
                                <tr>
                                    <td><h6>Borrower: </h6></td>
                                    <td><%= borrow.getUserID().getFullName()%></td>
                                </tr>
                                <tr>
                                    <td><h6>Ticket manager: </h6></td>
                                    <td><%= borrow.getStaffID().getFullName() != null ? borrow.getStaffID().getFullName() : ""%></td>
                                </tr>
                                <tr>
                                    <td><br></td>
                                </tr>
                            </table>
                                <br>
                            <button onclick="history.back()" type="button" class="btn btn-dark btn-sm">Back to List</button>
                        </div>
                    </div>
                                <%
                                    }
                                %>
                </div>


            </div>
            <div class="col-md-1"></div>
        </div>
        <div class="contents row">
            <div class="col-md-1"></div>
            <div class="col-md-10 contents">
                <div class="content">
                    <hr>
                </div>


            </div>
            <div class="col-md-1"></div>
        </div>
        <div class="introduce row">
            <div class="col-md-1"></div>
            <div class="col-md-10 contents">
                <div class="new">
                    <h6 class="text-center">More like This</h6>

                    <div class="new2 row" >
                        <div class="col-md-1"></div>
                        <%
                            List<BookDTO> list = (List<BookDTO>) session.getAttribute("TOP_BOOK");
                            if (list != null) {
                                if (!list.isEmpty()) {
                                    for (BookDTO b : list) {
                        %>
                        <div class="col-md-2 new-item text-center">
                            <a href="MainController?action=Detail&bookID=<%= b.getBookID()%>"><img src="<%= b.getImage()%>" width="188" height="230"/></a>
                            <a href="MainController?action=Detail&bookID=<%= b.getBookID()%>"><p><%= b.getBookName()%></p></a>
                        </div>
                        <%
                                    }
                                }
                            }
                        %>
                        <div class="col-md-1"></div>
                    </div>
                </div>

            </div>
            <div class="col-md-1"></div>
        </div>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>



</body>
</html>

