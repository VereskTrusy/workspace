package kr.or.ddit.servlet10.service;

import java.util.Map;

import kr.or.ddit.servlet10.dao.PropertyDAO;
import kr.or.ddit.servlet10.dao.PropertyDAOImpl;

public class PropertyServiceImpl implements PropertyService{
	private PropertyDAO dao = new PropertyDAOImpl();

	@Override
	public void readProperties(Map<String, Object> paramMap) {
		dao.selectProperties(paramMap);
	}

}
