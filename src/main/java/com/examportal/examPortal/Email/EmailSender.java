//package com.examportal.examPortal.Email;
//import jakarta.mail.*;
//import jakarta.mail.internet.InternetAddress;
//import jakarta.mail.internet.MimeMessage;
//
//import java.util.Properties;
//
//public class EmailSender {
//   public boolean sendEmail(String to,String from,String subject,String text){
//       boolean flag=false;
//       Properties properties = new Properties();
//       properties.put("mail.smtp.auth",true);
//       properties.put("mail.smtp.port",587);
//       properties.put("mail.smtp.host","smtp.gmail.com");
//
//       String userName = "sahootusar009";
//       String password = "";
//
//       //Session
//       Session session = Session.getInstance(properties, new Authenticator() {
//           @Override
//           protected PasswordAuthentication getPasswordAuthentication() {
//               return new PasswordAuthentication(userName, password);
//           }
//       });
//     try{
//         Message message = new MimeMessage(session);
//         message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
//         message.setFrom(new InternetAddress(from));
//         message.setSubject(subject);
//         message.setText(text);
//         Transport.send(message);
//         flag = true;
//     } catch (Exception e) {
//         e.printStackTrace();
//     }
//       return false;
//   }
//}
