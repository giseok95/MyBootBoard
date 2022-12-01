package kr.gi.mybootboard.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.gi.mybootboard.member.entity.Member;
import kr.gi.mybootboard.member.service.MemberService;

@Controller
@SessionAttributes("member")
public class LoginController {

	@Autowired
	private MemberService memberService;

	@GetMapping("/member/login")
	public String login() {
		return "/member/login";
	}

	@PostMapping("/member/login")
	public String loginProc(Member paramMember, Model model) {
		Member dbMember = memberService.getMember(paramMember);
		if (dbMember != null && dbMember.getPassword().equals(paramMember.getPassword())) {
			model.addAttribute("member", dbMember);
			return "forward:/board/listBoard";
		} else {
			return "redirect:/member/login";
		}
	}

	@GetMapping("/member/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:/";
	}

}
