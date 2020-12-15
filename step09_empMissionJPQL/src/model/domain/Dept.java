package model.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
public class Dept {
	@Id
	private Long deptNo;
	@Column(length = 20)
	private String dname;
	private String loc;
	@OneToMany(mappedBy = "deptNo")
	private List<Employee> emps;
	// 테스트용 주석이에요
}
