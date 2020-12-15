//step03 - 사용자 정의 시퀀스명 설정하는 속성 추가

package step03.entity.sequence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@SequenceGenerator(name="MEMBER_SEQ_GEN", sequenceName="MEMBER_SEQ", initialValue=1, allocationSize=50) //create sequence MEMBER_SEQ start with 1 increase 50
public class Member3 {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MEMBER_SEQ_GEN")//시퀀스 반영하는 방법
	private long id;
	
	@Column(length=20, nullable=false) //NOT NULL VARCHAR2(20)
	private String name;
	
	@Column(length=6, nullable=false)
	private String gender;
	
	@Column(nullable=false)
	private int age; //NOT NULL NUMBER(10)
}
