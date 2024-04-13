package vo;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class JobHistoryVO {
	@NotNull
	private Long employeeId; // 직원아이디, type:NUMBER, nullable:N
	@NotBlank
	private LocalDate startDate; // 업무시작일, type:DATE, nullable:N
	@NotBlank
	private LocalDate endDate; // 업무종료일, type:DATE, nullable:N
	@NotBlank
	private String jobId; // 직무코드, type:VARCHAR2, nullable:N
	private Integer departmentId; // 부서코드, type:NUMBER, nullable:Y
}
