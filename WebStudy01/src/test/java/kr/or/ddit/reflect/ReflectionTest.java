package kr.or.ddit.reflect;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.MemberVO;

class ReflectionTest {

	@Test
	void test() {
		
		Map<String, String[]> parameterMap = new LinkedHashMap<>(); // 리퀘스트에서 받은 맵
		// Map<String, String[]> parameterMap = request.getParameter("");
		MemberVO member = new MemberVO(); // 파라미터 담을 놈
		
		for(Entry<String, String[]> entry : parameterMap.entrySet()) {
			String paramName = "memId"; // entry.getKey();
			String paramValue = "a001"; // entry.getValue();
			
			// 리플렉션
			// 클래스 정보를 받아 올 수 있다.
			Class instanceType = member.getClass();
			//instanceType.getMethods(); // 클래스의 메서드 정보 반환 받을 수 있다.
			
			// 클래스의 필드 객체 배열 반환 받기
			Field[] fields = instanceType.getDeclaredFields();
			// 필드 순회
			for(Field fld : fields) {
				// 파라미터명과 필드명이 같으면  
				if(paramName.equals(fld.getName())){ // memId
					try {
						PropertyDescriptor pd = new PropertyDescriptor(paramName, instanceType); // Getter, Setter
						
						pd.getWriteMethod().invoke(member, paramValue); // Setter 호출
						Object propValue = pd.getReadMethod().invoke(member); // Getter 호출
						System.out.printf("%s : %s\n", pd.getName(), propValue);
					} catch (Exception e) { // IntrospectionException -> Exception
						throw new RuntimeException();
					}
					break;
				}
			}
		}
	}

}
