package kr.gi.mybootboard.member.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import kr.gi.mybootboard.board.entity.Board;
import kr.gi.mybootboard.domain.Role;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "boardList")
@Entity
public class Member {
	@Id
	private String id;
	
	private String password;
	private String name;
	@Enumerated(EnumType.STRING)
	private Role role;
	private boolean enabled;

	@OneToMany(mappedBy = "member", fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
	private List<Board> boardList = new ArrayList<Board>();
	
}
