package kr.or.ddit.servlet09;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;

import kr.or.ddit.vo.BtsVO;

public class FileWrapper implements Comparable<FileWrapper>, Serializable{
	private File adaptee;
	private String path;
	
	// 기본생성자 사용 x
	public FileWrapper(File adaptee, String path) {
		super();
		this.adaptee = adaptee;
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}
	
	public String getName() {
		return adaptee.getName();
	}
	
	public boolean isFile() {
		return adaptee.isFile();
	}
	
	public boolean isFolder() {
		return adaptee.isDirectory();
	}

	@Override
	public int compareTo(FileWrapper o) {
		if(this.isFile() ^ o.isFile()) { // ^ : ExcrusibOr
			return this.isFolder() ? -1 : 1; // 앞으로 -1, 뒤로 +1
		} else {
			return getName().toLowerCase().compareTo(o.getName().toLowerCase());			
		}
	}
	
	
}
