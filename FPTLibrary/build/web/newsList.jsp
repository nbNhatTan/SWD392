<%-- 
    Document   : newsList
    Created on : Jul 21, 2022, 10:37:38 PM
    Author     : admin
--%>

<%@page import="sample.DTO.NewsDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width" , initial-scale="1" />
        <title>News</title>
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
            <div class="main table-container">
            <%
                List<NewsDTO> list = (List<NewsDTO>) request.getAttribute("listNews");
                if (list != null) {

            %>
            <form name="" >
                <table>
                    <h5>Danh sách thông báo:</h5>
                    <tbody>
                        <tr style="border-bottom: 0 none">
                            <td>
                                <div>
                                    <ul style="font-size: 13px">
                                        <%                                            
                                            if (list.size() > 0) {
                                                for (NewsDTO p : list) {
                                        %>
                                        <li style="font-size: 13px">
                                            <a href="MainController?action=LoadNews&newsID=<%=p.getNewsID()%>"><p style="color: blue; margin-bottom: 0px ">[<%= p.getUploadDate()%>] <%= p.getTitle() %></p></a>
                                        </li>
                                        <%
                                                }
                                            }
                                        %>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <% 
                } 
            %>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
