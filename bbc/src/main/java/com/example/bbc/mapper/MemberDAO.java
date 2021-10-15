package com.example.bbc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.bbc.domain.MemberVO;

@Mapper
public interface MemberDAO {
	@Select("select * from member where bestyn='y'")
	public List<MemberVO> getBestMemberList() throws Exception;
	
	@Select("select * from member where email=#{email} and password=#{password}")
	public MemberVO selectOne(@Param("email") String email, @Param("password") String password) throws Exception;
	
	@Insert("insert into member(email,password,nickname,bestyn) values(#{email},#{password},#{nickname},'n')")
	public void insertMember(@Param("email") String email, @Param("password") String password,@Param("nickname") String nickname) throws Exception;

	@Select("select nickname from member where email=#{email}")
	public String checkId(@Param("email") String email) throws Exception;
	
	@Select("select email from member where nickname=#{nickname}")
	public String checkNickname(@Param("nickname") String nickname) throws Exception;
}
