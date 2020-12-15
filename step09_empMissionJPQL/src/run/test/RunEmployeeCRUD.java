package run.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;

import model.domain.Dept;
import model.domain.Employee;
import util.PublicCommon;

public class RunEmployeeCRUD {
	//create
	public static void createEmployee(EntityManager em, Long empNo, String ename, Long sal, Long deptNo) {
		Dept dept = em.find(Dept.class, deptNo);
		Employee employee = Employee.builder().empNo(empNo).ename(ename).sal(sal).deptNo(dept).build();
		em.persist(employee);
		System.out.println("--- 직원 생성 완료---");
	}
	
	// select
	//결과값이 없을 경우 에러 발생, 1개의 데이터를 찾더라도 List로 받아야 함 
	public static void findElement(EntityManager em, Long empNo) {
		List<Employee> emp = em.createNamedQuery("Employee.findByEmpno").setParameter("empNo", empNo).getResultList();
		emp.forEach(v -> System.out.println(v));
		if (emp.size() == 0) {
			System.out.println("검색 요청한 직원은 미존재합니다");
		} 
	}
	//selectALl
	public static void findAllElement(EntityManager em) {
		List<Employee> empList = em.createNamedQuery("Employee.findAll").getResultList();
		empList.forEach(v -> System.out.println(v));
	}
	
	//update
	public static void updateEmployee(EntityManager em, Long empNo, Long sal) {
		int result = em.createNamedQuery("Employee.update").setParameter("empNo", empNo).setParameter("sal",sal).executeUpdate();
		if(result !=0) {
			System.out.println("---업데이트 완료---");
		}else {
			System.out.println("---업데이트 실패---");
		}
	}

	// delete
	public static void deleteElement(EntityManager em, Long empNo) {
		int result = em.createNamedQuery("Employee.delete").setParameter("empNo", empNo).executeUpdate(); 
		if(result !=0) {
			System.out.println("--- 삭제 완료---");
		}else {
			System.out.println("--- 삭제 실패---");
		}
	}

	@Test
	public void runningTest() {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Long newNo = 1235L;
			System.out.println("---------------select all------------");
			findAllElement(em);
			System.out.println("---------------create---------------");
			createEmployee(em, newNo, "DDD", 6000L, 20L);
			em.flush();
			em.clear();
			System.out.println("---------------select one---------------");
			findElement(em, newNo);
			System.out.println("---------------update sal---------------");
			updateEmployee(em, newNo, 5000L);
			findElement(em, newNo);
			System.out.println("---------------delete---------------");
			deleteElement(em, newNo);
			findElement(em, newNo);
			System.out.println("---------------end---------------");
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
}