package org.zerock.domain;

import lombok.Data;

@Data
public class Ticket {

	// tno : 번호
	private int tno;
	// owner : 소유주
	private String owner;
	// grade : 등급
	private String grade;
}
