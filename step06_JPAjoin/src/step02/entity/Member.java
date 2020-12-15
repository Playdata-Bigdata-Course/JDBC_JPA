package step02.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@SequenceGenerator (name="member_seq_gen", sequenceName="member_seq_id", initialValue=1, allocationSize=50)
public class Member {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="member_seq_gen")
	@Column(name="memeber_id")
	private Long memberId;
	
	@Column(length=20)
	private String name;
	
	private int age;
	
	@OneToOne //Member to Team 즉 Member 하나가 Team 하나에 소속
	@JoinColumn(name="team_id") // Member table의 컬럼명 + 선언된 변수 타입의 pk와 연계되는 fk
	private Team teamId;
}
