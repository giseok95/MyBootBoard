package kr.gi.mybootboard.reply.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.gi.mybootboard.reply.Reply;

public interface ReplyRepository extends CrudRepository<Reply, Long>, QuerydslPredicateExecutor<Reply> {

	@Query(" SELECT b FROM Board b ")
	List<Reply> listBoard(Reply reply);
}
