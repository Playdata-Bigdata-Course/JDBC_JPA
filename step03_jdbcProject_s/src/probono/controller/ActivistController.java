package probono.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import probono.model.ProbonoService;
import probono.model.dto.ActivistDTO;
import probono.view.RunningEndView;

//현 로직 : view.RunningStrartView에서 호출 
public class ActivistController {

	// 모든 Activist 검색 로직
	public static void getAllActivists() {
		ArrayList<ActivistDTO> allActivist = null;
		try {
			allActivist = ProbonoService.getAllActivists();
			if (allActivist.size() != 0) {
				RunningEndView.ActivistListView(allActivist);
			}
		} catch (SQLException s) {
			s.printStackTrace();
			RunningEndView.showError("모든 재능 기부자 검색시 에러 발생");
		}
	}
}