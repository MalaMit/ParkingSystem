package databaseDAOImpl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import databaseDAO.TypeVehicleDAO;
import databaseModel.TypeVehicle;
import databaseUtil.DBUtil;


public class TypeVehicleDAOImpl implements TypeVehicleDAO {

	@Override
	public TypeVehicle getTypeVehicle() {
		return null;
	}

	@Override
	public TypeVehicle getPrice() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<TypeVehicle> getAllTypeVehicle() {
		String sql = "SELECT type FROM typevehicle";
		try {
			ResultSet rsSet = DBUtil.dbExecute(sql);
			ArrayList <TypeVehicle> tVehicle = new ArrayList<TypeVehicle>();
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
