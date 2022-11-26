package br.com.fiap.store.bo;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.fiap.store.exception.EmailException;

public class EmailBO {
	public void sendEmail(String receiver, String subject, String message) throws EmailException {
		final String emailSender = "pedrohlucena413@gmail.com";
		final String password = "Adorolasanha413!";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Authenticator authenticator = new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailSender, password);
			}
		  };

		Session session = Session.getInstance(props, authenticator);
		  
		try {
			Message email = new MimeMessage(session);
			email.setFrom(new InternetAddress(emailSender));
			email.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
			email.setSubject(subject);
			email.setText(message);

			Transport.send(email);
		} catch (MessagingException e) {
			throw new EmailException("Erro ao enviar o e-mail");
		}
	}
}