package run.test;


import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;

//step03
import step03.entity.Member;
import step03.entity.Team;
import util.PublicCommon;

public class RunningTest {
	@Test
	//step05 - 즉시 로딩, 늦은 로딩
	public void runningTest5() {
		
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Team team= Team.builder().teamName("team A").members(new ArrayList<Member>()).build();
			
			Member m1= Member.builder().name("김혜경").age(10).teamId(team).build();
			Member m2= Member.builder().name("신동엽").age(12).teamId(team).build();
			
			team.getMembers().add(m1);
			team.getMembers().add(m2);
			
			em.persist(team);	//insert 문장생성 + 스냅샷으로 저장 : 영속서 컨텍스트에 저장
			em.persist(m1);
			em.persist(m2);
			
			
			//검색 - 영속성 컨텍스트에서 검색 시도 동일한 데이터 존재할 경우 db까지 select 실행 안 함
			//영속성 컨텍스트 초기화 - 잔존된 데이터들 삭제 따라서 검색 요청시 무조건 db에 select 실행
			//생략시 select 문장 실행 안함 : 전제조건 - 영속성 컨텍스트에 있는 데이터 검색에 한함 
			em.flush();
			em.clear();
			
			Member sm = em.find(Member.class, m1.getMemberId());
			System.out.println("검색된 멤버명" +  sm.getName());
			System.out.println(sm.getTeamId().getTeamName());
			tx.commit();	//실제 insert
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		finally {
			em.close();
		}
	}
	
	
	//step04 -  일대다, 다대일 관계에서의 Entity 클래스 설계의 주의사항
	//@Test
	public void runningTest3() {
	
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Team team= Team.builder().teamName("team A").members(new ArrayList<Member>()).build();
			
			Member m1= Member.builder().name("김혜경").age(10).teamId(team).build();
			Member m2= Member.builder().name("신동엽").age(12).teamId(team).build();
			
			team.getMembers().add(m1);
			team.getMembers().add(m2);
			
			em.persist(team);
			em.persist(m1);
			em.persist(m2);
			// --- 검색 추가 
			Team teamInfo = em.find(Team.class, team.getTeamId());
			System.out.println("팀에 소속된 멤버 수 : " + teamInfo.getMembersCount());
			
			System.out.println("멤버 검색 ---");
			Member sm = em.find(Member.class, m2.getMemberId());
			System.out.println(sm);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			em.close();
		}
	
	
	
	
	
	
	
	
//	//step03 -  
////		@Test
//		public void runningTest3() {
//			/* 여러명의 Member 객체 생성해서 하나의 Team에 저장
//			 * table생성 및 alter 명령어 확인
//			 * table 구조 재확인
//			 * insert 성공이라면 각 table당 검색
//			 * 
//			 */
//			EntityManager em = PublicCommon.getEntityManager();
//			EntityTransaction tx = em.getTransaction();
//			tx.begin();
//			
//			try {
//				Team team= Team.builder().teamname("team A").members(new ArrayList<Member>()).build();
//				
//
////				Team t2= Team.builder().teamname("team B").build();
////				em.persist(t2);
//				
////				? Member 객체 생성 후 insert 시도할 경우 Member의 teamId는 이미 존재하는 team의 id값이어야 함
//				Member m1= Member.builder().name("김혜경").age(10).teamId(team).build();
//				Member m2= Member.builder().name("신동엽").age(12).teamId(team).build();
//				
//				team.getMembers().add(m1);
//				team.getMembers().add(m2);
//				em.persist(team);
//				em.persist(m1);
//				em.persist(m2);
//				tx.commit();
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//			finally {
//				em.close();
//			}
	
	
	
//	//step01 - 비합리적인 table 설계로 인한 거지같은 코드 개발 확인 
//	@Test
//	public void runningTest() {
//		EntityManager em = PublicCommon.getEntityManager();
//		EntityTransaction tx = em.getTransaction();
//		tx.begin();
//		
//		try {
//			Team t1= Team.builder().teamname("team A").build();
//			em.persist(t1);
//
//			Team t2= Team.builder().teamname("team B").build();
//			em.persist(t2);
//			
////			? Member 객체 생성 후 insert 시도할 경우 Member의 teamId는 이미 존재하는 team의 id값이어야 함
//			Member m1= Member.builder().name("김혜경").age(10).teamId(t2).build();
//			em.persist(m1);
//			tx.commit();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		finally {
//			em.close();
//		}
	}
}

