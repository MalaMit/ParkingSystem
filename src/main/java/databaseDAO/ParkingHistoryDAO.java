package databaseDAO;

public interface ParkingHistoryDAO {
	
    boolean insertParkingHistory(String timeIN, String timeOut, String bill, String licensePlate, String parkingNumber);
    boolean updateParkingHistory();
    boolean deleteParkingHistory();

}
