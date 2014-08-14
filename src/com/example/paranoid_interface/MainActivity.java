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
import com.example.paranoid_effects.AcceleroWahWahActivity;
import com.example.paranoid_effects.ReverbActivity;
import com.example.paranoid_effects.DistortionActivity;
import com.example.paranoidfrog.R;

public class MainActivity extends Activity {
	
	ImageButton fuzzRef, wahWahRef, acceleroFootRef, acceleroWahWahRef;

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		wahWahRef = (ImageButton)findViewById(R.id.wahWahRef);
		 fuzzRef = (ImageButton)findViewById(R.id.reverbRef);
		 acceleroFootRef = (ImageButton)findViewById(R.id.acceleroFootRef);
		 acceleroWahWahRef = (ImageButton)findViewById(R.id.acceleroWahWahRef);
		 
		 
		wahWahRef.setOnClickListener(wahWahRefOnClickListener);
		fuzzRef.setOnClickListener(fuzzRefOnClickListener);
		acceleroFootRef.setOnClickListener(acceleroFootRefOnClickListener);
		acceleroWahWahRef.setOnClickListener(acceleroWahWahRefOnClickListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.paranoid, menu);
		return true;
	}
	OnClickListener wahWahRefOnClickListener = new OnClickListener(){
		 @Override
		  public void onClick(View arg0) {
			 Intent i = new Intent(getApplicationContext(), DistortionActivity.class);
			 startActivity(i);
			  
		  	}
	};
	
	OnClickListener fuzzRefOnClickListener = new OnClickListener(){
		 @Override
		  public void onClick(View arg0) {
			 Intent i = new Intent(getApplicationContext(), ReverbActivity.class);
			 startActivity(i);
			  
		  	}
	};
	
	OnClickListener acceleroWahWahRefOnClickListener = new OnClickListener(){
		 @Override
		  public void onClick(View arg0) {
			 Intent i = new Intent(getApplicationContext(), AcceleroWahWahActivity.class);
			 startActivity(i);
			  
		  	}
	};

	OnClickListener acceleroFootRefOnClickListener = new OnClickListener(){
		 @Override
		  public void onClick(View arg0) {
			 
			 try{
			 Intent i = new Intent(getApplicationContext(), AcceleroFootActivity.class);
			 startActivity(i);
			 }
			 catch (Exception e){
				 e.printStackTrace();
				 Log.d("Error", "Unable to start acceleroFootActivity");
			 }
			
			  
		  	}
	};



}
