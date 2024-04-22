package kr.or.ddit.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import lombok.extern.slf4j.Slf4j;



/**
 * before : 타겟 메소드 호출 전에 위빙
 * after : 타겟 메소드 호출 이후에 위빙
 * around : 
 */
@Slf4j
public class LoggingAdvice {
	
	public void beforeAdvice(JoinPoint point){
		Object target = point.getTarget();
		String targetName = target.getClass().getName();
		String methodName = point.getSignature().getName(); // 시그니처 = 리턴타입 메소드이름 메소드파라미터
		Object[] args = point.getArgs();
		
		log.info("{}.{}({}) 호출 전 위빙", targetName, methodName, args);
	}
	
	public void afterAdvice(JoinPoint point, Object retValue){
		Object target = point.getTarget();
		String targetName = target.getClass().getName();
		String methodName = point.getSignature().getName();
		Object[] args = point.getArgs();
		
		log.info("{}.{}==>{} 호출 이후 위빙",targetName, methodName, retValue);
	}
	
	public Object aroundAdvice(ProceedingJoinPoint point) throws Throwable {
		Object target = point.getTarget();
		String targetName = target.getClass().getName();
		String methodName = point.getSignature().getName();
		Object[] args = point.getArgs();// 아규먼트 획득
		
		log.info("호출 전 위빙 {}.{}({})", targetName, methodName, args);
		// start time
		long start = System.currentTimeMillis();
		// 로직 호출 지점
		Object retValue = point.proceed(args); 
		// end time
		long end = System.currentTimeMillis();
		
		log.info("호출 이후 위빙, 소요시간:{}ms {}.{}==>{}", (end-start), targetName, methodName, retValue);
		
		// 중간에 로직을 호출했기때문에
		// 반환 값을 다시 컨트롤러로 던져줘야한다.
		return retValue;
	}
}














