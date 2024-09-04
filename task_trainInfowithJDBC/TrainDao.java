package space.jdbc.train;

import java.util.List;

public interface TrainDao {

    public List<Train> getAllTrain();
    public Train findTrain(int id);
    public int insertTrain(Train train);
    public int updateTrain(Train train);
    public int deleteTrain(int id);
    public List<Train> getDrivingInfo();


}
