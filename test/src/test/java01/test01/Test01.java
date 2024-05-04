package test.java01.test01;

import java.io.File;
import java.io.IOException;

public class Test01 {

	public static void main(String[] args) throws IOException {
		// 내가 쓰고 있는 파일을 읽어서 콘솔 화면에 나타내어 주세요.
//		FileReader reader =  new FileReader("F:/D_setting/B_Util/11. Egov/eGovFrameDev-4.0.0-64bit/workspace/test/src/test/java01/test01/Test01.java");
		
//		int ch;
//		while((ch = reader.read()) != -1) {
//			System.out.print((char)ch);
//		}
		
		
		
//		ParamMap test = ParamMap.init();
		//ParamMap test2 = new ParamMap();
		
		File file = new File("F:\\D_setting\\B_Util\\11. Egov\\eGovFrameDev-4.0.0-64bit\\workspace\\test\\src");
		File[] listFiles = file.listFiles();
		
		if(listFiles != null) {
			for(File f :listFiles) {
				
				System.out.println(f.getAbsolutePath());					
			}
		}
		// 재귀함수로 해결해보기
		
		
	}
}
