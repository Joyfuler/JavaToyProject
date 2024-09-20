package 희진;

import java.util.List;

public interface InbodyDao {
	// 인바디 인터페이스
	boolean insert(Inbody inbody); 

	boolean deleteByInbodyNum(int inbody_num);

	boolean deleteByMemberNum (int member_num);
	
	public List<Inbody> findAll();
	
	public Inbody findByInbodyNum(int inbody_num);

	public List<Inbody> findByMemberNum(int member_num);
}