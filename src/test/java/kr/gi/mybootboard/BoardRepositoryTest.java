package kr.gi.mybootboard;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import kr.gi.mybootboard.board.entity.Board;
import kr.gi.mybootboard.board.repository.BoardRepository;
import kr.gi.mybootboard.domain.Role;
import kr.gi.mybootboard.member.entity.Member;
import kr.gi.mybootboard.member.repository.MemberRepository;
import kr.gi.mybootboard.reply.repository.ReplyRepository;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	private BoardRepository boardRepo;
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private ReplyRepository replyRepo;
	@Autowired
	private PasswordEncoder encoder;

	//@Test
	public void testInsert() {

//		Member member1 = new Member();
//
//		member1.setId("member");
//		member1.setPassword(encoder.encode("member123"));
//		member1.setName("랄로");
//		member1.setRole(Role.ROLE_MEMBER);
//		member1.setEnabled(true);
//		memberRepo.save(member1);

		Member member2 = new Member();

		member2.setId("admin");
		member2.setPassword(encoder.encode("admin123"));
		member2.setName("파카");
		member2.setRole(Role.ROLE_ADMIN);
		member2.setEnabled(true);
		memberRepo.save(member2);

//		for (int i = 1; i <= 13; i++) {
//			Board board = new Board();
//			board.setMember(member1);
//			board.setTitle(member1.getName() + "가 등록한 게시글" + i);
//			board.setContent(member1.getName() + "가 등록한 게시글" + i);
//			board.setCategory("자유");
//			boardRepo.save(board);
//		}

		for (int i = 1; i <= 3; i++) {
			Board board = new Board();
			board.setMember(member2);
			board.setTitle(member2.getName() + "가 등록한 게시글" + i);
			board.setContent(member2.getName() + "가 등록한 게시글" + i);
			board.setCategory("공지");
			boardRepo.save(board);
		}

//		log.info(member1.toString());
		log.info(member2.toString());
	}

	
//	@Test
	public void testdelete() {
		memberRepo.deleteById("admin");
	}
} // class
