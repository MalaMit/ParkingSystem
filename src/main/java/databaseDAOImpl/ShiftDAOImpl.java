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
		String sql = "SELECT * FROM shift WHERE pesel = '"+ pesel +"'";
		try {
			ResultSet rsSet = DBUtil.dbExecute(sql);
			ObservableList<Shift> shiftList = FXCollections.observableArrayList();
			while(rsSet.next()) {
				Shift shift = new Shift();
				shift.setShiftID(rsSet.getInt("idShift"));
				shift.setShiftStart(rsSet.getString("shiftStart").substring(0, (rsSet.getString("shiftStart").indexOf("."))));							
				if(!rsSet.getTimestamp("shiftStart").equals(rsSet.getTimestamp("shiftEnd")) && (rsSet.getTimestamp("shiftEnd") != null))
					shift.setShiftEnd(rsSet.getString("shiftEnd").substring(0, (rsSet.getString("shiftEnd").indexOf("."))));
				else
					shift.setShiftEnd("---------");
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

	@Override
	public boolean insertStartWorkTime(Long pesel) {
		String sql = "INSERT INTO shift (pesel, shiftType)  VALUES ('"+ pesel +"', 'working')";
		try {
			DBUtil.dbExcecuteQuery(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean insertEndWorkTime(Long pesel) {
		String sql ="UPDATE shift SET shiftEnd = NOW()  where idShift = (select max(tmptable.idShift) FROM (select idShift from shift where pesel = '"+ pesel +"' AND shiftEnd IS NULL ) as tmptable)";
		try {
			DBUtil.dbExcecuteQuery(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
