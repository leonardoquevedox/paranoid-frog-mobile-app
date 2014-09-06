package com.example.paranoid_interface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.example.paranoid_effects.AcceleroFootActivity;
import com.example.paranoid_effects.AcceleroTremeloActivity;
import com.example.paranoid_effects.OverdriveActivity;
import com.example.paranoid_effects.WahWahActivity;
import com.example.paranoidfrog.R;

public class MainActivity extends Activity {

	ImageButton overdriveRef, wahWahRef, acceleroFootRef, acceleroWahWahRef;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		wahWahRef = (ImageButton) findViewById(R.id.wahWahRef);
		overdriveRef = (ImageButton) findViewById(R.id.overdriveRef);
		acceleroFootRef = (ImageButton) findViewById(R.id.acceleroFootRef);
		acceleroWahWahRef = (ImageButton) findViewById(R.id.acceleroTremeloRef);

		wahWahRef.setOnClickListener(wahWahOnClickListener);
		overdriveRef.setOnClickListener(overdriveOnClickListener);
		acceleroFootRef.setOnClickListener(acceleroFootOnClickListener);
		acceleroWahWahRef.setOnClickListener(acceleroWahWahOnClickListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.paranoid, menu);
		return true;
	}

	OnClickListener wahWahOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			Intent i = new Intent(getApplicationContext(), WahWahActivity.class);
			startActivity(i);
		}
	};

	OnClickListener overdriveOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			Intent i = new Intent(getApplicationContext(),
					OverdriveActivity.class);
			startActivity(i);

		}
	};

	OnClickListener acceleroWahWahOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			Intent i = new Intent(getApplicationContext(),
					AcceleroTremeloActivity.class);
			startActivity(i);

		}
	};

	OnClickListener acceleroFootOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View arg0) {

			try {
				Intent i = new Intent(getApplicationContext(),
						AcceleroFootActivity.class);
				startActivity(i);
			} catch (Exception e) {
				e.printStackTrace();
				Log.d("Error", "Unable to start acceleroFootActivity");
			}

		}
	};

}
