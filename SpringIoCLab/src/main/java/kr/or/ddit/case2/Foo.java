package kr.or.ddit.case2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.or.ddit.case1.service.SampleService;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Component(value = "foo1")
@RequiredArgsConstructor
@ToString
public class Foo {
	
	// required, optional 에 따라서 주입 방법 지정
	private final Bar bar; // required
	@Autowired
	private Baz baz; // optional
	
	private final SampleService service;
	
	// 생성자 주입 방식
//	public Foo(Bar bar) {
//		super();
//		this.bar = bar;
//	}

	// setter 주입 방식
	public void setBaz(Baz baz) {
		this.baz = baz;
	}
}
