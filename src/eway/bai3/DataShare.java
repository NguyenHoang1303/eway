package eway.bai3;

import java.util.concurrent.ArrayBlockingQueue;

public class DataShare {
    ArrayBlockingQueue<Position> listData = new ArrayBlockingQueue(5);

    public DataShare() {
    }
}
