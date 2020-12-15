package model.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
@Entity
@Table(name = "Product")
public class Product {
	@Id
	private int prodid ;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "prodid")
	private Buyer Buyid;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "prodid")
	private Seller Sellid;
	
	@Column(name="prodname")
	private String prodname;

	@Column(name="prodsize")
	private int prodsize;
	
	@Column(name="brand")
	private String brand;

	@Column(name="release_date")
	private String release_date;
	
	@Column(name="price")
	private int price;


	@Column(name="resellprice")
	private int resellprice;

	@Override
	public String toString() {
		return "Product [prodid=" + prodid + ", prodname=" + prodname + ", prodsize=" + 
	prodsize + ", brand=" + brand+ ", release_date=" + release_date + 
	", price=" + price + ", resellprice=" + resellprice + "]";
	}


}