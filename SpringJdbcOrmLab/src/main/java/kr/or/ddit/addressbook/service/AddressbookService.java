package kr.or.ddit.addressbook.service;

import java.util.List;

import kr.or.ddit.vo.AddressbookVO;


public interface AddressbookService {
	
	public List<AddressbookVO> addressbookList();
	
	public int insertAddressbook(AddressbookVO address);
}
