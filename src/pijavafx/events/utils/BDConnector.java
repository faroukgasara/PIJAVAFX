/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pijavafx.events.utils;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author farou
 */
public class BDConnector {
	static Connection conx = null;
	static ResultSet rs = null;
	static Statement stmt = null;

	public static void main(String[] args) {
		conx = driverBD();
	}

	public static Connection driverBD() {
		try {
                        
			Class.forName("com.mysql.jdbc.Driver");  
			String url = "jdbc:mysql://localhost:3306/pi";  
			Connection conx = DriverManager.getConnection(url, "root", "");System.out.println("Hello World!");
			return conx;
		} catch (Exception e) {
			System.out.println("Erreur lors du chargement du pilote :" + e.getMessage());
			return null;

		}
	}
}
