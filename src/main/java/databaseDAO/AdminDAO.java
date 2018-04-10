package databaseDAO;

import databaseModel.Admin;
import javafx.collections.ObservableList;


public interface AdminDAO {
	Admin getAdmin();
	ObservableList<Admin> getAllAdmin();
	boolean getAdminByLoginAndPassword(String login, String password);
	
    boolean insertAdmin();
    boolean updateAdmin();
    boolean deleteAdmin();
	
}
