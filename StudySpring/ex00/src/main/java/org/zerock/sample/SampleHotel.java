package org.zerock.sample;
 
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
 
@Component
@ToString
@Getter
@RequiredArgsConstructor 
public class SampleHotel {
 
	@NonNull
    private Chef chef;
//    SampleHotel(Chef chef){
//        
//        this.chef=chef;
//    }
}