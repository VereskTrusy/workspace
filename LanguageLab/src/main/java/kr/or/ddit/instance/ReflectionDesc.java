package kr.or.ddit.instance;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import kr.or.ddit.reflection.obj.DabakBungeoppangzip;

/**
 * ClassA instanceA = new ClassA();
 * 1. ClassLoader 가 ClassA.class 파일을 클래스 패스에서 검색하고,
 *    해당 클래스 정보를 메모리에 저장함.
 * 2. 1번 단계에서 저장된 클래스 정보를 기반으로 인스턴스를 생성하고, 메모리에 저장함.
 * 3. 2번 단계에서 생성하고 저장된 인스턴스의 참조주소를 instanceA 라는 변수에 저장함.
 * 
 * 
 * 리플렉션이란?
 *  : 일반적인 객체 생성과정을 거치지 않고 획득한 인스턴스를 대상으로, 
 *    해당 인스턴스의 타입(클래스), 상태정보(프로퍼티), 행동정보(메소드)를 찾아가는 작업.
 *    java.reflect 패키지의 API가 지원됨.
 * 
 */
public class ReflectionDesc {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IntrospectionException, IllegalArgumentException, InvocationTargetException {
		Object instance = DabakBungeoppangzip.buyBungeoppang();
		Class instanceType = instance.getClass();
		System.out.printf("%s \n", instanceType.getName());
		
		System.out.println("=======================상태정보 리플렉션==============================");
		Field[] fields = instanceType.getDeclaredFields();
		for( Field fld : fields) {
			String modifiers = Modifier.toString(fld.getModifiers()); // modifier 4가지 구성요소를 가져온다.
			Class fldType = fld.getType(); // type
			String fldName = fld.getName(); // 
			System.out.printf("%s %s %s;\n", modifiers, fldType.getSimpleName(), fldName);
		}
		
		System.out.println("=======================행동정보 리플렉션==============================");
		Method[] methods = instanceType.getDeclaredMethods();
		for( Method mtd : methods) {
			String modifiers = Modifier.toString(mtd.getModifiers()); // modifier 4가지 구성요소를 가져온다.
			Class returnType = mtd.getReturnType();
			String mtdName = mtd.getName(); //
			Class[] paramTypes = mtd.getParameterTypes();
			System.out.printf("%s %s %s(%s);\n", modifiers, returnType.getSimpleName(), mtdName, Arrays.toString(paramTypes));
		}
		
		System.out.println("=======================프로퍼티정보 리플렉션==============================");
		Object newBungeoppang = instanceType.newInstance();
		for( Field fld : fields) {
			//Class fldType = fld.getType(); // type
			String fldName = fld.getName(); //
			try {
				PropertyDescriptor pd = new PropertyDescriptor(fldName, instanceType);
				Method getter = pd.getReadMethod();
				Method setter = pd.getWriteMethod();
				Object value = getter.invoke(instance);
				setter.invoke(newBungeoppang, value);
				System.out.printf("%s = %s;\n", fldName, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(newBungeoppang);
		
		// fishType : 붕어 , ppangso : 슈크림
		
		instanceType.getDeclaredField("fishType");
		PropertyDescriptor pd1 = new PropertyDescriptor("fishType", instanceType);
		pd1.getWriteMethod().invoke(newBungeoppang, "붕어");
		
		PropertyDescriptor pd2 = new PropertyDescriptor("ppangso", instanceType);
		pd2.getWriteMethod().invoke(newBungeoppang, "슈크림");
		System.out.println(newBungeoppang);
		
	}
} // 존나 어려운데?
