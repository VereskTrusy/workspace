package kr.or.ddit.person.dao;

import java.util.List;

import kr.or.ddit.vo.PersonVo;


/**
 * Persistent Layer : persistence 영역에 저장된 raw data 를 domain 객체로 매핑하는 역활을 담당하는 객체이다. 
 *
 */
public interface PersonDAO {

	public List<PersonVo> selectPersonList();

	public PersonVo selectPerson(String id);

}