<%@page import="sample.DTO.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width" , initial-scale="1" />
        <title>Login</title>
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
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />

    </head>

    <body>
    <%
        AccountDTO acc = (AccountDTO) session.getAttribute("LOGIN_ACCOUNT");
        if (acc != null) {
            response.sendRedirect("HomeController");
            return;
        }
    %>
        <style>
            body {
                background-image: url('./image/background.jpg');
                background-repeat: no-repeat;
                background-size: cover;
                background-attachment: fixed; 
                background-size: 100% 100%;
            }
        </style>
        <a style="margin-left: 10%; margin-top: 2%" href="HomeController" type="button" class="btn btn-dark btn-sm material-icons">&#xe88a;</a>
        <div class="container login-container">
            <div class="row">
                <div class="col-md-5 mx-auto">
                    <div id="first">
                        <div class="myform form ">
                            <div class="logo mb-3">
                                <div class="col-md-12 text-center">
                                    <h3>Login</h3>
                                </div>
                            </div>
                            <form action="MainController" method="post" name="login">
                                <div class="form-group">
                                    <label>User ID</label>
                                    <input type="text" name="accountID" id="inputEmail" class="form-control" placeholder="User ID" required="">
                                </div>
                                <div class="form-group">
                                    <label>Password</label>
                                    <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required="">
                                </div>
                                <%
                                    String message = (String) request.getAttribute("ERROR");
                                    if (message == null) {
                                        message = "";
                                    }
                                    out.print("<p style='color: #dc3545;'>"+message+"</p>");
                                %>
                                <div class="col-md-12 text-center ">
                                    <button type="submit" class=" btn btn-block mybtn btn-warning tx-tfm" name="action" value="Login">Login</button>
                                </div>
                                <div class="col-md-12 ">
                                    <div class="login-or">
                                        <hr class="hr-or">
                                        <span class="span-or">or</span>
                                    </div>
                                </div>
                                <div class="col-md-12 mb-3">
                                    <p class="text-center">
                                        <a class="btn btn-outline-dark" href="https://accounts.google.com/o/oauth2/auth?scope=email profile&redirect_uri=http://localhost:8080/FPTLibrary-Git/LoginGoogleController&response_type=code
                                           &client_id=53864111144-aj9dkmdo6j87dl266muvck01ehdfnbjk.apps.googleusercontent.com&approval_prompt=force" role="button" style="text-transform:none">
                                            <img width="20px" style="margin-bottom:3px; margin-right:5px" alt="Google sign-in" src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Google_%22G%22_Logo.svg/512px-Google_%22G%22_Logo.svg.png" />
                                            Login with Google
                                        </a>
                                    </p>
                                </div>
                                <div class="form-group">
                                    <p class="text-center">Don't have account? <a href="register.jsp" id="signup">Sign up here</a></p>
                                </div>
                            </form>

                        </div>
                    </div>

                </div>
            </div>
        </div> 
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <%
        String mess = (String) request.getAttribute("message");
        if(mess != null){
    %>
        <script>
            Swal.fire({
                        title: 'Success!',
                        text: '<%=mess%> successful.',
                        confirmButtonColor: '#F5D98F',
                        timer: 2000,
                        timerProgressBar: true,
                        icon: 'success'
                    });
        </script>
    <%
        }
    %>
    <%
        String error = (String) request.getAttribute("messageerror");
        if(error != null){
    %>
        <script>
            Swal.fire({
                        title: 'Warning!',
                        text: '<%=error%>',
                        confirmButtonColor: '#F5D98F',
                        timer: 2000,
                        timerProgressBar: true,
                        icon: 'warning'
                    });
        </script>
    <%
        }
    %>
    <%
        String warning = (String) request.getAttribute("warning");
        if(warning != null){
    %>
        <script>
            Swal.fire({
                        title: 'Warning!',
                        text: '<%=warning%>',
                        confirmButtonColor: '#F5D98F',
                        timer: 2000,
                        timerProgressBar: true,
                        icon: 'warning'
                    });
        </script>
    <%
        }
    %>
    </body>

</html>
