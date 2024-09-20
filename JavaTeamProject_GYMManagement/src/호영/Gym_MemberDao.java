package 호영;

import 민국.LoginData;

import java.util.List;

public interface Gym_MemberDao {
	
	boolean insert(Gym_Member member);
	
	List<Gym_Member> findAll();
	
	Gym_Member findByMember_Num(int member_num);
	
	boolean update (Gym_Member member);
	
	boolean deleteByMember_Num(int member_num);

	Gym_Member findByLoginData(LoginData memberLogin);

	public boolean tryLogin(String login_id, String login_pw);
	
}
