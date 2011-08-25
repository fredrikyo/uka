package test.app.no;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import test.app.no.Humidity;
import test.app.no.Location;

import test.app.no.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class UkaActivity extends ListActivity {
	/** Called when the activity is first created. */
	private ListView list;
	private ArrayAdapter<CharSequence> adapter;
	private Location[] locations =  null;
	private Humidity[] humidityValues =  null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		locations = getAllLocations();
		
		if (locations != null){
			Toast.makeText(this, "Locations fetched", Toast.LENGTH_LONG).show();
			populateList(locations);
		}
	}
	public void populateList(Location[] locations) {
		adapter = new ArrayAdapter<CharSequence>(this, R.layout.list_item, R.id.text);
//		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


		for (Location loc : locations) {
			adapter.add(loc.getLocationName());
		}

		setListAdapter(adapter);
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);
		lv.setOnItemClickListener(new MyOnItemClickListener());
		
//		ImageView indicator = (ImageView) findViewById(R.id.icon);
//		Resources res = getResources();
//		Drawable myImage = res.getDrawable(R.drawable.high);
//		indicator.setImageDrawable(myImage);
	}

	public class MyOnItemClickListener implements OnItemClickListener {

		public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

			humidityValues = getHumidity(locations[pos].getLocationId());
			if (humidityValues != null) {
				String text = "Humidity at " + locations[pos].getLocationName() + ": " + humidityValues[0].getValue();
				Toast.makeText(parent.getContext(), text, Toast.LENGTH_LONG).show();
			} else {
				String text = "Humidityvalues is null!";
				Toast.makeText(parent.getContext(), text, Toast.LENGTH_LONG).show();
				
			}
		}
	}

	public InputStream retrieveStream(String url) {

		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");

		try {
			HttpResponse getResponse = client.execute(request);
			final int statusCode = getResponse.getStatusLine().getStatusCode();

			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}

			HttpEntity getResponseEntity = getResponse.getEntity();
			return getResponseEntity.getContent();

		} catch (IOException e) {
			request.abort();
		}
		return null;
	}

	public Location[] getAllLocations() {
		Location[] locations = null;
		String url = "http://findmyapp.net/findmyapp/locations";
		InputStream source = retrieveStream(url);

		if (source != null) {

			Gson gson = new Gson();
			Reader reader = new InputStreamReader(source);

			JsonParser parser = new JsonParser();
			JsonElement jsonElement = parser.parse(reader);
			locations = gson.fromJson(jsonElement, Location[].class);
		}

		return locations;

	}

	public Humidity[] getHumidity(int locationId) {
		Humidity[] humidityValues = null;
		String url = "http://findmyapp.net/findmyapp/locations/" + locationId + "/humidity/latest";
		InputStream source = retrieveStream(url);
		
		if (source != null) {
			
			Gson gson = new Gson();
			Reader reader = new InputStreamReader(source);
			
			JsonParser parser = new JsonParser();
			JsonElement jsonElement = parser.parse(reader);
			humidityValues = gson.fromJson(jsonElement, Humidity[].class);
			
		}
		
		return humidityValues;
		
	}
	
	public int getTemp(int locationId){
		int temp = 10;
		String text = "";
		String url = "http://findmyapp.net/findmyapp/locations/" + locationId + "/temperature/latest";
		InputStream source = retrieveStream(url);
		
		if (source != null) {

			Gson gson = new Gson();
			Reader reader = new InputStreamReader(source);

			JsonParser parser = new JsonParser();
			JsonElement jsonElement = parser.parse(reader);

//			text = array.toString();
			text = locations[1].getLocationName();
			Toast.makeText(this,text, Toast.LENGTH_LONG).show();
//			temp = jsonElement.getAsInt();
		}
		
		return temp;
	}
	
	public int getUserCount(int locationId){
		int count = 10;
		String url = "http://findmyapp.net/findmyapp/locations/" + locationId + "/users/count";
		InputStream source = retrieveStream(url);

		if (source != null){
			Reader reader = new InputStreamReader(source);
			reader.toString();

			JsonParser parser = new JsonParser();
			JsonElement jsonElement = parser.parse(reader);
			count = jsonElement.getAsInt();
		}
		return count;
	}
}