<%-- 
    Document   : about
    Created on : Jun 30, 2022, 10:24:43 AM
    Author     : NhatTan
--%>

<%@page import="sample.DTO.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width" , initial-scale="1" />
        <title>Thư viện FPTU HCM</title>
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

        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        
    </head>
    <body>
        <style>
            .contents {
                background-image: url('./image/background.jpg');
                background-repeat: no-repeat;
                background-size: cover;
                background-attachment: fixed; 
                background-size: 100% 100%;
            }
        </style>
        <jsp:include page="header.jsp"></jsp:include>
        <%
            AccountDTO acc = (AccountDTO) session.getAttribute("LOGIN_ACCOUNT");
            if (acc != null && acc.getRoleID() == 1) {
                response.sendRedirect("javascript:history.back()");
                return;
            }
        %>
            <div class="main">
                <div class="row contents">
                    <div class="col-md-2"></div>
                    <div class="col-md-8 content">
                        <div style="margin: 50px;">
                        <h3 style="text-align: center;">NỘI QUY THƯ VIỆN</h3>
                        <br>
                        <br>
                        <p>Xuất phát từ mục đích xây dựng Thư viện Trường đại học FPT trở thành nơi học tập, nghiên
                            cứu thuận tiện, văn minh, khai thác hiệu quả các nguồn tài liệu phục vụ cho học tập, các sinh viên
                            phải tuyệt đối tuân thủ các nội quy sau:</p>
                        <br>
                        <h6><strong>Điều 1:</strong> Thẻ Thư viện</h6>
                        <p>Thẻ sinh viên/ nhân viên đồng thời là thẻ thư viện để sử dụng các dịch vụ của thư viện.</p>
                        <br>
                        <h6><strong>Điều 2:</strong> Giờ mở cửa</h6>
                        <p>Thứ Hai – Thứ Sáu: 8:00 – 18:00</p>
                        <p>Thứ Bảy : 8:00 – 12:00</p>
                        <br>
                        <h6><strong>Điều 3:</strong> Các dịch vụ của Thư viện</h6>
                        <p>3.1. Không gian học tập chung;</p>
                        <p>3.2. Dịch vụ mượn/ trả tài liệu;</p>
                        <p>3.3. Truy cập nguồn tài nguyên điện tử;</p>
                        <p>3.4. Đề nghị bổ sung tài liệu;</p>
                        <p>3.5. Hỗ trợ giảng dạy/học tập/nghiên cứu;</p>
                        <p>3.6. Đặt phòng học nhóm cho cán bộ giảng viên/nhân viên</p>
                        <p>3.7. Truy cập Internet miễn phí</p>
                        <br>
                        <h6><strong>Điều 4:</strong> Các quy định chung khi vào thư viện</h6>
                        <p>4.1. Xuất trình thẻ sinh viên khi vào Thư viện. Không dùng thẻ của người khác và không cho
                            người khác mượn thẻ của mình.</p>
                        <p>4.2. Giữ gìn trật tự, đi nhẹ, nói khẽ.</p>
                        <p>4.3. Giữ gìn vệ sinh chung: không hút thuốc lá, không viết, vẽ lên mặt bàn, không ngồi gác</p>
                            chân lên ghế, vứt rác vào nơi quy định.
                        <p>4.4. Không được mang vào thư viện đồ ăn, uống, chất độc hại, chất cháy nổ...</p>
                        <p>4.5. Tắt chuông điện thoại, không nói chuyện điện thoại trong thư viện.</p>
                        <p>4.6. Không viết bút chì, bút mực hoặc sử dụng bút đánh dấu lên sách;</p>
                        <p>4.7. Không gập hoặc làm nhàu nát, rách sách;</p>
                        <p>4.8. Không để sách bị ẩm ướt, mốc hoặc hư hỏng dưới bất kỳ hình thức nào</p>
                        <br>
                        <h6><strong>Điều 5:</strong> Quy định khi mượn/trả tài liệu</h6>
                        <p>5.1. Sau khi đọc sách xong thì để sách về khu vực đã được quy định.  Không tự ý xếp sách lên giá;</p>
                        <p>5.2. Không mang bất cứ tài liệu thư viện nào ra khỏi phòng trừ những tài liệu đã làm thủ tục mượn về;</p>
                        <p>5.3. Đối với giáo trình, tài liệu học tập: Mỗi sinh viên được mượn 1 bộ giáo trình, tài liệu dùng cho kỳ 
                            học hiện tại theo danh mục xếp lớp của phòng Đào tạo. Những tài liệu giáo trình này bạn đọc được gia hạn 
                            tối đa 1 tuần khi có lý do hợp lý;</p>
                        <p>5.4. Đối với sách giáo trình được mượn như tài liệu tham khảo sau 01 tuần khi Block mới bắt đầu, hạn cuối 
                            cùng phải trả bằng hạn trả giáo trình của Block hiện tại;</p>
                        <p>5.5. Đối với tài liệu tham khảo: Bạn đọc được mượn tối đa 10 bản, trong thời hạn 1 tuần/ tài liệu tiếng Việt, 
                            2 tuần/ tài liệu ngoại văn và được gia hạn 4 lần;</p>
                        <p>5.6. Người mượn có trách nhiệm kiểm tra tình trạng thực tế của tài liệu đã được ghi mượn trước khi mang ra 
                            khỏi thư viện, đồng thời giữ gìn, bảo quản tài liệu trong thời gian mượn. Khi phát hiện hư hỏng, người mượn 
                            cuối cùng của tài liệu đó sẽ chịu trách nhiệm bồi thường theo quy định.</p>
                        <br>
                        <h6><strong>Điều 6:</strong> Xử lý vi phạm nội quy thư viện</h6>
                        <p>6.1 Sinh viên vi phạm các quy định tại Điều 4, 5 tùy theo mức độ và lần vi phạm có thể bị nhắc nhở, khiển 
                            trách và mời ra khỏi thư viện; lập biên bản cảnh cáo, tạm ngừng sử dụng các dịch vụ thư viện hoặc sẽ bị 
                            tước quyền sử dụng các dịch vụ thư viện vĩnh viễn, tạm thời đình chỉ học tập hoặc buộc thôi học;</p>
                        <p>6.2 Trường hợp làm hư hại (như long bìa, nhàu nát, bôi bẩn, viết, vẽ, mất trang...) hoặc mất tài liệu, sinh 
                            viên phải bồi thường thiệt hại tương đương với giá trị của tài liệu;</p>
                        <p>6.3 Trường hợp mượn tài liệu quá hạn sẽ phải chịu tiền phạt là: 5.000 đồng/ngày/1 cuốn kể cả ngày nghỉ;</p>
                        <p>6.4 Trong bất cứ trường hợp vi phạm nào, sinh viên phải đền bù những thiệt hại (nếu có) do hành vi vi phạm của mình gây ra.</p>
                        <br>
                        <br>
                        <h6 style="text-align: right;">TRUNG TÂM THÔNG TIN - THƯ VIỆN</h6>
                        </div>
                    </div>
                    <div class="col-md-2"></div>
                </div>
            </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
