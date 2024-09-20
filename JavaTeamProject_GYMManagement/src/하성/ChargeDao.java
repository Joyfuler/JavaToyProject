package 하성;

import java.util.List;

public interface ChargeDao {
	
	boolean insert(Charge chargeList);
	
	List<Charge> findAll();
	Charge findByCharge_num(int chargeList);
	
	boolean update(Charge chargeList);
	
	boolean deleteByCharge_Num(int chargeList);
}
