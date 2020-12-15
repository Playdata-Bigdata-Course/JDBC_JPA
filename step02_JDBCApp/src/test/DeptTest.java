package test;

import java.sql.SQLException;
import java.util.ArrayList;

import model.DeptDAO;
import model.domain.DeptDTO;

public class DeptTest {
	public static void selectall() {
		try {
			ArrayList<DeptDTO> all = DeptDAO.deptAll();
			// 차후에 EndView로 출력 위임
			for (DeptDTO dept : all) {
				System.out.println(dept); // dept.toString()코드가 자동 완성
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("모든 부서 정보 검색 실패, 재 요청 해주세요");
		}
	}

	public static void insert(int i, String a, String b) {
		try {
			boolean result = DeptDAO.insert(new DeptDTO(i, a, b));
			if (result == true) {
				System.out.println("저장성공");
			} else {
				System.out.println("다시 시도해 보세요"); // 어떤경우에 else인지 ? result == false
			}
		} catch (SQLException e) { // 부서번호 중복시 에러발생 -> SQLException과 연관
			e.printStackTrace();
			System.out.println("이미 해당 부서 번호는 존재합니다. 재확인 후 저장시도 하세요");
		}
	}

	public static void getDept(int i) {
		try {
			DeptDTO dept = DeptDAO.getDept(i);
			System.out.println(dept);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("부서 번호로 검색시 문제 발생, 잠시후에 재요청 해 주세요");
		}
	}

	public static void update(int i, String a) {
		try {
			boolean result = DeptDAO.update(i, a);
			if (result == true) {
				System.out.println("수정 성공");
			} else {
				System.out.println("다시 시도해 보세요");
			}
		} catch (SQLException e) { // 부서번호 미존재시 에러발생 -> SQLException과 연관
			e.printStackTrace();
			System.out.println("해당 부서 번호는 미존재합니다. 재확인 후 수정시도 하세요");
			// RA-12899: value too large for column "SCOTT"."DEPT"."LOC" (actual: 873,
			// maximum: 13)
		}
	}

	public static void delete(int i) {
		try {
			boolean result = DeptDAO.delete(i);
			if (result == true) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("다시 시도해 보세요");
			}
		} catch (SQLException e) {// 부서번호 미존재시 에러 발생 -> SQLExeption과 연관
			e.printStackTrace();
			System.out.println("해당 부서 번호는 미존재합니다. 재확인 후 삭제시도 하세요");
		}
	}

	public static void main(String[] args) {
//		//모든 검색 - 버튼 클릭으로 요청
//		selectall();
//		//저장 - deptno/dname/loc
//		insert(80,"qkqk","서울");
//		selectall();
//		//저장된 dept 정보만 검색 - deptno값으로 검색
//		getDept(40);
//		//수정 - deptno기준으로 loc 값 수정
//		update(80,"gkgk");
//		selectall();
		// 검색 - deptno값 검색
		try {
			ArrayList<String> all = DeptDAO.deptcol("deptno");
			//차후에 EndView로 출력 위임
			for(String ss : all) {
				System.out.println(ss); //dept.toString() 코드가 자동 완성
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//예외 메세지는 차후에 FailView로 출력 위임
			System.out.println("모든 부서 정보 검색 실패, 재 요청해 주세요");
		}

		// 삭제 - deptno로 삭제
//		delete(80);
//		// 모든 정보검색
//		selectall();

	}
}