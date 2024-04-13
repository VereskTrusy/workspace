package vo;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RegionsVO {
	@NotNull
	private Integer regionId; // 대륙코드, type:NUMBER, nullable:N
	private String regionName; // 대륙명, type:VARCHAR2, nullable:Y
	
	
	// Region,country,location 정보 한번에 받아보기
	private List<CountriesVO> countryList;
}
