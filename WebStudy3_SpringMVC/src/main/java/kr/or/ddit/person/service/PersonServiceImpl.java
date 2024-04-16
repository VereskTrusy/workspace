package kr.or.ddit.person.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.person.dao.PersonDAO;
import kr.or.ddit.person.exception.PersonNotFoundException;
import kr.or.ddit.vo.PersonVo;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonDAO dao;

	@Override
	public List<PersonVo> retrievePersonList() {
		List<PersonVo> people = dao.selectPersonList();
//		for(PersonVo once : people) {
//			System.out.printf("%s 조회함\n", once.getName());
//		}
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
