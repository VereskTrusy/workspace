package vo;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RetireVO {
	@NotNull
	private Long employeeId; // 직원아이디, type:NUMBER, nullable:N
	private Integer departmentId; // 부서코드, type:NUMBER, nullable:Y
	private String hireDate; // 입사일, type:DATE, nullable:Y
	private String retireDate; // 퇴사일, type:DATE, nullable:Y
}
