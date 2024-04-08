package kr.or.ddit.case2;

import lombok.ToString;

@ToString
public class Foo {
	
	// required, optional 에 따라서 주입 방법 지정
	private Bar bar; // required
	private Baz baz; // optional
	
	// 생성자 주입 방식
	public Foo(Bar bar) {
		super();
		this.bar = bar;
	}

	// setter 주입 방식
	public void setBaz(Baz baz) {
		this.baz = baz;
	}
}
