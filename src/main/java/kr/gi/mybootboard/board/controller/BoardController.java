package kr.gi.mybootboard.board.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import kr.gi.mybootboard.board.entity.Board;
import kr.gi.mybootboard.board.entity.Search;
import kr.gi.mybootboard.board.service.BoardService;
import kr.gi.mybootboard.files.FilesRepository;
import kr.gi.mybootboard.files.FilesSerivceImpl;
import kr.gi.mybootboard.files.FilesService;
import kr.gi.mybootboard.member.entity.Member;
import kr.gi.mybootboard.security.SecurityUser;

@SessionAttributes("member")
@Controller
public class BoardController {

	@Autowired
	private FilesService fileService;
	
	@Autowired
	private FilesRepository fileRepo;
	
	@Autowired
	private BoardService boardService;

	@ModelAttribute
	public Member setMember() {
		return new Member();
	}

	@RequestMapping("/board/listBoard")
	public String listBoard(Model model, Search search) {
		if (search.getSearchCondition() == null) 
			search.setSearchCondition("TITLE");
		if (search.getSearchKeyword() == null) 
			search.setSearchKeyword("");
		if (search.getSearchCategory() == null) 
			search.setSearchCategory("");
		Page<Board> boardList = boardService.listBoard(search);
		
		if(boardList.getNumberOfElements()==0){
            search.setPage(1);
        }else {
            search.setPage(boardList.getTotalPages());
        }
		
		model.addAttribute("boardList", boardList);
		int totalPage = search.getPage();
		model.addAttribute("totalPage", totalPage);
		return "/board/listBoard";
	}

	@GetMapping("/board/insertBoard")
	public String insertBoard() {
		return "/board/insertBoard";
	}

	@PostMapping("/board/insertBoardProc")
	public String insertBoardProc(Board board, @AuthenticationPrincipal SecurityUser principal,
			@RequestParam("files") List<MultipartFile> multifiles) throws IOException {
		
		for(MultipartFile multipartFile: multifiles) {
	         if(!multipartFile.isEmpty()) {
	            fileService.insertFiles(multipartFile).setBoard(board);
	         }
	      }
		board.setMember(principal.getMember());
		boardService.insertBoard(board);
		return "redirect:/board/listBoard";
	}

	@GetMapping("/board/getBoard")
	public String getBoard(Board board, Model model, @AuthenticationPrincipal SecurityUser principal) {
		System.out.println(boardService.getBoard(board));
		model.addAttribute("board", boardService.getBoard(board));
		model.addAttribute("member", principal.getMember());
		return "/board/getBoard";
	}

	@PostMapping("/board/updateBoardProc")
	public String updateBoardProc(Board board) {
		boardService.updateBoard(board);
		return "forward:listBoard";
	}

	@GetMapping("board/deleteBoard")
	public String deleteBoard(Board board) {
		boardService.deleteBoard(board);
		return "forward:listBoard";
	}
	


}
