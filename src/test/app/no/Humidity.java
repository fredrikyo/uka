package test.app.no;

public class Humidity {

	private int humidityId;
	private int locationId;
	private int value;
	private String date;
	private String time;

	public Humidity(int humidityId, int locationId, int value, String date, String time) {
		this.setHumidityId(humidityId);
		this.setLocationId(locationId);
		this.setValue(value);
		this.setDate(date);
		this.setTime(time);
	}

	public void setHumidityId(int humidityId) {
		this.humidityId = humidityId;
	}

	public int getHumidityId() {
		return humidityId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTime() {
		return time;
	}



	
}