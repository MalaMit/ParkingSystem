package databaseDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import databaseDAO.ClientDAO;
import databaseModel.Client;
import databaseUtil.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClientDAOImpl implements ClientDAO {

	@Override
	public ObservableList<Client> getAllClient() {
		String sql = "SELECT * FROM client";
		try {
			ResultSet rsSet = DBUtil.dbExecute(sql);
			ObservableList<Client> clientList = FXCollections.observableArrayList();
			while(rsSet.next()) {
				Client cl = new Client();
				cl.setLicensePlate(rsSet.getString("LicensePlate"));
				cl.setFirstName(rsSet.getString("FirstName"));
				cl.setSecondName(rsSet.getString("SecondName"));
				cl.setPhoneNumber(rsSet.getString("PhoneNumber"));
				clientList.add(cl);
			}
			return clientList;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insertClient(String licensePlate, String firstName, String secondName, String phoneNumber) {
		String sql = "INSERT INTO client (licensePlate, firstName, secondName, phoneNumber) VALUES ('" + licensePlate
				+ "', '" + firstName + "', '" + secondName + "', '" + phoneNumber + "')";
		try {
			DBUtil.dbExcecuteQuery(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkLicensePlate(String licensePlate) {
		String sql = "SELECT licensePlate FROM client WHERE LicensePlate = '" + licensePlate + "' ";
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
