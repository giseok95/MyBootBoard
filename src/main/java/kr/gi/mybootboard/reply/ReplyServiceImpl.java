package kr.gi.mybootboard.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.gi.mybootboard.reply.repository.ReplyRepository;

@Service
public class ReplyServiceImpl implements ReplyService {

	   @Autowired
	   ReplyRepository replyRepo;
	   
	   @Override
	   public void insertReply(Reply reply) {
	      replyRepo.save(reply);
	   }

	   @Override
	   public void deleteReply(Reply reply) {
	      replyRepo.deleteById(reply.getRno());
	   }
	   
	   @Override
	   public Reply getReply(Reply reply) {
	      return replyRepo.findById(reply.getRno()).get();
	   }

}
