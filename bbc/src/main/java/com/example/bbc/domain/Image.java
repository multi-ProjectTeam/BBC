package com.example.bbc.domain;

public class Image {
	private int image_seq;
	private String path;
	private String original_name;
	private String rename;
	private String board_seq;
	public int getImage_seq() {
		return image_seq;
	}
	public void setImage_seq(int image_seq) {
		this.image_seq = image_seq;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getOriginal_name() {
		return original_name;
	}
	public void setOriginal_name(String original_name) {
		this.original_name = original_name;
	}
	public String getRename() {
		return rename;
	}
	public void setRename(String rename) {
		this.rename = rename;
	}
	public String getBoard_seq() {
		return board_seq;
	}
	public void setBoard_seq(String board_seq) {
		this.board_seq = board_seq;
	}
}
