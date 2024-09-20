package Gym.Logic.Logic;

import Gym.Logic.Common.Input;
import 민국.JDBCTrainerDao;
import 민국.LoginData;
import 민국.Trainer;
import 하성.Admin;
import 호영.Gym_Member;
import 호영.Gym_MemberDao;
import 호영.Gym_MemberMain;


public class LoginManager {
    private static LoginManager instance = null;

    private LoginManager(){
        init();
    }

    public static LoginManager getInstance()
    {
        if(instance == null)
            instance = new LoginManager();
        return instance;
    }

    private LoginData currentLoginUser;
    private Trainer tr = null;
    private Gym_Member gm = null;
    private Admin admin = null;

    private boolean isLogin = false;

    public void init()
    {
        currentLoginUser = new LoginData();
        tr = null;
        gm = null;
        admin = null;
        isLogin = false;
    }

    //멤버 로그인 성공시 1
    //트레이너 로그인 성공시 2
    //관리자 로그인 성공시 3을 반환한다.
    public int selectLogin()
    {
        int result = 0;
        ShowManager.getInstance().showLoginMenu();

        int selectNum = Input.intScan(1,3);

            switch(selectNum)
            {
                case 1:
                {
                    if(tryMemberLogin())
                        result = 1;
                    break;
                }
                case 2:
                {
                    if(tryTrainerLogin())
                        result = 2;
                    break;
                }
                case 3:
                {
                    tryManagerLogin();
                    break;
                }
                default:
                {
                    ShowManager.getInstance().error();
                    assert(false);
                }
            }
        return result;
    }

    public boolean tryMemberLogin()
    {
        boolean result = false;

        ShowManager.getInstance().showMemberLogin();
        System.out.println("아이디를 입력 해주세요");
        String memberId = Input.stringScan();
        System.out.println("비밀번호를 입력 해주세요");
        String memberPw = Input.stringScan();

        LoginData memberLogin = new LoginData(memberId, memberPw, LoginData.MEMBERTYPE.MEMBER);
        Gym_Member currentLoginMember = DAOManager.getInstance().getmDao().findByLoginData(memberLogin);

        if (tryMemberTypeLogin(memberLogin))
        {
            gm = currentLoginMember;
            isLogin = true;
            result = true;
        }
        else
        {
            System.out.println("회원 로그인 실패");

            // 로그인이 실패했다면, 아무것도 하지 않고, 처음 화면으로 돌아간다.
        }

        return result;
    }

    public boolean tryTrainerLogin()
    {
        boolean isSuccess = false;

        ShowManager.getInstance().showTrainerLogin();

        System.out.println("아이디를 입력 해주세요");
        String id = Input.stringScan();
        System.out.println("비밀번호를 입력 해주세요");
        String pw = Input.stringScan();

        //입력받은 로그인 id, pw 를 기준으로 트레이너 타입의 객체 생성.
        LoginData login = new LoginData(id,pw, LoginData.MEMBERTYPE.TRAINER);
        //Trainer curLoginTrainer
        tr = DAOManager.getInstance().gettDao().findByLoginData(id);
        if(tryLogin(login))
        {
            //System.out.println("로그인 성공");
//            tr = curLoginTrainer;
            isLogin = true;
            isSuccess = true;
        }
        else
        {
            System.out.println("로그인 실패");
        }

        return isSuccess;
    }

    public void tryManagerLogin()
    {
        gm = DAOManager.getInstance().getmDao().findByMember_Num(1);
        isLogin = true;
        //임시로 넣어놓음,
        currentLoginUser = new LoginData(gm.getLogin_id(),gm.getLogin_pw(),LoginData.MEMBERTYPE.MEMBER);
    }

    public void tryLogin(LoginData.MEMBERTYPE type, String login_Id, String login_pw)
    {
        switch(type)
        {
            case MEMBER: {
                break;
            }
            case TRAINER: {
                JDBCTrainerDao tDao = DAOManager.getInstance().gettDao();

                if (tDao.tryLogin(login_Id, login_pw))
                {
                    System.out.println("로그인 성공");
                }
                else
                {
                    System.out.println("로그인 실패");
                }


                break;
            }
        }
    }

    public boolean tryLogin(LoginData loginData)
    {
        boolean result = false;
        JDBCTrainerDao tDao = DAOManager.getInstance().gettDao();
        if(tDao.tryLogin(loginData.getLogin_id(), loginData.getLogin_pw()))
        {
            //System.out.println("로그인 성공");
            result = true;
        }
        else
        {
            System.out.println("로그인 실패");
            result = false;
        }

        return result;
    }

    public boolean tryMemberTypeLogin(LoginData memberLogin)
    {
        boolean result = false;
        Gym_MemberDao gmDao = DAOManager.getInstance().getmDao();
        if (gmDao.tryLogin(memberLogin.getLogin_id(), memberLogin.getLogin_pw()))
        {
            System.out.println("멤버 로그인 성공");
            result = true;
        }
        else
        {
            System.out.println("멤버 로그인 실패");
        }

        return result;
    }

    public void setCurrentLoginUser(LoginData loginData)
    {
        if(loginData != null) {
            currentLoginUser = loginData;
        }
    }

    public void setCurTrainer(Trainer tr)
    {
        if(tr != null) {
            this.tr = tr;
            isLogin = true;
            this.currentLoginUser = tr.getLoginData();
        }
    }


    public LoginData getCurrentLoginUser()
    {
        if(isLogin)
            return this.currentLoginUser;
        else
        {
            System.out.println("로그인 데이터가 없습니다.");
            return null;
        }
    }

    public void logOut()
    {
        currentLoginUser = null;
        isLogin = false;

        init();
    }

    public Gym_Member getCurrentMember()
    {
        if(gm != null)
        {
            return gm;
        }
        else
        {
            System.out.println("멤버 로그인 상태가 아닙니다.");
            return null;
        }
    }

    public Trainer getCurrentTrainer()
    {
        if (tr != null)
            return this.tr;
        else
        {
            System.out.println("트레이너 로그인 상태가 아닙니다.");
            return null;
        }
    }


}
