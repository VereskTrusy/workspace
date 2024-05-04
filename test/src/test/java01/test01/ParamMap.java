package test.java01.test01;

public class ParamMap {
	private static ParamMap instance;
	
	// 기본 생성자
	private ParamMap() { }
	
	// 함수를 통해 반환 할 메서드
	public static ParamMap init() {
		instance = new ParamMap();
		return instance;
	}
	
	
}
