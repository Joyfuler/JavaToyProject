package space.jdbc.train;

import space.jdbc.memo.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TrainDaoImplements implements TrainDao {
    @Override
    public List<Train> getAllTrain() {
        List<Train> trainList = new LinkedList<Train>();
        String sql = "SELECT * FROM TRAIN";
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                trainList.add(new Train(rs.getInt("train_no"), rs.getInt("train_count"), rs.getString("driver"), rs.getString("isexpress"), new Line(rs.getInt("id"))));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trainList;
    }

    @Override
    public Train findTrain(int id) {
        Train searchTrain = null;
        String sql = "SELECT * FROM TRAIN WHERE TRAIN_NO = ?";

        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int train_no = rs.getInt("train_no");
                int train_count = rs.getInt("train_count");
                String driver = rs.getString("driver");
                String isExpress = rs.getString("isExpress");
                int tid = rs.getInt("id");
                searchTrain = new Train(train_no, train_count, driver, isExpress, new Line(tid));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return searchTrain;
    }

    @Override
    public int insertTrain(Train train) {
        int result = 0;
        String sql = "INSERT INTO train (train_no, train_count, driver, id) VALUES " +
                "(?, ?, ?, ?)";

        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, train.getTrain_no());
            pstmt.setInt(2, train.getTrain_count());
            pstmt.setString(3, train.getDriver());
            pstmt.setInt(4, train.getLine().getId());
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public int updateTrain(Train train) {
        int result = 0;
        String sql = "update train set train_count = ?, driver = ?, id = ?, isexpress = ? where train_no = ?";

        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, train.getTrain_count());
            pstmt.setString(2, train.getDriver());
            pstmt.setInt(3, train.getLine().getId());
            pstmt.setString(4, train.getIsExpress());
            pstmt.setInt(5, train.getTrain_no());


            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public int deleteTrain(int id) {
        int result = 0;
        String sql = "delete from train where train_no = ?";
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public List<Train> getDrivingInfo(int id) {
        List<Train> drivingList = new LinkedList<Train>();
        String sql = "select a.train_no, a.driver, a.isexpress, b.isdriving, id \n" +
                "from train a natural join line b " +
                "where id = ?";
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Train drivingTrain = new Train(rs.getString("a.driver"),
                        rs.getInt("a.train_no"),
                        rs.getString("a.isexpress"),
                        new Line(rs.getInt("id"), rs.getString("b.isdriving")));
                drivingList.add(drivingTrain);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return drivingList;
    }
}
