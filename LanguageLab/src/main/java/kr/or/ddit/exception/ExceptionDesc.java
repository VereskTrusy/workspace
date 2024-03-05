package kr.or.ddit.exception;

import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * 기본 개념을 잡자가 킥포인트 !
 * 
 * 예외는 무엇인가 ? : 프로그램이 실행이 중단될 수 있는 모든 상황에 대한 정보를 통칭한다. 
 * 		Throwable 의 하위 인스턴스 객체의 형태.
 * Throwable - Error : 런타임에 직접 처리가 불가능한 시스템 폴트 상황등의 심각정도가 다른 Throwable 객체이다. 전환, 복구, 회피, 처리가 불가능하다.
 * 			 - Exception : 필요에 의해 예외 처리 정책에 따라 직접 처리(회피)가 가능한 Throwable 객체이다.
 * 				- Checked Exception(Exception의 하위) : 예외 발생 가능한 로직에서 발생 가능한 예외를 반드시 직접 처리해야하는 예외이다.
 * 					ex) IOException, SQLException 
 * 				- UnChecked Exception(RuntimeException의 하위) : 직접 예외를 처리하지 않더라도 호출 구조를 통해 최종 JVM 까지 던지는 예외이다.
 * 					(모든 메소드의 선언부에 throws RuntimeException 구문이 묵시적으로 포함되어 있다.)
 * 					ex) NullPointException, IllegalArgumentException, NumberFormatException
 * 
 * ** 예외 처리 정책
 * 1. 적극적인 처리(전환, 복구)
 * 		try(){
 * 			예외 발생가능 로직
 * 		} catch(처리할 예외타입들...){
 * 			전환이나 복구 정책 구현
 * 			1) 전환(***) : 특정조건이나 상황을 명확하게 표현할 수 있는 구체적인 타입의 에외나, 혹은 전혀다른 예외의 분류로 wrapping 할 때 사용되는 전략
 * 				ex) catch(IOException e){
 * 					throw new FileCopyFilureException(e);
 * 	 			}
 * 		 	2) 복구 : 로그기록 -> 트랜젝션 마무리 -> 일정시간 지연 -> 재시도 
 * 		}
 * 2. 소극적인 처리(회피) : Throws키워드로 호출자예게 예외를 떠넘기는 구조
 * 
 * 
 * *** 커스터 예외 정의 방법
 * 1. 예외 계층 구조 결정(Exception, RuntimeException...)
 * 2. throw new 생성자() 로 예외 객체 생성 후 투과한다.
 * 
 * 
 * 
 * 
 * 
 * Ctrl + Shift + T : 타입 검색
 * Ctrl + Shift + R : 리소스 검색
 */
public class ExceptionDesc {
	public static void main(String[] args) {
		try {
			method1();			
		} catch(IOException e) {
			throw new UncheckedIOException(e); // 1.8버전에서 새로 나옴
//			throw new RuntimeException(e);
		}
		
//		try {
//			method1();			
//		} catch(RuntimeException e) {
//			e.printStackTrace();
//			System.out.println("정상처리");
//		}
	}
	
	private static void method1() throws IOException {
		throw new IOException("강제 발생 예외"); // 예외를 의도적으로 던질꺼당
	}
	
	private static void method2() { // 모든 메서드는 throws RuntimeException 이 있다
		throw new NullPointerException("강제 발생 예외");
	}
}
