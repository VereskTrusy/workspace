package vo;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class EmployeesVO {
	@NotBlank
	private Long employeeId; // 직원아이디, type:NUMBER, nullable:N
	private String firstName; // 성, type:VARCHAR2, nullable:Y
	@NotBlank
	private String lastName; // 이름, type:VARCHAR2, nullable:N
	@NotBlank
	private String email; // 이메일, type:VARCHAR2, nullable:N
	private String phoneNumber; // 핸드폰번호, type:VARCHAR2, nullable:Y
	@NotBlank
	private LocalDate hireDate; // 입사일, type:DATE, nullable:N
	@NotBlank
	private String jobId; // 직무코드, type:VARCHAR2, nullable:N
	private Long salary; // 기본급, type:NUMBER, nullable:Y
	private Long commissionPct; // 영업실적비율, type:NUMBER, nullable:Y
	private Long managerId; // 관리자아이디, type:NUMBER, nullable:Y
	private Long departmentId; // 부서코드, type:NUMBER, nullable:Y
	private String empName; // 사원명, type:VARCHAR2, nullable:Y
}
