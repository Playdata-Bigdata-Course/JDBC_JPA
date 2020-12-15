package run.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.junit.jupiter.api.Test;

import model.domain.Buyer;
import model.domain.Seller;
import model.domain.Product;
import util.PublicCommon;

public class BuyerCRUDTest {

	// create
	public static void addBuyer(String buyid, String buyname, String buyphonum, String buyaddress, EntityManager em) {
		Buyer b = Buyer.builder().buyid(buyid).buyname(buyname).buyphonum(buyphonum).buyaddress(buyaddress).build();
		em.persist(b);
	}
	// select

	public static void findAll(EntityManager em) {
		List<Buyer> b = em.createNativeQuery("select * from Buyer", Buyer.class).getResultList();
		b.forEach(v -> System.out.println(v));
	}

	public static void findBuyerId(int buyid, EntityManager em) {
		List<Buyer> b = em.createNativeQuery("select * from Buyer where buyid= ?", Buyer.class).setParameter(1, buyid)
				.getResultList();
		b.forEach(v -> System.out.println(v));
	}

	public static void findBuyerName(String buyname, EntityManager em) {
		List<Buyer> b = em.createNativeQuery("select * from Buyer where buyid= ?", Buyer.class).setParameter(1, buyname)
				.getResultList();
		b.forEach(v -> System.out.println(v));
	}

	// 구매자가 원하는 상품 검색 후, 존재하는 사이즈 목록 보기

	public static void updateBuyer(int buyid, String buyname, String buyphonum, String buyaddress, EntityManager em) {

	}

	// update
	public static void updateBuyer(EntityManager em, String buyname, String buyphonum, int buyid) {
			int result = em.createNativeQuery("update buyer set buyname = ?,  buyphonum =? where buyid =?", Buyer.class).setParameter(1, buyname).setParameter(2,buyphonum).setParameter(3,buyid).executeUpdate();
			if(result !=0) {
				System.out.println("---업데이트 완료---");
			}else {
				System.out.println("---업데이트 실패---");
			}
		}

	// delete
	public static void deleteBuyer(EntityManager em, int buyid) {
		int result = em.createNativeQuery("delete buyer where buyid=?").setParameter(1, buyid).executeUpdate();
		if (result != 0) {
			System.out.println("--- 삭제 완료---");
		} else {
			System.out.println("--- 삭제 실패---");
		}
	}

	public static void getProducts(int buyid, EntityManager em) {

	}

}
