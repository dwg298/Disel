package kh.study.shop.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.study.shop.config.MemberRole;
import kh.study.shop.config.MemberStatus;
import kh.study.shop.member.service.MemberService;
import kh.study.shop.member.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Resource(name="memberService")
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	//회원가입
	@PostMapping("/join")
	public String join(MemberVO memberVO) {
		System.out.println(memberVO);
		
		memberVO.setMemberStatus(MemberStatus.ACTIVE.toString());
		memberVO.setMemberRole(MemberRole.MEMBER.toString());
		String pw = encoder.encode(memberVO.getMemberPw());
		memberVO.setMemberPw(pw);
		
		memberService.join(memberVO);
		
		return "redirect:/item/list";
	}
	
	@GetMapping("/loginResult")
	public String loginResult() {
		return "content/member/login_result";
	}
	
	@ResponseBody
	@PostMapping("/ajaxLogin")
	public boolean ajaxLogin(MemberVO memberVO, HttpSession session) {
		MemberVO loginInfo = memberService.login(memberVO);
		
		if(loginInfo != null) {
			session.setAttribute("loginInfo", loginInfo);
		}
		
		return loginInfo == null ? false : true;
	}
	
}


















