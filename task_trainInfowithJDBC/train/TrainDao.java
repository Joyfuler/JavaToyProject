package space.jdbc.train;

import java.util.List;

public interface TrainDao {

    public List<Train> getAllTrain();
    public Train findTrain(int train_num);
    public int insertTrain(Train train);
    public int updateTrain(Train train);
    public int deleteTrain(int train_num);
    public List<Train> getDrivingInfo(int lineNum);


}
