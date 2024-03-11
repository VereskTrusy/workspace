package kr.or.ddit.enumpkg;

public enum BrowserInfo {
	//enum의 의미
//	private final BrowserInfo EDG = new BrowserInfo();
//	private final BrowserInfo WHALE = new BrowserInfo();
//	
//	private BrowserInfo() { // 나만 사용 가능한 생성자
//		super();
//	}
	
	EDG("엣지"), // 하나하나의 상수는 순서 ordianl을 가지고 있다.(인덱스값)
	WHALE("웨일"),
	CHROME("크롬"),
	SAFARI("사파리"),
	OTHERS("기타");
	
	// 생성자
	private BrowserInfo(String browserName) {
		this.browserName = browserName;
	}

	private String browserName;
	
	// 브라우저명 반환
	private String getBrowserName() {
		return browserName;
	}
	
	// 브라우저 정보 반환
	private static BrowserInfo findBrowser(String userAgent) {
		BrowserInfo finded = OTHERS; // 기타 까지 식별하기 위함
		
		for(BrowserInfo single : values()) { // 
			if(userAgent.toUpperCase().contains(single.name())) {
				finded = single;
				break;
			}
		}
		
		return finded;
	}
	
	// 식별된 브라우저명 반환
	public static String findBrowserName(String userAgent) {
		return findBrowser(userAgent).getBrowserName();
	}
}























