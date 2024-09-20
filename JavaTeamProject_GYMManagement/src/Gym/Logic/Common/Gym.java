package Gym.Logic.Common;

import Gym.Logic.Logic.DAOManager;
import Gym.Logic.Logic.JoinManager;
import Gym.Logic.Logic.LoginManager;
import Gym.Logic.Logic.ShowManager;
import 민국.Trainer;
import 진욱.Gym_Lesson;
import 하성.Charge;
import 호영.Gym_Member;
import 호영.Gym_MemberMain;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class Gym
{
    boolean loop = true;

    public void run()
    {
        while(loop)
        {
            mainPage();
        }
    }

    private void mainPage()
    {
        ShowManager.getInstance().showMainScreen();

        int selectNum = Input.intScan(1,3);

        int loginResult;
        //System.out.println(loginResult);
        switch (selectNum)
        {
            //login
            case 1:
            {
                loginResult = LoginManager.getInstance().selectLogin();

                switch(loginResult)
                {
                    case 1:
                    {
                        //멤버 로그인 성공시
                        memberMainPage();
                        break;
                    }

                    case 2:
                    {
                        //트레이너 로그인 성공시
                        trainerMainPage();

                        break;
                    }
                }

                break;
            }
            //요금표 열람.
            case 2:
            {
                System.out.println("===== GYM 요금표 =====");
                for (Charge c : DAOManager.getInstance().getcDao().findAll()){
                    System.out.println(c);
                }
                break;
            }
            //회원 가입.
            case 3:
            {
                //Join
                selectJoinPage();
                break;
            }
        }
    }

    //Join---------------------------------------------

    private void selectJoinPage()
    {
        ShowManager.getInstance().showJoinMenu();

        int selectNum = Input.intScan(1,3);


        switch(selectNum)
        {
            case 1:
            {
                memberJoinPage();
                if(JoinManager.getInstance().memberJoin())
                {
                    System.out.println("가입 성공");
                }
                break;
            }
            case 2:
            {
                trainerJoinPage();
                break;
            }
            case 3:
            {
                adminJoinPage();
                break;
            }
        }

    }

    private void trainerJoinPage()
    {
        ShowManager.getInstance().showTrainerJoin();
        JoinManager.getInstance().tryTraninerJoin();
    }

    private void memberJoinPage()
    {
        ShowManager.getInstance().showMemberJoin();

    }

    private void adminJoinPage()
    {
        ShowManager.getInstance().showAdminJoin();
    }

    //로그인 ------------------------------------------------


    //Page that arrives upon successful login of Trainer
    private void trainerMainPage()
    {
        System.out.println("트레이너 로그인 성공");

        boolean loop = true;

        while(loop) {

            ShowManager.getInstance().showTrainerMenu();

            int selectNum = Input.intScan();
            switch (selectNum) {
                //lesson
                case 1: {
                    trainerLessonMenu();
                    break;
                }

                //award
                case 2: {
                    displayMyAward();
                    break;
                }

                //change password
                case 7: {
                    changeTrainerPw();
                    break;
                }

                //logout
                case 8: {
                    LoginManager.getInstance().logOut();
                    loop = false;
                    break;
                }

                //without
                case 9: {
                    int deleteId = LoginManager.getInstance().getCurrentTrainer().getTrainer_num();
                    LoginManager.getInstance().logOut();
                    DAOManager.getInstance().gettDao().deleteById(deleteId);

                    loop = false;
                    break;
                }

                default: {
                    System.out.println("잘못된 입력입니다.");
                    break;
                }
            }
        }
    }

    private void trainerLessonMenu()
    {
        boolean loop = true;
        while(loop) {
            ShowManager.getInstance().showTrainerLessonMenu();

            int selectNum = Input.intScan();
            switch (selectNum) {
                //create new lesson
                case 1: {
                    CreateLesson();
                    break;
                }
                //display my lesson
                case 2: {
                    displayMyLesson();
                    break;
                }
                //close my lesson
                case 3: {
                    closeLesson();
                    break;
                }
                //back to menu
                case 9: {
                    loop = false;
                    break;
                }
                default: {
                    System.out.println("잘못된 입력");
                    break;
                }
            }
        }
    }

    //테스트 완료
    private void CreateLesson()
    {
        System.out.println("새로운 클래스를 등록 합니다.");
        System.out.println("클래스의 내용을 입력 해주세요 : ");

        String inputDetail = Input.stringScan();

        System.out.println("날짜를 입력해주세요");


        Timestamp ts = Input.inputTimestamp();

        Gym_Lesson newLesson = new Gym_Lesson(inputDetail,
                ts, LoginManager.getInstance().getCurrentTrainer());

        DAOManager.getInstance().getlDao().createLesson(newLesson);
    }

    private void displayMyLesson()
    {
        List<Gym_Lesson> lLst = DAOManager.getInstance().getlDao().findByTrainer
                (LoginManager.getInstance().getCurrentTrainer().getTrainer_num()) ;

        lLst.stream().forEach(x-> System.out.println(x.toString()));
    }

    private void closeLesson()
    {
        displayMyLesson();

        System.out.println("종료할 수업의 번호를 입력해주세요.");
        System.out.println("주의) 다른 트레이너의 수업을 닫을 수도 있습니다. 본인의 수업만 종료해주세요.");

        int selectNum = Input.intScan();

        DAOManager.getInstance().getlDao().nameToExpiredClass(selectNum);
    }

    private void displayMyAward()
    {
        String award = DAOManager.getInstance().gettDao().findByIndex(
                LoginManager.getInstance().getCurrentTrainer().getTrainer_num()
        ).getAward();

        System.out.println(award);
    }

    private void changeTrainerPw()
    {
        System.out.println("변경할 비밀번호를 입력 해주세요");
        String newPw = Input.stringScan();

        if(DAOManager.getInstance().gettDao().updatePasswoard(
                LoginManager.getInstance().getCurrentTrainer().getTrainer_num(), newPw))
        {
            System.out.println("비밀번호 변경 성공");
        }
    }


    //Page that arrives upon successful login of Member
    private void memberMainPage()
    {
        Gym_MemberMain gMain = new Gym_MemberMain();
        gMain.execute(); // 멤버 Main 프로그램 실행으로 이동.
    }




}
