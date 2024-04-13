package vo;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "countryId")
public class CountriesVO {
	@NotBlank
	private String countryId; // 국가코드, type:CHAR, nullable:N
	private String countryName; // 국가명, type:VARCHAR2, nullable:Y
	private Integer regionId; // 대륙코드, type:NUMBER, nullable:Y
	
	// Region,country,location 정보 한번에 받아보기
	private List<LocationsVO> locationList;
}
