package com.signied.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QnAReplyVO {
	private int replyNum;
	private String replyContent;
	private Timestamp replyRegidate;
	private int qnaNum;
}
