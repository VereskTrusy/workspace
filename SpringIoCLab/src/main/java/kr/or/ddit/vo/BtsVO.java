package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * VO(value object), DTO(Data Transfer Object), Model, Bean
 * 1. 값을 저장할 수 있는 프로퍼티가 있어야한다.
 * 2. 캡슐화 구조가 필요하다. 
 * 3. 캡슐화된 프로퍼티에 대한 인터페이스 제공해줘야 한다.
 * 4. 상태 비교 방법 제공해야한다.
 * 5. 상태 확인 방법 제공해야한다.
 * 6. 직렬화가 가능해야 한다.
 */
public class BtsVO implements Serializable{ // 마커 인터페이스
	
	public BtsVO() { // 프로퍼티가 널 일 수도 있다.
		super();
	}
	
	public BtsVO(String code, String name, String path, int count) {
		super();
		this.code = code;
		this.name = name;
		this.path = path;
		this.count = count;
	}
	
	private String code; // 식별자
	private String name;
	private transient String path; // transient 직렬화에 제외한다.  
	private int count; // 조회수
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public int getCount() {
		return count;
	}
	
	private void setcout(int count) {
		this.count = count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BtsVO other = (BtsVO) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BtsVo [code=" + code + ", name=" + name + ", count=" + count +"]";
	}
	
	
}
