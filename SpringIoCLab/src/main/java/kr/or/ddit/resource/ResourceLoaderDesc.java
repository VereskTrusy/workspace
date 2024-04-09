package kr.or.ddit.resource;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;

import org.apache.commons.io.IOUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;

import lombok.extern.slf4j.Slf4j;

/**
 * Resource : 스프링에서 자원을 표현하는 "추상" 타입.
 *  - 자원의 위치와 자원에 대한 접근방법에 따른 구현체 지원
 *    1. FileSystemResource : 물리경로 사용
 *    2. ClassPathResource : 클래스패스 이후의 qualified name(논리경로)을 통해 접근
 *    3. UrlResource : 웹상에 존재하는 url(논리경로)로 접근하는 자원
 * 
 * ResourceLoader : 자원에 분류와 관계없이 일관된 방법으로 Resource 를 로딩해주는 객체
 * 
 * 일반적으로 ResourceLoader 를 사용할 때는 readonly로 설정되어 있다.
 * WritableResource 는 읽는 용도로 사용 할 수 있다.
 */
@Slf4j
public class ResourceLoaderDesc {
	public static void main(String[] args) throws IOException {
		// Resource를 사용하려면 구현체가 필요하다.
		ResourceLoader loder = new ClassPathXmlApplicationContext(); // ResourceLoader 가 컨테이너의 상위라서 가능
		
		/*
		loder.getResource()
		Return a Resource handle for the specified resource location. 
		The handle should always be a reusable resource descriptor,
		allowing for multiple Resource.getInputStream() calls. 
			
		•Must support fully qualified URLs, e.g. "file:C:/test.dat". 
		•Must support classpath pseudo-URLs, e.g. "classpath:test.dat". 
		•Should support relative file paths, e.g. "WEB-INF/test.dat".(This will be implementation-specific, typically provided by anApplicationContext implementation.) 
			
		Note that a Resource handle does not imply an existing resource;you need to invoke Resource.exists to check for existence.
		 */
		// 1. FileSystemResource 사용 해보기
		Resource fsRes = loder.getResource("file:F:/00.medias/another_day.txt");
		log.info("FileSystemResource : {}", fsRes);
		
		// 2. ClassPathResource 사용 해보기
		// /SpringIoCLab/src/main/resources/kr/or/ddit/MemberData.properties
		// ->
		// kr/or/ddit/MemberData.properties 로 path 명시
		// 
		Resource cpRes = loder.getResource("classpath:kr/or/ddit/MemberData.properties");
		log.info("ClassPathResource : {}", cpRes);
		
		// 3. UrlResource 사용 해보기
		// https: 라는 prefix가 붙어 있기때문에 다른 prefix를 사용할 필요가 없다.
		Resource urlRes = loder.getResource("https://www.google.com/images/branding/googlelogo/1x/googlelogo_light_color_272x92dp.png");
		log.info("UrlResource : {}", urlRes);
		
		// 읽은 파일 쓰기
		FileSystemResource fsRes2 = new FileSystemResource(new File("F:/00.medias/MemberData.properties"));
		FileSystemResource fsRes3 = new FileSystemResource(new File("F:/00.medias/googlelogo_light_color_272x92dp.png"));
		
		// 나
//		ResourceLoaderDesc.copyResource(cpRes, fsRes2);
//		ResourceLoaderDesc.copyResource(urlRes, fsRes3);
		
		// 센세
		File destFolder = new File("F:/00.medias");
		copyResourceSsense(cpRes, destFolder);
		copyResourceSsense(urlRes, destFolder);
		
	}
	
	private static void copyResourceSsense(Resource src, File destFolder) {
		String fileName = src.getFilename();
		log.info("파일명 : {}", fileName);
		
		File destFile = new File(destFolder, fileName);
		WritableResource destRes = new FileSystemResource(destFile);
		try(
			InputStream is = src.getInputStream();
			OutputStream os = destRes.getOutputStream();
		){
			IOUtils.copy(is, os);
		}catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
	
	private static void copyResource(Resource res, FileSystemResource fsRes) throws IOException {
		try(InputStream fis = res.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(fis);
			OutputStream os = fsRes.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(os);
			){
				int readByte = -1;
				while((readByte = bis.read()) != -1) {
					bos.write(readByte);
			}
			bos.flush();
		}
	}
}
