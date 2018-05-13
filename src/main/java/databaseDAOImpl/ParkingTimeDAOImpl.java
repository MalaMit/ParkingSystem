package databaseDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import databaseDAO.ParkingTimeDAO;
import databaseModel.ParkingTime;
import databaseUtil.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ParkingTimeDAOImpl implements ParkingTimeDAO {

	@Override
	public ObservableList<ParkingTime> getAllTakenSpot() {
		String sql = "SELECT * FROM parkingtime";
		try {
			ResultSet rsSet = DBUtil.dbExecute(sql);
			ObservableList<ParkingTime> parkingTime = FXCollections.observableArrayList();
			while(rsSet.next()) {
				ParkingTime pTime = new ParkingTime();
				pTime.setClientLicensePlate(rsSet.getString("licensePlate"));
				pTime.setTimeIn(rsSet.getString("timeIn").substring(0, rsSet.getString("timeIn").indexOf(".")));
				pTime.setTimeOut(rsSet.getString("timeOut").substring(0, rsSet.getString("timeOut").indexOf(".")));
				pTime.setBill(rsSet.getFloat("bill"));
				pTime.setTypeVehicle(rsSet.getString("typeVehicle"));
				pTime.setParkingNumber(rsSet.getString("parkingNumber"));
				
				parkingTime.add(pTime);
			}
			return parkingTime;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
//licensePlate, timeIn, timeOut, bill, typeVehicle, parkingNumber
	@Override
	public ParkingTime getToExitParking(String licensePlate) {
		String sql = "SELECT timeOut, bill, typeVehicle, parkingNumber, timeIn FROM parkingtime WHERE licensePlate = '"
				+ licensePlate + "'";
		try {
			ResultSet rsSet = DBUtil.dbExecute(sql);
			ParkingTime pTime = new ParkingTime();
			while (rsSet.next()) {
				pTime.setTimeOut(rsSet.getString("timeOut").substring(0, (rsSet.getString("timeOut").indexOf("."))));
				pTime.setBill(rsSet.getFloat("bill"));
				pTime.setTimeIn(rsSet.getString("timeIn").substring(0, (rsSet.getString("timeIn").indexOf("."))));
				pTime.setTypeVehicle(rsSet.getString("typeVehicle"));
				pTime.setParkingNumber(rsSet.getString("parkingNumber"));
			}
			return pTime;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getOverrunTimePrice(String licensePlate) {
		String sql = "select if((now() - timeOut) > 0, ((UNIX_TIMESTAMP(now()) - unix_timestamp(timeOut))/3600), 0) from parkingtime where licensePlate ='"
				+ licensePlate + "'";
		try {
			ResultSet rsSet = DBUtil.dbExecute(sql);
			int overrunTimeCost = 0;
			while (rsSet.next()) {
				overrunTimeCost = (int) rsSet.getFloat(
						"if((now() - timeOut) > 0, ((UNIX_TIMESTAMP(now()) - unix_timestamp(timeOut))/3600), 0)");
			}
			return overrunTimeCost;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean checkLicensePlateExist(String licensePlate) {
		String sql = "SELECT * FROM parkingtime WHERE licensePlate = '"+ licensePlate +"'";
		boolean isExist = false;
		try {
			ResultSet rs = DBUtil.dbExecute(sql);
			if (rs.next()) {
				isExist = true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return isExist;
	}
	
	@Override
	public void insertParkingTime(String clientLicensePlate, int timeOut, int bill, String typeVehicle,
			String parkingNumber) {
		String sql = "INSERT INTO parkingTime (licensePlate, timeOut, bill, typeVehicle, parkingNumber) VALUES ('"
				+ clientLicensePlate + "', (now() + INTERVAL " + timeOut + " HOUR), '" + bill + "', '" + typeVehicle + "', '" + parkingNumber
				+ "')";
		try {
			DBUtil.dbExcecuteQuery(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean deleteParkingTime(String licensePlate) {
		String sql = "DELETE FROM parkingtime WHERE licensePlate = '" + licensePlate + "'";
		try {
			DBUtil.dbExcecuteQuery(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
