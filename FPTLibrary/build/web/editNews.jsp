<%-- 
    Document   : editNews
    Created on : Jul 22, 2022, 12:12:23 PM
    Author     : admin
--%>


<%@page import="sample.DTO.NewsDTO"%>
<%@page import="sample.DTO.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width" , initial-scale="1" />
        <title>Add News</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" />
        <link href="https://use.fontawesome.com/releases/v5.0.4/css/all.css" rel="stylesheet" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="CSS/style1.css" />

        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap" rel="stylesheet" />
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
        <div class="container main">
            <%
//                                BookError bookError = (BookError) request.getAttribute("BOOK_ERROR");
//                                if (bookError == null) {
//                                    bookError = new BookError();
//                                }
                NewsDTO news = (NewsDTO) request.getAttribute("news");
                if (news == null) {
                    news = new NewsDTO();
                }
            %>
            
            <form action="MainController" style="width: 100%; max-width: 100%">
                <table>
                    <tbody>
                    <h3 class="title-text">Update NEWS</h3>
                    <tr style="border-bottom: 0 none" class ="containr-news">

                    <tr>
                    <div>
                        <input name="newsID" type="hidden" value="<%= news.getNewsID()%>" />
                    </div>
                    </tr>
                    <tr>
                    <div class="news_box">
                        <label for=""> Writer Name:</label>
                        <input name="writerName" type="text" placeholder="Enter Writter Name" required="" value="<%= news.getWriterName()%>">

                    </div>
                    </tr>
                    <tr>
                    <div class="news_box">
                        <label for="" class="titlenews">News title:</label>
                        <input name="title" type="text" placeholder="Enter news title" required="" value="<%= news.getTitle()%>">
                    </div>
                    </tr>
                    <tr>
                    <div class="news_box">
                        <label for="" >Head:</label>
                        <input  name="head" type="text" placeholder="Enter news head" required="" value="<%= news.getHead()%>">

                    </div> 
                    </tr>
                    <tr>
                    <div class="news_box">
                        <label for="" >Body:</label>
                        <textarea row="8"  name="body" type="text" placeholder="Enter news body" required=""><%= news.getBody()%></textarea> 

                    </div>
                    </tr>
                    <tr>
                    <div class="news_box">
                        <label class="" for="">Upload date:</label>
                        <input name="uploadDate" type="date" placeholder="Select DateTime" required="" value="<%= news.getUploadDate()%>">                            

                    </div>
                    </tr>


                    
                    <tr>
                        <td>
                            <div class="addNewsButton"><button class="btn btn-warning btn-sm " type="submit" name="action" value="UpdateNews">Update News</button></div>
                        </td>
                        <td>
                            <div class="addNewsButton"><button class="btn btn-warning btn-sm" type="submit" name="action" value="ListNews">Cancel</button></div> 
                        </td>
                    </tr>
                    </tbody>
                </table>            
            </form>
               </div>         
        </div>




        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
