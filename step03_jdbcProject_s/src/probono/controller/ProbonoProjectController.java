package probono.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import probono.model.ProbonoService;
import probono.model.dto.ProbonoProjectDTO;
import probono.view.RunningEndView;

//현 로직 : view.RunningStrartView에서 호출 
public class ProbonoProjectController {
	private static ProbonoProjectController instance = new ProbonoProjectController();

	private ProbonoProjectController() {

	}

	public static ProbonoProjectController getinstance() {
		return instance;
	}

	// 모든 프로젝트 검색 로직
	public static void getAllProbonoProjects() {
		ArrayList<ProbonoProjectDTO> allProject = null;
		try {
			allProject = ProbonoService.getAllProbonoUsers();
			if (allProject.size() != 0) {
				RunningEndView.projectListView(allProject);
			} else {
				RunningEndView.showError("현재 진행중인 프로젝트는 존재하지 않습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			RunningEndView.showError("모든 프로젝트 검색시 에러 발생");
		}

	}

	// 새로운 프로젝트 저장 로직
	public static void addProbonoProject(ProbonoProjectDTO probonoProject) {
		boolean result = false;

		try {
			result = ProbonoService.addProbonoUser(probonoProject);
			if (result == true) {
				RunningEndView.showMessage("저장 성공");
			} else {
				RunningEndView.showError("저장 실패");
			}
		} catch (SQLException s) {
			s.printStackTrace();
			RunningEndView.showError("모든 프로젝트 저장시 에러 발생");
		}
	}

	// 프로보노 프로젝트 ID로 재능기부자 수정
	public static void updateProjectActivist(int probonoUserId, String activistId) {
		boolean result = false;
		try {
			result = ProbonoService.updateProbonoUserActivist(probonoUserId, activistId);
			if (result == true) {
				RunningEndView.showMessage("수정 성공");
			} else {
				RunningEndView.showError("수정 실패");
			}
		} catch (Exception s) {
			s.printStackTrace();
			RunningEndView.showError("프로보노 프로젝트 ID로  재능기부자 수정 오류");
		}
	}

	// 프로보노 아이디로 프로젝트 삭제
	public static void deleteProject(int probonoUserId) {
		boolean result = false;
		try {
			result = ProbonoService.deleteProbonoUser(probonoUserId);
			if (result == true) {
				RunningEndView.showMessage("삭제 성공");
			} else {
				RunningEndView.showMessage("삭제 실패");
			}
		} catch (Exception s) {
			s.printStackTrace();
			RunningEndView.showError("프로젝트 삭제 실패");
		}
	}
}
