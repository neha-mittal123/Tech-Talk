package Dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Db_Utils {

	public static Connection getConnection(String drvr, String url, String nm,
			String pass) throws Exception {
		Class.forName(drvr);
		System.out.println(url);
		System.out.println(nm);
		System.out.println(pass);
		return DriverManager.getConnection(url, nm, pass);

	}
}
