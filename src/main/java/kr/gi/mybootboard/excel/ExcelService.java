package kr.gi.mybootboard.excel;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import kr.gi.mybootboard.board.entity.Board;



@Service
public interface ExcelService {
    
	public void excelDown(Board board, HttpServletResponse response); 
}
