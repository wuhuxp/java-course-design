package Constants;

public class Constants {
	    // jdbc
		public static final String JDBC_URL = "jdbc:sqlite:Student.db";
		public static final String JDBC_USERNAME = "123";
		public static final String JDBC_PASSWORD = "123";
		public static final String JDBC_DRIVER = "org.sqlite.JDBC";
		
		// student field
		public static final String STUDENT_WINDOW="STUDENT_WINDOW";
		public static final String STUDENT_SNO = "sno";
		public static final String STUDENT_NAME = "name";
		public static final String STUDENT_SEX = "sex";
		public static final String STUDENT_BIRTH = "birth";
		
		// course field
		public static final String COURSE_WINDOW="COURSE_WINDOW";
		public static final String COURSE_CID="id of course";
		public static final String COURSE_CNAME="cname";
		
		// grade field
		public static final String GRADE_WINDOW="GRADE_WINDOW";
		public static final String GRADE_SNO="sno";
		public static final String GRADE_CID="id of course";
		public static final String GRADE_GRADE="grade";

		public static final String LOGIN_TITLE = "LOGIN";
		public static final String LOGIN_USERNAME = "username";
		public static final String LOGIN_PASSWORD = "password";
		public static final String LOGIN = "login";
		public static final String RESET = "reset";
		
		public static final String MAINVIEW_TITLE = "Student_master";
		public static final String MAINVIEW_FIND_JLABEL = "result";
		public static final String PARAM_FIND_CONDITION = "";
		public static final String PARAM_FINDBYNAME = "find by name";
		public static final String PARAM_FINDBYSNO = "find by sno";
		public static final String PARAM_ADD = "add";
		public static final String PARAM_DELETE = "delete";
		public static final String PARAM_UPDATE = "update";
		public static final String PARAM_EXPORT="export";
		// add view
		public static final String ADDVIEW_TITLE = "add_stu_info";
		public static final String ADDVIEW_ADDBUTTON = "add";
		public static final String EXITBUTTON = "exit";

		// delete view
		public static final String DELETEVIEW_TITLE = "delete_stu_info";
		public static final String DELETEVIEW_DELETEBUTTON = "delete";

		// update view
		public static final String UPDATEVIEW_TITLE = "update_stu_info";
		public static final String UPDATEVIEW_UPDATEBUTTON = "update";
		
		public static final String MAINWINDOW_TITLE="mainpage";
		public static final String MAINWINDOW_stuBUTTON="stu_info";
		public static final String MAINWINDOW_cosBUTTON="course_info";
		public static final String MAINWINDOW_gdeBUTTON="grade_info";
		
		public static final String SEARCHWINDOW_TITLE="result";

}
