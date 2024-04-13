package vo;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LocationsVO {
	@NotNull
	private Integer locationId; // 지역코드, type:NUMBER, nullable:N
	private String streetAddress; // 도로명 주소, type:VARCHAR2, nullable:Y
	private String postalCode; // 우편번호, type:VARCHAR2, nullable:Y
	private String city; // 도시명, type:VARCHAR2, nullable:N
	private String stateProvince; // 상세 주소, type:VARCHAR2, nullable:Y
	private String countryId; // 국가코드, type:CHAR, nullable:Y
	
	// Region,country,location 정보 한번에 받아보기
	private List<DepartmentsVO> departmentList;
}
