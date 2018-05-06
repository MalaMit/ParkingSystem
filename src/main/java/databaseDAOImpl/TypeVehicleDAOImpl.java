package databaseDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import databaseDAO.TypeVehicleDAO;
import databaseModel.TypeVehicle;
import databaseUtil.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

	@Override
	public ObservableList<TypeVehicle> getPriceAndNumber() {
		String sql = "SELECT * FROM typevehicle";
		try {
			ResultSet rsSet = DBUtil.dbExecute(sql);
			ObservableList<TypeVehicle> typeVeh = FXCollections.observableArrayList();
			while (rsSet.next()) {
				TypeVehicle tV = new TypeVehicle();
				tV.setType(rsSet.getString("type"));
				tV.setPriceForHour(rsSet.getInt("priceForHour"));
				typeVeh.add(tV);
			}
			return typeVeh;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insertTypeVehicle(String typeVehicle, int priceForHour) {
		String sql = "INSERT INTO typevehicle (type, priceForHour) VALUES ('"+ typeVehicle +"', '"+ priceForHour +"')";
		try {
			DBUtil.dbExcecuteQuery(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updatePriceTypeVehicle(String typeVehicle, int priceForHour) {
		String sql = "UPDATE typevehicle SET priceForHour = '"+ priceForHour +"' WHERE  type ='" + typeVehicle + "' ";
		try {
			DBUtil.dbExcecuteQuery(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteTypeVehicle(String typeVehicle) {
		String sql = "DELETE FROM typevehicle WHERE type = '"+ typeVehicle +"'";
		try {
			DBUtil.dbExcecuteQuery(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean checkTypeVehicleIsNotUse(String typeVehicle) {
		String sql = "SELECT typeVehicle FROM parkingspot WHERE typeVehicle = '" + typeVehicle + "' ";
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
	public boolean checkTypeVehicleIsExist(String typeVehicle) {
		String sql = "SELECT type FROM typevehicle WHERE type = '" + typeVehicle + "' ";
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
