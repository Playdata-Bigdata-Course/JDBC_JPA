package step01.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Member {
	@Id
	@GeneratedValue
	@Column(name="memeber_id")
	private Long memberId;
	
	@Column(length=20)
	private String name;
	
	private int age;
	
	@ManyToOne
	@JoinColumn(name="team_id")
	private Long teamId;
}
