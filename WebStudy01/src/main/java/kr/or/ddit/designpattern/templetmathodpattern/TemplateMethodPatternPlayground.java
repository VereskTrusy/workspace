package kr.or.ddit.designpattern.templetmathodpattern;

import kr.or.ddit.designpattern.templetmathodpattern.obj.DerivedClass1;
import kr.or.ddit.designpattern.templetmathodpattern.obj.DerivedClass2;
import kr.or.ddit.designpattern.templetmathodpattern.obj.TemplateClass;

public class TemplateMethodPatternPlayground {
	public static void main(String[] args) {
		TemplateClass[] array = new TemplateClass[]{new DerivedClass1(), new DerivedClass2()};
		
		for(TemplateClass tmp : array) {
			tmp.template();
		}
	}
}
