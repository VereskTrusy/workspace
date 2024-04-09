package kr.or.ddit.case6;

import java.time.LocalDate;

import org.springframework.beans.factory.FactoryBean;

// 배열을 FactoryBean을 사용하여 만들기
public class ObjectArrayFactoryBean implements FactoryBean<Object[]> {

	@Override
	public Object[] getObject() throws Exception {
		return new Object[] {LocalDate.now(), "element2"};
	}

	@Override
	public Class<?> getObjectType() {
		return Object[].class;
	}

}
