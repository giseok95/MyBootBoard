package kr.gi.mybootboard.Mailer;

import java.io.Serializable;

import lombok.Data;

@Data
public class MailDTO implements Serializable {
	
	public static final long serialVersionUID = 9823477983284L;
	
	public MailDTO() {
	}
	
	public MailDTO(String from, String to, String subject, String content, String[] filePath) {
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.content = content;
		this.filePath = filePath;
	}
	
	private String from;		// 발신자 이메일
	private String to;			// 수신자 이메일
	private String subject;		// 메일 제목
	private String content;		// 메일 내용
	private String[] filePath;	// 첨부파일

} // class





