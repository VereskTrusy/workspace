package kr.co.sample.spring.conf;

import java.util.Properties;

import javax.annotation.Resource;

import org.jasypt.encryption.pbe.PBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.co.sample.AbstractRootContextTest;


class JasyptEncryptionConfigTest extends AbstractRootContextTest {

	@Autowired
	PBEStringEncryptor encrytor;
	
	@Resource(name="dbInfo")
	Properties dbInfo;
	
	@Test
	void test() {
		dbInfo.keySet().forEach( k->{
			String key = k.toString();
			String value = dbInfo.getProperty(key);
			
			System.out.printf("%s=%s\n", key, value);
			
//			String encrypted = encrytor.encrypt(value);
//			System.out.printf("%s=ENC(%s)\n", key, encrypted);
		});
	}

}
