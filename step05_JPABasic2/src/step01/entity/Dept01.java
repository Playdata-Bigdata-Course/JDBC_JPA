//컬럼명과 사이즈 조절 학습

package step01.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class Dept01 {
	
	//? int deptno 의 매핑된 컬럼은 정수 3자리
	//? String dname의 컬럼 사이즈는 20byte
	//step02 - 멤버 변수명과 다른 걸럼명, 타입 사이즈 강제 조절
	@Id
	@Column(name="no", precision=3)
	private BigDecimal deptno;
	@Column(name="name", length=20)
	private String dname;
	private String loc;
	
	//step01 - 멤버 변수에 맞게 컬럼명과 타입 사이즈 자동 설정
//	@Id
//	private int deptno;
//	private String dname;
//	private String loc;
}
