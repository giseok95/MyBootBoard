package kr.gi.mybootboard.reply;

public interface ReplyService {

	   void insertReply(Reply reply);
	   
	   void deleteReply(Reply reply);
	   
	   Reply getReply(Reply reply);
}
