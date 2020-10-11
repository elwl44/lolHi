package com.sbs.example.lolHi.controller.usr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.example.lolHi.dto.Article;
import com.sbs.example.lolHi.service.ArticleService;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	@RequestMapping("/usr/article/list")
	public String showList(Model model) {
		List<Article> articles = articleService.getArticles();

		model.addAttribute("articles", articles);
		return "usr/article/list";
	}

	@RequestMapping("/usr/article/detail")
	public String showDetail(Model model, int id) {
		Article article = articleService.getArticleById(id);
		model.addAttribute("article", article);
		return "usr/article/detail";
	}

	@RequestMapping("/usr/article/delete")
	@ResponseBody
	public String showDelete(int id) {
		articleService.doDeleteId(id);
		return String.format("<script> alert('%d번 글을 삭제하였습니다.'); location.replace('/usr/article/list'); </script>", id);
	}
	
	@RequestMapping("/usr/article/modify")
	@ResponseBody
	public String showModify(int id,String title, String body) {
		articleService.doModifyid(id,title,body);
		return String.format("<script> alert('%d번 글을 수정하였습니다.'); location.replace('/usr/article/detail?id=%d'); </script>", id, id);
	}
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(@RequestParam Map<String, Object> param) {
		int id = articleService.writeArticle(param);
		return String.format("<script> alert('%d번 글이 생성되였습니다.'); location.replace('/usr/article/detail?id=%d'); </script>", id, id);
	}
	@RequestMapping("/usr/article/write")
	public String showWrite() {
		return "usr/article/write";
	}

}
