package com.example.paranoid_effects;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.paranoid_interface.MainActivity;
import com.example.paranoidfrog.R;

public class DistortionActivity extends Activity implements SensorEventListener {
	ImageButton startLoop, led, playButton;
	
	
	boolean loopStarted = false;
	boolean playStarted = false;
	
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	float y = 0;
	

	Thread loopThread = new Thread(new Runnable(){
		@Override
		public void run() {
			dist.DistortionLoop();
					}
		}
	);
	
	
	Distortion dist = new Distortion();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_distortion);
		startLoop = (ImageButton)findViewById(R.id.startLoop);
		led = (ImageButton)findViewById(R.id.led);
		playButton = (ImageButton) findViewById(R.id.playButton);
	
		startLoop.setOnClickListener(startLoopOnClickListener);
		playButton.setOnClickListener(playButtonOnClickListener);
	
		mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.paranoid, menu);
		return true;
	}
	

	OnClickListener startLoopOnClickListener = new OnClickListener(){ 
		  @Override
		  public void onClick(View arg0) {
			if (loopStarted == false){
					Thread loopThread = new Thread(new Runnable(){
						@Override
						public void run() {
							dist.DistortionLoop();
									}
						}
									);
					
				  loopStarted = true;
				  dist.setRecording(true);
				  loopThread.setPriority(Thread.MAX_PRIORITY);
				  					try{
				  				loopThread.start();
				  						}
				  	
				  		catch(Exception e){
				  			e.printStackTrace();
				  			Log.d("Erro", "Thread is already started");
				  		}
				 led.setImageResource(R.drawable.led_on);
			  
			  }
			  else{
				  dist.setRecording(false);
				  	loopStarted = false;
				  		try{
				  			loopThread = null;
				  			}
				  	catch(Exception e){
				  		e.printStackTrace();
					  	Log.d("Thread Error", "System wasn't able to kill thread");
				  						}
				  led.setImageResource(R.drawable.led_off);
			  		}
		  	}
		 };
	

		 
		 OnClickListener playButtonOnClickListener = new OnClickListener(){ 
			  @Override
			  public void onClick(View arg0) {
				  
				  
				  dist.playRecord();
				  
			  	}
			 };
		 

		 
			   
			   @Override
				  public final void onAccuracyChanged(Sensor sensor, int accuracy)
				  {
				  }

				  @Override
				  public final void onSensorChanged(SensorEvent event) 
				  {
					  y =  (((4-(Math.abs(event.values[1])))*2)/4);
					  dist.setAxisY(y);
				  }
				  
				  
				  public float getAxis(){
					  return y;
				  }
				  
				  @Override
				  protected void onResume()
				  {
				    super.onResume();
				    mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
				  }
				
				

}
