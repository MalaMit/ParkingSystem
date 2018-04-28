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
			while (rsSet.next()) {
				Admin adm = new Admin();
				adm.setAdminnID(rsSet.getInt("adminID"));
				adm.setLogin(rsSet.getString("login"));
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
		String sql = "SELECT login,password  FROM admin WHERE login = '" + imputLogin + "' AND password = '"
				+ imputPassword + "' ";
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
	public boolean updateAdminChangePassword(int id, String newPassword) {
		String sql = "UPDATE admin SET password ='" + newPassword + "' WHERE adminID='" + id + "' ";
		try {
			DBUtil.dbExcecuteQuery(sql);
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean insertAdmin(String login, String password, String firstName, String secondName) {
		String sql = "INSERT INTO admin (login, password, firstName, secondName)  VALUES ('" + login + "', '" + password
				+ "', '" + firstName + "', '" + secondName + "');";
		try {
			DBUtil.dbExcecuteQuery(sql);
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteAdmin(int id) {
		String sql = "DELETE FROM admin WHERE `adminID`='" + id + "'";
		try {
			DBUtil.dbExcecuteQuery(sql);
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean checkLoginIsExisting(String login) {
		String sql = "SELECT login FROM admin WHERE login = '" + login + "'";
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
