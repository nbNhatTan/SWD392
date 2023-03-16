<%-- 
    Document   : Detail
    Created on : Jun 14, 2022, 11:09:52 AM
    Author     : NhatTan
--%>

<%@page import="sample.DAO.FeedbackDAO"%>
<%@page import="sample.DTO.FeedBackDTO"%>
<%@page import="sample.DTO.CategoryDTO"%>
<%@page import="java.util.List"%>
<%@page import="sample.DTO.AccountDTO"%>
<%@page import="sample.DTO.BookDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width" , initial-scale="1" />
        <title>Book Detail</title>
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" />
        <link href="https://use.fontawesome.com/releases/v5.0.4/css/all.css"
              rel="stylesheet" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        <script src="https://kit.fontawesome.com/c8e4d183c2.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="CSS/style1.css" />
        <link rel="stylesheet" href="CSS/ui.css" />

        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />

    </head>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <%
        AccountDTO acc = (AccountDTO) session.getAttribute("LOGIN_ACCOUNT");
        if (acc != null && acc.getRoleID() == 1) {
            response.sendRedirect("javascript:history.back()");
            return;
        }
    %>
    <div class="main">
        <div class="row news">
            <div class="col-md-1"></div>
            <div class="col-md-10 contents">
                <div class="content">
                    <%
                        AccountDTO accLogin = (AccountDTO) session.getAttribute("LOGIN_ACCOUNT");
                        boolean check = true;
                        String userID = "";
                        if (accLogin == null) {
                            check = false;
                        } else {
                            userID = accLogin.getAccountID();
                        }
                        BookDTO book = (BookDTO) request.getAttribute("DETAIL_BOOK");
                        if (book != null) {
                    %>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="bookDetailImage text-center container-fluid">
                                <img style="width: 80%;height: 80%;" src="<%= book.getImage()%>"/>
                            </div>
                        </div>
                        <div class="col-md-8 container-fluid">
                            <h3><strong><%= book.getBookName()%></strong></h3>
                            <table width="100%">
                                <dl>
                                    <dt><strong>&emsp;&emsp;BookID </strong></dt>
                                    <dd>&emsp;&emsp;<%= book.getBookID()%></dd>
                                </dl>
                                <dl>
                                    <dt><strong>&emsp;&emsp;Authors </strong></dt>
                                    <dd>&emsp;&emsp;<%= book.getAuthor()%></dd>
                                </dl>
                                <dl>
                                    <dt><strong>&emsp;&emsp;Publisher </strong></dt>
                                    <dd>&emsp;&emsp;<%= book.getPublisher()%></dd>
                                </dl>
                                <dl>
                                    <dt><strong>&emsp;&emsp;Publication Year </strong></dt>
                                    <dd>&emsp;&emsp;<%= book.getPublishYear()%></dd>
                                </dl>
                                <dl>
                                    <dt><strong>&emsp;&emsp;Language </strong></dt>
                                    <dd>&emsp;&emsp;<%= book.getLanguage()%></dd>
                                </dl>
                                <dl>
                                    <dt><strong>&emsp;&emsp;DDC </strong></dt>
                                    <dd>&emsp;&emsp;<%= book.getDDC()%></dd>
                                </dl>
                                <dl>
                                    <dt><strong>&emsp;&emsp;Bookshelf </strong></dt>
                                    <dd>&emsp;&emsp;<%= book.getBookshelf()%></dd>
                                </dl>
                                <dl>
                                    <dt><strong>&emsp;&emsp;Quantity </strong></dt>
                                    <%
                                        if (book.getQuantity() < 1) {
                                    %>
                                    <dd  style="color: #f00">&emsp;&emsp;Out of stock</dd>
                                    <%
                                    } else {
                                    %>
                                    <dd style="color: #00b050">&emsp;&emsp;<%= book.getQuantity()%></dd>
                                    <%
                                        }
                                    %>
                                    
                                </dl>
                                <dl>
                                    <dt><strong>&emsp;&emsp;Book Tag </strong></dt>
                                    <dd class="list-unstyled">
                                    <li>
                                        <%
                                            List<CategoryDTO> listCategory = (List<CategoryDTO>) request.getAttribute("LIST_CATEGORY");
                                            if (listCategory != null) {
                                                for (CategoryDTO c : listCategory) {
                                        %>
                                        &emsp;&emsp;<a href="MainController?action=AdvanceSearch&categoryId=<%=c.getCategoryID()%>" style="font-size: 12px; background: #F3F3F3; border: 1px solid #E8E8E8;
                                                       display: inline-block; color: #000; padding: 2px 5px; text-align: center;"><%=c.getCategoryName()%></a>
                                        <%
                                                }
                                            }
                                        %>
                                    </li>
                                    </dd>
                                </dl>
                            </table>
                        </div>
                        <dl style="padding-left: 50px;padding-right: 50px;">
                            <dt><strong>Description </strong></dt>
                            <dd><%= book.getDescription()%></dd>
                        </dl>

                        <div style="text-align: center;">
                            <%
                                if (book.getQuantity() < 1) {
                            %>
                            <button type="button" style="background-color: deeppink;color: #ffffff;font-size: 20px;"onclick="book()" class="bookingButton btn btn-light btn-sm">Add to wish list</button>
                            <%
                            } else {
                            %>
                            <button type="button" style="background-color: deeppink;color: #ffffff;font-size: 20px;"onclick="book()" class="bookingButton btn btn-light btn-sm">Book</button>
                            <%
                                }
                            %>
                            <button style="font-size: 20px;"type="button" class="btn btn-dark btn-sm"><a style="color: #FFFFFF" href="HomeController">Back</a></button>
                        </div>
                    </div>
                    <%
                        }
                    %>
                    <div >
                        <form action="MainController">
                            <input type ="hidden" name="userID" value="<%=userID%>"/>
                            <input type="hidden" name="bookID" value="<%=book.getBookID()%>"/>
                            <div> <br></div>
                            <div class="comment_heading" style="background: #FFFFFF; color: #000">
                                <label class="col-md-12 text-center">- - - - - - - Your Comment - - - - - - -</label>
                                <textarea class="form-control" type="text" name="comment" required="" placeholder="What do you think about this book?"/></textarea>
                                <div class="stars">
                                    <input class="star star-5" id="star-5" type="radio" name="star" value="5" required=""/>
                                    <label class="star star-5" for="star-5"></label>
                                    <input class="star star-4" id="star-4" type="radio" name="star" value="4" required=""/>
                                    <label class="star star-4" for="star-4"></label>
                                    <input class="star star-3" id="star-3" type="radio" name="star" value="3" required=""/>
                                    <label class="star star-3" for="star-3"></label>
                                    <input class="star star-2" id="star-2" type="radio" name="star" value="2" required=""/>
                                    <label class="star star-2" for="star-2"></label>
                                    <input class="star star-1" id="star-1" type="radio" name="star" value="1" required=""/>
                                    <label class="star star-1" for="star-1"></label>
                                </div>
                            </div>
                            <div class="feedbackButton">
                                <button type="submit"  class="btn btn-light btn-sm"  name="action" value="CreateFeedback">Feedback</button>
                            </div>
                            <div><br></div>
                        </form>

                    </div>
                    <div style=" text-align: center"> 
                        <h2 class="comment_heading" style="background: #FFFFFF; color: #000"> COMMENT BY OTHER USER</h2>
                    </div> 
                    <%
                        FeedbackDAO dao = new FeedbackDAO();
                        List<FeedBackDTO> listFeedback = dao.getFeedbackList(book.getBookID());

                        if (listFeedback != null) {
                            if (listFeedback.size() > 0) {
                    %>
                    <div>
                        <div>                    
                            <%
                                for (FeedBackDTO feedback : listFeedback) {
                            %>                
                            <div class="box_comment">
                                <div >
                                    <div >
                                        <div class="user_name">
                                            <strong><%= feedback.getUserID()%></strong>
                                        </div>
                                        <div >                                           
                                            <%
                                                int star;
                                                for (star = 1; star <= feedback.getStar(); star++) {
                                            %>
                                            <i class="fas fa-star " style="color : #ffcc00 " ></i>
                                            <%
                                                }
                                            %>
                                        </div>    
                                        <div class = "client_comment"> 
                                            <div><%= feedback.getComment()%></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%
                                        }
                                    }
                                }
                            %>
                        </div>
                    </div> 
                </div>
                <div class="col-md-1"></div>
            </div>
            <div class="contents row">
                <div class="col-md-1"></div>
                <div class="col-md-10 contents">
                    <div class="content">
                        <hr>
                    </div>
                </div>
                <div class="col-md-1"></div>
            </div>
            <div class="introduce row">
                <div class="col-md-1"></div>
                <div class="col-md-10 contents">

                    <section class="section-name padding-y-sm">
                        <div class="container">

                            <header class="section-heading">

                                <h3 style="text-align: center"class="section-title">New Arrival</h3>
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
                        </div>
                    </section>
                </div><!-- container // -->

            </div>
        </div>           
        <jsp:include page="footer.jsp"></jsp:include>

            <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
            <script>
                                function book() {
                                    if (<%=check%>) {
                                        if (<%=book.getQuantity() > 0%>)
                                        {
                                            Swal.fire({
                                                title: 'Are you sure?',
                                                text: "You want to borrow this book",
                                                icon: 'question',
                                                showCancelButton: true,
                                                confirmButtonColor: '#F5D98F',
                                                cancelButtonColor: '#F7E5D7',
                                                confirmButtonText: 'Yes, I want.'
                                            }).then((result) => {
                                                if (result.isConfirmed) {
                                                    location.href = "MainController?action=Borrow&bookID=<%= book.getBookID()%>";
                                                }
                                            });
                                        } else {
                                            Swal.fire({
                                                title: 'Are you sure?',
                                                text: "You want to add this book to your wish list.",
                                                icon: 'question',
                                                showCancelButton: true,
                                                confirmButtonColor: '#F5D98F',
                                                cancelButtonColor: '#F7E5D7',
                                                confirmButtonText: 'Yes, I want.'
                                            }).then((result) => {
                                                if (result.isConfirmed) {
                                                    location.href = "WishListController?bookID=<%= book.getBookID()%>";
                                                }
                                            });
                                        }
                                    } else {
                                        location.href = "MainController?action=Borrow&bookID=<%= book.getBookID()%>";
                                    }

                                }
        </script>

        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <%
            String mess = (String) request.getAttribute("message");
            if (mess != null) {
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
</body>
</html>

