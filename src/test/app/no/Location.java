package test.app.no;

public class Location {

	private String locationName;
	private int locationId;
	private String locationStatus;

	public Location(String locationName, int locationId) {
		this.locationName = locationName;
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public int getLocationId() {
		return locationId;
	}

	public String getLocationStatus() {
		return locationStatus;
	}

	public void setLocationStatus(String locationStatus) {
		this.locationStatus = locationStatus;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	
}