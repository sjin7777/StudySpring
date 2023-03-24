package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {

	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int total;
	private Criteria cri;
	
	// Criteria - amount  : 페이지에서 보여주는 데이터 수
	//			- pageNum : 현재 페이지 번호
	// total : 전체 데이터 수
	public PageDTO(Criteria cri, int total) {
		
		this.cri = cri;
		this.total = total;
		
//		System.out.println("total : " + this.total);
//		System.out.println("cri.getPageNum : " + cri.getPageNum());
		
		// endPage : 페이징의 끝 번호 
		this.endPage = (int)(Math.ceil(cri.getPageNum() / 10.0)) * 10;
//		System.out.println("endPage : " + endPage);

		// startPage : 페이징의 시작 번호
		this.startPage = this.endPage - 9;
//		System.out.println("startPage : " + this.startPage);
		
		// realEnd : 진짜 끝 페이지 (total 이용)
//		System.out.println("Math.ceil : " + Math.ceil(total * 1.0));
//		System.out.println("getAmount : " + cri.getAmount());
//		System.out.println("Math.ceil(total * 1.0) / cri.getAmount() : " + Math.ceil(total * 1.0) / cri.getAmount());
		int realEnd = (int)(Math.ceil((total * 1.0) / cri.getAmount()));
		
//		System.out.println("realEnd : " + realEnd);
		// 만약 realEnd가 endPage보다 작으면 끝번호는 realEnd가 되어야 함
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
//		System.out.println("endPage2 : " + this.endPage);
		
		// prev : 이전 (startPage(시작 번호)가 1보다 크면 존재)
		this.prev = this.startPage > 1;
		// next : 다음 (realEnd(진짜 끝 페이지)가 endPage(끝 번호) 보다 크면 존재)
		this.next = this.endPage < realEnd;
		
//		int a = 0;
//		if( a < 1 ) {
//			System.out.print("a");
//		} else {
//		}
//		
//		if( a < 1 ) System.out.print("a");
//		
//		System.out.print( a < 1 ? "a" : "");
//		
//		log.info("page log:{}", "test");
//		}
	}
}
