package Gym.Logic.Logic;

public class ShowManager {

    //별도의 기능을 갖지 않으며 전체적인 출력 폼 통일을 위해 생성.
    private static ShowManager instance = null;
    private ShowManager(){}
    public static ShowManager getInstance()
    {
        if(instance == null)
            instance = new ShowManager();
        return instance;
    }

    public void showMainScreen()
    {
        System.out.println("========================================================");
        System.out.println("=                                                      =");
        System.out.println("=                1. Login                              =");
        System.out.println("=                2. Open Charge List                   =");
        System.out.println("=                3. Join                               =");
        System.out.println("=                                                      =");
        System.out.println("========================================================");
        numberInputform();
    }

    public void showLoginMenu()
    {
        System.out.println("========================================================");
        System.out.println("=                                                      =");
        System.out.println("=                1. Member Login                       =");
        System.out.println("=                2. Trainer Login                      =");
        System.out.println("=                3. Manager Login                      =");
        System.out.println("=                                                      =");
        System.out.println("========================================================");
        numberInputform();
    }

    public void showJoinMenu()
    {
        System.out.println("========================================================");
        System.out.println("=                                                      =");
        System.out.println("=                1. Member Join                        =");
        System.out.println("=                2. Trainer Join                       =");
        System.out.println("=                3. Manager Join                       =");
        System.out.println("=                                                      =");
        System.out.println("========================================================");
        numberInputform();
    }


    //Join
    ////////////////////////////////////////////////////

    public void showMemberJoin(){
        System.out.println("========================================================");
        System.out.println("=                                                      =");
        System.out.println("=                                                      =");
        System.out.println("=                Member Join                           =");
        System.out.println("=                                                      =");
        System.out.println("=                                                      =");
        System.out.println("========================================================");
    }

    public void showTrainerJoin(){
        System.out.println("========================================================");
        System.out.println("=                                                      =");
        System.out.println("=                                                      =");
        System.out.println("=                Trainer Join                          =");
        System.out.println("=                                                      =");
        System.out.println("=                                                      =");
        System.out.println("========================================================");
    }

    public void showAdminJoin(){
        System.out.println("========================================================");
        System.out.println("=                                                      =");
        System.out.println("=                                                      =");
        System.out.println("=                Admin Join                            =");
        System.out.println("=                                                      =");
        System.out.println("=                                                      =");
        System.out.println("========================================================");
    }

    //Login
    //////////////////////////////////////////////////

    public void showMemberLogin(){
        System.out.println("========================================================");
        System.out.println("=                                                      =");
        System.out.println("=                                                      =");
        System.out.println("=                Member Login                          =");
        System.out.println("=                                                      =");
        System.out.println("=                                                      =");
        System.out.println("========================================================");
    }

    public void showTrainerLogin(){
        System.out.println("========================================================");
        System.out.println("=                                                      =");
        System.out.println("=                                                      =");
        System.out.println("=                Trainer Login                         =");
        System.out.println("=                                                      =");
        System.out.println("=                                                      =");
        System.out.println("========================================================");
    }


    ///////////////////////////////////////////////////

    //Trainer First Menu
    public void showTrainerMenu()
    {
        System.out.println("========================================================");
        System.out.println("=                                                      =");
        System.out.println("=                1. Lesson                             =");
        System.out.println("=                2. Award                              =");
        System.out.println("=------------------------------------------------------=");
        System.out.println("=                                                      =");
        System.out.println("=                7. Change Password                    =");
        System.out.println("=                8. Log out                            =");
        System.out.println("=                9. With Out                           =");
        System.out.println("========================================================");
        numberInputform();
    }


    public void showTrainerLessonMenu()
    {
        System.out.println("========================================================");
        System.out.println("=                                                      =");
        System.out.println("=                1. Create New Lesson                  =");
        System.out.println("=                2. Display My Lesson                  =");
        System.out.println("=                3. Close Lesson                       =");
        System.out.println("=                                                      =");
        System.out.println("=------------------------------------------------------=");
        System.out.println("=                                                      =");
        System.out.println("=                9. Back To Main                       =");
        System.out.println("=                                                      =");
        System.out.println("========================================================");
        numberInputform();
    }

