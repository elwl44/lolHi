package com.sbs.example.lolHi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sbs.example.lolHi.dto.Article;

@Mapper
public interface ArticleDao {

	List<Article> getArticles();

}
