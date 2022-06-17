package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import BaseDAO.*;
import Model.*;

public class StudentDAO extends BaseDAO {
	private final int fieldNum = 4; 
	private static StudentDAO sd = null;

	public static synchronized StudentDAO getInstance() {
		if (sd == null) {
			sd = new StudentDAO();
		}
		return sd;
	}

	// update
	public boolean update(Student stu) throws SQLException {
		boolean result = false;
		if (stu == null) {
			return result;
		}
		try {
			// update
			String sql = "update student set sex=?,birth=? where name=? and sno=?";
			String[] param = { stu.getSex(),stu.getBirth(),stu.getName(),stu.getSno() };
			int rowCount = db.executeUpdate(sql, param);
			if (rowCount == 1) {
				result = true;
			}
		} finally {
			destroy();
		}
		return result;
	}

	// delete
	public boolean delete(Student stu) {
		boolean result = false;
		if (stu == null) {
			return result;
		}
		String sql = "delete from student where sno=?";
		String[] param = { stu.getSno() };
		int rowCount = db.executeUpdate(sql, param);
		if (rowCount == 1) {
			result = true;
		}
		destroy();
		return result;
	}

	// add
	public boolean add(Student stu) throws SQLException {
		boolean result = false;
		if (stu == null) {
			return result;
		}
		try {
			
			// insert
			String sql = "insert into student(sno,name,sex,birth) values(?,?,?,?)";
			String[] param = { "2022"+randomsno(8),stu.getName(),stu.getSex(),stu.getBirth() };
			if (db.executeUpdate(sql, param) == 1) {
				result = true;
			}
		} finally {
			destroy();
		}
		return result;
	}
	
	public static String randomsno(int factor){
		String characters = "0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < factor; i++) {
            sb.append(characters.charAt(random.nextInt(10)));
        }
        return sb.toString();
    }

	// query
	public String[][] quary() {
		String[][] result = null;
		List<Student> stus = new ArrayList<>();
		int i = 0;
		
		String sql = "select * from student";
		
		rs = db.executeQuery(sql);
		try {
			while (rs.next()) {
				buildList(rs, stus, i);
				i++;
			}
			if (stus.size() > 0) {
				result = new String[stus.size()][fieldNum];
				for (int j = 0; j < stus.size(); j++) {
					buildResult(result, stus, j);
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}

		return result;
	}
	
	public String[][] quaryname(String name) {
		String[][] result = null;
		List<Student> stus = new ArrayList<>();
		int i = 0;
		
		String sql = "select * from student where name like ?";
		String param[]= {name};
		rs = db.executeQuery(sql,param);
		try {
			while (rs.next()) {
				buildList(rs, stus, i);
				i++;
			}
			if (stus.size() > 0) {
				result = new String[stus.size()][fieldNum];
				for (int j = 0; j < stus.size(); j++) {
					buildResult(result, stus, j);
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			destroy();
		}

		return result;
	}
	
	public String[][] quarysno(String sno) {
		String[][] result = null;
		List<Student> stus = new ArrayList<>();
		int i = 0;
		
		String sql = "select * from student where sno like ?";
		String param[]= {sno};
		rs = db.executeQuery(sql,param);
		try {
			while (rs.next()) {
				buildList(rs, stus,i);
				i++;
			}
			if (stus.size() > 0) {
				result = new String[stus.size()][fieldNum];
				for (int j = 0; j < stus.size(); j++) {
					buildResult(result, stus, j);
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
	private void buildList(ResultSet rs, List<Student> list,int i) throws SQLException {
		Student stu = new Student();
		stu.setName(rs.getString("name"));
		stu.setSex(rs.getString("sex"));
		stu.setSno(rs.getString("sno"));
		stu.setBirth(rs.getString("birth"));
		list.add(stu);
	}

	// 将list中记录添加到二维数组中
	private void buildResult(String[][] result, List<Student> stus, int j) {
		Student stu = stus.get(j);
		result[j][0] = stu.getSno();
		result[j][1] = stu.getName();
		result[j][2] = stu.getSex();
		result[j][3] = stu.getBirth();
	}
	
	// searchbyname
		public String[][] searchbyname(String name) {
			String[][] result = null;
			List<Result> lres = new ArrayList<>();
			int i = 0;
			
			String sql = "select name,student.sno,course.cname,grade from student,course,grade where student.sno=grade.sno and grade.cid=course.cid and name like ?";
			String[] param= {name};
			rs = db.executeQuery(sql,param);
			try {
				while (rs.next()) {
					BuildList(rs, lres, i);
					i++;
				}
				if (lres.size() > 0) {
					result = new String[lres.size()][4];
					for (int j = 0; j < lres.size(); j++) {
						BuildResult(result, lres, j);
					}
				}
			} catch (SQLException se) {
				se.printStackTrace();
			} finally {
				destroy();
			}

			return result;
		}

		// searchbysno
		public String[][] searchbysno(String sno) {
				String[][] result = null;
				List<Result> lres = new ArrayList<>();
				int i = 0;
					
				String sql = "select name,student.sno,course.cname,grade from student,course,grade where student.sno=grade.sno and grade.cid=course.cid and student.sno like ?";
				String[] param= {sno};
				rs = db.executeQuery(sql,param);
				try {
					while (rs.next()) {
						BuildList(rs, lres, i);
						i++;
					}
					if (lres.size() > 0) {
						result = new String[lres.size()][4];
						for (int j = 0; j < lres.size(); j++) {
							BuildResult(result, lres, j);
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
		private void BuildList(ResultSet rs, List<Result> list, int i) throws SQLException {
			Result res = new Result();
			res.setName(rs.getString("name"));
			res.setSno(rs.getString("sno"));
			res.setCname(rs.getString("cname"));
			res.setGrade(rs.getString("grade"));
			list.add(res);
		}

		// 将list中记录添加到二维数组中
		private void BuildResult(String[][] result, List<Result> ress, int j) {
			Result res = ress.get(j);
			result[j][0] = res.getName();
			result[j][1] = res.getSno();
			result[j][2] = res.getCname();
			result[j][3] = res.getGrade();
		}
		
		//exportsearch
		public String[][] search() {
			String[][] result = null;
			List<Result2> lres = new ArrayList<>();
			int i = 0;
				
			String sql = "select name,cname,grade from student,course,grade where student.sno=grade.sno and grade.cid=course.cid";
			rs = db.executeQuery(sql);
			try {
				while (rs.next()) {
					BuildList2(rs, lres, i);
					i++;
				}
				if (lres.size() > 0) {
					result = new String[lres.size()][3];
					for (int j = 0; j < lres.size(); j++) {
						BuildResult2(result, lres, j);
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
				private void BuildList2(ResultSet rs, List<Result2> list, int i) throws SQLException {
					Result2 res = new Result2();
					res.setName(rs.getString("name"));
					res.setCname(rs.getString("cname"));
					res.setGrade(rs.getString("grade"));
					list.add(res);
				}

				// 将list中记录添加到二维数组中
				private void BuildResult2(String[][] result, List<Result2> ress, int j) {
					Result2 res = ress.get(j);
					result[j][0] = res.getName();
					result[j][1] = res.getCname();
					result[j][2] = res.getGrade();
				}
}
