package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BaseDAO.*;
import Model.*;

public class GradeDAO extends BaseDAO{
	private static GradeDAO sd = null;
	private final int fieldNum = 3; 
	public static synchronized GradeDAO getInstance() {
		if (sd == null) {
			sd = new GradeDAO();
		}
		return sd;
	}
	
	public boolean add(Grade gde){
		boolean result=false;
		if(gde==null) {
			return result;
		}
		try {
			// insert
			String sql = "insert into grade(sno,cid,grade) values(?,?,?)";
			String[] param = { gde.getSno(),gde.getCid(),gde.getGrade() };
			if (db.executeUpdate(sql, param) == 1) {
				result = true;
			}
		} finally {
			destroy();
		}
		return result;
	}
	
	public boolean delete(Grade gde) {
		boolean result = false;
		if (gde == null) {
			return result;
		}
		String sql = "delete from grade where sno=? and cid=?";
		String[] param = { gde.getSno(),gde.getCid() };
		int rowCount = db.executeUpdate(sql, param);
		if (rowCount == 1) {
			result = true;
		}
		destroy();
		return result;
	}
	
	public boolean update(Grade gde) throws SQLException {
		boolean result = false;
		if (gde == null) {
			return result;
		}
		try {
			// update
			String sql = "update grade set grade=? where sno=? and cid=?";
			String[] param = { gde.getGrade(),gde.getSno(),gde.getCid() };
			int rowCount = db.executeUpdate(sql, param);
			if (rowCount == 1) {
				result = true;
			}
		} finally {
			destroy();
		}
		return result;
	}
	
	public String[][] quary() {
		String[][] result = null;
		List<Grade> gde = new ArrayList<Grade>();
		int i = 0;
		
		String sql = "select * from grade";
		
		rs = db.executeQuery(sql);
		try {
			while (rs.next()) {
				buildList(rs, gde, i);
				i++;
			}
			if (gde.size() > 0) {
				result = new String[gde.size()][fieldNum];
				for (int j = 0; j < gde.size(); j++) {
					buildResult(result, gde, j);
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}

		return result;
	}

	// 将rs记录添加到list中
	private void buildList(ResultSet rs, List<Grade> list, int i) throws SQLException {
		Grade gde = new Grade();
		gde.setCid(rs.getString("cid"));
		gde.setSno(rs.getString("sno"));
		gde.setGrade(rs.getString("grade"));
		list.add(gde);
	}

	// 将list中记录添加到二维数组中
	private void buildResult(String[][] result, List<Grade> gdee, int j) {
		Grade gde = gdee.get(j);
		result[j][0] = gde.getSno();
		result[j][1] = gde.getCid();
		result[j][2] = gde.getGrade();
	}
}