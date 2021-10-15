package com.example.bbc.domain;

public class Board {
	private int board_seq;
	private String title;
	private String content;
	private char bestyn;
	private int view_num;
	private int like;
	private String cre_date;
	private String mod_date;
	private String nation;
	private String region;
	private String place;
	private char board_statu;
	public int getBoard_seq() {
		return board_seq;
	}
	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public char getBestyn() {
		return bestyn;
	}
	public void setBestyn(char bestyn) {
		this.bestyn = bestyn;
	}
	public int getView_num() {
		return view_num;
	}
	public void setView_num(int view_num) {
		this.view_num = view_num;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public String getCre_date() {
		return cre_date;
	}
	public void setCre_date(String cre_date) {
		this.cre_date = cre_date;
	}
	public String getMod_date() {
		return mod_date;
	}
	public void setMod_date(String mod_date) {
		this.mod_date = mod_date;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public char getBoard_statu() {
		return board_statu;
	}
	public void setBoard_statu(char board_statu) {
		this.board_statu = board_statu;
	}
	public int getMember_seq() {
		return member_seq;
	}
	public void setMember_seq(int member_seq) {
		this.member_seq = member_seq;
	}
	private int member_seq;
	
}
