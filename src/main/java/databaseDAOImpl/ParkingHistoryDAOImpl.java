package databaseDAOImpl;

import java.sql.SQLException;

import databaseDAO.ParkingHistoryDAO;
import databaseUtil.DBUtil;

public class ParkingHistoryDAOImpl implements ParkingHistoryDAO {

	@Override
	public boolean insertParkingHistory(String timeIN, String timeOut, String bill, String licensePlate, String parkingNumber) {
		String sql = "INSERT INTO historyparking (timeIN, timeOut, bill, ClientLicensePlate, parkingNumber) VALUES ('"+timeIN+"', '"+timeOut+"', '"+bill+"', '"+licensePlate+"', '"+parkingNumber+"')";
		try {
			DBUtil.dbExcecuteQuery(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateParkingHistory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteParkingHistory() {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
