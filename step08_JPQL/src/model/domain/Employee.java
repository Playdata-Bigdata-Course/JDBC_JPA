//JPQL & named query test용 Entity


package model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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

//step02 - named query 실습 Entity
// 사번으로 사원 검색, 이름으로 사원 검색, 사번과 이름으로 사원 검색
@NamedQuery(query="select e from Employee e where e.eid=:eid" , name= "Employee.findByEmpno")
//@NamedQuery(query= , name= "Employee.findByEname")
@NamedQuery(query="select e from Employee e where e.eid=:eid and e.ename=:ename", name= "Employee.findByEmpnoAndEname")
@NamedQuery(query="select e from Employee e where e.eid=:eid and e.ename=ename", name="Employee.findByEmpnoAndEname")
@Entity
public class Employee {

   @Id
   @Column(name="empno")
   private Long eid;
   
   @Column(name="ename", length=20)
   private String ename;
   
   @Column
   private Long sal;
   
   @Column(name="deptno", length=20)
   private Long dname;

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("Employee [eid=");
      builder.append(eid);
      builder.append(", ename=");
      builder.append(ename);
      builder.append(", sal=");
      builder.append(sal);
      builder.append(", dname=");
      builder.append(dname);
      builder.append("]");
      return builder.toString();
   }
//step01 - JPQL 실습 Entity
//@Entity
//public class Employee {
//
//   @Id
//   @Column(name="empno")
//   private Long eid;
//   
//   @Column(name="ename", length=20)
//   private String ename;
//   
//   @Column
//   private Long sal;
//   
//   @Column(name="deptno", length=20)
//   private Long dname;
//
//   @Override
//   public String toString() {
//      StringBuilder builder = new StringBuilder();
//      builder.append("Employee [eid=");
//      builder.append(eid);
//      builder.append(", ename=");
//      builder.append(ename);
//      builder.append(", sal=");
//      builder.append(sal);
//      builder.append(", dname=");
//      builder.append(dname);
//      builder.append("]");
//      return builder.toString();
//   }
}