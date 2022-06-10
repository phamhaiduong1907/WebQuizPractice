/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Email;

/**
 *
 * @author ADMIN
 */
//https://www.youtube.com/watch?v=O-sTJbeUagE&t=61s
public class EmailUtils {

    private final String KEY = "dsacnq&23nd3";
    private final int EXPIRED = 30;

    public static void send(Email email) throws Exception {
//        Properties props = new Properties();
//        props.put("mail.imap.ssl.enable", "true"); // required for Gmail
//        props.put("mail.imap.auth.mechanisms", "XOAUTH2");
//        Session session = Session.getInstance(props);
//        Store store = session.getStore("imap");
//        store.connect("imap.gmail.com", username, oauth2_access_token);

        Properties prop = new Properties();
//
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.host", "smtp.gmail.com");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(email.getFrom(), email.getFromPassword());
            }
        });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(email.getFrom()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
            message.setSubject(email.getSubject());
            message.setContent(email.getContent(), "text/html; charset=utf-8");

            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public String createToken(String username) {
        Instant now = Instant.now();

        return Jwts.builder()
                .setSubject("username")
                .setAudience("user")
                .claim("username", username)
                .signWith(SignatureAlgorithm.HS512, KEY)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(EXPIRED, ChronoUnit.MINUTES)))
                .compact();
    }

    public String readUsernameFromToken(String token) {
        Jws<Claims> results = Jwts.parser()
                .requireAudience("user")
                .setSigningKey(KEY)
                .parseClaimsJws(token);
        return results.getBody().get("username", String.class);
    }
}
