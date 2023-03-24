package org.zerock.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {

	// 테스트 전에 해당 번호 게시물이 존재하는지 반드시 확인해 볼 것
	private Long[] bnoArr = {333L, 334L, 335L, 336L, 337L};
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Test
	public void testMapper() {
		log.info(mapper);
	}
	
	
	
	/* 등록(create) */
	@Test
	public void testCreate() {
		
		IntStream.rangeClosed(1, 10).forEach(i -> {
			
			ReplyVO vo = new ReplyVO();
			
			// 게시물 번호
			vo.setBno(bnoArr[i % 5]);
			vo.setReply("댓글 테스트 " + i);
			vo.setReplyer("replyer" + i);
			
			mapper.insert(vo);
		});
	}
	
	
	/* 조회(read) */
	/* 특정 댓글 읽기 */
	@Test
	public void testRead555() {
		
		Long targetRno = 5L;
		
		ReplyVO vo = mapper.read(targetRno);
		
		log.info(vo);
		log.info("dddddddddddddddddddddddddddddddddddddddddd");
	}
	
	
	/* 삭제(delete) */
	@Test
	public void testDelete() {
		
		Long targetRno = 1L;
		
		mapper.delete(targetRno);
	}
	
	
	/* 수정(update) */
	@Test
	public void testUpdate() {
		
		Long targetRno =  10L;
		
		ReplyVO vo = mapper.read(targetRno);
		
		vo.setReply("Update Reply ");
		
		int count = mapper.update(vo);
		
		log.info("UPDATE COUNT: " + count);
	}
	
	
	/* 댓글 목록 및 페이징 처리 */
	public void testList() {
		
		Criteria cri = new Criteria();
		
		// 333L
		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
		
		replies.forEach(reply -> log.info(reply));
	}
	
	@Test
	public void testList2() {
		
		log.info("-------------------");
		
		Criteria cri = new Criteria(2, 10);
		// 335L
		List<ReplyVO> replies = mapper.getListWithPaging(cri, 335L);
		
		replies.forEach(reply -> log.info(reply));
	}
}

