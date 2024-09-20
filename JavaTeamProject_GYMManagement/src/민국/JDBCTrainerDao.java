package 민국;

import DataSource.DataSource;
import Gym.Logic.Logic.LoginManager;
import Gym.Logic.Logic.ShowManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class JDBCTrainerDao implements TrainerDao {
    @Override
    public boolean insert(Trainer trainer) {
        boolean result = false;
        //try 문이 끝나면 자동으로 close 된다.
        try(Connection conn = DataSource.getDataSource();
            PreparedStatement pStatement = conn.prepareStatement(
                    "insert into GYM_TRAINER(LOGIN_ID, LOGIN_PW, GENDER, AWARD, AGE, NAME) " +
                            "values (?, ?, ?, ?, ?,?)"))
        {
            //id 중복 검사
            if(findById(trainer.getLogin_Id()))
            {
                return false;
            }

            //삽입
            pStatement.setString(1,trainer.getLogin_Id());
            pStatement.setString(2, trainer.getLogin_Pw());
            pStatement.setString(3,trainer.getGender());
            pStatement.setString(4,trainer.getAward());
            pStatement.setInt(5,trainer.getAge());
            pStatement.setString(6, trainer.getName());

            int rows = pStatement.executeUpdate();

            if(rows > 0)
            {
                result = true;
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        if(result)
            System.out.println("트레이너 생성 성공");
        else
            System.out.println("생성 실패");

        return result;
    }

    //트레이너가 최초 가입 시 LoginData 만 받는다.
    public boolean insertTrianerJoin(LoginData loginData) {
        boolean result = false;

        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement = conn.prepareStatement(
                     "insert into GYM_TRAINER(LOGIN_ID, LOGIN_PW, GENDER, AGE, NAME) " +
                             "values (?, ?, ?, ?, ?)"))
        {
            //아이디 중복검사, false 가 반환 되면 중복되는 id가 없다는 뜻.
            if(checkOverlabId(loginData.getLogin_id()))
            {
                pStatement.setString(1, loginData.getLogin_id());
                pStatement.setString(2, loginData.getLogin_pw());
                pStatement.setString(3, loginData.getGender());
                pStatement.setInt(4, loginData.getAge());
                pStatement.setString(5, loginData.getName());

                int rows = pStatement.executeUpdate();

                result = true;
            }
            else
            {
                ShowManager.getInstance().idOverlabError();
                result = false;
            }
        }
        catch
        (Exception e) {
            e.printStackTrace();
        }


        return result;
    }


    //트레이너의 대표 수상 경력을 추가 및 변경
    //변경에 성공하면 true 를 반환함
    public boolean updateAward(int trainerNum, String award)
    {
        boolean result = false;

        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement =
                     conn.prepareStatement("update GYM_TRAINER set " +
                             "AWARD = ? where TRAINER_NUM = ?"))
        {
            pStatement.setString(1,award);
            pStatement.setInt(2,trainerNum);

            int rows = pStatement.executeUpdate();
            if (rows > 0)
            {
                ShowManager.getInstance().successUpdate();
                result = true;
            }
            else
            {
                ShowManager.getInstance().failedUpdate();
                result = false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            ShowManager.getInstance().failedUpdate();
            result = false;
        }
        return result;
    }

    public boolean updatePasswoard(int trainerNum, String changePw)
    {
        boolean isSuccess = false;
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement =
                     conn.prepareStatement("update GYM_TRAINER set " +
                             "LOGIN_PW = ? where TRAINER_NUM = ?"))
        {
            pStatement.setString(1,changePw);
            pStatement.setInt(2,trainerNum);

            int rows = pStatement.executeUpdate();
            if (rows > 0)
            {
                ShowManager.getInstance().successUpdate();
                isSuccess = true;
            }
            else
            {
                ShowManager.getInstance().failedUpdate();
                isSuccess = false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            ShowManager.getInstance().failedUpdate();
            isSuccess = false;
        }

        return isSuccess;
    }

    //테스트 완료
    @Override
    public List<Trainer> findAll() {
        List<Trainer> tLst = new ArrayList<>();
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement = conn.prepareStatement("select * from GYM_TRAINER");
             ResultSet rs = pStatement.executeQuery())
        {
            while (rs.next())
            {
                Trainer t = new Trainer();
                t.setTrainer_num(rs.getInt("TRAINER_NUM"));
                t.setLogin_Id(rs.getString("LOGIN_ID"));
                t.setLogin_Pw(rs.getString("LOGIN_PW"));
                t.setGender(rs.getString("GENDER"));
                t.setAward(rs.getString("AWARD"));
                t.setAge(rs.getInt("AGE"));
                t.setName(rs.getString("NAME"));

                tLst.add(t);
            }

            if(tLst.isEmpty())
            {
                System.out.println("테이블이 비어있습니다.");
                return null;
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return tLst;
    }

    //테스트 완료
    @Override
    public Trainer findByIndex(int trainerNum) {
        //select * from gym_trainer where TRAINER_NUM = 1
        Trainer t = null;
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement = conn.prepareStatement("select * from GYM_TRAINER where TRAINER_NUM = ?"))
        {
            pStatement.setInt(1,trainerNum);
            ResultSet rs = pStatement.executeQuery();
            if (rs.next())
            {
                t = new Trainer();
                t.setTrainer_num(rs.getInt("TRAINER_NUM"));
                t.setLogin_Id(rs.getString("LOGIN_ID"));
                t.setLogin_Pw(rs.getString("LOGIN_PW"));
                t.setGender(rs.getString("GENDER"));
                t.setAward(rs.getString("AWARD"));
                t.setAge(rs.getInt("AGE"));
                t.setName(rs.getString("NAME"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return t;
    }

    //login_Id 중복검사
    //해당 id가 존재하면 true
    //없으면 false 를 반환.
    @Override
    public boolean findById(String login_id)
    {
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement = conn.prepareStatement("select * from GYM_TRAINER where LOGIN_ID = ?"))
        {
            pStatement.setString(1,login_id);
            ResultSet rs = pStatement.executeQuery();
            if (rs.next())
            {
                System.out.println("해당 ID를 가진 트레이너가 이미 존재합니다.");
                return true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public int getTrainerNum(String login_id)
    {
        int result = 0;

        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement = conn.prepareStatement("select * from GYM_TRAINER where LOGIN_ID = ?"))
        {
            pStatement.setString(1,login_id);
            ResultSet rs = pStatement.executeQuery();
            if (rs.next())
            {
                result =  rs.getInt("TRAINER_NUM");
            }
            else
                System.out.println("존재하지 않는 트레이너 입니다.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return result;
    }

    //아이디 중복검사 mk2 이름 헷갈려서 만듦
    public boolean checkOverlabId(String login_id)
    {
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement = conn.prepareStatement("select * from GYM_TRAINER where LOGIN_ID = ?"))
        {
            pStatement.setString(1,login_id);
            ResultSet rs = pStatement.executeQuery();
            if (rs.next())
            {
                System.out.println("해당 ID를 가진 트레이너가 이미 존재합니다.");
                return false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }

    //트레이너 id 로 검색하여 트레이너 객체를 반환하는 메서드
    public Trainer findByLoginData(String login_id) {
        Trainer t = null;

        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement = conn.prepareStatement("select * from GYM_TRAINER where LOGIN_ID = ?")) {
            pStatement.setString(1, login_id);
            ResultSet rs = pStatement.executeQuery();

            if (rs.next())
            {
                t = new Trainer();
                t.setTrainer_num(rs.getInt("TRAINER_NUM"));
                t.setLogin_Id(rs.getString("LOGIN_ID"));
                t.setLogin_Pw(rs.getString("LOGIN_PW"));
                t.setGender(rs.getString("GENDER"));
                t.setAward(rs.getString("AWARD"));
                t.setAge(rs.getInt("AGE"));
                t.setName(rs.getString("NAME"));
            }
            else
            {
                System.out.println("해당 ID의 트레이너가 존재하지 않습니다.");
                t = null;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return t;
    }

    //테스트 완료
    @Override
    public boolean update(Trainer trainer) {
        boolean result = false;

        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement =
                     conn.prepareStatement("update GYM_TRAINER set " +
                             "LOGIN_PW = ?, AWARD = ?, AGE = ? where TRAINER_NUM = ?"))
        {
            //해당 id가 존재하는지 우선적으로 확인.
            if(findByIndex(trainer.getTrainer_num()) == null)
            {
                System.out.println("변경 실패!");
                System.out.println("해당 index의 트레이너가 존재하지 않습니다.");
                return false;
            }

            pStatement.setString(1, trainer.getLogin_Pw());
            pStatement.setString(2, trainer.getAward());
            pStatement.setInt(3,trainer.getAge());
            pStatement.setInt(4,trainer.getTrainer_num());

            int affectedrows = pStatement.executeUpdate();

            if(affectedrows > 0)
            {
                System.out.println("변경 성공!");
                result = true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("변경 실패!");
            result = false;
        }

        return result;
    }

    //테스트 완료
    @Override
    public boolean deleteById(int id) {
        boolean isSuecces = false;
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement = conn.prepareStatement("delete from GYM_TRAINER where TRAINER_NUM = ?")) {
            if (findByIndex(id) == null) {
                System.out.println("해당 index 의 트레이너가 존재하지 않습니다.");
                return false;
            }

            pStatement.setInt(1, id);
            int delectCount = pStatement.executeUpdate();

            if (delectCount > 0)
                isSuecces = true;
        } catch (Exception e) {
            System.out.println("삭제에 실패하였습니다.");
            e.printStackTrace();
            return false;
        }
        if (isSuecces) {
            System.out.println("성공적으로 삭제하였습니다.");
        } else
            System.out.println("데이터가 존재하지 않습니다.");
        return isSuecces;
    }

    //테스트 완료
    @Override
    public boolean tryLogin(String login_id, String login_pw)
    {
        boolean isSuccess = false;

        //nullpointer exception 발생
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement = conn.prepareStatement("select * from GYM_TRAINER where LOGIN_ID = ?"))
        {
            pStatement.setString(1,login_id);
            ResultSet rs = pStatement.executeQuery();

            //해당 id 가 있는 트레이너가 존재하는 경우 (아이디 안 틀린 경우)
            if(rs.next())
            {
                String resultPw = rs.getString("LOGIN_PW");

                if(resultPw.equals(login_pw)) {
                    isSuccess = true;
                    Trainer loginTrainer = new Trainer();
                    loginTrainer.setTrainer_num(rs.getInt("TRAINER_NUM"));
                    loginTrainer.setLogin_Id(rs.getString("LOGIN_ID"));
                    loginTrainer.setLogin_Pw(rs.getString("LOGIN_PW"));
                    loginTrainer.setName(rs.getString("NAME"));
                    loginTrainer.setAge(rs.getInt("AGE"));
                    loginTrainer.setAward(rs.getString("AWARD"));
                    loginTrainer.setMemberType();

                    LoginManager.getInstance().setCurTrainer(loginTrainer);

                    System.out.println("로그인 성공, 현재 유저 : " + LoginManager.getInstance().getCurrentLoginUser().getLogin_id());
                    isSuccess = true;
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


    public boolean tryJoin(LoginData loginData)
    {
        boolean result = false;

        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pStatement = conn.prepareStatement("select * from GYM_TRAINER where LOGIN_ID = ?"))
        {
            pStatement.setString(1, loginData.getLogin_id());
            ResultSet rs = pStatement.executeQuery();

            //해당 id 가 있는 트레이너가 존재하는 경우 (아이디 안 틀린 경우)
            //가입 하는 부분이기 때문에 실패를 반환 해야함.
            if (rs.next()) {
                System.out.println("이미 존재하는 회원 입니다.");
            }
            else {
                //이 부분에서 insert 문을 사용하여 새로운 트레이너를 추가.
                //insertTrianerJoin();
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return result;
    }



    //update 기능을 이용하여 트레이너의 정보를 추가 기입 가능하도록 해야함.
    public boolean updateName(String award)
    {
        return false;
    }

    //Login Data Update?


}
