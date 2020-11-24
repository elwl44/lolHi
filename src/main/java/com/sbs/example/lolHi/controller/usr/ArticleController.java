package com.sbs.example.lolHi.controller.usr;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sbs.example.lolHi.dto.Article;
import com.sbs.example.lolHi.dto.Board;
import com.sbs.example.lolHi.dto.Member;
import com.sbs.example.lolHi.dto.Reply;
import com.sbs.example.lolHi.service.ArticleService;
import com.sbs.example.lolHi.service.ReplyService;
import com.sbs.example.lolHi.util.Util;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	@Autowired
	private ReplyService replyService;

	@RequestMapping("/usr/article-{boardCode}/list")
	public String showList(HttpServletRequest req, Model model, @RequestParam Map<String, Object> param ,
			@PathVariable("boardCode") String boardCode) {
		
		Board board = articleService.getBoardByCode(boardCode);

		if (board == null) {
			model.addAttribute("msg", "존재하지 않는 게시판 입니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}
		
		Member loginedMember = (Member)req.getAttribute("loginedMember");
		
		param.put("boardId", board.getId());
		
		int totalCount = articleService.getTotalCount(param);
		int itemsCountInAPage = 10;
		int totalPage = (int) Math.ceil(totalCount / (double) itemsCountInAPage);
		int pageMenuArmSize = 10;
		int page = Util.getAsInt(param.get("page"), 1);
		int pageMenuStart = page - pageMenuArmSize;
		if (pageMenuStart < 1) {
			pageMenuStart = 1;
		}
		int pageMenuEnd = page + pageMenuArmSize;
		if (pageMenuEnd > totalPage) {
			pageMenuEnd = totalPage;
		}

		param.put("itemsCountInAPage", itemsCountInAPage);
		List<Article> articles = articleService.getForPrintArticles(loginedMember, param);
		
		model.addAttribute("board", board);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("pageMenuArmSize", pageMenuArmSize);
		model.addAttribute("pageMenuStart", pageMenuStart);
		model.addAttribute("pageMenuEnd", pageMenuEnd);
		model.addAttribute("page", page);
		model.addAttribute("articles", articles);
		System.out.println(articles);
		return "usr/article/list";
	}

	@RequestMapping("/usr/article-{boardCode}/detail")
	public String showDetail(HttpServletRequest req, Model model, int id, String listUrl,
			@PathVariable("boardCode") String boardCode) {
		
		Board board = articleService.getBoardByCode(boardCode);
		Member loginedMember = (Member) req.getAttribute("loginedMember");
		Article article = articleService.getForPrintArticleById(loginedMember, id);
		List<Reply> replies = replyService.getForPrintReplies(loginedMember, "article", id);

		if ( listUrl == null ) {
			listUrl = "/usr/article-free/list";
		}
		
		model.addAttribute("board", board);
		model.addAttribute("article", article);
		model.addAttribute("replies", replies);
		model.addAttribute("listUrl", listUrl);
		
		return "usr/article/detail";
	}

	@RequestMapping("/usr/article-{boardCode}/doDelete")
	public String doDelete(HttpServletRequest req, int id, Model model, @PathVariable("boardCode") String boardCode) {
		Member loginedMember = (Member) req.getAttribute("loginedMember");

		Article article = articleService.getForPrintArticleById(loginedMember, id);

		if ((boolean) article.getExtra().get("actorCanDelete") == false) {
			model.addAttribute("msg", "권한이 없습니다.");
			model.addAttribute("replaceUri", "/usr/article-free/list");
			return "common/redirect";
		}
		articleService.doDeleteId(id);
		model.addAttribute("msg", String.format("%d번 글을 삭제하였습니다.", id));
		model.addAttribute("replaceUri", String.format("/usr/article-%s/list", boardCode));
		return "common/redirect";
	}

	@RequestMapping("/usr/article-{boardCode}/modify")
	public String showModify(HttpServletRequest req, Model model, int id, @PathVariable("boardCode") String boardCode, String listUrl) {
		Board board = articleService.getBoardByCode(boardCode);
		
		Member loginedMember = (Member) req.getAttribute("loginedMember");

		Article article = articleService.getForPrintArticleById(loginedMember, id);
		
		if ((boolean) article.getExtra().get("actorCanModify") == false) {
			model.addAttribute("msg", "권한이 없습니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}
		
		model.addAttribute("board", board);
		model.addAttribute("article", article);
		model.addAttribute("listUrl", listUrl);
		return "usr/article/modify";
	}

	@RequestMapping("/usr/article-{boardCode}/doModify")
	public String doModify(HttpServletRequest req, int id, String title, String body, Model model,
			@PathVariable("boardCode") String boardCode,String listUrl) {
		
		Member loginedMember = (Member) req.getAttribute("loginedMember");

		Article article = articleService.getForPrintArticleById(loginedMember, id);

		if ((boolean) article.getExtra().get("actorCanModify") == false) {
			model.addAttribute("msg", "권한이 없습니다.");
			model.addAttribute("replaceUri", "/usr/article-free/list");
			return "common/redirect";
		}

		articleService.doModifyid(id, title, body);
		model.addAttribute("msg", String.format("%d번 글을 수정하였습니다.", id));
		if(listUrl.equals("")) {
			model.addAttribute("replaceUri", String.format("/usr/article-%s/detail?id=%d", boardCode, id));
		}
		else {
			model.addAttribute("replaceUri", String.format("/usr/article-%s/detail?id=%d&listUrl=%s", boardCode, id,listUrl));
		}
		return "common/redirect";
	}

	@RequestMapping("/usr/article-{boardCode}/write")
	public String showWrite(HttpServletRequest req, Model model, @PathVariable("boardCode") String boardCode, String listUrl) {
		Board board = articleService.getBoardByCode(boardCode);

		model.addAttribute("board", board);
		model.addAttribute("listUrl", listUrl);
		return "usr/article/write";
	}

	@RequestMapping("/usr/article-{boardCode}/doWrite")
	public String doWrite(HttpServletRequest req, @RequestParam Map<String, Object> param, Model model,
			@PathVariable("boardCode") String boardCode, String listUrl) {
		Board board = articleService.getBoardByCode(boardCode);
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		param.put("boardId", board.getId());
		param.put("memberId", loginedMemberId);
		int id = articleService.writeArticle(param);

		model.addAttribute("msg", String.format("%d번 글이 생성되었습니다.", id));
		model.addAttribute("replaceUri", String.format("/usr/article-%s/detail?id=%d&listUrl=%s", boardCode, id,listUrl));
		
		return "common/redirect";
	}
}
