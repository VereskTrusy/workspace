package kr.or.ddit.case5.person.dao;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PersonVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor
public class PersonDAOImpl implements PersonDAO {
	// 동적으로 주입 받기
	// 1. properties 가 있어야함
	// 2. 하드코딩 안하고픔
	// 리소스로 만들어보기
	//private final Resource cpRes;
	@javax.annotation.Resource(name = "props")
	private final Properties props;
	
	// 연습용
	@Value(value = "file:F:/00.medias/another_day.txt") // 리소스를 검색하기 위한 prefix로 사용한다.
	private Resource fsRes;
	
	public void setFsRes(Resource fsRes) {
		this.fsRes = fsRes;
	}
	
	@PostConstruct
	public void init() {
		// 주입이 끝난이후에 동작하여햐함.
//		try(
//			InputStream is = cpRes.getInputStream();
//		){
//			props.load(is);
//			
//			// 연습용
//			log.info("주입된 리소스 : {}", cpRes);
			log.info("주입된 리소스 : {}", fsRes);
//		} catch (IOException e) { // IOException은 체크드익셉션이기때문에 처리하지 않으면 컴파일 에러 발생하는데 
//			throw new UncheckedIOException(e); // 언체크드 익셉션으로 만들어서 던지면 상위에서 처리
//		}
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
