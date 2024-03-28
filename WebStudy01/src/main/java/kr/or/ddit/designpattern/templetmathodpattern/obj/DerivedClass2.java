package kr.or.ddit.designpattern.templetmathodpattern.obj;

public class DerivedClass2 extends TemplateClass{

	@Override
	protected void stepTwe() {
		System.out.println("파생클래스 2번에서 실행된 2단계");
	}
	
}
