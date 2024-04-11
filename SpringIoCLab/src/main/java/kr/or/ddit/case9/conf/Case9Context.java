package kr.or.ddit.case9.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import kr.or.ddit.case9.obj.BBtan;
import kr.or.ddit.case9.obj.Gun;
import kr.or.ddit.case9.obj.Hunter;
import kr.or.ddit.case9.obj.HuntingDog;
import kr.or.ddit.case9.obj.RifleGun;

@Configuration
@Lazy
@ComponentScan("kr.or.ddit.case9.obj")
public class Case9Context {
	
	@Bean("dog")
	public HuntingDog dog() {
		return new HuntingDog();
	}  
	
	@Bean
	@Scope("prototype")
	public Gun rifleGun() {
		return new RifleGun();
	}
	
	@Bean
	public Gun bbtan() {
		return new BBtan();
	}
	
	@Bean
	public Hunter hunter1(@Autowired Gun rifleGun, HuntingDog dog) {
		
		Hunter hunter = new Hunter(rifleGun);
		hunter.setDog(dog);
		
		return hunter;
	}
	
	@Bean
	public Hunter hunter2(@Autowired Gun bbtan, HuntingDog dog) {
		
		Hunter hunter = new Hunter(bbtan);
		hunter.setDog(dog);
		
		return hunter;
	}
}
