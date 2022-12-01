package kr.gi.mybootboard.Mailer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.gi.mybootboard.board.entity.Board;
import kr.gi.mybootboard.board.service.BoardService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MailController {

	private final MailService mailService;
	
	@Autowired
	private BoardService boardService;
	 
	@GetMapping("/mail")
    public String dispMail() {
		return "getBoard";
    }
 
 
    @PostMapping("/mail")
    public String sendMail(MailDTO mailDto, Board board, Model model) {
    	model.addAttribute("board", boardService.getBoard(board));
    	mailService.sendAttachMail(mailDto);
        return "redirect:/board/listBoard";
    }
}
