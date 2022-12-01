package kr.gi.mybootboard.board.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;

import kr.gi.mybootboard.board.entity.Board;
import kr.gi.mybootboard.board.entity.QBoard;
import kr.gi.mybootboard.board.entity.Search;
import kr.gi.mybootboard.board.repository.BoardRepository;
import kr.gi.mybootboard.board.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Override
	public Page<Board> listBoard(Search search) {
		BooleanBuilder builder = new BooleanBuilder();

		QBoard qboard = QBoard.board;

		if (search.getSearchCondition().equals("TITLE")) {
			builder.and(qboard.title.like("%" + search.getSearchKeyword() + "%"));
			builder.and(qboard.category.like("%" + search.getSearchCategory() + "%"));
		} else if (search.getSearchCondition().equals("CONTENT")) {
			builder.and(qboard.content.like("%" + search.getSearchKeyword() + "%"));
			builder.and(qboard.category.like("%" + search.getSearchCategory() + "%"));
		} 
		Pageable pageable = PageRequest.of(search.getPage(), 10, Sort.Direction.DESC, "seq");
		
		return boardRepository.findAll(builder, pageable);
	}

	@Override
	public void insertBoard(Board board) {
		boardRepository.save(board);
	}

	@Override
	public void updateBoard(Board board) {
		Board findboard = boardRepository.findById(board.getSeq()).get();
		findboard.setTitle(board.getTitle());
		findboard.setContent(board.getContent());
		boardRepository.save(findboard);

	}

	@Override
	public void deleteBoard(Board board) {
		boardRepository.deleteById(board.getSeq());
	}

	@Override
	public Board getBoard(Board board) {
		 Board findBoard = boardRepository.findById(board.getSeq()).get();
	     findBoard.setCnt(findBoard.getCnt() + 1);
	     boardRepository.save(findBoard);
	     return findBoard;
	}


}
