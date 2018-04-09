package databaseDAO;

import databaseModel.Admin;
import javafx.collections.ObservableList;


public interface AdminDAO {
	Admin getAdmin();
	ObservableList<Admin> getAllAdmin();
	Admin getAdminByLoginAndPassword();
	
    boolean insertAdmin();
    boolean updateAdmin();
    boolean deleteAdmin();
	
}
