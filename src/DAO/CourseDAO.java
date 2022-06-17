package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BaseDAO.*;
import Model.*;

public class CourseDAO extends BaseDAO {
	private static final int fieldNum = 2;
	private static CourseDAO cd = null;

	public static synchronized CourseDAO getInstance() {
		if (cd == null) {
			cd = new CourseDAO();
		}
		return cd;
	}
	
	public boolean add(Course cos){
		boolean result=false;
		if(cos==null) {
			return result;
		}
		try {
			// insert
			String sql = "insert into course(cid,cname) values(?,?)";
			String[] param = { cos.getCid(),cos.getCname() };
			if (db.executeUpdate(sql, param) == 1) {
				result = true;
			}
		} finally {
			destroy();
		}
		return result;
	}
	
	public boolean delete(Course cos) {
		boolean result = false;
		if (cos == null) {
			return result;
		}
		String sql = "delete from course where cid=? ";
		String[] param = { cos.getCid() };
		int rowCount = db.executeUpdate(sql, param);
		if (rowCount == 1) {
			result = true;
		}
		destroy();
		return result;
	}
	
	public boolean update(Course cos) throws SQLException {
		boolean result = false;
		if (cos == null) {
			return result;
		}
		try {
			// update
			String sql = "update course set cname=? where cid=?";
			String[] param = { cos.getCname(),cos.getCid() };
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
		List<Course> cos = new ArrayList<>();
		int i = 0;
		
		String sql = "select * from course";
		
		rs = db.executeQuery(sql);
		try {
			while (rs.next()) {
				buildList(rs, cos, i);
				i++;
			}
			if (cos.size() > 0) {
				result = new String[cos.size()][fieldNum];
				for (int j = 0; j < cos.size(); j++) {
					buildResult(result, cos, j);
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
	private void buildList(ResultSet rs, List<Course> list, int i) throws SQLException {
		Course cos=new Course();
		cos.setCid(rs.getString("cid"));
		cos.setCname(rs.getString("cname"));
		list.add(cos);
	}

	// 将list中记录添加到二维数组中
	private void buildResult(String[][] result, List<Course> coss, int j) {
		Course cos = coss.get(j);
		result[j][0] = cos.getCid();
		result[j][1] = cos.getCname();
	}
}
