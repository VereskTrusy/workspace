package kr.or.ddit.crypto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class PasswordEncoderTest {
	// encrypting , encoding 둘다~
	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

	@Test
	void testEncrypt() {
		String plain = "java";
		
		String encoded = encoder.encode(plain);
		log.info("encoded : {}", encoded);
	}

	@Test
	void testMatches() {
		String savedPass = "{bcrypt}$2a$10$61os8SmHo1ZnfzI3Sk0liuonMKWa2mk1FczoCoDLdy50Zjz/145Ny";
		// {bcrypt}$2a$10$feEzKvFH9XDMvPCfI5B.reozZKbr9ZiDOw6RRlPC0Knn/UZp4YdPS // 시간을 추가하여 암호화하기 때문에 해시 충돌을 막는다.
		String inputPass = "java";
		
		log.info("인증 성공 여부 : {} ", encoder.matches(inputPass, savedPass));
	}
}
