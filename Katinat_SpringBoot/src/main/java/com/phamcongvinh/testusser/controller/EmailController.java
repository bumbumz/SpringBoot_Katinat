package com.phamcongvinh.testusser.controller;

import jakarta.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phamcongvinh.testusser.dto.OrderConfirmationRequest;
import com.phamcongvinh.testusser.dto.OrderDetailDTO;
import com.phamcongvinh.testusser.service.ProductSevice;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@RequestMapping("/api/email")
@RestController
public class EmailController {

    private final JavaMailSender mailSender;
    private final ProductSevice productService;

    public EmailController(JavaMailSender mailSender, ProductSevice productService) {
        this.mailSender = mailSender;
        this.productService = productService;
    }

    @GetMapping
    public String sendEmail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom("vinhcph554@gmail.com");
            message.setTo("tinguyen554@gmail.com");
            message.setSubject("Hello Sao nè");
            message.setText("TSSsss");

            mailSender.send(message);
            return "success!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // @GetMapping
    // public String sendEmail() {
    // try {
    // SimpleMailMessage message = new SimpleMailMessage();

    // message.setFrom("nguyenminhco2964@gmail.com");
    // message.setTo("nguyensaovn2019@gmail.com");
    // message.setSubject("Hello Sao nè");
    // message.setText("TSSsss");

    // mailSender.send(message);
    // return "success!";
    // } catch (Exception e) {
    // return e.getMessage();
    // }
    // }

    // @GetMapping("/send-email-with-attachment")
    // public String sendEmailWithAttachment() {
    // try {
    // MimeMessage message = mailSender.createMimeMessage();
    // MimeMessageHelper helper = new MimeMessageHelper(message, true);

    // helper.setFrom("nguyenminhco2964@gmail.com");
    // helper.setTo("nguyensaovn2019@gmail.comm");
    // helper.setSubject("Java email with attachment | From GC");
    // helper.setText("Please find the attached documents below");

    // helper.addAttachment("logo.png", new File("C:\\Users\\Genuine
    // Coder\\Documents\\Attachments\\logo.png"));
    // helper.addAttachment("presentation.pptx",
    // new File("C:\\Users\\Genuine
    // Coder\\Documents\\Attachments\\presentation.pptx"));

    // mailSender.send(message);
    // return "success!";
    // } catch (Exception e) {
    // return e.getMessage();
    // }
    // }

    // @GetMapping("/send-html-email")
    // public String sendHtmlEmail() {
    // try {
    // MimeMessage message = mailSender.createMimeMessage();
    // MimeMessageHelper helper = new MimeMessageHelper(message, true);

    // helper.setFrom("nguyenminhco2964@gmail.com");
    // helper.setTo("nguyensaovn2019@gmail.comm");
    // helper.setSubject("Java email with attachment | From GC");

    // try (var inputStream = Objects
    // .requireNonNull(EmailController.class.getResourceAsStream("/templates/email-content.html")))
    // {
    // helper.setText(
    // new String(inputStream.readAllBytes(), StandardCharsets.UTF_8),
    // true);
    // }
    // helper.addInline("logo.png", new File("C:\\Users\\Genuine
    // Coder\\Documents\\Attachments\\logo.png"));
    // mailSender.send(message);
    // return "success!";
    // } catch (Exception e) {
    // return e.getMessage();
    // }
    // }

