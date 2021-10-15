package com.example.bbc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.bbc.domain.BoardVO;
import com.example.bbc.domain.ImageVO;

@Mapper
public interface BoardDAO {
	@Select("select * from image where board_seq=#{board_seq}")
	public List<ImageVO> getImageList(@Param("board_seq")int board_seq) throws Exception;
	@Select("select * from board where bestyn='y'")
	public List<BoardVO> getBestBoardList() throws Exception;
	@Select("select * from board where date(cre_date) >= date(subdate(now(),INTERVAL 7 day)) and date(cre_date) <= now()")
	public List<BoardVO> getNewBoardList() throws Exception;

	@Select("select * from board where content like concat('%',#{content},'%')")
	public List<Integer> getCList(@Param("content") String content);
	@Select("select * from board where nation =#{nation}")
	public List<Integer> getNList(@Param("nation") String nation);
	@Select("select * from board where nation =#{nation} and content like concat('%',#{content},'%')")
	public List<Integer> getNCList(@Param("nation") String nation,@Param("content") String content);
	@Select("select * from board where nation =#{nation} and region =#{region}")
	public List<Integer> getNRList(@Param("nation") String nation,@Param("region") String region);
	@Select("select * from board where nation =#{nation} and region = #{region} and content like concat('%',#{content},'%')")
	public List<Integer> getNRCList(@Param("nation") String nation,@Param("region") String region,@Param("content") String content);
	@Select("select * from board where nation =#{nation} and region = #{region} and place = #{place}")
	public List<Integer> getNRPList(@Param("nation") String nation,@Param("region") String region, @Param("place") String place);
	@Select("select * from board where nation =#{nation} and region = #{region} and place = #{place} and content like concat('%',#{content},'%')")
	public List<Integer> getNRPCList(@Param("nation") String nation,@Param("region") String region,  @Param("place") String place, @Param("content") String content);
	
	@Select("select nation from board group by nation")
	public List<String> getNationList();
	@Select("select region from board where nation=#{nation} group by region")
	public List<String> getRegionList(@Param("nation") String nation);
	@Select("select place from board where nation=#{nation} and region=#{region} group by place")
	public List<String> getPlaceList(@Param("nation") String nation, @Param("region") String region);
}


//@Select("select board_seq from board where content like concat('%',#{content},'%')")
//public List<Integer> getCBoardList(@Param("content") String content);
//@Select("select board_seq from board where nation like concat('%',#{nation},'%') and region like concat('%', #{region},'%') and place like concat('%',#{place},'%') and content like concat('%',#{content},'%')")
//public List<Integer> getNRPBoardList(@Param("nation") String nation, @Param("region") String region, @Param("place") String place, @Param("content") String content);
//@Select("select board_seq from board where nation like concat('%',#{nation},'%') and content like concat('%',#{content},'%')")
//public List<Integer> getNBoardList(@Param("nation") String nation, @Param("content") String content);
//@Select("select board_seq from board where nation like concat('%',#{nation},'%') and region like concat('%', #{region},'%') and content like concat('%',#{content},'%')")
//public List<Integer> getNRBoardList(@Param("nation") String nation, @Param("region") String region, @Param("content") String content);
//@Select("select board_seq from board where nation like concat('%',#{nation},'%') and place like concat('%',#{place},'%') and content like concat('%',#{content},'%')")
//public List<Integer> getNPBoardList(@Param("nation") String nation, @Param("place") String place, @Param("content") String content);
//@Select("select board_seq from board where region like concat('%', #{region},'%') and content like concat('%',#{content},'%')")
//public List<Integer> getRBoardList(@Param("region") String region, @Param("content") String content);
//@Select("select board_seq from board where region like concat('%', #{region},'%') and place like concat('%',#{place},'%') and content like concat('%',#{content},'%')")
//public List<Integer> getRPBoardList(@Param("region") String region, @Param("place") String place, @Param("content") String content);
//@Select("select board_seq from board where place like concat('%',#{place},'%') and content like concat('%',#{content},'%')")
//public List<Integer> getPBoardList(@Param("place") String place, @Param("content") String content);