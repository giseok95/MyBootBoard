package kr.gi.mybootboard.Mailer;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class GoogleSMTPMailSender {
	
	@Autowired
	private JavaMailSender javaEmailSender;
	
	private static final String FROM_ADDRESS="gost450@gmail.com";

	
	// 첨부파일이 있는 메일 발송
	public void sendMessageWithAttachment(MailDTO mailDTO) throws Exception {

		MimeMessage message = javaEmailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom(FROM_ADDRESS);
		helper.setTo(mailDTO.getTo());
		helper.setSubject(mailDTO.getSubject());
		helper.setText(mailDTO.getContent());
		
		for (String attachFileName : mailDTO.getFilePath()) {
			File file = new File(attachFileName);
			helper.addAttachment(file.getName()
				, new FileSystemResource(file));			
		}

		javaEmailSender.send(message);
	}
	
} // class
