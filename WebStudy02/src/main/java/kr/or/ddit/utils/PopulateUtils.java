package kr.or.ddit.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.AbstractConverter;

public class PopulateUtils {
	
	public static <T> void populate(T bean, Map<String, ? extends Object> parameterMap) {
		AbstractConverter localDateConverter = new AbstractConverter() {
			@Override
			protected Class<?> getDefaultType() {
				return Temporal.class;
			}
			
			@Override
			protected <T> T convertToType(Class<T> type, Object value) throws Throwable {
				if(value == null || value.toString().isEmpty()) return null; 
				else {
					String paramValue = value.toString();
//					return (T) LocalDate.parse(paramValue);
//					return (T) LocalDateTime.parse(paramValue);
					return (T) type.getDeclaredMethod("parse", CharSequence.class).invoke(null, paramValue);
				}
			}
		};
		
		ConvertUtils.register(localDateConverter, LocalDate.class);
		ConvertUtils.register(localDateConverter, LocalDateTime.class); // Temporal : 시간을 관리 할 수 있는 모든 유틸은 얘를 사용
		
		try {
			BeanUtils.populate(bean, parameterMap); // 내부적으로 리플렉션 시킴
			
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
