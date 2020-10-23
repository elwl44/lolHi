package com.sbs.example.lolHi.controller.usr;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.example.lolHi.service.ArticleService;
import com.sbs.example.lolHi.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/usr/member/join")
	public String showJoin() {
		return "usr/member/join";
	}
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(@RequestParam Map<String, Object> param) {
		int id=memberService.join(param);
		return String.format("<script> alert('%d번 회원이 생성되였습니다.'); location.replace('/usr/article/list'); </script>", id);

	}
}
