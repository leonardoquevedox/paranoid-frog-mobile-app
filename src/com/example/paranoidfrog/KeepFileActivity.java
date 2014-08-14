package com.example.paranoidfrog;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class KeepFileActivity extends Activity {
	
	KeepFile keep = new KeepFile();

	public TextView fileName;
	Button buttonSave;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_keep_file);
		fileName=(TextView)findViewById(R.id.fileName);
		buttonSave = (Button)findViewById(R.id.buttonSave);
		buttonSave.setOnClickListener(buttonSaveOnClickListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.keep_file, menu);
		return true;
	}
	
	OnClickListener buttonSaveOnClickListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			String name = fileName.getText().toString();
			keep.rename(name);
			
		}
	}
	;
	

}
