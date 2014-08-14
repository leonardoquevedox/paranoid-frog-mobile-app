package com.example.paranoid_base;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.Environment;

public class ParanoidEffect {
	protected int paranoidSampleRate = 24000;
	protected boolean recording = false;
	
	
	protected Float axisY = (float) 0;
	
	protected int minBufferSize = AudioTrack.getMinBufferSize(paranoidSampleRate, 
		     AudioFormat.CHANNEL_CONFIGURATION_MONO, 
		     AudioFormat.ENCODING_PCM_16BIT);

	protected AudioRecord paranoidRecord = new AudioRecord(MediaRecorder.AudioSource.DEFAULT,
			paranoidSampleRate,
			AudioFormat.CHANNEL_CONFIGURATION_MONO,
			AudioFormat.ENCODING_PCM_16BIT,
			minBufferSize);

	protected AudioTrack paranoidTrack = new AudioTrack(
		     AudioManager.STREAM_MUSIC,
		     paranoidSampleRate,
		     AudioFormat.CHANNEL_CONFIGURATION_MONO,
		     AudioFormat.ENCODING_PCM_16BIT,
		     minBufferSize,
		     AudioTrack.MODE_STREAM);

	protected short[] audioData = new short[minBufferSize];
	
	public boolean isRecording() {
	return recording;
	}

	public void setRecording(boolean recording) {
	this.recording = recording;
	}

	public Float getAxisY() {
	return axisY;
	}

	public void setAxisY(Float axisY) {
	this.axisY = axisY;
	}

	
	 public void playRecord(){
		 
		 
		  File file = new File(Environment.getExternalStorageDirectory(), "teste.pcm");
		  file.setWritable(true);
		  int shortSizeInBytes = Short.SIZE/Byte.SIZE;
		  int bufferSizeInBytes = (int)(file.length()/shortSizeInBytes);
		  short[] audioData = new short[bufferSizeInBytes];
		  
		  
		  try {
			  InputStream inputStream = new FileInputStream(file);
			  BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
			  DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);
		  
			  int i = 0;
			  while(dataInputStream.available() > 0){
			  audioData[i] = dataInputStream.readShort();
			  i++;
		   }
		  
		   
		   dataInputStream.close();
		   
		   AudioTrack audioTrack = new AudioTrack(
		     AudioManager.STREAM_MUSIC,
		     paranoidSampleRate,
		     AudioFormat.CHANNEL_CONFIGURATION_MONO,
		     AudioFormat.ENCODING_PCM_16BIT,
		     bufferSizeInBytes,
		     AudioTrack.MODE_STREAM);
		   
		   audioTrack.play();
		   audioTrack.write(audioData, 0, bufferSizeInBytes);
		   
		  		} 	catch (FileNotFoundException e) {
		  			e.printStackTrace();
		  	   } 
		catch (IOException e) {
		e.printStackTrace();
	}
}
	

	

}
