package model.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@NamedQuery(query = "select e from Employee e where e.empNo=:empNo ", name = "Employee.findByEmpno")
@NamedQuery(query = "select e from Employee e ", name = "Employee.findAll")
@NamedQuery(query = "delete Employee e where e.empNo=:empNo", name = "Employee.delete")
@NamedQuery(query = "update Employee e set e.sal=:sal where e.empNo = :empNo", name = "Employee.update")
@Entity
public class Employee {

	@Id
	private Long empNo;
	private String ename;
	private Long sal;
	@ManyToOne
	@JoinColumn(name = "deptNo")
	private Dept deptNo;

	@Override
	public String toString() {
		return "Employee [사원 아이디 =" + empNo + ", 사원명 =" + ename + ", 급여 =" + sal + ", 부서=" + deptNo.getDeptNo() + "]";

	}

}
