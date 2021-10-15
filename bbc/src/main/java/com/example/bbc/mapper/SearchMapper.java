package com.example.bbc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.bbc.domain.Board;
import com.example.bbc.domain.Image;

public interface SearchMapper {

	@Select("select * from board where (nation like CONCAT('%',#{keyword},'%')) or (region like CONCAT('%',#{keyword},'%')) or (place like CONCAT('%',#{keyword},'%')) or (content like CONCAT('%',#{keyword},'%'))")
	public List<Board>getBoardList(@Param("keyword")String keyword) throws Exception;
	
	@Select("select * from board where board_seq > #{board_seq} and nation=#{nation} and region=#{region} and place=#{place} order by board_seq")
	public List<Board> getBoardListBiggerSeq(@Param("board_seq")int board_seq, @Param("nation")String nation, @Param("region")String region, @Param("place")String place) throws Exception;
	
	@Select("select * from board where board_seq < #{board_seq} and nation=#{nation} and region=#{region} and place=#{place} order by board_seq desc")
	public List<Board> getBoardListSmallerSeq(@Param("board_seq")int board_seq, @Param("nation")String nation, @Param("region")String region, @Param("place")String place) throws Exception;
	
	
	@Select("select * from image where board_seq = #{board_seq} order by image_seq")
	public List<Image>getImagesByBoard_Seq(@Param("board_seq")int board_seq) throws Exception;
	
	@Select("select * from board where board_seq = #{board_seq} order by board_seq")
	public Board getBoardByBoard_Seq(@Param("board_seq")int board_seq) throws Exception;
	
	// 테스트용
	//@Select("select * from board where (nation like '%#{keyword}%') or (region like '%#{keyword}%') or (place like '%#{keyword}%')")
	@Select("select * from board where region like CONCAT('%',#{keyword},'%')")
	public List<Board> TestMapper(@Param("keyword")String keyword) throws Exception;
}
