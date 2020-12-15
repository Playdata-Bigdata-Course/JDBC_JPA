package run.test;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;

import model.domain.Employee;
import util.PublicCommon;

public class RunningTest {
	//NamedQuery Test
	@Test
	public void runNamedQueryTest() {
		EntityManager em = PublicCommon.getEntityManager();
		try {
			//Employee.findByEmpno - 사용하기 위해서는 Employee에 선언시 매핑했던 이름과 사번값
			Employee e = (Employee)em.createNamedQuery("Employee.findByEmpno").setParameter("eid", 7369L).getSingleResult();
			System.out.println(e);
			
			
			//Employee.findByEmpnoAndEname 
			 e = (Employee)em.createNamedQuery("Employee.findByEmpnoAndEname")
					.setParameter("eid", 7369L)
					.setParameter("ename", "SMITH")
					.getSingleResult();
			 
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
	}
	
	// ? select 실행 - EntityManager
	/* 이미 존재하는 영문 이름들을 소문자로 변환해서 검색해야 하는 상황이라 가정
	 * 현 소스상에선 select 즉 데이터가 database에 있다는 가정
	 * ? table 수작업으로 생성
	 *  create table employee as select empno, ename, sal, deptno from emp;
	 *  select * from emp;
	 *   
	 */
//	@Test
	public void runJPQLTest() {
		EntityManager em = PublicCommon.getEntityManager();
		try {
			System.out.println("--- 이름 소문자로 변경 후 검색 ---");
			//이름 소문자로 변경해서 검색
			String jpql = "select LOWER(e.ename) from Employee e";
			List<String> list = em.createQuery(jpql).getResultList();//e.ename 값이 string
			list.forEach(v -> System.out.println(v));
			
			System.out.println("--- 최고 급여 검색 ---");
			//최고 급여 검색
			jpql = "select max(e.sal) from Employee e";
			Long maxSal = (Long)em.createQuery(jpql).getSingleResult(); 
			System.out.println(maxSal);
			
			/*select e from Employee e
			 * select e from Employee e where e.salary between 800 and 3000
			 * Employee 객체들 생성 의미
			 * 
			 * sql문장 실행 후에는 Employee 객체들이 다수 저장된 list 객체
			 * 
			 */
			System.out.println("--- between ~ and ---");
			//between ~ and 사잇값의 해당되는 사원들의 모든정보 검색
			jpql = "select e from Employee e where e.sal between 800 and 3000";
			List<Employee> list2 = em.createQuery(jpql).getResultList();
			list2.forEach(v -> System.out.println(v));
			
			System.out.println("--- like 연산자 ---");
			//? like 'M%' - M으로 시작하는 모든 사원의 모든 정보 검색 및 출력
			jpql = "select e from Employee e where e.ename like 'M%'";
			list2 = em.createQuery(jpql).getResultList();
			list2.forEach(v -> System.out.println(v));
			
			System.out.println("--- ORDER BY ---");
			//? 사원명을 오름차순으로 정렬해서 사원명만 검색 및 출력하기
			jpql = "select e.ename from Employee e order by ename asc";
			list = em.createQuery(jpql).getResultList();
			list.forEach(v -> System.out.println(v));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			em.close();
		}
	}
}
