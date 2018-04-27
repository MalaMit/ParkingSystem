package databaseDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import databaseDAO.AdminDAO;
import databaseModel.Admin;
import databaseUtil.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdminDAOImpl implements AdminDAO {

	@Override
	public ObservableList<Admin> getAllAdmin() {
		String sql = "SELECT * FROM admin";
		try {
			ResultSet rsSet = DBUtil.dbExecute(sql);
			ObservableList<Admin> adminList = FXCollections.observableArrayList();
			while(rsSet.next()) {
				Admin adm = new Admin();
				adm.setAdminnID(rsSet.getInt("adminID"));
				adm.setLogin(rsSet.getString("login"));
				adm.setPassword(rsSet.getString("password"));
				adm.setFirstName(rsSet.getString("firstName"));
				adm.setSecondName(rsSet.getString("secondName"));
				adminList.add(adm);
			}
			return adminList;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean getLoginAdminByLoginAndPassword(String imputLogin, String imputPassword) {
		String sql = "SELECT * FROM admin WHERE login = '" + imputLogin + "' AND password = '" + imputPassword + "' ";
		boolean isUser = false;
		try {
			ResultSet rs = DBUtil.dbExecute(sql);
			if (rs.next()) {
				isUser = true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return isUser;
	}

	@Override
	public boolean insertAdmin() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAdmin() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAdmin() {
		// TODO Auto-generated method stub
		return false;
	}
}
