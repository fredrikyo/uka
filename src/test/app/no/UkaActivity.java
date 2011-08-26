package test.app.no;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import test.app.no.Location;

import test.app.no.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
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
	protected static Location[] locations =  null;
	protected static int locPos = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		generateLocations();
		
		if (locations != null){
			Toast.makeText(this, "Data innhentet", Toast.LENGTH_LONG).show();
			populateList(locations);
		}
	}
	public void populateList(Location[] locations) {
		adapter = new ArrayAdapter<CharSequence>(this, R.layout.list_item, R.id.text);
//		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


		for (Location loc : locations) {
			adapter.add(loc.getLocName());
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

//			String text = "Går til " + locations[pos].getLocName();
//			Toast.makeText(parent.getContext(), text, Toast.LENGTH_LONG).show();
			locPos = pos;
			
			Intent myIntent = new Intent(view.getContext(), LocActivity.class);
            startActivityForResult(myIntent, 0);
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
	
	public void getData(Location obj) {
		Location[] locTemp = null;
		int locId = obj.getLocId();
//		//hack
		locId = 1;
		String url = "http://findmyapp.net/findmyapp/locations/"+locId+"/temperature/latest";
		InputStream source = retrieveStream(url);

		if (source != null) {

			Gson gson = new Gson();
			Reader reader = new InputStreamReader(source);

			JsonParser parser = new JsonParser();
			JsonElement jsonElement = parser.parse(reader);
			locTemp = gson.fromJson(jsonElement, Location[].class);

		}
		String value = locTemp[0].getValue();
		obj.setTemperature(value);
		
		//ONE MORE TIEM :D:D:DD

		url = "http://findmyapp.net/findmyapp/locations/"+locId+"/humidity/latest";
		source = retrieveStream(url);

		if (source != null) {

			Gson gson = new Gson();
			Reader reader = new InputStreamReader(source);

			JsonParser parser = new JsonParser();
			JsonElement jsonElement = parser.parse(reader);
			locTemp = gson.fromJson(jsonElement, Location[].class);

		}
		value = locTemp[0].getValue();
		obj.setHumidity(value);
		
		url = "http://findmyapp.net/findmyapp/locations/"+locId+"/noise/latest";
		source = retrieveStream(url);

		if (source != null) {

			Gson gson = new Gson();
			Reader reader = new InputStreamReader(source);

			JsonParser parser = new JsonParser();
			JsonElement jsonElement = parser.parse(reader);
			locTemp = gson.fromJson(jsonElement, Location[].class);

		}
		value = locTemp[0].getAverage();
		obj.setNoise(value);
	}
	
	public void generateLocations(){
		locations = new Location[9];
		locations[0] = new Location(1, "Strossa");
		locations[1] = new Location(2, "Storsalen");
		locations[2] = new Location(3, "Edgar");
		locations[3] = new Location(6, "Klubben");
		locations[4] = new Location(10, "Bodegaen");
		locations[5] = new Location(11, "Knaus");
		locations[6] = new Location(12, "Selskapssiden");
		locations[7] = new Location(18, "Daglighallen");
		locations[8] = new Location(19, "Lyche");
		
		for (Location obj : locations) {
			getData(obj);
			obj.setPartyFactor();
		}
		while (true){
			boolean changeDone = false;
			for (int i=1;i<locations.length;i++){
				if (locations[i-1].getPartyFactor()<locations[i].getPartyFactor()){
					Location temp = locations[i-1];
					locations[i-1] = locations[i];
					locations[i] = temp;
					
					changeDone = true;
				}
			}
			if(!changeDone){
				break;
			}
		}
	}
}