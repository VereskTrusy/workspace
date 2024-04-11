package kr.or.ddit.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "adrsNo")
public class AddressbookVO {
	
	@NotNull(groups = {UpdateGroup.class, DeleteGroup.class})
	private Long adrsNo;
	private String memId;
	private String adrsName;
	private String adrsTel;
	private String adrsAdd;
}
