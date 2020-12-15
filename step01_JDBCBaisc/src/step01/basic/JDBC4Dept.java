/*1. 추가 응용
 * - web 브라우저를 통해서 실시간 검색? 수정? 삭제? 저장? 등의 개별 로직 호출시에 입력 데이터가 client별 다르게 요청이 들어온다 가정
 * 2. sql 문장 실행 가능한 활용 API
 * 	1. java.sql.Statement
 * 		- sql문장 구성 시 문자열의 단일 따옴표등 명확하게 처리 필수
 * 		- sql 실행 요청 프로세스
 * 			db가 요청 수락 -> sql문장 분석 -> 검증 후 sql 문장을 db만의 실행 코드로 변환 -> 실행
 * 			모든 요청에 동일한 process 
 * 
 * 	2. java.sql.PreparedStatement
 * 		- sql문장 구성시 단일 따옴표 처리 불필요, 데이터값 위치에 ? 표기로만 처리
 * 		- statement 상속
 * 		- 실무형 API
 * 		- DB들이 실행 시 인식할때 Statement 보다 실행 속도가 더 향샹된 형식으로 처리
 * 		- sql 실행 요청 프로세스 
 * 			db가 요청 수락 -> sql문장 분석 -> 검증 후 sql 문장을 db만의 실행코드로 변환 -> 실행
 * 			동일한 sql 요청이 유입되었을 때는 이ㅣ 변화된 실행 코드만 실행 (sql문장 분석 -> 검증 후 sql 문장을 db만의 실행 코드로 변환 skip)
 */

package step01.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.DBUtil;

public class JDBC4Dept {
	static void select() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			con = DBUtil.getConnection();	
			stmt = con.createStatement();
			String sql = "select * from dept";		
			rset = stmt.executeQuery(sql);
		
			while(rset.next()) {
				System.out.println(rset.getInt("deptno") + " " 
									+ rset.getString("dname") + " " 
									+ rset.getString("loc"));
			}	
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("죄송합니다 접속 문제가 생겼으니 잠시후에 재 요청해 주세요~");
		}finally {	
			DBUtil.close(con, stmt, rset);
		}
	}
	static void insert(int newDeptno, String newDname, String newLoc) {
		Connection con = null;
//		Statement stmt = null;
		PreparedStatement pstmt = null;
	
		try {
			con = DBUtil.getConnection();	
//			stmt = con.createStatement();
			pstmt = con.prepareStatement("insert into dept values(?, ?, ?)"); 
//			int result = stmt.executeUpdate("insert into dept values(60, '인사과', '남부')");
			
			//? 표기의 sql문장에 데이터값 셋팅 - 각 column별로 별도로 값 셋팅 
			pstmt.setInt(1, newDeptno);
			pstmt.setString(2, newDname);
			pstmt.setString(3, newLoc);
			
			
			int result = pstmt.executeUpdate(); //db에 실제  insert하는 로직
			
			if(result != 0 ) {
				System.out.println("저장성공");
			}else {
				System.out.println("저장실패");
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("죄송합니다 접속 문제가 생겼으니 잠시후에 재 요청해 주세요~");
		}finally {	
			DBUtil.close(con, pstmt);
		}
	}
	
	//update - update dept set loc='서초' where deptno=60
	static void update(int deptno, String newLoc) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();	
			pstmt = con.prepareStatement("update dept set loc=? where deptno=?");
			pstmt.setString(1, newLoc);
			pstmt.setInt(2,  deptno);
			
			
			int result = pstmt.executeUpdate();	
			if(result != 0 ) {
				System.out.println("갱신성공");
			}else {
				System.out.println("갱신실패");
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("죄송합니다 접속 문제가 생겼으니 잠시후에 재 요청해 주세요~");
		}finally {	
			DBUtil.close(con, pstmt);
		}
	}	
	
	//delete - delete from dept where deptno=60
	//?
	static void delete(int deptno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();	
			pstmt = con.prepareStatement("delete from dept where deptno=?");
			pstmt.setInt(1, deptno);
			
			int result = pstmt.executeUpdate();	
			if(result != 0 ) {
				System.out.println("삭제성공");
			}else {
				System.out.println("삭제실패");
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("죄송합니다 접속 문제가 생겼으니 잠시후에 재 요청해 주세요~");
		}finally {	
			DBUtil.close(con, pstmt);
		}
	}
		public static void main(String[] args) {
			//select 호출
//			select();
//			System.out.println("-- 저장 --");
//			insert(90, "hr", "일산");
//			System.out.println("-- 저장 후 검색 -- ");
//			select();
//			System.out.println("-- 수정 --");
//			update(20, "DALLAS");
//			System.out.println("-- 수정 후 검색");
//			select();
			System.out.println("-- 삭제 --");
			delete(90);
			System.out.println("-- 삭제 후 검색");
			select();
		}

	}


