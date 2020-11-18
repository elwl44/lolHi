package com.sbs.example.lolHi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.example.lolHi.dto.Article;
import com.sbs.example.lolHi.dto.Board;

@Mapper
public interface ArticleDao {


	List<Article> getForPrintArticles(Map<String, Object> param);
	Article getForPrintArticleById(@Param("id") int id);

	void doDeleteId(@Param("id") int id);

	void modifyArticle(@Param("id") int id, @Param("title") String title, @Param("body") String body);

	void writeArticle(Map<String, Object> param);
	
	int getTotalCount(Map<String, Object> param);
	
	Board getBoardByCode(@Param("boardCode") String boardCode);
	
}
