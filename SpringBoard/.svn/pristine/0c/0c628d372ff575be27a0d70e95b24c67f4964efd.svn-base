package kr.co.sample.member.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.sample.member.mapper.MemberMapper;
import kr.co.sample.member.vo.MemberVO;
import kr.co.sample.member.vo.MemberVOWrapper;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO realUser = mapper.selectMemberForAuth(username);
		if(realUser==null) {
			throw new UsernameNotFoundException(String.format("%s 사용자 없음.", username));
		}
		String memRole = realUser.getMemRole();
		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(memRole);
		authorities.addAll( loadAuthrotiesByRole(memRole) );
		
		MemberVOWrapper userDetails = new MemberVOWrapper(realUser, authorities);
		return userDetails;
	}
	
	
	private List<GrantedAuthority> loadAuthrotiesByRole(String role) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		if(role.endsWith("ADMIN")) {
			authorities.add(new SimpleGrantedAuthority("READ"));
			authorities.add(new SimpleGrantedAuthority("WRITE"));
		}else {
			authorities.add(new SimpleGrantedAuthority("READ"));
		}
		return authorities;
	}

}













