package kr.or.ddit.properties;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.junit.jupiter.api.Test;

import kr.or.ddit.servlet01.DescriptionServlet;
import kr.or.ddit.vo.PersonVo;

class PropertiesTest {
	
	@Test
	void testPropertiesHandling() throws IOException {
		Properties props = new Properties();
		try(
				InputStream is = DescriptionServlet.class.getResourceAsStream("/kr/or/ddit/MemberData.properties");
			){
			props.load(is);
			Set<Object> ketSet = props.keySet();
			// list : index를 가지고 있는 순차집합
			// set : 중복 허용하지 않는 집합
			// map : key/value 형태의 엔트리를 가진 집합
//			for(Object key : ketSet) {
//				Object value = props.get(key);
//				System.out.printf("%s = %s\n", key, value);
//			}
			
			// 두번째 방법
			for(Entry<Object, Object> entry : props.entrySet()) {
				System.out.printf("%s = %s\n", entry.getKey(), entry.getValue());
			}
			
			// 세번째 방법
			// collection view : 실제 집합 객체에 대한 접근 방법을 정의하고 있는 객체.
			Enumeration<Object> keys = props.keys();
			while (keys.hasMoreElements()) {
				Object key = (Object) keys.nextElement();
				Object value = props.get(key);
				String[] tokens = value.toString().split("\\|");
				PersonVo person = new PersonVo(key.toString(), tokens[0], tokens[1], tokens[2], tokens[3]);
				System.out.println(person);
			}
		}
	}
	
	@Test
	void testProperties() throws IOException {
		Properties props = new Properties();
		System.out.printf("properties size : %d\n", props.size());
		
		String realPath = DescriptionServlet.class.getResource("/kr/or/ddit/MemberData.properties").getFile();
		File writeFile = new File(realPath);
		try(
			InputStream is = DescriptionServlet.class.getResourceAsStream("/kr/or/ddit/MemberData.properties"); // 클래스 패스 이후의 경로에 슬러시가 반드시 붙어야한다.
			//FileOutputStream fos = new FileOutputStream(writeFile);
		){
			props.load(is);
			//props.clear(); // 데이터 싹 지우기
			//props.save(fos, "clear");
			System.out.printf("properties size : %d\n", props.size());
		}
		// DescriptionServlet.class.getResourceAsStream("../kr/or/ddit/MemberData.properties"); // 위 코드와 같은 의미다  
		
	}

	@Test
	void test1() {
		System.out.println("test1");
	}

	@Test
	void test2() {
		System.out.println("test2");
	}
}
