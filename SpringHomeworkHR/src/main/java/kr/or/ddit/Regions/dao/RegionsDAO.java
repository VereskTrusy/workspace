package kr.or.ddit.Regions.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import vo.RegionsVO;

@Mapper
public interface RegionsDAO {
	public List<RegionsVO> selectRegionsList();
	
	public RegionsVO selectRegion(@Param(value = "regionId") String regionId);
	
	public void insertRegion(RegionsVO region);
	
	public void updateRegion(RegionsVO region);
	
	public void deleteRegion(RegionsVO region);
}
