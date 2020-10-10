package com.sbs.example.lolHi.controller.usr;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String showDelete(Model model, int id) {
		Article article = articleService.doDeleteId(id);
		model.addAttribute("id", id);
		return "usr/article/delete";
	}
	
	@RequestMapping("/usr/article/modify")
	public String showModify(Model model, int id,String title, String body) {
		Article article = articleService.doModifyid(id,title,body);
		System.out.println("아이디"+id+"<>"+title);
		return String.format("<script> alert('%d번 글을 수정하였습니다.'); location.replace('/usr/article/detail?id=%d'); </script>", id, id);
	}
}
