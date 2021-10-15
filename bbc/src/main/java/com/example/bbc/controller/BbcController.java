package com.example.bbc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.bbc.domain.Board;
import com.example.bbc.domain.Image;
import com.example.bbc.mapper.SearchMapper;

@Controller
public class BbcController {
	@Autowired
	private SearchMapper mapper;
	
	@GetMapping("search")
	public ModelAndView search(HttpServletRequest request){
		String keyword = request.getParameter("keyword");
//		String keyword = "서울";
		System.out.println(keyword);
		ModelAndView mav = new ModelAndView("searchResult");
		
		if(keyword != null) {
			try {
				List<Board> boards = mapper.getBoardList(keyword);
				if(boards.size() > 0) {
					List<Integer> seqlist = new ArrayList<Integer>();
					for(Board board : boards) {
						seqlist.add(board.getBoard_seq());
					}
					
					mav.addObject("content", keyword);
					mav.addObject("nation", boards.get(0).getNation());
					mav.addObject("nation", boards.get(0).getRegion());
					mav.addObject("nation", boards.get(0).getPlace());
					mav.addObject("seqlist", seqlist);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mav;
	}
	
	@PostMapping("nextPage")
	@ResponseBody
	public Map<String, Object> nextPage(int board_seq, String stat, String nation, String region, String place) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Board> boards = new ArrayList<Board>();
		List<Image> images = new ArrayList<Image>();
		
		try {
			if(stat.equals("right")) {
				boards = mapper.getBoardListBiggerSeq(board_seq, nation, region, place);
			}else {
				boards = mapper.getBoardListSmallerSeq(board_seq, nation, region, place);
			}
			
			if(boards.size() >0) {
				for(Board board : boards) {
					images.add(mapper.getImagesByBoard_Seq(board.getBoard_seq()).get(0));
				}
			}
			map.put("boards", boards);
			map.put("images", images);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	@PostMapping("refreshment")
	@ResponseBody
	public Map<String, Object> refreshment(String seqlist){
		Map<String, Object> map = new HashMap<String, Object>();
		if(seqlist == null) {
			System.out.println("NULL");
		}
		else {
			seqlist = seqlist.replaceAll("[^0-9,]", "");
			String[] seqList = seqlist.split(",");
			
			List<Board> boards = new ArrayList<Board>();
			List<Image> images = new ArrayList<Image>();
			try {
				for(int i=0; i< (seqList.length > 18? 18 : seqList.length); i++) {
					boards.add(mapper.getBoardByBoard_Seq( Integer.parseInt(seqList[i]) ));
					images.add(mapper.getImagesByBoard_Seq(Integer.parseInt(seqList[i])).get(0));
				}
				
				map.put("boards", boards);
				map.put("images", images);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	
	@RequestMapping("{path}")
	public String demo(@PathVariable String path) {
		System.out.println(path);
		return path;
	}
}
