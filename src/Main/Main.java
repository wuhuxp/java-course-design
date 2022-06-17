package Main;

import Database.*;
import Windows.*;

public class Main {
	public static void initDB() {
		DBUtil dbUtil = DBUtil.getDBUtil();

		
		if (dbUtil.exeute("select 0 from admin")) {
			return;
		}

		
		dbUtil.exeute("create table if not exists admin"+
				"(id int primary key," +
				"name varchar(32)," +
				"username varchar(32)," +
				"password varchar(32))");
		dbUtil.exeute("insert into admin(id, name, username, password) "+
				 "values(0, 'root', '123', '123')");

		//BC·¶Ê½
		dbUtil.exeute("create table if not exists student"+
				"(sno varchar(16) primary key," +
				"name varchar(32) not null," +
				"sex varchar(8) not null," +
				"birth varchar(10))");
		
		dbUtil.exeute("create table if not exists course"+
				"(cid varchar(16) primary key,"+
				"cname varchar(16))");
		
		dbUtil.exeute("create table if not exists grade"+
				"(sno varchar(16),"+
				"cid varchar(16),"+
				"grade varchar(16),"+
				"primary key(cid,sno),"+
				"foreign key(sno) references student(sno),"+
				"foreign key(cid) references course(cid))");
				
	}
	public static void main(String[] args) {
		initDB();
		new Loginv2();
		DBUtil.getDBUtil().close();
	}
}
