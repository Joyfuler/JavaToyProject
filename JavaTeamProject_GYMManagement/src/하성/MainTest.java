package 하성;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;



public class MainTest {
	public static void main(String[] args) {
		
		Scanner rs = new Scanner(System.in);
		
		Admin admin = new Admin();
//		
//		admin.setManager_num(7777);
//		admin.setPosition("테스터");
		
		AdminDao adminDao = new JDBCAdminDao();
//		adminDao.insert(admin);
		
		
//		List<Admin> admin1 = adminDao.findAll();
//		for (Admin admin2 : admin1) {
//			System.out.println(admin2);
//		}
		
//		Admin admin2 = new Admin();
//		admin2.setManager_num(3214);
//		admin2.setPosition("테스터");
		
//		adminDao.deleteByManager_num(rs.nextInt());
		
		Charge chargeList = new Charge();
		
		chargeList.setCharge_num(77);
		chargeList.setPeriod_date(12);
		chargeList.setPt_count(5);
		
		ChargeDao chargeDao = new JDBCChargeDao();
//		chargeDao.insert(chargeList);
		
//		for (Charge chargeList2 : chargeDao.findAll()) {
//			System.out.println(chargeList2);
//		}
		
//		System.out.println(chargeDao.findByCharge_num(77));
		
//		chargeDao.update(chargeList);
		
		chargeDao.deleteByCharge_Num(rs.nextInt());
		
//		for (Charge charge_list2 : chargeDao.findAll()) {
//			System.out.println(charge_list2);
//		}
		
//		System.out.println(chargeDao.findByManager_num(7777));
		
//		jdbcadmindao.deleteByManager_num(7777);
		
	}
}
