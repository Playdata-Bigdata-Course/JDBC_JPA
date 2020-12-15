/*학습내용
 * 1. dept 라는 table을 java program으로 crud 로직 처리하는 여습
 * 2. non-pattern 즉 연습용 코드
 * 3. 실습 순서
 * 		1. 존재하는 데이터들 검색(select=query)
 * 		2. 하나의 새로운 부서 추가 저장(insert-존재하는 table의 내용 갱신 : DML) - 검색
 * 		3. 데이터 수정(update-존재하는 table의 내용 갱신 : DML) - 검색
 * 		4. 데이터 삭제(delete-존재하는 table의 내용 갱신 : DML) - 검색
 * 
 * 		5. JDBC 주요 API
 * 			1. select(query) 실행 메소드
 * 				ResultSet executeQuery(String select)
 * 				row가 
 *  		2. insert/update/delete 실행 메소드
 *  			int executeUpdate(String dml)
 * 4. 공통된 로직
 *	1. driver 로딩 필수
 *	2. 접속 - Connection 객체 획득
 *			- url/ ID/ PW 동일
 *	3. sql문장 실행 - Statement 객체 생성 및 메소드로 crud로직 실행 및 결과 확인
 *		-로직별 다름
 *	4. db접속 해제 - 자원반환
 * 
 */

package step01.basic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.DBUtil;

public class JDBC2Dept {
	static void select() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rset = null;
		//접속 객체 생성 - 절대 client들이 공유해서는 안됌
		try {
			con = DBUtil.getConnection();
			
			//sql문장 실행 객체 생성 - 정상 접속된 Connection 객체로부터 sql문장 실행 객체 받아옴
			stmt = con.createStatement();
			
			//select
			//select 문장 실행 -Connection 객체와 연계된 로직으로 실행
			/*sql문법 오류 발생 시 db자체적인 error 발생 -> jdbc api 코드 상에선 SQLException으로 인지
			 * SQLException 발생시 catch블록으로 처리 실행 권한 이동
			 * 자원반환은  무조건 기본자세 */
			
			String sql = "select * from dept";
			rset = stmt.executeQuery(sql);
			/* 반환받은 ResultSet에는 데이터(ROW)가 있을수도 있고, 없을수도 있음
			 * 데이터가 있을 경우 : 모든 row의 각 속성(column)을 화면에 출력
			 * 데이터가 없을 경우 : ResultSet객체 반환되나 단지 데이터가 없음
			 * 데이터가 있나? 없나? 검증 - boolean next()
			 * 		row가 있으면 true/없으면 false
			 * 		반복문 조건식(조건값은?)으로 반영해서 존재하는 모든 row의 탐색
			 */
//			System.out.println(rset);
			//활용 : 결과값을 콘솔에 출력(System.out)
			while(rset.next()) {
				//컬럼 순서로 데이터 뽑기
//				System.out.println(rset.getInt(1) + " " + rset.getString(2) + " " + rset.getString(3));
				//컬럼명으로 데이터 뽑기
				System.out.println(rset.getInt("deptno") + " " 
									+ rset.getString("dname") + " " 
									+ rset.getString("loc"));
			}
			
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("죄송합니다 접속 문제가 생겼으니 잠시후에 재 요청해 주세요~");
		}finally {
			//자원반환 - 접속(connection), 문장실행(Statement), 결과집합(ResultSet) : close()
			try {
				if(rset != null) {
					rset.close();//sql문장 오류시 null인 상태로 close() 시도, db 접속 자원 관련 정리
					rset = null; //메모리 회수 요청을 하는 코드로 간주, 자바 실행환경의 메모리 정리 고려
				}
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
				if(con != null) {
					con.close();
					con = null;
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	static void insert() {
		//접속 객체 생성 - 절대 client들이 공유해서는 안됌
		try {
			Connection con = DBUtil.getConnection();
		} catch (SQLException e) {
			
		}
	}
	public static void main(String[] args) {
		//select 호출
		select();
	}

}
