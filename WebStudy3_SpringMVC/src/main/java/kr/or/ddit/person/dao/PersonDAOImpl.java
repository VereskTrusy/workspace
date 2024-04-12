package kr.or.ddit.person.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import kr.or.ddit.vo.PersonVo;

public class PersonDAOImpl implements PersonDAO {
	
	Properties props;
	
	public PersonDAOImpl() {
		super();
		props = new Properties();
		try(
			InputStream is = this.getClass().getResourceAsStream("/kr/or/ddit/MemberData.properties");
		){
			props.load(is);
		} catch (IOException e) { // IOException은 체크드익셉션이기때문에 처리하지 않으면 컴파일 에러 발생하는데 
			throw new UncheckedIOException(e); // 언체크드 익셉션으로 만들어서 던지면 상위에서 처리
		}
	}
	
	private PersonVo rawToObject(String id, String rawData) {
		String[] tokens = rawData.toString().split("\\|");
		return new PersonVo(id.toString(), tokens[0], tokens[1], tokens[2], tokens[3]);
	}
	
	
	@Override
	public List<PersonVo> selectPersonList(){
		List<PersonVo> people = new ArrayList<>();
		Enumeration<Object> keys = props.keys();
		while (keys.hasMoreElements()) {
			Object key = (Object) keys.nextElement();
			Object value = props.get(key);
			PersonVo person = rawToObject(key.toString(), value.toString());
			people.add(person);
		}
		return people;
	}
	
	@Override
	public PersonVo selectPerson(String id){
		String property = props.getProperty(id);
//		if(property != null) {
//			return rawToObject(id, property);
//		} else {
//			return null;
//		}
		return Optional.ofNullable(property).map((p)->rawToObject(id, p)).orElse(null);
	}
}
