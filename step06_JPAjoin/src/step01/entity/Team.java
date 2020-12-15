package step01.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Entity
public class Team {

	@Id
	@GeneratedValue
	private Long teamId;
	
	@Column(length=20, name="team_name")
	private String teamname;
	
}
