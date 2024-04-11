package kr.or.ddit.addressbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.addressbook.dao.AddressbookDAO;
import kr.or.ddit.vo.AddressbookVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressbookServiceImpl implements AddressbookService {
	
	@Autowired
	private final AddressbookDAO dao;

	@Override
	public List<AddressbookVO> addressbookList() {
		return dao.selectAddressbookList();
	}

	@Override
	public int insertAddressbook(AddressbookVO address) {
		return dao.insertAddressbook(address);
	}

}
