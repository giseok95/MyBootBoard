package kr.gi.mybootboard.excel;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.gi.mybootboard.board.entity.Board;


@Controller
public class ExcelController {
	@Autowired
	private ExcelService excelService;
	
	@RequestMapping(value = "/excelDown")
	public void excelDown(@ModelAttribute Board board, HttpServletResponse response
			, HttpServletRequest request) throws Exception {
		excelService.excelDown(board, response);
	}
 
}