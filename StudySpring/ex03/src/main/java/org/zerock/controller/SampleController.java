package org.zerock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {

	/* 단순 문자열 반환 */
	@GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
	public String getText() {
		
		log.info("MIME TYPE: " + MediaType.TEXT_PLAIN_VALUE);
		
		return "안녕하세요";
	}
	
	/* 객체 반환 */
	/* XML과 JSON 방식의 데이터를 생성할 수 있도록 작성 */
	@GetMapping(value = "/getSample",
			produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
						MediaType.APPLICATION_XML_VALUE})
		public SampleVO getSample() {
		
			return new SampleVO(112, "스타", "로드");
	}
	
	/* produces 속성 생략 가능 */
	@GetMapping(value = "/getSample2")
		
		public SampleVO getSample2() {
		
			return new SampleVO(113, "로켓", "라쿤");
	}
	
	
	/* 컬렉션 타입의 객체 반환 */
	@GetMapping(value = "/getList")
	public List<SampleVO> getList(){
		
		// 1부터 10미만까지의 루프를 처리
		// SampleVO 객체를 만들어서 List<SampleVO>로 만들어냄
		return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i + "First", i + "Last")).collect(Collectors.toList());
	}
	
	/* 맵인 경우 - '키'와 '값'을 가지는 하나의 객체로 간주 */
	@GetMapping(value =  "/getMap")
	public Map<String, SampleVO> getMap(){
		
		Map<String, SampleVO> map = new HashMap<>();
		map.put("First", new SampleVO(111, "그루트", "주니어"));
		
		return map;
	}
	
	
	/* ResponseEntity 타입 */
	/* 데이터와 함께 HTTP 헤더의 상태 메시지(HTTP의 상태 코드와 에러 메시지) 등을 전달하는 용도로 사용 */
	@GetMapping(value = "/check", params = {"height", "weight"})
	public ResponseEntity<SampleVO> check(Double height, Double weight){
		
		SampleVO vo = new SampleVO(0, "" + height, "" + weight);
		
		ResponseEntity<SampleVO> result = null;
		
		if(height < 150) {
			// 502(bad gateway) 상태 코드와 데이터 전송
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		} else {
			// 200(ok) 상태 코드와 데이터 전송
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		
		return result;
	}
	
	
	/* @PathVariable 어노테이션 이용 - URL 상에 경로의 일부를 파라미터로 사용 */
	/* @PathVariable을 적용하고 싶은 경우 - '{}'를 이용하여 변수명 지정 */
	/* @PathVariable을 이용 - 지정된 이름의 변숫값을 얻을 수 있음 (기본 자료형 사용 불가능) */	
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(
			@PathVariable("cat") String cat,
			@PathVariable("pid") Integer pid){
				
				return new String[] { "category: " + cat, "productid: " + pid };
				
	}
	
	
	/* convert() - JSON으로 전달되는 데이터를 받아서 Ticket 타입으로 변환 */
	/* @PostMapping을 적용 - @RequestBody로 처리하기 때문에 */
	/* @RequestBody : 요청한 내용을 처리하기 때문에 일반적인 파라미터 전달방식 사용 불가능 */
	@PostMapping("/ticket")
	public Ticket convert(@RequestBody Ticket ticket) {
		
		log.info("convert......................ticket" + ticket);
		
		return ticket;
	}
	
}
