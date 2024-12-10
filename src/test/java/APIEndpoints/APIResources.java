package APIEndpoints;

public enum APIResources {
	
	getTokenAPI("/auth"),
	addBookingAPI("/booking"),
	getBookingAPI("booking/"),
	updateBookingAPI("booking/"),
	updatePatchBookingAPI("booking/"),
	deleteBookingAPI("booking/");
	
	private String resources;
	
	
	APIResources(String resources)
	{
		this.resources=resources;
	}
	
	public String getResources()
	{
		return resources;
	}
	
}
