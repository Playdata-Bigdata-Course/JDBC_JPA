package model.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude="products")

@Entity
@Table(name = "Seller")
public class Seller {
	@Id
	@Column(name="sellid")
	private String sellid;
	
	@Column(name="sellname")
	private String selname;

	@Column(name="sellphonum")
	private String sellphonum;

	@Column(name="selladdress")
	private String selladdress;

	@OneToMany(mappedBy = "Sellid")
	private List<Product> products;

//	@Override
//	public String toString() {
//		return "Seller [sellid=" + sellid + ", selname=" + selname + ","
//				+ " selphonum=" + selphonum + ", seladdress="
//				+ seladdress + ", products=" + products + "]";
//	}
	
	

}