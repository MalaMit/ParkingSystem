package databaseDAO;

import databaseModel.Admin;
import javafx.collections.ObservableList;


public interface AdminDAO {
	
	ObservableList<Admin> getAllAdmin();
	boolean getLoginAdminByLoginAndPassword(String login, String password);
	boolean checkLoginIsExisting(String login);
	
    boolean updateAdminChangePassword(int id, String newPassword);
    boolean insertAdmin(String login, String password, String firstName, String secondName);
    boolean deleteAdmin(int id);
}
