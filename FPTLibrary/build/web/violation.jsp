<%-- 
    Document   : violation
    Created on : Jun 21, 2022, 8:11:30 AM
    Author     : Admin
--%>

<%@page import="sample.DTO.AccountDTO"%>
<%@page import="sample.DTO.ViolationTicketDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width" , initial-scale="1" />
        <title>Violation</title>
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
                    <%
                        String staffID = (String) request.getAttribute("StaffID");
                    %>
                    <h3>
                        <ol class="breadcrumb">
                            <li class="nac-item">
                                <a class="nav-link" href="ViewViolationController"><%= staffID.equals("%%") ? "<strong>All Violation Ticket</strong>" : "All Violation Ticket"%></a>
                            </li>
                            <%
                                AccountDTO loginAccount = (AccountDTO) session.getAttribute("LOGIN_ACCOUNT");
                            %>
                            <li class="nav-item " >
                                <a class="nav-link" href="ViewViolationController?staffID=<%= loginAccount.getAccountID()%>"><%= !staffID.equals("%%") ? "<strong>Violation Ticket by me</strong>" : "Violation Ticket by me"%></a>
                            </li>
                        </ol>
                    </h3>
                    <%
                        String status = (String) request.getAttribute("Status");
                        if(status==null) status="";
                    %>
                    <h6>
                        <ol class="breadcrumb">
                            <li class="nac-item">
                                <a class="nav-link" href="ViewViolationController?staffID=<%=staffID%>&status=0"><%= status.equals("0") ? "<strong>Paid</strong>" : "Paid"%></a>
                            </li>
                            <li class="nav-item " >
                                <a class="nav-link" href="ViewViolationController?staffID=<%=staffID%>&status=1"><%= status.equals("1") ? "<strong>Unpaid</strong>" : "Unpaid"%></a>
                            </li>
                        </ol>
                    </h6>
                    <table class="borrow-table">
                        <thead>
                            <tr>
                                <th>Violation Ticket ID</th>
                                <th>Date Created</th>
                                <th>Description</th>                       
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<ViolationTicketDTO> list = (List<ViolationTicketDTO>) request.getAttribute("ListViolationStaff");
                                if (list != null) {
                                    if (list.size() > 0) {
                                        for (ViolationTicketDTO v : list) {
                            %>
                            <tr>
                                <td><%=v.getViolationTicketID()%></td>
                                <td><%=v.getCreateDate()%></td>
                                <td><%=v.getDescription()%></td>
                                <%
                                    if (v.getTicketStatus()) {
                                %>
                                <td><span class="Expired">[Unpaid]</span> </td>
                                <td>
                                    <a href="ViolationDetailController?bookingTicketID=<%= v.getBookingTicketID()%>"><button class="btn btn-light btn-sm">View</button></a>
                                    <a href="SendMailController?ViolationTicketID=<%= v.getViolationTicketID() %>"><button class="btn btn-light btn-sm">Send Mail</button></a>
                                </td>
                                <%
                                } else {
                                %>
                                <td><span class="Borrowing">[Paid]</span> </td>
                                <td></td>
                                <%
                                    }
                                %>
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
    </body>
</html>

