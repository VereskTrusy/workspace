package kr.or.ddit.security;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import kr.or.ddit.mapper.LoginMapper;
import kr.or.ddit.vo.CustomUser;
import kr.or.ddit.vo.MemberVO;

public class CustomUserDetailsService implements UserDetailsService  {

	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Inject
	BCryptPasswordEncoder pe;
	
	@Inject
	private LoginMapper loginMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO member;
		try {
			member = loginMapper.readByUserId(username);
			logger.warn("queried by member mapper : " + member);
			return member == null ? null : new CustomUser(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
