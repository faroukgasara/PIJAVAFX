/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.reclamation.service;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ala
 */
public class Mail {
    public static void sendMail(String recepient) throws Exception{
        Properties prop=new Properties();
         System.out.println("prepare to send email");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        String myAccountEmail="faroukgasaraa@gmail.com";
        String password="Farouk1998Gassara";
        String to ="wael.bannani@esprit.tn";
        
        Session session =Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(myAccountEmail, password);
            }
            
        });
       
        try {
            MimeMessage m = new MimeMessage(session);
            m.setFrom(myAccountEmail);
            m.addRecipients(Message.RecipientType.TO, recepient);
            m.setSubject("your complaint ");
            m.setText("your complaint has been created ,we will study it soon");
            Transport.send(m);

        } catch (MessagingException e) {
        }
       
        System.out.println("message sent successfully");
    }
public static void sendMail2(String recepient) throws Exception{
        Properties prop=new Properties();
         System.out.println("prepare to send email");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        String myAccountEmail="faroukgasaraa@gmail.com";
        String password="Farouk1998Gassara";
        String to ="wael.bannani@esprit.tn";
        
        Session session =Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(myAccountEmail, password);
            }
            
        });
       
        try {
            MimeMessage m = new MimeMessage(session);
            m.setFrom(myAccountEmail);
            m.addRecipients(Message.RecipientType.TO, recepient);
            m.setSubject("your complaint ");
            m.setText("your complaint has been studied  ,we will contact  you  soon ,thank u for your patience");
            Transport.send(m);

        } catch (MessagingException e) {
        }
       
        System.out.println("message sent successfully");
    }
}
