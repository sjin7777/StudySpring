package org.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Component
@Data

public class Restaurant {
	
	// Chef를 주입받도록 함
	@Setter(onMethod_ = @Autowired)
	private Chef chef;
}