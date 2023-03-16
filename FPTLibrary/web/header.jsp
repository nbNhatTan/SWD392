
<%@page import="sample.DTO.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String search = request.getParameter("search");
    if (search == null) {
        search = "";
    }
%>
<div class="header">
<nav class="navbar navbar-expand-md navbar-light">
    <div class="container-fluid padding row">
       <div class="col-md-4">
            <div class="p-2">
                <a class="navbar-branch" href="HomeController">
                    <img
                        class="img"
                        src="https://cdn.glitch.global/b5568004-6653-447c-bb6a-cd2cd0c89e38/LogoFPT.png?v=1653532923912"
                        width="325"
                        height="71"
                        />
                </a>
            </div>
        </div>
        <div class="col-md-4">
                <form action="MainController" class="search">
                <div class="input-group w-100">
                    <input type="text" name="bookName" value=""<%=search%>" class="form-control" placeholder="Search">
                    <div class="input-group-append">
                      <button class="btn btn-warning" type="submit" name="action" value="SearchBook">
                        <i class="fa fa-search"></i>
                      </button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-1"></div>
        <%
            AccountDTO acc = (AccountDTO) session.getAttribute("LOGIN_ACCOUNT");
            if (acc != null) {
                if (acc.getRoleID() == 2 || acc.getRoleID() == 3) {
        %>
            <div class="col-md-3">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item" style="width: 360px;overflow: hidden;white-space: nowrap; text-overflow: ellipsis;">
                        <strong><a class="nav-link" href="LoadAccountController?accountID=<%=acc.getAccountID()%>">Hello <i><%=acc.getFullName()%></i></a></strong>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="LogoutController">Logout</a>
                    </li>
                </ul>
            </div>
        <%
                } else {
        %>
        <div class="col-md-3">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item" style="width: 360px;overflow: hidden;white-space: nowrap; text-overflow: ellipsis;">
                        <strong><a class="nav-link" href="LoadAccountController?accountID=<%=acc.getAccountID()%>">Hello <i><%=acc.getFullName()%></i></a></strong>
                    </li>
                <li class="nav-item">
                    <a class="nav-link" href="LogoutController">Logout</a>
                </li>
            </ul>
        </div>
        <%
                }
            } else {
        %>
        <div class="col-md-2 col"></div>
        <div class="col-md-1 col">
            <a  href="login.jsp"><button class="btn btn-warning" type="submit">Login</button> </a>
        </div>
        <%
            }
        %>
    </div>
</nav>

<nav class="nav navbar-expand-md navbar-light sticky-top">
    <div>
        <button
            class="navbar-toggler"
            type="button"
            data-toggle="collapse"
            data-target="#navbarResponsive"
            >
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <%
                    String url= request.getServletPath();
                    if (acc == null ||  acc.getRoleID() == 3) {
                %>
                <li class="nav-item">
                    <a class="nav-link <%= url.equals("/home.jsp")?"active":""%>" href="HomeController">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= url.equals("/about.jsp")?"active":""%>" href="about.jsp">About</a>
                </li>
                <%
                    }
                    if(acc != null){
                        if (acc.getRoleID() == 1) {
                %>
                <li class="nav-item">
                    <a class="nav-link <%= url.equals("/manageAccount.jsp")?"active":""%>" href="ViewAccountController">Account Management</a>
                </li>
                    <%
                        }
                        if (acc.getRoleID() == 2) {
                    %>
                <li class="nav-item">
                    <a class="nav-link <%= url.equals("/bookingOffline.jsp")?"active":""%>" href="bookingOffline.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= url.equals("/manageBook.jsp")?"active":""%>" href="LoadListBookController">Book Management</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= url.equals("/borrowStaff.jsp")?"active":""%>" href="ViewborrowStaffController">Borrow Management</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= url.equals("/violation.jsp")?"active":""%>" href="ViewViolationController">Violation Management</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= url.equals("/manageNews.jsp")?"active":""%>" href="NewsController">News Management</a>
                </li>
                    <%
                        }
                        if (acc.getRoleID() == 3) {
                    %>
                <li class="nav-item">
                    <a class="nav-link <%= url.equals("/advancedSearch.jsp")?"active":""%>" href="LoadAdvancedSearchController">Advanced Search</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= url.equals("/borrow.jsp")?"active":""%>" href="ViewborrowController">Borrow History</a>
                </li>
                <%
                        }
                    }
                %>
            </ul>
        </div>
    </div>
</nav>
</div>
