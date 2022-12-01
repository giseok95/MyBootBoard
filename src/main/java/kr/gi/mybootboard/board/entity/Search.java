package kr.gi.mybootboard.board.entity;

import lombok.Data;

@Data
public class Search {

	private String searchCondition;
	private String searchKeyword;
	private String searchCategory;
	private int page;
	

}
