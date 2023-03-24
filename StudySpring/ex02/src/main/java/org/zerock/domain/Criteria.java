package org.zerock.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
// Criteria : 검색의 기준
public class Criteria {
	
	// pageNum : 페이지 번호
	private int pageNum;
	// amount : 한 페이지당 보여주는 데이터 수
	private int amount;
	
	private String type;
	private String keyword;
	
	// 기본 값 : 1페이지, 10개
	public Criteria() {
		this(1, 10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}

	// getTypeArr 검색 조건 : 각 글자(T, W, C)로 구성
	public String[] getTypeArr() {
		
		return type == null? new String[] {} : type.split("");
	}
	
	
	public String getListLink() {
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.getAmount())
				.queryParam("type", this.getType())
				.queryParam("keyword", this.getKeyword());
		
		return builder.toUriString();
	}
}
