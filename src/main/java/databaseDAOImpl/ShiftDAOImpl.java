package databaseDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import databaseDAO.ShiftDAO;
import databaseModel.Shift;
import databaseUtil.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ShiftDAOImpl implements ShiftDAO{

	@Override
	public ObservableList<Shift> getAllForEmployee(String pesel) {
		String sql = "SELECT * FROM shift";
		try {
			ResultSet rsSet = DBUtil.dbExecute(sql);
			ObservableList<Shift> shiftList = FXCollections.observableArrayList();
			while(rsSet.next()) {
				Shift shift = new Shift();
				shift.setShiftID(rsSet.getInt("idShift"));
				shift.setShiftStart(rsSet.getString("shiftStart").substring(0, (rsSet.getString("shiftStart").indexOf("."))));
				shift.setShiftEnd(rsSet.getString("shiftEnd").substring(0, (rsSet.getString("shiftEnd").indexOf("."))));
				shift.setPesel(rsSet.getLong("pesel"));
				shift.setShiftType(rsSet.getString("shiftType"));
				shiftList.add(shift);
			}
			return shiftList;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insertNewWorkTime(String shiftStart, String shiftEnd, Long pesel, String shiftType) {
		String sql = "INSERT INTO shift (shiftStart, shiftEnd, pesel, shiftType)  VALUES ('"+ shiftStart +"', '"+ shiftEnd +"', '"+ pesel +"', '"+ shiftType +"')";
		try {
			DBUtil.dbExcecuteQuery(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
