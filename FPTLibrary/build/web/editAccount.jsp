<%@page import="sample.DTO.AccountDTO"%>
<%@page import="sample.DTO.AccountError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width" , initial-scale="1" />
        <title>Edit Account</title>
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
        <link rel="stylesheet" href="CSS/login.css" />
        <link rel="stylesheet" href="CSS/style1.css" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    </head>

    <body>
        <style>
            body {
                background-image: url('./image/background.jpg');
                background-repeat: no-repeat;
                background-size: cover;
                background-attachment: fixed;
                background-size: 100% 100%;
            }
        </style>
    <jsp:include page="header.jsp"></jsp:include>
    <%
        AccountDTO accLog = (AccountDTO) session.getAttribute("LOGIN_ACCOUNT");
        if (accLog == null || accLog.getRoleID() != 1) {
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
                                    <h3>Edit Account</h3>
                                </div>
                            </div>
                            <%                                 
                                AccountError accountError = (AccountError) request.getAttribute("ACCOUNT_ERROR");
                                if (accountError == null) {
                                    accountError = new AccountError();
                                }
                                AccountDTO acc = (AccountDTO) request.getAttribute("ACCOUNT_DETAIL");
                            %>
                            <form action="MainController" onsubmit="return create(this);">
                                <input name="action" value="EditAccount" type="hidden"/>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Account ID:</label>
                                    <input name="accountID" type="text" placeholder="Enter accountID"  class="form-control" value="<%= acc.getAccountID()%>" readonly="" >
                                </div>
                                <div class="form-group">
                                        <label for="exampleInputEmail1">Mail:</label>
                                        <input name="email" type="text" placeholder="Enter Mail @fpt.edu.vn" value="<%= acc.getEmail()%>" class="form-control" readonly="" ><%= accountError.getEmailError()%>
                                    </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Role ID:</label>
                                    <input  name="roleID"type="text" placeholder="[2-staff, 3-user]"  class="form-control" value="<%= acc.getRoleID()%>" ><%= accountError.getRoleIDError()%>
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">Full Name:</label>
                                        <input name="fullName" type="text" placeholder="Enter Full Name" value="<%= acc.getFullName()%>" class="form-control" required="" ><%= accountError.getFullNameError()%>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">Address:</label>
                                        <input name="address"type="text" placeholder="Enter Address" value="<%= acc.getAddress()%>"  class="form-control" required="" ><%= accountError.getAddressError()%>
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">Phone:</label>
                                        <input  name="phone" type="text" placeholder="Enter Phone" value="<%= acc.getPhone()%>"  class="form-control" required="" ><%= accountError.getPhoneError()%>
                                    </div>
                                    <div class="col-md-12 text-center mb-3">
                                        <button class=" btn btn-block mybtn btn-warning tx-tfm">Edit</button>
                                    </div>
                                    <div class="col-md-12 ">
                                        <div class="form-group">
                                            <a onclick="history.back()"href="#" id="cancel_signup"><i class="fas fa-angle-left"></i> Back</a>
                                        </div>
                                    </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
        function create(form) {
            Swal.fire({
                title: 'Are you sure?',
                text: "You want to edit your information.",
                icon: 'question',
                showCancelButton: true,
                confirmButtonColor: '#F5D98F',
                cancelButtonColor: '#F7E5D7',
                confirmButtonText: 'Yes, I want!'
            }).then((result) => {
                if (result.isConfirmed) {
                    form.submit();
                }
            });
            return false;
        }
    </script>         
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>