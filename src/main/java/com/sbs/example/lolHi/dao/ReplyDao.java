package com.sbs.example.lolHi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.example.lolHi.dto.Reply;

@Mapper
public interface ReplyDao {
	void write(Map<String, Object> param);

	List<Reply> getForPrintReplies( @Param("relTypeCode") String relTypeCode, @Param("relId") int id);
}