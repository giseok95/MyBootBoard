package kr.gi.mybootboard.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.gi.mybootboard.board.entity.Board;
import kr.gi.mybootboard.security.SecurityUser;

@Controller
@RequestMapping("/reply/")
public class ReplyController {

	   @Autowired
	   private ReplyService replyService;
	   

	   @RequestMapping("/insertReply")
	   public String insertReply( Reply reply, @AuthenticationPrincipal SecurityUser principal) {
	       
	      reply.setReplyer(principal.getMember().getId());
	      replyService.insertReply(reply);
	      
	      
	      return "redirect:/board/getBoard?seq="+reply.getBoard().getSeq();
	   }

	   @RequestMapping("/deleteReply")
	   public String deleteReply(Reply reply) {
	      Board board  = replyService.getReply(reply).getBoard();
	      replyService.deleteReply(reply);
	      return "redirect:/board/getBoard?seq="+board.getSeq();
	   }
}
