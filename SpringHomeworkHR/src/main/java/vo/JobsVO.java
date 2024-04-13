package vo;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class JobsVO {
	@NotBlank
	private String jobId; // 직무코드, type:VARCHAR2, nullable:N
	@NotBlank
	private String jobTitle; // 직무명, type:VARCHAR2, nullable:N
	private Long minSalary; // 최저급여, type:NUMBER, nullable:Y
	private Long maxSalary; // 최대급여, type:NUMBER, nullable:Y
}
