<%-- 
    Document   : borrow
    Created on : Jun 14, 2022, 5:07:23 PM
    Author     : NhatTan
--%>

<%@page import="java.sql.Date"%>
<%@page import="sample.DTO.AccountDTO"%>
<%@page import="sample.DTO.BorrowDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width" , initial-scale="1" />
        <title>Borrow Staff</title>
        <link
            rel="stylesheet"
            href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
            />
        <link
            href="https://use.fontawesome.com/releases/v5.0.4/css/all.css"
            rel="stylesheet"
            />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="CSS/styleBorrow.css" />
        <link rel="stylesheet" href="CSS/style1.css" />

        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />

    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
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
            <div class="table-container">
                <div>
                    <h3 class = "">
                    <%
                        String status = (String) request.getAttribute("Status");
                        if(status==null) status="";
                    %>
                        <ol class="breadcrumb">
                            <li class="nac-item">
                                <a class="nav-link" href="ViewborrowStaffController"><%= status.equals("") ? "<strong>All Ticket</strong>" : "All Ticket"%></a>
                            </li>
                            <li class="nav-item " >
                                <a class="nav-link" href="ViewborrowStaffController?borrowStatus=Pending"><%= status.equals("Pending") ? "<strong>Confirmation</strong>" : "Confirmation"%></a>
                            </li>
                            <li class="nav-item " >
                                <a class="nav-link" href="ViewborrowStaffController?borrowStatus=Expired"><%= status.equals("Expired") ? "<strong>Ticket Expired</strong>" : "Ticket Expired"%></a>
                            </li>
                            <li class="nav-item " >
                                <a class="nav-link" href="ViewborrowStaffController?borrowStatus=Returned"><%= status.equals("Returned") ? "<strong>Borrowed Ticket</strong>" : "Borrowed Ticket"%></a>
                            </li>
                            <li class="nav-item " >
                                <a class="nav-link" href="LoadListPreOrderController"><%= status.equals("Approved") ? "<strong>Approved list</strong>" : "Approved list"%></a>
                            </li>
                        </ol>
                    </h3>
                    <table class="borrow-table">
                        <thead>
                            <tr>
                                <th>Image</th>
                                <th>ID</th>
                                <th>Borrower</th>
                                <th>BookItemID</th>
                                <th>Borrow Date</th>
                                <th>Expiry Date</th>
                                <th>Return Date</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                        <%
                            List<BorrowDTO> list = (List<BorrowDTO>) request.getAttribute("ListBorrow");
                            if (list != null) {
                                if (list.size() > 0) {
                                    for (BorrowDTO p : list) {
                        %>
                        <tr>
                            <td>
                                <img src="<%= p.getImage() %>" width="100" height="150"/>
                            </td>
                            <td><%= p.getBookingTicketID()%></td>
                            <td><%= p.getUserID().getFullName()%></td>
                            <td><%= p.getBookItemID()%></td>
                            <td><%= p.getBorrowDate()%></td>
                            <td><%= p.getExpiredDate()%></td>
                            <td>
                                <% 
                                    if (p.getReturnDate() == null) {
                                        String returnDate = "";
                                        out.print(returnDate);
                                    } else {
                                        out.print(p.getReturnDate());
                                    }
                                %>
                            </td>
                            <td><span class="<%= p.getBorrowStatus()%>">[<%= p.getBorrowStatus()%>]</span> </td>
                            <td>
                                <a href="MainController?action=View&bookingTicketID=<%= p.getBookingTicketID()%>"><button class="btn btn-light btn-sm">View</button></a>
                                <form action="MainController" method="POST" onsubmit="return confirm(this);">
                                    <input type="hidden" name="bookingTicketID" value="<%= p.getBookingTicketID()%>"/>
                                    <%
                                        if(p.getBorrowStatus().equals("Pending")){
                                    %>
                                    <input type="hidden" name="action" value="Confirm"/>
                                    <button class="btn btn-light btn-sm" name="action" value="Confirm">Confirm</button>
                                    <%
                                        }
                                        if(p.getBorrowStatus().equals("Borrowing")){
                                    %>
                                    <input type="hidden" name="action" value="Return"/>
                                    <button class="btn btn-light btn-sm" name="action" value="Return">Return</button>
                                    <%
                                        }
                                        if(p.getBorrowStatus().equals("Expired")){
                                    %>
                                    <input type="hidden" name="action" value="ViewOrCreate"/>
                                    <button class="btn btn-light btn-sm">Violation</button>
                                    <%
                                        }
                                        if(p.getBorrowStatus().equals("Approved")){
                                    %>
                                    <input type="hidden" name="bookingTicketStatus" value="Borrowing"/>
                                    <input type="hidden" name="action" value="ConfirmRecived"/>
                                    <button class="btn btn-light btn-sm">Received</button>
                                    <%
                                        }
                                    %> 
                                </form>  
                                <%
                                    if(p.getBorrowStatus().equals("HandleViolation")){
                                    %>
                                    <a href="MainController?action=ViewOrCreate&bookingTicketID=<%= p.getBookingTicketID()%>"><button class="btn btn-light btn-sm">Violation</button></a>
                                    <%
                                        }
                                    %>    
                            </td>
                        </tr>
                        <%
                                    }
                                }
                            }
                        %>
                    </tbody>
                </table>
            </div>
            <ul class="pagination" id="pagination">
                <li class="page-item first disabled"><a href="#" class="page-link">&lt;&lt;&lt;</a></li>
                <li class="page-item prev disabled"><a href="#" class="page-link">&lt;-</a></li>
                <li class="page-item active"><a href="#" class="page-link">1</a></li>
                <li class="page-item next disabled"><a href="#" class="page-link">-&gt;</a></li>
                <li class="page-item last disabled"><a href="#" class="page-link">&gt;&gt;&gt;</a></li>
            </ul>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <%
            String message = (String) request.getAttribute("message");
            if(message != null){
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
            function confirm(form) {
                Swal.fire({
                    title: 'Are you sure?',
                    text: "Keep taking action with this ticket",
                    icon: 'question',
                    showCancelButton: true,
                    confirmButtonColor: '#F5D98F',
                    cancelButtonColor: '#F7E5D7',
                    confirmButtonText: 'Yes, I`m sure!'
                }).then((result) => {
                    if (result.isConfirmed) {
                        form.submit();
                    }
                });
                return false;
            }
        </script>         
    </body>
</html>
