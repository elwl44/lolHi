package com.sbs.example.lolHi.controller.usr;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sbs.example.lolHi.dto.Member;
import com.sbs.example.lolHi.dto.Reply;
import com.sbs.example.lolHi.service.ReplyService;
import com.sbs.example.lolHi.util.Util;

@Controller
public class ReplyController {
	@Autowired
	private ReplyService replyService;

	@RequestMapping("/usr/reply/doWrite")
	public String doWrite(HttpServletRequest req, @RequestParam Map<String, Object> param, Model model, String redirectUrl) {
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		param.put("memberId", loginedMemberId);
		int id = replyService.write(param);

		String relTypeCode = (String) param.get("relTypeCode");
		int relId = Util.getAsInt(param.get("relId"));

		if ( redirectUrl == null || redirectUrl.length() == 0 ) {
			redirectUrl = String.format("/usr/%s/detail?id=%d", relTypeCode, relId);
		}
		
		model.addAttribute("msg", String.format("%d번 댓글이 생성되였습니다.", id));
		model.addAttribute("replaceUri", redirectUrl);
		return "common/redirect";
	}

	@RequestMapping("/usr/reply/doDelete")
	public String doDelete(HttpServletRequest req, Model model, int id, String redirectUrl) {
		Member loginedMember = (Member) req.getAttribute("loginedMember");
		Reply reply = replyService.getForPrintReply(loginedMember, id);

		if ( redirectUrl == null || redirectUrl.length() == 0 ) {
			redirectUrl = String.format("/usr/%s/detail?id=%d", reply.getRelTypeCode(), reply.getRelId());
		}

		
		if (reply == null) {
			model.addAttribute("msg", "존재하지 않는 댓글입니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		if ((boolean) reply.getExtra().get("actorCanDelete") == false) {
			model.addAttribute("msg", "권한이 없습니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		replyService.deleteReplyById(id);

		model.addAttribute("msg", String.format("%d번 댓글이 삭제되었습니다.", id));
		model.addAttribute("replaceUri", redirectUrl);
		return "common/redirect";
	}
	
	@RequestMapping("/usr/reply/modify")
	public String showModify(HttpServletRequest req, Model model, int id, String redirectUrl) {
		Member loginedMember = (Member) req.getAttribute("loginedMember");

		Reply reply = replyService.getForPrintReply(loginedMember, id);

		if (redirectUrl == null || redirectUrl.length() == 0) {
			redirectUrl = String.format("/usr/%s/detail?id=%d", reply.getRelTypeCode(), reply.getRelId());
		}

		if (reply == null) {
			model.addAttribute("msg", "존재하지 않는 댓글입니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		if ((boolean) reply.getExtra().get("actorCanModify") == false) {
			model.addAttribute("msg", "권한이 없습니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		model.addAttribute("reply", reply);

		return "usr/reply/modify";
	}
	
	@RequestMapping("/usr/reply/doModify")
	public String doModify(HttpServletRequest req, Model model, int id, String redirectUrl, @RequestParam Map<String, Object> param) {
		Member loginedMember = (Member) req.getAttribute("loginedMember");
		Reply reply = replyService.getForPrintReply(loginedMember, id);

		if (redirectUrl == null || redirectUrl.length() == 0) {
			redirectUrl = String.format("/usr/%s/detail?id=%d", reply.getRelTypeCode(), reply.getRelId());
		}

		if (reply == null) {
			model.addAttribute("msg", "존재하지 않는 댓글입니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		if ((boolean) reply.getExtra().get("actorCanModify") == false) {
			model.addAttribute("msg", "권한이 없습니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		replyService.modify(param);

		model.addAttribute("msg", String.format("%d번 댓글이 수정되었습니다.", id));
		model.addAttribute("replaceUri", redirectUrl);
		return "common/redirect";
	}
}