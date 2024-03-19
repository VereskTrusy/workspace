package kr.or.ddit.json;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.or.ddit.json.MarshillingTest.TestVo;

class UnMashallingTest {
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	void test1() throws JsonMappingException, JsonProcessingException {
		String json = "[1,2,3]";
		int[] array = mapper.readValue(json, int[].class); // 내부적으로 제네릭 타입 사용
		System.out.println(array.length);
	}

	
	@Test
	void test2() throws JsonMappingException, JsonProcessingException {
		String json = "{\"key1\":34,\"key2\":false,\"key5\":{\"innerKey\":\"INNERVALUE\"},\"key3\":\"STRINGVALUE\",\"key4\":[1,2,3]}";
		Map<String, Object> target = mapper.readValue(json, HashMap.class);
		System.out.println(target);
	}
	
	@Test
	void test3() throws JsonMappingException, JsonProcessingException {
		String json ="{\"prop1\":\"한글값\",\"prop2\":23.567}";
		TestVo target = mapper.readValue(json, TestVo.class);
		System.out.println(target.getProp1() + " " + target.getProp2());
	}
	
	@Test
	void test4() throws JsonMappingException, JsonProcessingException {
		String xml = "<TestVo><prop1>한글값</prop1><prop2>23.567</prop2></TestVo>";
		ObjectMapper mapper = new XmlMapper();
		TestVo target = mapper.readValue(xml, TestVo.class);
		System.out.println(target.getProp1() + " " + target.getProp2());
	}
}
