package 호영;


import Gym.Logic.Logic.DAOManager;
import Gym.Logic.Logic.LoginManager;
import 민국.LoginData;
import DataSource.DataSource;
import 진욱.Gym_Lesson;
import 진욱.Gym_LessonDao;
import 진욱.ReviewDao;
import 희진.InbodyDao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcGym_MemberDao implements Gym_MemberDao {

    @Override
    public boolean insert(Gym_Member member) {
    	    	    	    	
        boolean result = false;
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement = conn.prepareStatement(
                     "INSERT INTO GYM_MEMBER (PT_COUNT, REG_DATE, EXP_DATE, LOGIN_ID, LOGIN_PW, GENDER, AGE, TRAINER_NUM, CHARGE_NUM, NAME) " +
                     "VALUES (?, SYSTIMESTAMP, ADD_MONTHS(SYSDATE, 3), ?, ?, ?, ?, ?, ?, ?)")) {

            pStatement.setInt(1, member.getPt_count());
            pStatement.setString(2, member.getLogin_id());
            pStatement.setString(3, member.getLogin_pw());
            pStatement.setString(4, member.getGender());
            pStatement.setInt(5, member.getAge());
            pStatement.setInt(6, member.getTrainer_num());
            pStatement.setInt(7, member.getCharge_num());
            pStatement.setString(8, member.getName());

            int rows = pStatement.executeUpdate();

            result = rows > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (result) {
            System.out.println("성공적으로 보냄");
        } else {
            System.out.println("전송 실패");
        }

        return result;
    }

    @Override
    public List<Gym_Member> findAll() {
        List<Gym_Member> mLst = new ArrayList<>();
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement = conn.prepareStatement("SELECT * FROM Gym_Member ORDER BY Member_Num");
             ResultSet rs = pStatement.executeQuery()) {

            while (rs.next()) {
                Gym_Member gm = new Gym_Member(
                        rs.getInt("member_num"),
                        rs.getInt("pt_count"),
                        rs.getTimestamp("reg_date"),
                        rs.getDate("exp_date"),
                        rs.getString("login_id"),
                        rs.getString("login_pw"),
                        rs.getString("gender"),
                        rs.getInt("age"),
                        rs.getString("name"),
                        rs.getInt("charge_num"),
                        rs.getInt("trainer_num")
                );
                mLst.add(gm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mLst;
    }

    @Override
    public Gym_Member findByMember_Num(int member_num) {
        Gym_Member member = null;

        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement = conn.prepareStatement(
                     "SELECT * FROM gym_member WHERE member_num = ?")) {

            pStatement.setInt(1, member_num);
            ResultSet rs = pStatement.executeQuery();
            if (rs.next()) {
                member = new Gym_Member();
                member.setMember_num(rs.getInt("member_num"));
                member.setPt_count(rs.getInt("pt_count"));
                member.setReg_date(rs.getTimestamp("reg_date"));
                member.setExp_date(rs.getDate("exp_date"));
                member.setLogin_id(rs.getString("login_id"));
                member.setLogin_pw(rs.getString("login_pw"));
                member.setGender(rs.getString("gender"));
                member.setAge(rs.getInt("age"));
                member.setName(rs.getString("name"));
                member.setCharge_num(rs.getInt("charge_num"));
                member.setTrainer_num(rs.getInt("trainer_num"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return member;
    }

    // 로그인을 위해, member의 login_id를 통해 객체를 반환하는 메소드를 추가한다.
    public Gym_Member findByLoginData(LoginData memberLogin) {
        Gym_Member gm = null;
        try (Connection conn = DataSource.getDataSource();
            PreparedStatement pStatement = conn.prepareStatement("select * from GYM_MEMBER where LOGIN_ID = ?")) {
            pStatement.setString(1, memberLogin.getLogin_id());
            ResultSet rs = pStatement.executeQuery();

            if (rs.next())
            {
                gm = new Gym_Member();
                gm.setMember_num(rs.getInt("MEMBER_NUM"));
                gm.setPt_count(rs.getInt("PT_COUNT"));
                gm.setReg_date(rs.getTimestamp("REG_DATE"));
                gm.setExp_date(rs.getDate("EXP_DATE"));
                gm.setLogin_id(rs.getString("LOGIN_ID"));
                gm.setLogin_pw(rs.getString("LOGIN_PW"));
                gm.setGender(rs.getString("GENDER"));
                gm.setAge(rs.getInt("AGE"));
                gm.setName(rs.getString("NAME"));
                gm.setTrainer_num(rs.getInt("TRAINER_NUM"));
                gm.setCharge_num(rs.getInt("CHARGE_NUM"));
            }
            else
            {
                System.out.println("해당 ID의 회원이 존재하지 않습니다.");
                gm = null;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return gm;
    }

    @Override
    public boolean update(Gym_Member member) {
        boolean result = false;

        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement = conn.prepareStatement(
                     "UPDATE gym_member SET pt_count = ?, reg_date = ?, exp_date = ?, login_id = ?, login_pw = ?, gender = ?, age = ?, trainer_num = ?, charge_num = ?, name = ? " +
                     "WHERE member_num = ?")) {

            pStatement.setInt(1, member.getPt_count());
            pStatement.setTimestamp(2, member.getReg_date());
            pStatement.setDate(3, member.getExp_date());
            pStatement.setString(4, member.getLogin_id());
            pStatement.setString(5, member.getLogin_pw());
            pStatement.setString(6, member.getGender());
            pStatement.setInt(7, member.getAge());
            pStatement.setInt(8, member.getTrainer_num());
            pStatement.setInt(9, member.getCharge_num());
            pStatement.setString(10, member.getName());
            pStatement.setInt(11, member.getMember_num());

            int affectedRows = pStatement.executeUpdate();
            result = affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean update2(Gym_Member member) {
        boolean result = false;
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement = conn.prepareStatement(
                     "UPDATE gym_member SET login_pw = ? WHERE login_id = ?")) {

            pStatement.setString(1, member.getLogin_pw());
            pStatement.setString(2, member.getLogin_id());

            if (pStatement.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean deleteByMember_Num(int member_num) {
        boolean result = false;
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement = conn.prepareStatement("DELETE FROM gym_member WHERE member_num = ?")) {

            pStatement.setInt(1, member_num);

            if (pStatement.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean tryLogin(String login_id, String login_pw)
    {
        boolean isSuccess = false;

        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement = conn.prepareStatement("select * from GYM_MEMBER where LOGIN_ID = ?"))
        {
            pStatement.setString(1,login_id);
            ResultSet rs = pStatement.executeQuery();

            //해당 id 가 있는 회원이 존재하는 경우, 다음으로 비밀번호가 동일한지를 체크한다. 동일하면, LoginData 객체를 만들어 로그인 처리.
            if(rs.next())
            {
                String resultPw = rs.getString("LOGIN_PW");

                if(resultPw.equals(login_pw))
                {
                    isSuccess = true;
                    LoginData curLoginUser = new LoginData();

                    curLoginUser.setLogin_id(rs.getString("LOGIN_ID"));
                    curLoginUser.setLogin_pw(rs.getString("LOGIN_PW"));
                    curLoginUser.setName(rs.getString("NAME"));
                    curLoginUser.setAge(rs.getInt("AGE"));
                    curLoginUser.setMemberType(LoginData.MEMBERTYPE.MEMBER);

                    LoginManager.getInstance().setCurrentLoginUser(curLoginUser);

                    System.out.println("로그인 성공, 현재 유저 : " + curLoginUser.getName() + "님 (" +curLoginUser.getLogin_id() + ")");
                }
                else
                {
                    System.out.println("비밀번호가 틀립니다.");
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return isSuccess;
    }

}
