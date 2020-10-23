package com.sbs.example.lolHi.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {

	void join(Map<String, Object> param);

}
