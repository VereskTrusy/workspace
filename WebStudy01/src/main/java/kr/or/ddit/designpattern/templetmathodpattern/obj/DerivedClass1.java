package kr.or.ddit.designpattern.templetmathodpattern.obj;

public class DerivedClass1 extends TemplateClass{

	@Override
	protected void stepTwe() {
		System.out.println("파생클래스 1번에서 실행된 2단계");
	}

}
