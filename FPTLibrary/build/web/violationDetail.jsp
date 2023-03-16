<%-- 
    Document   : Detail
    Created on : Jun 14, 2022, 11:09:52 AM
    Author     : NhatTan
--%>

<%@page import="sample.DTO.BookDTO"%>
<%@page import="sample.DTO.ViolationTicketDTO"%>
<%@page import="java.util.List"%>
<%@page import="sample.DTO.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width" , initial-scale="1" />
        <title>Violation Detail</title>
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
        if (acc == null || acc.getRoleID() == 1) {
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
                        ViolationTicketDTO violation = (ViolationTicketDTO) request.getAttribute("DETAIL_VIOLATION");
                        if (violation != null) {
                    %>
                    <div class="row">
                        <div class="col-md-3"></div>
                        <div class="col-md-6 container-fluid">
                            <table width="100%">
                                <tr>
                                    <td><h6>ViolationTicket ID: </h6></td>
                                    <td><%=violation.getViolationTicketID()%></td>
                                </tr>
                                <tr>
                                    <td><h6>BookingTicket ID: </h6></td>
                                    <td><%= violation.getBookingTicketID()%></td>
                                </tr>
                                <tr>
                                    <td><h6>Staff: </h6></td>
                                    <td><%=violation.getStaffID()%></td>
                                </tr>
                                <tr>
                                    <td><h6>Description: </h6></td>
                                    <td><%=violation.getDescription()%></td>
                                </tr>
                                <tr>
                                    <td><h6>Create Date: </h6></td>
                                    <td><%=violation.getCreateDate()%></td>
                                </tr>
                                <tr>
                                    <td><h6>Status: </h6></td>
                                    <%
                                        if (violation.getTicketStatus()) {
                                    %>
                                    <td><span style="color: #f00">[Unpaid]</span> </td>
                                    <%
                                        } else {
                                    %>
                                    <td><span style="color: #00b050">[Paid]</span> </td>
                                    <%
                                        }
                                    %>
                                </tr>
                            </table>                            
                            <%
                                AccountDTO loginAccount = (AccountDTO) session.getAttribute("LOGIN_ACCOUNT");
                                if (loginAccount.getRoleID() == 2){
                            %>
                            <a href="PayViolationController?violationTicketID=<%= violation.getViolationTicketID()%>"><button type="button" class="bookingButton btn btn-light btn-sm">Pay</button></a>
                            <%
                                }
                            %>
                            <button onclick="history.back()" type="button" class="btn btn-dark btn-sm">Back to List</button>
                    <%
                        }
                    %> 

                        </div>
                        <div class="col-md-3"></div>
                    </div>
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
                    <h6 class="text-center">New Arrival</h6>

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

