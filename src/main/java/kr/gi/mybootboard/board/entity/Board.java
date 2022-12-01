package kr.gi.mybootboard.board.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import kr.gi.mybootboard.files.Files;
import kr.gi.mybootboard.member.entity.Member;
import kr.gi.mybootboard.reply.Reply;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = {"member","replyList","fileList"})
@AllArgsConstructor
@Entity
public class Board {

	public Board() {

	}

	public Board(String title, String content, String category) {
		this.title = title;
		this.content = content;
		this.category = category;
	}

	@Id
	@GeneratedValue
	private Long seq;

	@Column(nullable = true)
	private String category;

	private String title;

	private String content;

	@Column(insertable = false, updatable = false, columnDefinition = "date default sysdate")
	private Date createDate;

	@Column(updatable = true)
	private Long cnt = 0L;

	@ManyToOne
	@JoinColumn(name = "MEMBER_ID", nullable = false, updatable = false)
	private Member member;

	public void setMember(Member member) {
		this.member = member;
		member.getBoardList().add(this);
	}
	
	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Reply> replyList = new ArrayList<Reply>();
	
	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
	private List<Files> fileList = new ArrayList<Files>();

}// class
