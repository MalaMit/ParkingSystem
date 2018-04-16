package databaseDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import databaseDAO.ParkingSpotDAO;
import databaseModel.ParkingSpot;
import databaseModel.TypeVehicle;
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
				TypeVehicle tV = new TypeVehicle();
				pS.setParkingSpotID(rsSet.getInt("parkingSpot_ID"));
				pS.setNumberSpot(rsSet.getString("number"));
				pS.setStatusSpot(rsSet.getString("status"));
				tV.setType(rsSet.getString("typeVehicle"));
				pS.setTypeVehicle(tV);
				pSpot.add(pS);
			}
			return pSpot;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insertParkingSpot(ParkingSpot parkingSpot) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateParkingSpot(ParkingSpot parkingSpot) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteParkingSpot(ParkingSpot parkingSpot) {
		// TODO Auto-generated method stub
		return false;
	}



	
	
}
