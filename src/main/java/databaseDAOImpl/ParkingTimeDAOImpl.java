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
	public ObservableList<ParkingTime> getAllTakenSPot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertParkingTime(String clientLicensePlate, String timeOut, int bill, String typeVehicle,
			String parkingNumber) {
		String sql = "INSERT INTO parkingTime (clientLicensePlate, timeOut, bill, typeVehicle, parkingNumber) VALUES ('"
				+ clientLicensePlate + "', '" + timeOut + "', '" + bill + "', '" + typeVehicle + "', '" + parkingNumber
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
		String sql = "DELETE FROM parkingtime WHERE ClientLicensePlate = '" + licensePlate + "'";
		try {
			DBUtil.dbExcecuteQuery(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ObservableList<ParkingTime> getToExitParking(String licensePlate) {
		String sql = "SELECT TimeOut, Bill, typeVehicle, parkingNumber,TimeIN FROM parkingtime WHERE ClientLicensePlate = '"
				+ licensePlate + "'";
		try {
			ResultSet rsSet = DBUtil.dbExecute(sql);
			ObservableList<ParkingTime> pTime = FXCollections.observableArrayList();
			while (rsSet.next()) {
				ParkingTime pT = new ParkingTime();
				pT.setTimeOut(rsSet.getString("TimeOut").substring(0, (rsSet.getString("TimeOut").indexOf("."))));
				pT.setBill(rsSet.getFloat("Bill"));
				pT.setTimeIn(rsSet.getString("TimeIN").substring(0, (rsSet.getString("TimeIn").indexOf("."))));
				pT.setTypeVehicle(rsSet.getString("typeVehicle"));
				pT.setParkingNumber(rsSet.getString("parkingNumber"));
				pTime.add(pT);
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
		String sql = "select if((now() - TimeOut) > 0, ((UNIX_TIMESTAMP(now()) - unix_timestamp(TimeOut))/3600), 0) from parkingtime where ClientLicensePlate ='"
				+ licensePlate + "'";
		try {
			ResultSet rsSet = DBUtil.dbExecute(sql);
			int overrunTimeCost = 0;
			while (rsSet.next()) {
				overrunTimeCost = (int) rsSet.getFloat(
						"if((now() - TimeOut) > 0, ((UNIX_TIMESTAMP(now()) - unix_timestamp(TimeOut))/3600), 0)");
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
		String sql = "SELECT * FROM parkingtime WHERE ClientLicensePlate = '" + licensePlate + "' ";
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
}
