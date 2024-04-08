package kr.or.ddit.case5.person.service;

import java.util.List;

import kr.or.ddit.case5.person.dao.PersonDAO;
import kr.or.ddit.case5.person.dao.PersonDAOImpl;
import kr.or.ddit.case5.person.exception.PersonNotFoundException;
import kr.or.ddit.vo.PersonVo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


public class PersonServiceImpl implements PersonService {
	private PersonDAO dao;

	// 생성자
	public PersonServiceImpl(PersonDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public List<PersonVo> retrievePersonList() {
		List<PersonVo> people = dao.selectPersonList();
		return people;
	}

	@Override
	public PersonVo retrievePerson(String id) {
		PersonVo person = dao.selectPerson(id);
		
		if(person == null) {
			throw new PersonNotFoundException(id);
		}
		
		return person;
	}
}
