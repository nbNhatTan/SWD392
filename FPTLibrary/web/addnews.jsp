<%-- 
    Document   : addnews
    Created on : Jul 21, 2022, 3:27:38 PM
    Author     : anhkhoa
--%>

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
            
        <div class="main container ">

              
                <h3 class="title-text">ADD NEWS</h3>

                <div class = "container-news">
                    <form action="MainController">
                        <div></div>
                        
                        <div class="news_box">
                            <label for=""> Writer Name:</label>
                            <input class="form-control" name="writerName" type="text" placeholder="Enter Writter Name" required="">
                            
                        </div>


                        <div class="news_box">
                            <label for="" class="titlenews">News title:</label>
                            <input class="form-control" name="title" type="text" placeholder="Enter news title" required="">
                            
                        </div>


                        <div class="news_box">
                            <label for="" >Head:</label>
                            <input class="form-control" name="head" type="text" placeholder="Enter news head" required="">
                            
                        </div> 


                        <div class="news_box">
                            <label for="" >Body:</label>
                            <textarea class="form-control" row="8"  name="body" type="text" placeholder="Enter news body" required=""></textarea> 
                            
                        </div>

                        <div class="news_box">
                            <label class="" for="">Upload date:</label>
                            <input class="form-control" name="uploadDate" type="date" placeholder="Select DateTime" required="">                            
                            
                        </div>



                        <div class="addNewsButton" style="padding: 40px;margin-left: 38%;"><button class="btn btn-warning btn-sm " type="submit" name="action" value="AddNews">Add News</button>
                        <button onclick="history.back()" type="button" class="btn btn-dark btn-sm">Back</button></div>


                    </form>
                </div>



            </div>




        <jsp:include page="footer.jsp"></jsp:include>
    </body>






</html>