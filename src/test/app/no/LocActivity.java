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
        
        TextView tv;
        tv = (TextView)findViewById(R.id.LocName); 
        tv.setText(UkaActivity.locations[UkaActivity.locPos].getLocName()+" PF: "+UkaActivity.locations[UkaActivity.locPos].getPartyFactor());
        
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
