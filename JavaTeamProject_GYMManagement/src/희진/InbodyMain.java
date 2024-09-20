package 희진;

import Gym.Logic.Common.Input;
import Gym.Logic.Logic.DAOManager;
import Gym.Logic.Logic.LoginManager;
import Gym.Logic.Logic.ShowManager;

import java.util.List;
import java.util.Scanner;

public class InbodyMain {
    InbodyMethod iMethod = new InbodyMethod();

    public void inbodyExecute() {
        boolean loop = true;
        while (loop) {
            ShowManager.getInstance().showInbodyMenu();
            int select = Input.intScan();
            switch (select) {
                case 1:
                    System.out.println(LoginManager.getInstance().getCurrentMember().getName() + "님의 인바디 데이터를 출력합니다.");
                    List<Inbody> myInbodyList = DAOManager.getInstance().getiDao().findByMemberNum(LoginManager.getInstance().getCurrentMember().getMember_num());
                    if (myInbodyList.isEmpty()){
                        System.out.println("현재 등록된 인바디 데이터가 없습니다.");
                    } else {
                        for (Inbody i : myInbodyList){
                            System.out.println(i);
                        }
                    }
                    break;
                case 2:
                    iMethod.insertInbody();
                    break;

                case 3:
                    iMethod.findByInbodyNum();
                    break;

                case 4:
                    iMethod.deleteInbody();
                    break;

                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    loop = false;
                    break;

                default:
                    System.out.println("올바른 선택이 아닙니다. 다시 선택해 주세요.");
                    break;
            }
        }
    }
}