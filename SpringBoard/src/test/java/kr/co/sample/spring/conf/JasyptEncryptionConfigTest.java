package kr.co.sample.spring.conf;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Properties;

import javax.annotation.Resource;

import org.jasypt.encryption.pbe.PBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.co.sample.AbstractRootContextTest;
import lombok.extern.slf4j.Slf4j;

class JasyptEncryptionConfigTest extends AbstractRootContextTest{
	
	@Autowired
	PBEStringEncryptor encryptor;
	
	@Resource(name="dbInfo")
	Properties dbInfo;

	@Test
	void test() {
		dbInfo.keySet().forEach(k->{
			String key = k.toString();
			String value = dbInfo.getProperty(key);
			System.out.printf("%s=%s\n", key, value);
//			String encrypted = encryptor.encrypt(value);
//			System.out.printf("%s=ENC(%s)\n", key, encrypted);
		});
	}

}




















