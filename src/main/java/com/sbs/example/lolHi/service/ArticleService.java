package com.sbs.example.lolHi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.example.lolHi.dao.ArticleDao;
import com.sbs.example.lolHi.dto.Article;

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
	public Article doDeleteId(int id) {
		return articleDao.doDeleteId(id);
	}
	public Article doModifyid(int id,String title, String body) {
		System.out.println("아이디"+id+"*"+title);
		return articleDao.modifyArticle(id,title,body);
	}
}
