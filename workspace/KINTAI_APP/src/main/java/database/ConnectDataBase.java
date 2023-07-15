package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDataBase {
	
	/*
	 * MySQLの接続
	 */
	public static Connection connectDB() {
		Connection connection = null;
		
		// JDMC接続先情報
	    String JDBC_CONNECTION = "jdbc:mysql://localhost/kintai_db";
	    
	    // ユーザー名
	    String USER = "root";
	    
	    // パスワード
	    String PASS = "test1234";
	    
	    try {
			connection = DriverManager.getConnection(JDBC_CONNECTION, USER, PASS);
		} catch (SQLException e) {
			System.out.println("MySQLへの接続に失敗しました。");
		}
	    
	    return connection;
	}
}
