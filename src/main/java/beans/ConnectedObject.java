package beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectedObject {
	
	private Integer id;
	private String type;
	private Boolean state;
	private Integer apartmentId;
	private String macAddress;
	
	public ConnectedObject (ResultSet result) throws SQLException{
		id = result.getInt(1);
		type = result.getString(2);
		state = result.getBoolean(3);
		apartmentId = result.getInt(4);
		macAddress = result.getString(5);
	}

	public String getTableLine() {
		String line = "<tr>\n";
		line+="<td>" + id + "</td>";
		line+="<td>" + type + "</td>";
		line+="<td>" + ( (state)? "On":"Off") + "</td>";
		line+="<td>" + macAddress + "</td>";
		line+= "</tr>\n";
		return line;
	}

}
