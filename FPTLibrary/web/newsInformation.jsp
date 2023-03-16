<%-- 
    Document   : newsInformation
    Created on : Jul 22, 2022, 12:29:19 AM
    Author     : admin
--%>

<%@page import="sample.DTO.NewsDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width" , initial-scale="1" />
        <title>News Detail</title>
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
        <div class="main container ">
                <table>
                    <tbody>
                        <tr style="border-bottom: 0 none">
                            <td>
                                <div>
                                <%
                                    NewsDTO news = (NewsDTO) request.getAttribute("news");
                                    if (news != null) {
                                %>
                                <div>
                                    <span class="fon31"> <%= news.getTitle()%></span>
                                    <br>Post by <%= news.getAccountID()%> on <%= news.getUploadDate()%><hr>
                                    <p><%= news.getHead()%></p>



                                    <p><%= news.getBody()%></p>

                                    <p>Người viết: <%= news.getWriterName()%></p>
                                </div>
                                <% 
                                    }
                                %>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