    //Member First Menu
    public void showManagerMenu()
    {
        System.out.println("========================================================");
        System.out.println("=                                                      =");
        System.out.println("=                1. Member Password Initialize         =");
        System.out.println("=                2. Chase out Member                   =");
        System.out.println("=                3. Review                             =");
        System.out.println("=                4. Inbody Data                        =");
        System.out.println("=------------------------------------------------------=");
        System.out.println("=                                                      =");
        System.out.println("=                7. Change Password                    =");
        System.out.println("=                8. Log out                            =");
        System.out.println("=                                                      =");
        System.out.println("========================================================");
        numberInputform();
    }

    //Review Menu
    public void showReviewMenu()
    {
        System.out.println("========================================================");
        System.out.println("=                                                      =");
        System.out.println("=                1. Display All Review List            =");
        System.out.println("=                2. Write A Review                     =");
        System.out.println("=                3. Modify A Review                    =");
        System.out.println("=------------------------------------------------------=");
        System.out.println("=                                                      =");
        System.out.println("=                4. Delete A Review                    =");
        System.out.println("=                5. Show my Trainer's Review Score     =");
        System.out.println("=                9. Back                               =");
        System.out.println("=                                                      =");
        System.out.println("========================================================");
        numberInputform();
    }

    //Lesson Menu
    public void showMemberLessonMenu()
    {
        System.out.println("========================================================");
        System.out.println("=                                                      =");
        System.out.println("=                1. Display All Lesson List            =");
        System.out.println("=                2. Display Trainer's Lesson           =");
        System.out.println("=                3. Modify A Lesson's Info             =");
        System.out.println("=------------------------------------------------------=");
        System.out.println("=                                                      =");
        System.out.println("=                4. Delete A Lesson Info               =");
        System.out.println("=                9. Back To Main                       =");
        System.out.println("=                                                      =");
        System.out.println("========================================================");
        numberInputform();
    }

    public void showMemberMenu()
    {
        System.out.println("========================================================");
        System.out.println("=                                                      =");
        System.out.println("=                1. ModifyMemberInfo(Trainer,PT,PW)    =");
        System.out.println("=                2. Display All Member's List          =");
        System.out.println("=                3. Display Review Menu                =");
        System.out.println("=------------------------------------------------------=");
        System.out.println("=                                                      =");
        System.out.println("=                4. Display Inbody Menu                =");
        System.out.println("=                5. Withdrawal Member                  =");
        System.out.println("=                9. LogOut & Back To Main              =");
        System.out.println("=                                                      =");
        System.out.println("========================================================");
        numberInputform();
    }

    public void showInbodyMenu()
    {
        System.out.println("========================================================");
        System.out.println("=                1. Show My Inbody List                =");
        System.out.println("=                2. Insert New Inbody Data             =");
        System.out.println("=------------------------------------------------------=");
        System.out.println("=                                                      =");
        System.out.println("=                9. Back                               =");
        System.out.println("=                                                      =");
        System.out.println("========================================================");
        numberInputform();
    }


    //InputField
    private void numberInputform()
    {
        System.out.println("메뉴 번호를 입력 해주세요 : ");
    }

    public void nameInputform()
    {
        System.out.println("이름을 입력 해주세요 : ");
    }

    public void numberInputForm() { System.out.println("담당 트레이너 번호를 입력해 주세요 :"); }

    public void genderInputform() 
    {
        System.out.println("성별을 입력 해주세요 : ");
    }
    
    public void ageIntpuform() 
    {
        System.out.println("나이를 입력 해주세요 : ");
    }
    
    public void idInputform()
    {
        System.out.println("아이디를 입력 해주세요 : ");
    }

    public void pwInputform()
    {
        System.out.println("패스워드를 입력 해주세요 : ");
    }

    public void idOverlabError()
    {
        System.out.println("아이디 중복으로 인한 가입 실패.");
    }

    public void pwError()
    {
        System.out.println("비밀번호 가 올바르지 않습니다.");
    }
    
    public void successJoin()
    {
        System.out.println("가입 성공");
    }
    
    public void failedJoin()
    {
        System.out.println("가입 실패");
    }
    
    public void successUpdate()
    {
        System.out.println("변경 성공");
    }
    public void failedUpdate()
    {
        System.out.println("변경 실패");        
    }
    
    public void error()
    {
        System.out.println("잘못된 입력");
    }

    public void awardInputform()
    {
        System.out.println("대표 수상경력을 입력 해주세요.");
    }
}
