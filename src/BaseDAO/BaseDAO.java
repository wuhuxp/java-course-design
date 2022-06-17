package BaseDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import Constants.*;
import DAO.*;
import Database.*;

public abstract class BaseDAO {
	protected final DBUtil db = DBUtil.getDBUtil();
	protected ResultSet rs;
	private static BaseDAO baseDAO;

	public BaseDAO() {
		init();
	}

	private void init() {
		// buildAbilityDAO();
	}

	// protected abstract void buildAbilityDAO();

	public static synchronized BaseDAO getAbilityDAO(DAO dao) {
		switch (dao) {
		case AdminDAO:
			if (baseDAO == null || baseDAO.getClass() != AdminDAO.class) {
				baseDAO = AdminDAO.getInstance();
			}
			break;
		case StudentDAO:
			if (baseDAO == null || baseDAO.getClass() != StudentDAO.class) {
				baseDAO = StudentDAO.getInstance();
			}
			break;
		case CourseDAO:
			if (baseDAO == null || baseDAO.getClass() != CourseDAO.class) {
				baseDAO = CourseDAO.getInstance();
			}
			break;
		case GradeDAO:
			if (baseDAO == null || baseDAO.getClass() != GradeDAO.class) {
				baseDAO = GradeDAO.getInstance();
			}
			break;
		default:
			break;
		}
		return baseDAO;
	}

	protected void destroy() {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
}
