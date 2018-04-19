package databaseDAOImpl;

import java.sql.SQLException;

import databaseDAO.ParkingTimeDAO;
import databaseModel.ParkingTime;
import databaseUtil.DBUtil;
import javafx.collections.ObservableList;

public class ParkingTimeDAOImpl implements ParkingTimeDAO{

	@Override
	public ObservableList<ParkingTime> getAllTakenSPot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertParkingTime(String clientLicensePlate, String timeOut, int bill, String typeVehicle, String parkingNumber) {
		String sql = "INSERT INTO parkingTime (clientLicensePlate, timeOut, bill, typeVehicle, parkingNumber) VALUES ('"+clientLicensePlate+"', '"+timeOut+"', '"+bill+"', '"+typeVehicle+"', '"+parkingNumber+"')";
		try {
			DBUtil.dbExcecuteQuery(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean deleteParkingTime() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateParkingTime() {
		// TODO Auto-generated method stub
		return false;
	}

}
