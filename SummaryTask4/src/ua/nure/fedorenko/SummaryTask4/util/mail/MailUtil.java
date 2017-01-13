package ua.nure.fedorenko.SummaryTask4.util.mail;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
public class MailUtil {

    public static void sendEmailRegistrationLink(String id, String email, String hash) throws AddressException, MessagingException {
        Properties props = getProperties();

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(MailSetUp.USERNAME, MailSetUp.PASSWORD);
                    }
                });

        String link = MailSetUp.REGISTRATION_SITE_LINK+"scope=activation&login="+id+"&hash="+hash;

        StringBuilder bodyText = new StringBuilder();
        bodyText.append("<div>")
                .append("  Dear User<br/><br/>")
                .append("  Thank you for registration. Your mail ("+email+") is under verification<br/>")
                .append("  Please click <a href=\""+link+"\">here</a> or open below link in browser<br/>")
                .append("  <a href=\""+link+"\">"+link+"</a>")
                .append("  <br/><br/>")
                .append("  Thanks,<br/>")
                .append("  SodhanaLibrary Team")
                .append("</div>");
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(MailSetUp.USERNAME));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(email));
        message.setSubject("Email Registration");
        message.setContent(bodyText.toString(), "text/html; charset=utf-8");
        Transport.send(message);
    }

    public static void sendResetPasswordLink(String id, String email, String hash) throws AddressException, MessagingException {

        Properties props = getProperties();
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(MailSetUp.USERNAME, MailSetUp.PASSWORD);
                    }
                });

        String link = MailSetUp.REGISTRATION_SITE_LINK+"scope=resetPassword&login="+id+"&hash="+hash;

        StringBuilder bodyText = new StringBuilder();
        bodyText.append("<div>")
                .append("  Dear User<br/><br/>")
                .append("  We got your reset password request, Find below link to reset password <br/>")
                .append("  Please click <a href=\""+link+"\">here</a> or open below link in browser<br/>")
                .append("  <a href=\""+link+"\">"+link+"</a>")
                .append("  <br/><br/>")
                .append("  Thanks,<br/>")
                .append("  SodhanaLibrary Team")
                .append("</div>");
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(MailSetUp.USERNAME));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(email));
        message.setSubject("Reset Password");
        message.setContent(bodyText.toString(), "text/html; charset=utf-8");
        Transport.send(message);
    }
    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.port", "465");
        return properties;
    }
}
