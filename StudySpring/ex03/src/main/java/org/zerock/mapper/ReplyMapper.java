package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyMapper {
	
	/* 등록(create) */
	public int insert(ReplyVO vo);

	
	/* 조회(read) */
	/* 특정 댓글 읽기 */
	public ReplyVO read(Long rno);
	
	
	/* 삭제(delete) */
	public int delete(Long rno);
	
	
	/* 수정(update) */
	public int update(ReplyVO reply);
	
	
	/* 댓글 목록 및 페이징 처리 */
	public List<ReplyVO> getListWithPaging(
			@Param("cri") Criteria cri,
			@Param("bno") Long bno);
	
	
	/* 댓글 숫자 파악 */
	public int getCountByBno(Long bno);
	
}
