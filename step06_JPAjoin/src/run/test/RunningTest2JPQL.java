package run.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;

import step03.entity.Member;
import step03.entity.Team;
import util.PublicCommon;

public class RunningTest2JPQL {
	
	
	//simple select
	//20살 미만의 멤버의 모든 정보 검색
	//select m from Member m where age < 20 
	
	private static void simpleQuery(EntityManager em) {
		List<Member> resultList = em.createQuery("select m from Member m ", Member.class).getResultList();
		
		resultList.forEach(m -> System.out.println(m));
		

	}
	//join
	//첫번째 실습 : Member의 id값으로 teamId 타입인 Team의 teamName 변수값을 검색하는 join문장
//	private static void joinQuery(EntityManager em) {
////		List<Member> resultList = em.createQuery("select m from Member m join m.teamId t where t.teamName='teamA'").getResultList();
//		List<Member> resultList = em.createQuery("select m from Member m join m.teamId t where t.teamId=1L").getResultList();
//		resultList.forEach(v -> System.out.println(v));
//	}
	
	
	
	@Test
	public void runningJPQL() {
		
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Team team= Team.builder().teamName("teamA").members(new ArrayList<Member>()).build();
			
			Member m1= Member.builder().name("김혜경").age(10).teamId(team).build();
			Member m2= Member.builder().name("신동엽").age(22).teamId(team).build();
			Member m3= Member.builder().name("강호동").age(15).teamId(team).build();
			Member m4= Member.builder().name("유재석").age(10).teamId(team).build();
			Member m5= Member.builder().name("창훈").age(42).teamId(team).build();
			Member m6= Member.builder().name("창훈").teamId(team).build();
			Member m7= Member.builder().age(12).teamId(team).build();
			team.getMembers().add(m1);
			team.getMembers().add(m2);
			team.getMembers().add(m3);
			team.getMembers().add(m4);
			team.getMembers().add(m5);
			team.getMembers().add(m6);
			team.getMembers().add(m7);
			
			em.persist(team);	
			em.persist(m1);
			em.persist(m2);
			em.persist(m3);
			em.persist(m4);
			em.persist(m5);
			em.persist(m6);
			em.persist(m7);
			
			em.flush();
			em.clear();
			tx.commit();	
		
			//jpql test
			simpleQuery(em);
//			joinQuery(em);
			
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		finally {
			em.close();
		}
	}
	
	
	
}
