package test.app.no;

import test.app.no.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LocActivity extends Activity{
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locactivity);
        
        TextView locName;
        locName = (TextView)findViewById(R.id.LocName); 
        locName.setText(UkaActivity.locations[UkaActivity.locPos].getLocName()+" PF: "+UkaActivity.locations[UkaActivity.locPos].getPartyFactor());
        
        TextView noise;
        noise = (TextView)findViewById(R.id.Noise); 
        noise.setText(UkaActivity.locations[UkaActivity.locPos].getNoise());
        
        TextView temp;
        temp = (TextView)findViewById(R.id.Temp); 
        temp.setText(UkaActivity.locations[UkaActivity.locPos].getTemperature());
        
        TextView hum;
        hum = (TextView)findViewById(R.id.Hum); 
        hum.setText(UkaActivity.locations[UkaActivity.locPos].getHumidity());
//wat
        Button next = (Button) findViewById(R.id.Button02);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }

        });
    }

}
