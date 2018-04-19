package databaseDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import databaseDAO.TypeVehicleDAO;
import databaseModel.TypeVehicle;
import databaseUtil.DBUtil;

public class TypeVehicleDAOImpl implements TypeVehicleDAO {

	@Override
	public int getPrice(String type) {
		String sql = "SELECT priceForHour FROM typevehicle where type = '" + type +"'";
		try {
			ResultSet rsSet = DBUtil.dbExecute(sql);
			int cost = 0;
			while (rsSet.next()) {
				cost = rsSet.getInt("priceForHour");
			}
			return cost;

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public ArrayList<TypeVehicle> getAllTypeVehicle() {
		String sql = "SELECT type FROM typevehicle";
		try {
			ResultSet rsSet = DBUtil.dbExecute(sql);
			ArrayList<TypeVehicle> tVehicle = new ArrayList<TypeVehicle>();
			while (rsSet.next()) {
				TypeVehicle tV = new TypeVehicle();
				tV.setType(rsSet.getString("type"));
				tVehicle.add(tV);
			}
			return tVehicle;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
