package kr.or.ddit.designpattern.templetmathodpattern.obj;

public abstract class TemplateClass {
	
	// 반복되는 작업의 순서를 일정하게 제어하는 메소드 : template mothod
	public final void template() {
		// final 이라서 메서드 호출 순서 변경 불가.
		stepOne();
		stepTwe();
		stepThree();
	}
	
	// 실제 작업 단계를 담당하고 template method 의 제어를 "받는" 메소드 : hook method
	private void stepOne() {
		System.out.println("1단계 실행");
	}
	
	protected abstract void stepTwe();
	
	private void stepThree() {
		System.out.println("3단계 실행");
	}
}
