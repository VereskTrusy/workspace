package kr.or.ddit.addressbook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.AddressbookVO;

@Mapper
public interface AddressbookDAO {
	
	public List<AddressbookVO> selectAddressbookList();
	
	public int insertAddressbook(AddressbookVO address);
}
