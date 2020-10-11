package com.sbs.example.lolHi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.example.lolHi.dao.ArticleDao;
import com.sbs.example.lolHi.dto.Article;

import cmd.sbs.example.lolHi.util.Util;

@Service
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;
	public List<Article> getArticles() {
		
		return articleDao.getArticles();
	}
	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}
	public void doDeleteId(int id) {
		articleDao.doDeleteId(id);
	}
	public void doModifyid(int id, String title, String body) {
		System.out.println("아이디"+id+"*"+title);
		articleDao.modifyArticle(id,title,body);
	}
	public int writeArticle(Map<String, Object> param) {
		articleDao.writeArticle(param);
		
		int id = Util.getAsInt(param.get("id"));
		
		return id;
	}
}
