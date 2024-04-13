package vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "departmentId")
public class DepartmentsVO {
	@NotNull
	private Integer departmentId; // 부서코드, type:NUMBER, nullable:N
	@NotBlank
	private String departmentName; // 부서명, type:VARCHAR2, nullable:N
	private Integer managerId; // 관리자아이디, type:NUMBER, nullable:Y
	private Integer locationId; // 지역코드, type:NUMBER, nullable:Y
}
