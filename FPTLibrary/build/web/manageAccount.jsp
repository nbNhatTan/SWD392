<%@page import="sample.DTO.AccountDTO"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width" , initial-scale="1" />
        <title>Manager Account</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"/>
        <link href="https://use.fontawesome.com/releases/v5.0.4/css/all.css" rel="stylesheet"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="CSS/styleBorrow.css" />
        <link rel="stylesheet" href="CSS/style1.css" />
        <link rel="stylesheet" href="CSS/ui.css" />

        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />

    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <%
            AccountDTO accLog = (AccountDTO) session.getAttribute("LOGIN_ACCOUNT");
            if (accLog == null || accLog.getRoleID() != 1) {
                response.sendRedirect("javascript:history.back()");
                return;
            }
        %>
            <div class="table-container main">
                <div>
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-10">
                                <h2>Manage <b>Account</b></h2>
                            </div>
                            <div class="col-sm-2">
                                <a href="addAccount.jsp"  class="btn btn-warning btn-sm" ><span>+ New Account</span></a>
                            </div>
                        </div>
                    </div>
                    <table class="borrow-table">
                        <thead>
                            <tr>
                                <th>Account ID</th>
                                <th>Full Name</th>
                                <th>Role ID</th>
                                <th>Email</th>
                                <th>Address</th>
                                <th>Phone</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                        <%
                            List<AccountDTO> list = (List<AccountDTO>) request.getAttribute("listA");
                            if (list != null) {
                                if (list.size() > 0) {
                                    for (AccountDTO p : list) {
                                        if (p.getStatus()) {
                                            if(p.getRoleID()!=1){
                        %>
                        <tr>
                            <td><%= p.getAccountID()%></td>
                            <td><%= p.getFullName()%></td>
                            <td><%= p.getRoleID()%></td>
                            <td><%= p.getEmail()%></td>
                            <td><%= p.getAddress()%></td>
                            <td><%= p.getPhone()%></td>
                            <td>
                                <a href="LoadManageController?accountID=<%=p.getAccountID()%>"  class="edit" ><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                <a onclick="delacc('<%=p.getAccountID()%>')" class="delete" href="#"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                            </td>
                        </tr>
                        <%
                                            }
                                        }
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
            function delacc(id) {
                    Swal.fire({
                        title: 'Are you sure?',
                        text: "You want to delete this account.",
                        icon: 'question',
                        showCancelButton: true,
                        confirmButtonColor: '#F5D98F',
                        cancelButtonColor: '#F7E5D7',
                        confirmButtonText: 'Yes, delete it!'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            location.href = "DeleteAccountController?accountID=" + id;
                        }
                    })
            }
        </script>
    </body>
</html>
