package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ConnectedObjects {

	private Integer id;
	private String type;
	private Integer state;
	private Integer idAppart;
	private String macAdress;
	
public ConnectedObjects(ResultSet result) throws SQLException {
	id = result.getInt(1);
	type = result.getString(2);
	state = result.getInt(3);
	idAppart = result.getInt(4);
	macAdress = result.getString(5); 	
}

}
