package com.sbs.example.lolHi.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.example.lolHi.dto.Member;

@Mapper
public interface MemberDao {

	void join(Map<String, Object> param);

	Member getMemberByLoginId(@Param("loginId") String loginId);

}
