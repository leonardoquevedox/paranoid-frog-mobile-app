package com.example.paranoidfrog;

import java.io.File;

import android.os.Environment;
import android.util.Log;

public class KeepFile {

public void rename(String fileName){
	File sdcard = Environment.getExternalStorageDirectory();
	boolean rename; 
	File file = new File("/sdcard/teste.pcm");
	 File file2 = new File(sdcard+"paranoidTracks/", "raw.pcm");
	 rename = file.renameTo(file2);
	 
	 Log.d("Success", Boolean.toString(rename));
	 Log.d("The Right Name",file2.getName());
	 Log.d("The Name",file.getName());
}
	 
	
}
