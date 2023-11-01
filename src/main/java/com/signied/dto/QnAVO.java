package com.signied.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QnAVO {
	private int qnaNum;
	
	private String qnaTitle;
	
	private String email;
	
	private String qnaContent;
	
	private Timestamp qnaRegidate;
	
	private String qnaPwd;
	
	private String qnaCategory;
}
