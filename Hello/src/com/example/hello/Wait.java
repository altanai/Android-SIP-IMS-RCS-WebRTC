package com.example.hello;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Toast;

public class Wait extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wait);
		
		final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
            	Intent homepage = new Intent(Wait.this, Welcome.class);
                startActivity(homepage);
                finish();
                            
        		Toast.makeText(getApplicationContext(), "Login Successfully !!!", Toast.LENGTH_LONG).show();
            }
        }, 3000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wait, menu);
		return true;
	}

}
