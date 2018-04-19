package databaseDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

import databaseDAO.ClientDAO;
import databaseModel.Client;
import databaseModel.TypeVehicle;
import databaseUtil.DBUtil;
import javafx.collections.ObservableList;

public class ClientDAOImpl implements ClientDAO {

	@Override
	public ArrayList<TypeVehicle> getClientForLicensePlate(String licensePlate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObservableList<Client> getAllClient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertClient(String licensePlate, String firstName, String secondName, String phoneNumber) {
		String sql = "INSERT INTO client (licensePlate, firstName, secondName, phoneNumber) VALUES ('"+licensePlate+"', '"+firstName+"', '"+secondName+"', '"+phoneNumber+"')";
		try {
			DBUtil.dbExcecuteQuery(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateClient() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteClient() {
		// TODO Auto-generated method stub
		return false;
	}

}
