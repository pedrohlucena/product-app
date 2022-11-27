package br.com.fiap.store.bo;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.fiap.store.exception.EmailException;
import br.com.fiap.store.utils.DotEnvManager;
import io.github.cdimascio.dotenv.Dotenv;

public class EmailBO {
	public void sendEmail(String receiver, String subject, String message) throws EmailException {
		Dotenv dotenv = DotEnvManager.getEnvVars();
		
		final String emailSender = dotenv.get("EMAIL_SENDER_LOGIN");
		final String password = dotenv.get("EMAIL_SENDER_PASSWORD");

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailSender, password);
			}
		  });
		  
		try {
			Message email = new MimeMessage(session);
			email.setFrom(new InternetAddress(emailSender));
			email.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
			email.setSubject(subject);
			email.setText(message);

			Transport.send(email);
		} catch (MessagingException e) {
			throw new EmailException();
		}
	}
}