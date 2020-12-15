package step03.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Entity
@SequenceGenerator(name = "member_seq_gen", sequenceName = "member_seq_id", initialValue = 1, allocationSize = 50)
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_gen")
	@Column(name = "memeber_id")
	private Long memberId;

	@Column(length = 20)
	private String name;

	private int age;

	// �뿬�윭紐낆쓽 Member�뒗 �븯�굹�쓽 Team�뿉 �룷�븿 �떎:1愿�怨�
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private Team teamId;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Member [memberId=");
		builder.append(memberId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", age=");
		builder.append(age);
		builder.append(", teamId=");
		builder.append(teamId.getTeamId());
		builder.append("]");
		return builder.toString();
	}

}


/*
1踰� test - Member table�쓽 �젙蹂대쭔 find()
 @maneyToOne (fetch = FetchType.LAZY)
	select member0_.memeber_id as memeber_1_0_0_, 
			member0_.age as age2_0_0_,
			member0_.name as name3_0_0_,
        	member0_.team_id as team_id4_0_0_ 
   from Member member0_ 
   where member0_.memeber_id=?
   
  @maneyToOne
  	select member0_.memeber_id as memeber_1_0_0_, 
			member0_.age as age2_0_0_, 
			member0_.name as name3_0_0_, 
			member0_.team_id as team_id4_0_0_, 
			team1_.team_id as team_id1_1_1_, 
			team1_.team_name as team_nam2_1_1_ 
	from Member member0_, Team team1_ 
	where member0_.team_id=team1_.team_id(+) and member0_.memeber_id=?
  
2踰� test - Member table�쓽 �젙蹂� -> �빐�떦 Member媛� �냼�냽 �맂 Team�쓽 Team紐�(team_name) find()
 @ManyToOne(fetch=FetchType.LAZY)
	select member0_.memeber_id as memeber_1_0_0_, 
			member0_.age as age2_0_0_, 
			member0_.name as name3_0_0_, 
			member0_.team_id as team_id4_0_0_ 
	from Member member0_ 
	where member0_.memeber_id=?
	
	select team0_.team_id as team_id1_1_0_, 
			team0_.team_name as team_nam2_1_0_ 
	from Team team0_team0_.team_id=?
 */

