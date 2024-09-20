package Gym.Logic.Common;

import java.sql.Timestamp;
import java.util.Scanner;

public class Input {
    public static Scanner scan = new Scanner(System.in);

    public static String stringScan()
    {
        return scan.nextLine();
    }

    public static int intScan()
    {
        String s = scan.nextLine();
        int result = Integer.parseInt(s);

        if(result > 9999 || result < -9999)
        {
            System.out.println("-9999~9999 사이 값을 입력해주세요");
            return 0;
        }
        return result;
    }

    public static int intScan(int min, int max)
    {
        String s = scan.nextLine();
        int result = Integer.parseInt(s);

        if(result > max || result < min)
        {
            System.out.println(min + "~" + max + " 사이의 정수값을 입력해주세요");
            return 0;
        }
        return result;
    }

    public static double doubleScan()
    {
        String s = scan.nextLine();
        double result = Double.parseDouble(s);

        if(result > 9999 || result < -9999)
        {
            System.out.println("-9999~9999 사이 값을 입력해주세요");
            return 0;
        }
        return result;
    }


    public static Timestamp inputTimestamp()
    {
        int releaseDateYear;
        int releaseDateMonth;
        int releaseDateDay;
        int timeH;
        while (true)
        {
            System.out.println("개강 연도 : ");
            String year = scan.nextLine();
            int releaseYear = Integer.parseInt(year);
            if (releaseYear >= 1900 && releaseYear <= 2024)
            {
                releaseDateYear = releaseYear;
                break;
            }
            else
                System.out.println("올바른 정보를 입력해주세요 (1900 ~ 2024)");
        }

        while (true)
        {
            System.out.println("개강 월 : ");
            String month = scan.nextLine();
            int releaseMonth = Integer.parseInt(month);
            if (releaseMonth >= 1 && releaseMonth <= 12)
            {
                releaseDateMonth = releaseMonth;
                break;
            }
            else
                System.out.println("올바른 정보를 입력해주세요 (1 ~ 12)");
        }

        while (true)
        {
            System.out.println("개강 일 : ");
            String day = scan.nextLine();
            int releaseDay = Integer.parseInt(day);
            if (releaseDay >= 1 && releaseDay <= 31)
            {
                releaseDateDay = releaseDay;
                break;
            }
            else
                System.out.println("올바른 정보를 입력해주세요 (1 ~ 31)");
        }

        while (true)
        {
            System.out.println("개강 시간을 입력 해주세요");
            timeH = Input.intScan();
            if (timeH >= 0 && timeH <= 24)
            {
                break;
            }
            else
                System.out.println("올바른 정보를 입력해주세요 (0~24)");
        }

        String time = releaseDateYear + "-" + releaseDateMonth + "-" + releaseDateDay + " " +
                timeH + ":00:00";

        Timestamp date = Timestamp.valueOf(time);

        return date;
    }



}
