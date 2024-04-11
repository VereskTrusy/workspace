package kr.or.ddit.case9.obj;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Component
@ToString
@RequiredArgsConstructor
public class Hunter {
	private final Gun gun;
	private HuntingDog dog;
	
	public void setDog(HuntingDog dog) {
		this.dog = dog;
	}
}
