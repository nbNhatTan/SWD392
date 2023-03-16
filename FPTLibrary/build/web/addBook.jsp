<%-- 
    Document   : addBook
    Created on : Jun 16, 2022, 11:04:28 AM
    Author     : Admin
--%>

<%@page import="sample.DTO.PackageDTO"%>
<%@page import="sample.DTO.BookDTO"%>
<%@page import="sample.DTO.AccountDTO"%>
<%@page import="sample.DTO.BookError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width" , initial-scale="1" />
        <title>Add Book</title>
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
        <div class="container">
            <div class="row">
                <div class="col-md-5 mx-auto">
                    <div id="first">
                        <div class="myform form ">
                            <div class="logo mb-3">
                                <div class="col-md-12 text-center">
                                    <h3>Add New Book</h3>
                                </div>
                            </div>
                            <%
                                BookError bookError = (BookError) request.getAttribute("BOOK_ERROR");
                                if (bookError == null) {
                                    bookError = new BookError();
                                }
                                BookDTO book = (BookDTO) request.getAttribute("BOOK");
                                if(book == null){
                                    book = new BookDTO();
                                }
                                PackageDTO pack = (PackageDTO) request.getAttribute("PACKAGE");  
                                if(pack == null){
                                    pack = new PackageDTO();
                                }
                            %>
                            <form action="MainController">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Book ID:</label>
                                    <input name="bookID" type="text" placeholder="Can be auto fill"  class="form-control" value="<%=book.getBookID()%>"><%= bookError.getBookID()%>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Book Name:</label>
                                    <input name="bookName" type="text" placeholder="Enter book name"  class="form-control" required="" value="<%=book.getBookName()!=null?book.getBookName():""%>"><%= bookError.getBookNameError()%>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Quantity:</label>
                                    <input name="quantity" type="text" placeholder="Enter quantity"  class="form-control" required="" value="<%=String.valueOf(book.getQuantity())!=null?book.getQuantity():""%>"><%= bookError.getQuantityError()%>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Bookshelf:</label>
                                    <input name="bookshelf" type="text" placeholder="Enter bookshelf"  class="form-control" required="" value="<%=book.getBookshelf()!=null?book.getBookshelf():""%>"><%= bookError.getBookshelfError()%>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Language:</label>
                                    <input name="language" type="text" placeholder="Enter language" class="form-control" required="" value="<%=book.getLanguage()!=null?book.getLanguage():""%>"><%= bookError.getLanguageError()%>
                                </div>

                                <div class="form-group">
                                    <label for="exampleInputEmail1">Author:</label>
                                    <input name="author" type="text" placeholder="Enter author"  class="form-control" required="" value="<%=book.getAuthor()!=null?book.getAuthor():""%>"><%= bookError.getAuthorError()%>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Publisher:</label>
                                    <input name="publisher"type="text" placeholder="Enter publisher"   class="form-control" required="" value="<%=book.getPublisher()!=null?book.getPublisher():""%>"><%= bookError.getPublisherError()%>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Description:</label>
                                    <textarea  style="height: 200px;" name="description" type="text" placeholder="Enter description"  class="form-control" required="" ><%=book.getDescription()!=null?book.getDescription():""%></textarea><%= bookError.getDescriptionError()%>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">DDC:</label>
                                    <input  name="DDC" type="text" placeholder="Enter DDC"  class="form-control" required="" value="<%=book.getDDC()!=null?book.getDDC():""%>"><%= bookError.getDDCError()%>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Publish Year:</label>
                                    <input  name="publishYear" type="text" placeholder="Enter publishYear"  class="form-control" required="" value="<%=book.getPublishYear()!=null?book.getPublishYear():""%>"><%= bookError.getPublishYearError()%>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Image:</label>
                                    <input  name="image" type="text" placeholder="Enter url image"  class="form-control" required="" value="<%=book.getImage()!=null?book.getImage():""%>"><%= bookError.getImageError()%>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Package Name:</label>
                                    <input  name="packageName" type="text" placeholder="Enter packageName"  class="form-control" required="" value="<%=pack.getPackageName()!=null?pack.getPackageName():""%>"><%= bookError.getPackageNameError()%>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Price:</label>
                                    <input  name="price" type="text" placeholder="Enter price"  class="form-control" required="" value="<%=String.valueOf(pack.getPrice())!=null?String.valueOf(pack.getPrice()):""%>"><%= bookError.getPriceError()%>
                                </div>

                                <div class="col-md-12 text-center mb-3">
                                    <button class=" btn btn-block mybtn btn-warning tx-tfm" type="submit" name="action" value="AddBook" >Add</button>
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
