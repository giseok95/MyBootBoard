package kr.gi.mybootboard.board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.gi.mybootboard.board.entity.Board;

public interface BoardRepository extends CrudRepository<Board, Long>, QuerydslPredicateExecutor<Board> {

	@Query(" SELECT b FROM Board b ")
	Page<Board> listBoard(Pageable pageable);
	
	@Query(" SELECT b FROM Board b ")
	List<Board> listBoard(Board board);
}
