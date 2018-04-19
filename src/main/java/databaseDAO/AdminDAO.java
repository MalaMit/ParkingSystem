package databaseDAO;

import databaseModel.Admin;
import javafx.collections.ObservableList;


public interface AdminDAO {
	
	ObservableList<Admin> getAllAdmin();
	boolean getLoginAdminByLoginAndPassword(String login, String password);

    boolean insertAdmin();
    boolean updateAdmin();
    boolean deleteAdmin();
}
