package kr.or.ddit.properties;

import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import org.junit.jupiter.api.Test;

class ResourceBundleTest {

	@Test
	void test() {
		// 
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.Messages", Locale.ENGLISH); // getBundle(baseName)에는 확장자가 포함되지 않는다. 무조건 properties 확장자를 사용하기 때문이다. 맨앞에 /가 빠진다. 키벨류 쌍으로 관리. 프로퍼티는 읽기쓰기 가능하지만 리소스번들은 읽기만 가능하다. 번들 안에 들어있는 내용을 메시지라고 한다. 키벨류 쌍이라는 표현보다 코드라ㅡㄴㄴ 표현을 더많이 사용. 클래스를 통해서 자원을 찾아내는 과정이 생략이되어있다.
		Set<String> keySet = bundle.keySet();
		Iterator<String> it = keySet.iterator(); // 컬렉션 뷰 이용
		while(it.hasNext()) {
			String code = (String) it.next();
			String message = bundle.getString(code);
			System.out.printf("%s : %s\n", code, message);
		}
	}
}
