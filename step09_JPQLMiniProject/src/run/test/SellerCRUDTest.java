package run.test;

import java.util.List;

import javax.persistence.EntityManager;

import model.domain.Buyer;
import model.domain.Seller;

public class SellerCRUDTest {

	// create
	public static void addSeller(String sellid, String sellname, String selphonum, String seladdress, EntityManager em) {
		Seller s = Seller.builder().sellid(sellid).sellphonum(selphonum).selladdress(seladdress).build();
		em.persist(s);
	}
	// select

	public static void findAll(EntityManager em) {
		List<Seller> b = em.createNativeQuery("select * from Seller", Seller.class).getResultList();
		b.forEach(v -> System.out.println(v));
	}

	public static void findBuyerId(int sellid, EntityManager em) {
		List<Seller> b = em.createNativeQuery("select * from Seller where sellid= ?", Seller.class)
				.setParameter(1, sellid).getResultList();
		b.forEach(v -> System.out.println(v));
	}

	public static void findSellerName(String sellname, EntityManager em) {
		List<Buyer> b = em.createNativeQuery("select * from Seller where buyid= ?", Buyer.class).setParameter(1, sellname)
				.getResultList();
		b.forEach(v -> System.out.println(v));
	}

	// 구매자가 원하는 상품 검색 후, 존재하는 사이즈 목록 보기

	public static void updateBuyer(int sellid, String sellname, String sellphonum, String selladdress, EntityManager em) {

	}

	// update
	public static void updateBuyer(EntityManager em, String sellname, String sellphonum, int sellid) {
		int result = em.createNativeQuery("update seller set buyname = ?,  buyphonum =? where buyid =?", Buyer.class)
				.setParameter(1, sellname).setParameter(2, sellphonum).setParameter(3, sellid).executeUpdate();
		if (result != 0) {
			System.out.println("---업데이트 완료---");
		} else {
			System.out.println("---업데이트 실패---");
		}
	}

	// delete
	public static void deleteBuyer(EntityManager em, int sellid) {
		int result = em.createNativeQuery("delete seller where sellid=?").setParameter(1, sellid).executeUpdate();
		if (result != 0) {
			System.out.println("--- 삭제 완료---");
		} else {
			System.out.println("--- 삭제 실패---");
		}
	}

	public static void getProducts(int sellid, EntityManager em) {

	}

}
