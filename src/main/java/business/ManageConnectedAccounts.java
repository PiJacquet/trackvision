package business;

import java.util.HashMap;

public class ManageConnectedAccounts {
	
	private HashMap<String,ConnectAccount> connectedAccounts;
	
	public ManageConnectedAccounts() {
		connectedAccounts = new HashMap<String,ConnectAccount>();
	}
	
	public String connectAccount(ConnectAccount account) {
		String hash = "";
		do {
			hash = Long.toHexString(Double.doubleToLongBits(Math.random()));
		}while(connectedAccounts.containsKey(hash));
		connectedAccounts.put(hash, account);
		return hash;
	}
	
	public void disconnectAccount(String hash) {
		connectedAccounts.remove(hash);
	}
	
	public boolean isConnected(String hash) {
		return connectedAccounts.containsKey(hash);
	}
	
	public ConnectAccount getAccount(String hash) {
		return connectedAccounts.get(hash);
	}
	
}
