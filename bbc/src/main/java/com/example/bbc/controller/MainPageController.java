package com.example.bbc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.bbc.domain.BoardVO;
import com.example.bbc.domain.ImageVO;
import com.example.bbc.domain.MemberVO;
import com.example.bbc.mapper.BoardDAO;
import com.example.bbc.mapper.MemberDAO;

@RestController
public class MainPageController {
	
	@Autowired
	private BoardDAO boardDao;
	
	@Autowired
	private MemberDAO memberDao;
	
	@RequestMapping("/signup")
	public ModelAndView goLogin() {
		return new ModelAndView("signup");
	}
	
	@RequestMapping("/main")
	public ModelAndView getMain() {
		ModelAndView mav = new ModelAndView();
		List<String> nationList = new ArrayList<>();
		List<HashMap<String,Object>> bestList = new ArrayList<>();
		List<HashMap<String,Object>> newList = new ArrayList<>();
		try {
			List<ImageVO> bestImageList = new ArrayList<>();
			List<ImageVO> newImageList = new ArrayList<>();
			List<BoardVO> boardList = boardDao.getBestBoardList();
			nationList = boardDao.getNationList();
			
			for(int i =0; i< boardList.size(); i++) {
				bestImageList = boardDao.getImageList(boardList.get(i).getBoard_seq());
				HashMap<String,Object> map = new HashMap<>();
				map.put("board", boardList.get(i));
				map.put("image", bestImageList);
				bestList.add(map);
			}
			
			boardList = boardDao.getNewBoardList();
			for(int i = 0; i< boardList.size(); i++) {
				newImageList = boardDao.getImageList(boardList.get(i).getBoard_seq());
				HashMap<String,Object> map = new HashMap<>();
				map.put("board", boardList.get(i));
				map.put("image", newImageList);
				newList.add(map);
			}
			
			if(!nationList.isEmpty()) {
				mav.addObject("nation", nationList);
			}
			mav.addObject("bestboardlist", bestList);
			mav.addObject("newboardlist", newList);
			mav.setViewName("main");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@PostMapping("/search")
	public ModelAndView getSearch(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String nation = request.getParameter("nation");
		String region = request.getParameter("region");
		String place = request.getParameter("place");
		String content = request.getParameter("searching");
		if(nation.equals("Nation")) {
			nation = "";
		}
		if(region.equals("Region")) {
			region = "";
		}
		if(place.equals("Place")) {
			place = "";
		}
		List<Integer> list = new ArrayList<>();
		try {
			if(nation.isEmpty() && region.isEmpty() && place.isEmpty() && !content.isEmpty()) {
				list = boardDao.getCList(content);
				mav.addObject("seqlist", list);
			} else if(region.isEmpty() && place.isEmpty() && !content.isEmpty() && !nation.isEmpty()) {
				list = boardDao.getNCList(nation, content);
				mav.addObject("seqlist", list);
			} else if(region.isEmpty() && place.isEmpty() && content.isEmpty() && !nation.isEmpty()) {
				list = boardDao.getNList(nation);
				mav.addObject("seqlist", list);
			} else if(place.isEmpty() && !content.isEmpty() && !nation.isEmpty() && !region.isEmpty()) {
				list = boardDao.getNRCList(nation, region, content);
				mav.addObject("seqlist", list);
			} else if(place.isEmpty() && content.isEmpty() && !nation.isEmpty() && !region.isEmpty()) {
				list = boardDao.getNRList(nation, region);
				mav.addObject("seqlist", list);
			} else if(content.isEmpty() && !place.isEmpty() && !nation.isEmpty() && !region.isEmpty()) {
				list = boardDao.getNRPList(nation, region, place);
				mav.addObject("seqlist", list);
			} else if(!place.isEmpty() && !content.isEmpty() && !nation.isEmpty() && !region.isEmpty()) {
				list = boardDao.getNRPCList(nation, region, place, content);
				mav.addObject("seqlist", list);
			}
			for(int i : list) {
				System.out.println(i);
			}
			System.out.println();
		} catch(Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("redirect:");
		return mav;
	}
	
	@PostMapping("/selectregion")
	public List<String> getRegion(HttpServletRequest request) {
		String nation = request.getParameter("nation");
		List<String> regionList = boardDao.getRegionList(nation);
		
		return regionList;
	}
	
	@PostMapping("/selectplace")
	public List<String> getPlace(HttpServletRequest request){
		String nation = request.getParameter("nation");
		String region = request.getParameter("region");
		
		List<String> placeList = boardDao.getPlaceList(nation, region);
		return placeList;
	}
	
	@PostMapping("/login")
	public ModelAndView login(HttpServletRequest request) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		ModelAndView mav = new ModelAndView();
		try {
			MemberVO member = memberDao.selectOne(email, password);
			
			if(member == null) {
				mav.setViewName("redirect:main");
			}else {
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
				mav.addObject("session", session);
				mav.setViewName("redirect:main");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@PostMapping("/signup")
	public ModelAndView signup(HttpServletRequest request) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		ModelAndView mav = new ModelAndView();
		MemberVO member = null;
		System.out.println(email);
		System.out.println(password);
		System.out.println(nickname);
		
		try {
			mav.setViewName("redirect:main");
			memberDao.insertMember(email, password, nickname);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return new ModelAndView("redirect:main");
	}
	
	@PostMapping("/checkid")
	public String checkId(HttpServletRequest request) {
		String email = request.getParameter("email");
		String result = "";
		try {
			String nickname = memberDao.checkId(email);
			if(nickname == null) {
				result = "usable";
			} else {
				result = "notusable";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@PostMapping("/checknickname")
	public String checkNickname(HttpServletRequest request) {
		String nickname = request.getParameter("nickname");
		String result = "";
		try {
			String email = memberDao.checkNickname(nickname);
			if(email ==  null) {
				result = "usable";
			} else {
				result = "notusable";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}

//if(nation == null && region == null && place == null) {
//list = boardDao.getCBoardList(content);
//mav.addObject("seqlist",list);
//} else if(region == null && place == null && !nation.isEmpty()) {
//list = boardDao.getNBoardList(nation,content);
//mav.addObject("seqlist",list);
//} else if(place == null && !nation.isEmpty() && !region.isEmpty()) {
//list = boardDao.getNRBoardList(nation,region,content);
//mav.addObject("seqlist",list);
//} else if(region == null && !nation.isEmpty() && !place.isEmpty()) {
//list = boardDao.getNPBoardList(nation,place,content);
//mav.addObject("seqlist",list);
//} else if(nation == null && place == null && !region.isEmpty()) {
//list = boardDao.getRBoardList(region,content);
//mav.addObject("seqlist",list);
//} else if(nation == null && !place.isEmpty() && !region.isEmpty()) {
//list = boardDao.getRPBoardList(place,region,content);
//mav.addObject("seqlist",list);
//} else if(nation == null && region == null && !place.isEmpty()) {
//list = boardDao.getPBoardList(place,content);
//mav.addObject("seqlist",list);
//} else {
//list = boardDao.getNRPBoardList(nation,region, place, content);
//mav.addObject("seqlist",list);
//}