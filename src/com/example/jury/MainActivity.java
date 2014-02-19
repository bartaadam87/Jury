package com.example.jury;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.jury.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_layout);

        //final TextView titleLeft = (TextView) findViewById(R.id.title_left);

        final TextView titleRight = (TextView) findViewById(R.id.title_right);
		//titleLeft.setText("@string/app_name");
        titleRight.setText(juryName);

    }
    	
//    	super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void backButtonClicked(View button) {
    	Toast.makeText(this, R.string.backToast, Toast.LENGTH_LONG).show();
    	this.finish();    	
    }
    
    public void buttonClicked(View button) {
    	Toast.makeText(this, R.string.wellcome_toast, Toast.LENGTH_LONG).show();
    	this.finish();
    }
    
    public String juryName = "Test Testovic";

}
