<%-- 
    Document   : Home.jsp
    Created on : Jun 16, 2022, 1:43:06 AM
    Author     : NhatTan
--%>

<%@page import="sample.DTO.AccountDTO"%>
<%@page import="sample.DTO.NewsDTO"%>
<%@page import="java.util.List"%>
<%@page import="sample.DTO.BookDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width" , initial-scale="1" />
        <title>FPTU HCM Library</title>
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
        <link rel="stylesheet" href="CSS/style1.css" />
        <link rel="stylesheet" href="CSS/ui.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />

    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <%
            AccountDTO acc = (AccountDTO) session.getAttribute("LOGIN_ACCOUNT");
            if (acc != null && acc.getRoleID() != 3) {
                response.sendRedirect("javascript:history.back()");
                return;
            }
        %>
        <section class="section-name padding-y-sm">
            <div class="container">

                <header class="section-heading">

                    <h3 class="section-title">New Arrival</h3>
                </header><!-- sect-heading -->


                <div class="row">
                    <div class="col-md-1"></div>
                    <%
                        List<BookDTO> list = (List<BookDTO>) session.getAttribute("TOP_BOOK");
                        if (list != null) {
                            if (list.size() > 0) {
                                for (BookDTO p : list) {
                    %>
                    <div class="col-md-2">
                        <div href="MainController?action=Detail&bookID=<%=p.getBookID()%>" class="card card-product-grid">
                            <a href="MainController?action=Detail&bookID=<%=p.getBookID()%>" class="img-wrap"> <img src=<%=p.getImage()%>> </a>
                            <figcaption class="info-wrap">
                                <a href="MainController?action=Detail&bookID=<%=p.getBookID()%>" title="<%=p.getBookName()%>" class="title"><%=p.getBookName()%> </a>
                            </figcaption>
                        </div>
                    </div> <!-- col.// -->
                    <%
                                }
                            }
                        }
                    %>
                    <div class="col-md-1"></div>
                </div> <!-- row.// -->

            </div><!-- container // -->
        </section>
        <hr height="10">
        <section class="section-main bg padding-y">
            <div class="container">

                <div class="row">
                    <div class="col-md-9">

                        <%
                            NewsDTO news = (NewsDTO) request.getAttribute("NEWS");
                            if (news != null) {
                        %>
                        <div>
                            <h1><%=news.getTitle()%></h1>                                    
                            <h4><i>&nbsp;&nbsp;&nbsp;Writer: <%=news.getWriterName()%></i></h4>                                    
                            <h6><i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Post by: <%=news.getAccountID()%></i></h6>                                    
                            <h6><i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date: <%=news.getUploadDate()%></i></h6>
                            <br>
                            <p>&nbsp;&nbsp;&nbsp;&nbsp;<%=news.getHead()%></p>
                            <%
                                String[] body = news.getBody().split("\\R");
                                for (String i : body) {
                                    out.println("<p>&nbsp;&nbsp;&nbsp;&nbsp;"+i+"</p>");
                                }
                            %>
                        </div>


                        <%
                            }
                        %> 

                    </div> <!-- col.// -->
                    <div class="col-md-3">
                        <table>
                            <tbody>
                                <tr>
                            <ul style="font-size: 13px">




                                <%
                                    List<NewsDTO> listN = (List<NewsDTO>) request.getAttribute("TOP_NEWS");
                                    if (listN != null) {
                                        if (listN.size() > 0) {
                                            for (NewsDTO n : listN) {
                                %>
                                <li>
                                    <div>
                                        <h6 style="margin-bottom: 4px"><a href="MainController?action=LoadNews&newsID=<%=n.getNewsID()%>"><%= n.getTitle()%></a></h6>
                                    </div>
                                </li>
                                <%
                                            }
                                        }
                                    }
                                %>

                            </ul>

                            </tr>
                            <tr>
                                <ul class="list-unstyled">
                                    <li><h6><a href="MainController?action=ListNews">&emsp;&emsp;All News...</a></h6></li>
                                </ul>
                            </tr>
                            </tbody>
                        </table>
                    </div> <!-- col.// -->

                </div> <!-- row.// -->
            </div> <!-- container //  -->
        </section>
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