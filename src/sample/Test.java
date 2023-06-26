package sample;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class Test {
    public static void sendEmail(String recipient, String subject, String content) {
        // Địa chỉ email và mật khẩu của người gửi
        String senderEmail = "toanhuynh1114@gmail.com";
        String senderPassword = "@Hay123456789";

        // Cấu hình thông tin SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.example.com");
        properties.put("mail.smtp.port", "587");

        // Tạo đối tượng Session để xác thực người gửi
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Tạo đối tượng MimeMessage
            MimeMessage message = new MimeMessage(session);

            // Đặt thông tin người gửi và người nhận
            message.setFrom(new InternetAddress(senderEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            // Đặt tiêu đề và nội dung email
            message.setSubject(subject);
            message.setText(content);

            // Gửi email
            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String recipient = "thienduyen836@gmail.com";
        String subject = "Hello";
        String content = "This is a test email.";

        sendEmail(recipient, subject, content);
    }
}
