package beans;

public class Package {
	private String package_name;
	private int price;
	private String description;
	private int connected_object;
	private int meal;;
	private int total_price;
	
	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getConnected_object() {
		return connected_object;
	}
	public void setConnected_object(int connected_object) {
		this.connected_object = connected_object;
	}
	public int getMeal() {
		return meal;
	}
	public void setMeal(int meal) {
		this.meal = meal;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	

}
