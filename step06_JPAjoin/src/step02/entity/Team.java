package step02.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

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
@SequenceGenerator (name="team_seq_gen", sequenceName="team_seq_id", initialValue=1, allocationSize=50)
public class Team {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="team_seq_gen")
	@Column(name="team_id2") //team 테이블의 컬럼명
	private Long teamId;
	
	@Column(length=20, name="team_name")
	private String teamname;
	
}
