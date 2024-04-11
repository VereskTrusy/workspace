package kr.or.ddit.person.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.person.dao.PersonDAO;
import kr.or.ddit.person.exception.PersonNotFoundException;
import kr.or.ddit.vo.PersonVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

	@Autowired
	private final PersonDAO dao;

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
