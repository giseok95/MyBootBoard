package kr.gi.mybootboard.Mailer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private GoogleSMTPMailSender mailSender;

	@Override
	public void sendAttachMail(MailDTO mailDTO) {
		try {
			mailSender.sendMessageWithAttachment(mailDTO);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	

} // class




