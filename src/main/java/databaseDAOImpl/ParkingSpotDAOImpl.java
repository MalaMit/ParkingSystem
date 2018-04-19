package databaseDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import databaseDAO.ParkingSpotDAO;
import databaseModel.ParkingSpot;
import databaseUtil.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ParkingSpotDAOImpl implements ParkingSpotDAO{

	@Override
	public ObservableList<ParkingSpot> getAllParkingSpot(String typ) {
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
	public void changeStatusSpot( String numberSpot) {
		String sql = "UPDATE parkingspot SET status = 'TAKEN' WHERE  number ='" +numberSpot+ "' ";
		try {
			DBUtil.dbExcecuteQuery(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean insertParkingSpot() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateParkingSpot() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteParkingSpot() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