    @PostMapping("/success")
    public String sendOrderConfirmation(@RequestBody OrderConfirmationRequest orderRequest) {
        try {
            StringBuilder orderDetailsHtml = new StringBuilder();
            orderDetailsHtml.append("<h3 style='color:#ffffff;'>Thông tin sản phẩm:</h3>");

            // Iterate over the order details to create HTML content
            for (OrderDetailDTO detail : orderRequest.getOrderDetails()) {
                List<Long> productIds = detail.getProductId();
                List<Integer> quantities = detail.getQuantity();
                List<Double> prices = detail.getPrice();

                // Loop through each product in the detail entry
                for (int i = 0; i < productIds.size(); i++) {
                    String productImageUrl = productService.showimage(productIds.get(i));

                    orderDetailsHtml.append("<div class='product'>")
                            .append("<img style='color: #bf9369;' src='")
                            .append(productImageUrl != null ? productImageUrl : "https://via.placeholder.com/150")
                            .append("' alt='Sản phẩm'/>")
                            .append("<div class='product-info'>")
                            .append("<p class='producttext'><strong>Mã sản phẩm:</strong> ").append(productIds.get(i))
                            .append("</p>")
                            .append("<p class='producttext'><strong>Giá:</strong> ").append(prices.get(i))
                            .append(" VND</p>")
                            .append("<p class='producttext'><strong>Số lượng:</strong> ").append(quantities.get(i))
                            .append("</p>")
                            .append("<p class='producttext'><strong>Liên kết ảnh sản phẩm:</strong> <a style='color: #bf9369;' href='")
                            .append(productImageUrl != null ? productImageUrl : "#")
                            .append("'>Xem hình ảnh</a></p>")
                            .append("</div></div>");
                }
            }

            // Build the main email content
            String emailContent = "<html>" +
                    "<head>" +
                    "<style>" +
                    "body { font-family: Arial, sans-serif; background-color: #114459; margin: 0; padding: 20px; color: white; }"
                    +
                    ".container { max-width: 600px; margin: auto; background-color: #114459; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }"
                    +
                    ".header { background-color: #114459; color: white; padding: 20px; text-align: center; border-radius: 8px 8px 0 0; }"
                    +
                    ".footer { margin-top: 20px; font-size: small; color: #666; text-align: center; }" +
                    ".product { border: 1px solid #bf9369; border-radius: 5px; overflow: hidden; display: flex; margin: 10px 0; }"
                    +
                    ".producttext { color: #ffffff; text-decoration: none; }" +
                    ".product img { max-width: 150px; height: auto; }" +
                    ".product-info { padding: 10px; color: #000; }" +
                    ".total { font-weight: bold; color: #ffffff; }" +
                    "a { color: #ffffff; text-decoration: none; }" +
                    "p { color: #ffffff; text-decoration: none; }" +
                    "a:hover { text-decoration: underline; }" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<div class='container'>" +
                    "<div class='header'>" +
                    "<img src='https://katinat.vn/wp-content/uploads/2023/12/cropped-Kat-Logo-fa-rgb-05__1_-removebg-preview.png' alt='Logo SN Mobile' style='max-width: 250px;'>"
                    +
                    "</div>" +
                    "<p>Cảm ơn <strong>" + orderRequest.getCustomerName() + "</strong> đã đặt hàng!</p>" +
                    "<p><strong>Mã đơn hàng:</strong> " + orderRequest.getOrderCode() + "</p>" +
                    "<p class='total'><strong>Tổng tiền:</strong> " + orderRequest.getTotal() + " VND</p>" +
                    "<p><strong>Địa chỉ giao hàng:</strong> " + orderRequest.getCustomerAddress() + "</p>" +
                    "<p><strong>Số điện thoại:</strong> " + orderRequest.getCustomerPhone() + "</p>" +
                    orderDetailsHtml.toString() +
                    "<p>Chúng tôi sẽ xử lý đơn hàng của bạn ngay lập tức.</p>" +
                    "<p>Để kiểm tra tình trạng đơn hàng, vui lòng nhấp vào <a style='color: #bf9369;' href='http://yourwebsite.com/order-status/"
                    + orderRequest.getOrderCode() + "'>đây</a>.</p>" +
                    "<div class='footer'>" +
                    "<p>Katinat trân trọng,<br>Đội ngũ hỗ trợ khách hàng</p>" +
                    "<p>Nếu bạn cần hỗ trợ thêm, hãy liên hệ với chúng tôi qua: " +
                    "<a style='color: #bf9369;' href='https://zalo.me/your-zalo-id'>Zalo</a> | " +
                    "<a style='color: #bf9369;' href='https://www.facebook.com/veen.coon.111'>Facebook</a></p>" +
                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("vinhcph554@gmail.com");
            helper.setTo(orderRequest.getUserEmail());
            helper.setSubject("Xác nhận đơn hàng #" + orderRequest.getOrderCode());
            helper.setText(emailContent, true);

            mailSender.send(message);
            return "Email xác nhận đã được gửi thành công!";
        } catch (Exception e) {
            return "Có lỗi xảy ra: " + e.getMessage();
        }
    }

}
