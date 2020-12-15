package probono.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProbonoUserDTO {
	private int prouserId;
	private String probonoId;
	private String activistId; 
	private String receiveId;
	private String projectContent;
	

}





