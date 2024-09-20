package Gym.Logic.Logic;

import 민국.JDBCTrainerDao;
import 진욱.JDBCGymLessonDao;
import 진욱.JDBCReviewDao;
import 하성.JDBCChargeDao;
import 호영.JdbcGym_MemberDao;
import 희진.JDBCInbodyDao;

public class DAOManager {
    private static DAOManager instance = null;
    private DAOManager()
    {
        init();
    }
    public static DAOManager getInstance()
    {
        if(instance == null) {
            instance = new DAOManager();
        }
        return instance;
    }

    private JDBCTrainerDao tDao;
    private JDBCGymLessonDao lDao;
    private JDBCReviewDao rDao;
    private JdbcGym_MemberDao mDao;
    private JDBCInbodyDao iDao;
    private JDBCChargeDao cDao;

    private void init()
    {
        tDao = new JDBCTrainerDao();
        lDao = new JDBCGymLessonDao();
        rDao = new JDBCReviewDao();
        mDao = new JdbcGym_MemberDao();
        iDao = new JDBCInbodyDao();
        cDao = new JDBCChargeDao();
    }

    public JDBCTrainerDao gettDao() {
        return tDao;
    }

    public JDBCGymLessonDao getlDao() {
        return lDao;
    }

    public JDBCReviewDao getrDao() {
        return rDao;
    }

    public JdbcGym_MemberDao getmDao() {
        return mDao;
    }

    public JDBCInbodyDao getiDao() {
        return iDao;
    }

    public JDBCChargeDao getcDao() { return cDao; }

}

