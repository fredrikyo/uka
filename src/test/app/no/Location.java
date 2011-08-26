package test.app.no;

public class Location {

	private int locId;
	private String locName;
	
	private String temperature;
	private String humidity;
	private String noise;
	
	private int partyFactor;
	
	private String value;
	private String average;
	
	public Location(int locId, String locName, String temperature, String humidity,
			String noise, String value, String average) {
		super();
		this.locId = locId;
		this.locName = locName;
		this.temperature = temperature;
		this.humidity = humidity;
		this.noise = noise;
		this.value = value;
		this.average = average;
	}
	public Location(int locId, String locName) {
		super();
		this.locId = locId;
		this.locName = locName;
	}
	
	public void setPartyFactor(){
		partyFactor = (int) Math.ceil(Math.random() * 100);
	}
	
	public int getPartyFactor() {
		return partyFactor;
	}

	public String getTemperature() {
		return temperature;
	}
	public String getHumidity() {
		return humidity;
	}
	public String getNoise() {
		return noise;
	}
	public int getLocId() {
		return locId;
	}

	public void setLocId(int locId) {
		this.locId = locId;
	}

	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}

	public String getValue() {
		return value;
	}

	public String getAverage() {
		return average;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public void setNoise(String noise) {
		this.noise = noise;
	}

	
}