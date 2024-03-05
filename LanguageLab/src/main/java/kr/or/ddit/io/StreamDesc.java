package kr.or.ddit.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import kr.or.ddit.exception.FileCopyFilureException;

/**
 * 스트림(stream)
 * 	: 연속성을 지닌 순차적인 일련의 데이터 집합이면서 , 데이터 집합이 이동하는 [단방향] 통로. 
 * 1. 데이터 읽기 위한 통로 개방
 * 2. 데이터 쓰기 위한 통로 개방
 * 
 * 스트림의 분류
 * 1. 전송 데이터의 크기 
 * 		1) 1 byte : byte stream, ~~~In[out]putStream
 * 			FileInputStream/FileOutputStream
 * 			ByteArrayInputStream/ByteArrayOutputStream
 * 			SocketInputStream/SoketOutputStream
 * 		2) 2 byte : 1개의 char단위 : character stream, ~~~Reader[Writer]
 * 			StringReader/StringWriter
 * 2. 스트림 생성 방법과 필터링 여부에 따른 분류
 * 		1) 1차 스트림 : 미디어를 대상을 직적 개방, 매체에 직접 연결
 * 			ex) FileInputStream
 * 		2) 2차 스트림(연결형 스트림)  : 1차 스트림에 연결혀응ㄹ 생성되는 
 * 			ex) BufferedInputStream, bufferedWriter
 * 
 * 
 * 스트림 사용 단계
 * 1. media(매체)를 객체화 시킨다.
 * 		ex) new File(), new byte[], Soket.open
 * 2. media 종류에 따른 입출력 스트림 개방
 * 		ex) new FileInputStream(media)/FileReader(media) 
 * 		--(optional)2차 스트림으로 전송 효율을 높일 수 있음.
 * 		// 이스트림은 미디어와 연결이 되어야한다., 언제나 기본형 스트림은 존재하지 않는다., 생성시 미디어를 전달해준다.
 * 3. 전송크기에 따라 전송데이터의 끝(EOF:-1,null/EOS)까지 반복적인 i(read계열사용)/o(write계열사용) 작업을 수행한다.
 * 4. media release 단계(close())
 * 
 * 
 * 1.7 이후에서는 nio라는 패키지를 사용한다. 양방향으로 사용하고싶을때 채널을 사용하는데 그때 사용한다.
 * 채널 : 양방향 데이터 통신 가능
 */
public class StreamDesc {
	public static void main(String[] args) throws IOException {
		//readEngStrings_byteStream();
		//readEngStrings_charStream();
		//readKorStrings_charStream();
		//readKorStrings_tryWithResource();
		//readAndWriteKorString_tryWithResource();
		readAndWriteKorString_copy();
	}
	
	private static void readAndWriteKorString_copy() {
		File readFile = new File("F:/00.medias/ETA_ANSI.txt");
		File writeFile = new File("F:/ETA_ANSI.txt");

		try(InputStream is = new FileInputStream(readFile);
			BufferedInputStream bis = new BufferedInputStream(is);
			OutputStream os = new FileOutputStream(writeFile);
			BufferedOutputStream bos = new BufferedOutputStream(os); 
		){
			int readbyte = -1;
			while((readbyte = bis.read()) != -1) {
				bos.write(readbyte);
			}
		} catch(IOException e) {
			throw new FileCopyFilureException(e);
		}
	}

	// 파일 시스템에서 파일 읽어서, 다른 경로에 파일 쓰기, 1차,2차 스트림 사용.
	private static void readAndWriteKorString_tryWithResource() throws IOException{
		File readFile = new File("F:/00.medias/ETA_ANSI.txt");
		File writeFile = new File("F:/ETA_ANSI.txt");
		
		try(
				FileInputStream fis = new FileInputStream(readFile);
				InputStreamReader isr = new InputStreamReader(fis, "MS949");
				BufferedReader reader = new BufferedReader(isr);
				FileWriter writer = new FileWriter(writeFile);
				BufferedWriter bw = new BufferedWriter(writer);
			) {
				String readStr = null;
				while((readStr = reader.readLine()) != null) {
					System.out.println(readStr);
					bw.write(String.format("%s\n", readStr));
				}
			}
		
	}

	private static void readKorStrings_tryWithResource() throws IOException {
		File txtFile = new File("F:/00.medias/ETA_ANSI.txt");
		// try(closable 객체 선언문 - finally 내부에서 close 되는 객체) { }
		try(
			FileInputStream fis = new FileInputStream(txtFile);
			InputStreamReader isr = new InputStreamReader(fis, "MS949");
			BufferedReader reader = new BufferedReader(isr);
		) {
			String readStr = null;
			while((readStr = reader.readLine()) != null) {
				System.out.println(readStr);
			}
		}
	}

	private static void readKorStrings_charStream() throws IOException {
		// 1. media(매체)를 객체화 시킨다.
		File txtFile = new File("F:/00.medias/ETA_ANSI.txt");
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader reader = null;
		
		try {
			// 2. media 종류에 따른 입출력 스트림 개방
			fis = new FileInputStream(txtFile);
			
			// 3. 전송크기에 따라 전송데이터의 끝(EOF:-1,null/EOS)까지 반복적인 i(read계열사용)/o(write계열사용) 작업을 수행한다.
			isr = new InputStreamReader(fis, "MS949");
			reader = new BufferedReader(isr);
			String readStr = null;
			while((readStr = reader.readLine()) != null) {
				System.out.println(readStr);
			}
		} finally {
			// 4. media release 단계(close())
			if(fis != null) 
				fis.close();
			if(fis != null) 
				isr.close();
			if(fis != null) 
				reader.close();
		}
	}

	private static void readEngStrings_charStream() throws IOException {
		// 1. media(매체)를 객체화 시킨다.
		File txtFile = new File("F:/00.medias/another_day.txt");
		
		// 2. media 종류에 따른 입출력 스트림 개방
		FileReader fr = new FileReader(txtFile);
		
		// 3. 전송크기에 따라 전송데이터의 끝(EOF:-1,null/EOS)까지 반복적인 i(read계열사용)/o(write계열사용) 작업을 수행한다.
		BufferedReader reader = new BufferedReader(fr);
		String readStr = null;
		while((readStr = reader.readLine()) != null) {
			System.out.println(readStr);
		}
		
//		int readByte = -1;
//		while((readByte = fr.read()) != -1) {
//			System.out.print((char)readByte);
//		}
		
		// 4. media release 단계(close())
		fr.close();
		reader.close();
	}

	private static void readEngStrings_byteStream() throws IOException {
		// 1. media(매체)를 객체화 시킨다.
		File txtFile = new File("F:/00.medias/another_day.txt");
		
		// 2. media 종류에 따른 입출력 스트림 개방
		FileInputStream fis = new FileInputStream(txtFile);
		
		// 3. 전송크기에 따라 전송데이터의 끝(EOF:-1,null/EOS)까지 반복적인 i(read계열사용)/o(write계열사용) 작업을 수행한다.
		BufferedInputStream bis = new BufferedInputStream(fis);
		int readByte = -1;
		while((readByte = bis.read()) != -1) {
			System.out.print((char)readByte);
		}
		
		// 4. media release 단계(close())
		bis.close();
		fis.close();
	}
}















