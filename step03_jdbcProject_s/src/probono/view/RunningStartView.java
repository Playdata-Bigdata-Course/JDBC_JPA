package probono.view;

import java.sql.SQLException;

import probono.controller.ActivistController;
import probono.controller.processController;
import probono.controller.ProbonoProjectController;
import probono.model.dto.ProbonoProjectDTO;

public class RunningStartView {
	
	public static void main(String [] args) throws SQLException{
		System.out.println("***** 모든 진행되는 실제 Project 검색 *****");
		ProbonoProjectController.getAllProbonoProjects();;
		
		//모든 재능 기부자들 검색
		System.out.println("\n***** 모든 재능 기부자 검색 *****");
		ActivistController.getAllActivists();
		
		//특정 프로보노 정보 검색
		System.out.println("\n***** 특정 프로보노 정보 검색 *****");
		processController.getProbono("schweitzer");
		
		
//		프로보노 id로 프로보노 목적 수정	
//		test data - id : schweitzer,  목적 : 애완동물사랑
		System.out.println("\n***** 특정 프로보노 정보 수정후 재 검색 *****");
		processController.updateProbono("schweitzer", "애완동물사랑");
		processController.getProbono("schweitzer");
//		System.out.println("전체 row 출력");
//		ProbonoProjectController.getAllProbonoProjects();
//		System.out.println("---------------행 생성 후 출력-----------------");
//		ProbonoProjectController.addProbonoProject(new ProbonoProjectDTO("슈바이처 프로젝트", "schweitzer", "giver1", "receivePeople1", "심리 치료"));
//		ProbonoProjectController.getAllProbonoProjects();
//		System.out.println("---------------행 삭제 후 출력-------------------");
//		ProbonoProjectController.deleteProject(9);
//		ProbonoProjectController.getAllProbonoProjects();
		
	}

	
}