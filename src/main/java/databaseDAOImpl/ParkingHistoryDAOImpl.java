package databaseDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import databaseDAO.ParkingHistoryDAO;
import databaseModel.ParkingHistory;
import databaseUtil.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ParkingHistoryDAOImpl implements ParkingHistoryDAO {

	@Override
	public boolean insertParkingHistory(String timeIN, String timeOut, String bill, String licensePlate,
			String parkingNumber) {
		String sql = "INSERT INTO parkinghistory (timeIN, timeOut, bill, licensePlate, parkingNumber) VALUES ('"
				+ timeIN + "', '" + timeOut + "', '" + bill + "', '" + licensePlate + "', '" + parkingNumber + "')";
		try {
			DBUtil.dbExcecuteQuery(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ObservableList<ParkingHistory> getHistoryByLicensePlate(String licensePlate) {
		String sql = "SELECT historyParkingID, timeIn, timeOut, bill, parkingNumber FROM parkinghistory WHERE licensePlate = '"+ licensePlate +"'" ;
		try {
			ResultSet rsSet  = DBUtil.dbExecute(sql);
			ObservableList<ParkingHistory> pHistory = FXCollections.observableArrayList();
			while(rsSet.next()) {
				ParkingHistory pH = new ParkingHistory();
				pH.setHistoryParkingID(rsSet.getInt("historyParkingID"));
				pH.setTimeIn(rsSet.getString("timeIn").substring(0, (rsSet.getString("timeIn").indexOf("."))));
				pH.setTimeOut(rsSet.getString("timeOut").substring(0, (rsSet.getString("timeOut").indexOf("."))));			
				pH.setBill(rsSet.getFloat("bill"));
				pH.setParkingNumber(rsSet.getString("parkingNumber"));
				pHistory.add(pH);
			}	
			return pHistory;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
