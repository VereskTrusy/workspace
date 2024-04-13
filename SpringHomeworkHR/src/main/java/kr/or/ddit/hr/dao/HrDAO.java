package kr.or.ddit.hr.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import vo.RegionsVO;

@Mapper
public interface HrDAO {
	
	public List<RegionsVO> selectAllLocationInfo();
}
