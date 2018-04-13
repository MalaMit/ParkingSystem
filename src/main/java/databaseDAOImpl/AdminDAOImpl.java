package databaseDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import databaseDAO.AdminDAO;
import databaseModel.Admin;
import databaseUtil.DBUtil;
import javafx.collections.ObservableList;

public class AdminDAOImpl implements AdminDAO {

	@Override
	public ObservableList<Admin> getAllAdmin() {
		// TODO Auto-generated method stub
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
	public boolean insertAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return false;
	}

}
