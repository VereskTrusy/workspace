package kr.or.ddit.util;

public enum ServerAdress {
	FLASK_SERVER("192.168.141.26:5000");
	
	private String ip;
	
	
	ServerAdress(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}
}
