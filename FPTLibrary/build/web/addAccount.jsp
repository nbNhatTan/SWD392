<%@page import="sample.DTO.AccountDTO"%>
<%@page import="sample.DTO.AccountError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width" , initial-scale="1" />
        <title>Add Account</title>
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
            if (acc.getRoleID() != 1) {
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
                                    <h3>Add Account</h3>
                                </div>
                            </div>
                            <%                                AccountError accountError = (AccountError) request.getAttribute("ACCOUNT_ERROR");
                                if (accountError == null) {
                                    accountError = new AccountError();
                                }
                                String accountID = request.getParameter("accountID");
                                String fullName = request.getParameter("fullName");
                                String password = request.getParameter("password");
                                String confirm = request.getParameter("confirm");
                                String email = request.getParameter("email");
                                String address = request.getParameter("address");
                                String phone = request.getParameter("phone");
                                String roleID = request.getParameter("roleID");
                            %>
                            <form action="MainController">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Username:</label>
                                    <input name="accountID" type="text" class="form-control" required="" value="<%=accountID != null ? accountID : ""%>"><%= accountError.getAccountIDError()%>
                                </div>

                                <div class="form-group">
                                    <label for="exampleInputEmail1">Full Name:</label>
                                    <input name="fullName" type="text" placeholder="Enter Full Name"  class="form-control" required="" value="<%=fullName != null ? fullName : ""%>"><%= accountError.getFullNameError()%>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Password:</label>
                                    <input name="password" type="password" placeholder="Enter Password"  class="form-control" required="" value="<%=password != null ? password : ""%>">
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Confirm:</label>
                                    <input name="confirm" type="password" placeholder="Enter Password" class="form-control" required="" value="<%=confirm != null ? confirm : ""%>"><%= accountError.getConfirmError()%>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Role ID:</label>
                                    <input  name="roleID"type="text" placeholder="[2-staff, 3-user]"  class="form-control" required="" value="<%=roleID != null ? roleID : ""%>"><%= accountError.getRoleIDError()%>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Mail:</label>
                                    <input name="email" type="text" placeholder="Enter Mail @fpt.edu.vn"  class="form-control" required="" value="<%=email != null ? email : ""%>"><%= accountError.getEmailError()%>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Address:</label>
                                    <input name="address"type="text" placeholder="Enter Address"   class="form-control" required="" value="<%=address != null ? address : ""%>"><%= accountError.getAddressError()%>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Phone:</label>
                                    <input  name="phone" type="text" placeholder="Enter Phone"  class="form-control" required="" value="<%=phone != null ? phone : ""%>"><%= accountError.getPhoneError()%>
                                </div>


                                <div class="col-md-12 text-center mb-3">
                                    <button class=" btn btn-block mybtn btn-warning tx-tfm" type="submit" name="action" value="AddAccount" >Add new account</button>
                                </div>
                                <div class="col-md-12 ">
                                    <div class="form-group">
                                        <a onclick="history.back()" href="#" id="cancel_signup"><i class="fas fa-angle-left"></i> Back</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>