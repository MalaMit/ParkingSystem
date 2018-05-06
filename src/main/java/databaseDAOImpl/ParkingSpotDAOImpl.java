package databaseDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import databaseDAO.ParkingSpotDAO;
import databaseModel.ParkingSpot;
import databaseUtil.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ParkingSpotDAOImpl implements ParkingSpotDAO {
	
	@Override
	public ObservableList<ParkingSpot> getAllParkingSpot() {
		String sql = "SELECT * FROM parkingspot";
		try {
			ResultSet rsSet = DBUtil.dbExecute(sql);
			ObservableList<ParkingSpot> pSpot = FXCollections.observableArrayList();
			while (rsSet.next()) {
				ParkingSpot pS = new ParkingSpot();
				pS.setNumberSpot(rsSet.getString("number"));
				pS.setStatusSpot(rsSet.getString("status"));
				pS.setTypeVehicle(rsSet.getString("typeVehicle"));
				pSpot.add(pS);
			}
			return pSpot;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ObservableList<ParkingSpot> getAllFreeParkingSpot(String typ) {
		String sql = "SELECT * FROM parkingspot WHERE typeVehicle = '" + typ + "' AND status = 'FREE' ";
		try {
			ResultSet rsSet = DBUtil.dbExecute(sql);
			ObservableList<ParkingSpot> pSpot = FXCollections.observableArrayList();
			while (rsSet.next()) {
				ParkingSpot pS = new ParkingSpot();
				pS.setNumberSpot(rsSet.getString("number"));
				pS.setStatusSpot(rsSet.getString("status"));
				pSpot.add(pS);
			}
			return pSpot;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void changeStatusSpot(String numberSpot) {
		String sql = "UPDATE parkingspot SET status = 'TAKEN' WHERE  number ='" + numberSpot + "' ";
		try {
			DBUtil.dbExcecuteQuery(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void changeStatusSpotExit(String numberSpot) {
		String sql = "UPDATE parkingspot SET status = 'FREE' WHERE  number ='" + numberSpot + "' ";
		try {
			DBUtil.dbExcecuteQuery(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean insertParkingSpot(String number, String typevehicle) {
		String sql = "INSERT INTO parkingspot (number, typevehicle) VALUES ('"+ number +"', '"+ typevehicle +"')";
		try {
			DBUtil.dbExcecuteQuery(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteParkingSpot(String numberSpot) {
		String sql = "DELETE FROM parkingspot WHERE number = '"+ numberSpot +"'";
		try {
			DBUtil.dbExcecuteQuery(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkNumberFree(String number) {
		String sql = "SELECT number FROM parkingspot WHERE number = '" + number + "' ";
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
