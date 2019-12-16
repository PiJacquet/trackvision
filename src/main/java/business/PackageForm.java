package business;

import java.sql.Connection;
import java.sql.PreparedStatement;

import beans.Package;
import common.Configuration;

public class PackageForm {
	
	Package Package;
	
	public PackageForm(Package Package) {
		this.Package=Package;
	}
	
	
	public boolean executeChoice() {
		Connection connection = Configuration.connectionPool.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("insert into Package(package_name,price, description, connected_object, meal, total_price) VALUES (?,?,?,?,?,?);");
			stmt.setString(1, Package.getPackage_name());
			stmt.setInt(2, Package.getPrice());
			stmt.setString(3, Package.getDescription());
			stmt.setInt(4, Package.getConnected_object());
			stmt.setInt(5, Package.getMeal());
			stmt.setInt(6, Package.getTotal_price());
			stmt.executeUpdate();
			System.out.println("cool");
			Configuration.connectionPool.closeConnection(connection);	
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Configuration.connectionPool.closeConnection(connection);	
			return false;
		}
		return true;
	}
	// test
	

}
