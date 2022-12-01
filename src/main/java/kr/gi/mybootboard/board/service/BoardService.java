package kr.gi.mybootboard.board.service;

import org.springframework.data.domain.Page;

import kr.gi.mybootboard.board.entity.Board;
import kr.gi.mybootboard.board.entity.Search;

public interface BoardService {

	Page<Board> listBoard(Search search);

	void insertBoard(Board board);

	void updateBoard(Board board);

	void deleteBoard(Board board);

	Board getBoard(Board board);
	
}
