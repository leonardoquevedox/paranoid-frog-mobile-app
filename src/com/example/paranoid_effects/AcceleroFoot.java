package com.example.paranoid_effects;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.audiofx.EnvironmentalReverb;
import android.os.Environment;
import android.util.Log;

import com.example.paranoid_base.ParanoidEffect;


public class AcceleroFoot extends ParanoidEffect{


	
			private boolean enableReverb = false;
			private boolean asc;
			private boolean wahWah;

			public void AcceleroFootLoop(){		

	   try {
		   
		   enableReverb = true;
			 int decayTime = 5000;            
			 short density = 500;            
			 short diffusion = 500; 
			 short roomLevel = 0;             
			 short reverbLevel = 500;    
			 short reflectionsDelay = 100;   
			 short reflectionsLevel = 100;
			 short reverbDelay = 0;          
			 EnvironmentalReverb reverb; 

			    reverb = new EnvironmentalReverb(0, 0);
			    reverb.setDecayTime(decayTime);
			    reverb.setDensity(density);
			    reverb.setDiffusion(diffusion);
			    reverb.setReverbLevel(reverbLevel);
			    reverb.setRoomLevel(roomLevel);
			    reverb.setReflectionsDelay(reflectionsDelay);
			    reverb.setReflectionsLevel(reflectionsLevel);
			    reverb.setReverbDelay(reverbDelay);
			    reverb.setEnabled(enableReverb );
			    paranoidTrack.attachAuxEffect(reverb.getId());
			    paranoidTrack.setAuxEffectSendLevel(100.0f);
		    File file = new File(Environment.getExternalStorageDirectory(), "teste.pcm");
		    file.setWritable(true);
		  OutputStream outputStream = new FileOutputStream(file);
		  BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
		  DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);
			

		 paranoidTrack.play();
		 int i = 0;
		 int o = 0;
		 double sample_l;
		 short sl;
		 float leftVolumeLow = (float) 0.25;
		 float rightVolumeLow = (float) 0.25;
		 float leftVolumeHigh = (float) 1.0;
		 float rightVolumeHigh = (float) 1.0;
		 double volume = 0.0;
		 	paranoidRecord.startRecording();
		 		while(recording) {
		 				o = paranoidRecord.read(audioData, 0, minBufferSize);
		 				for (i=0;i < o; i++){
		 					if (axisY < 3.0){
		 						reverb.setEnabled(true);
		 						volume = 45.0;
		 						paranoidTrack.setStereoVolume(leftVolumeLow, rightVolumeLow);
		 					}
		 					if (axisY > 3.0 && axisY < 5.0 ) {
		 						reverb.setEnabled(false);
		 						volume = 45.0;
		 						paranoidTrack.setStereoVolume(leftVolumeLow, rightVolumeLow);
		 					}
		 					
		 					if(axisY > 5.0 && axisY < 7.0 ){
		 						
		 						reverb.setEnabled(false);
		 						volume = 1.0;
		 						paranoidTrack.setStereoVolume(leftVolumeHigh, rightVolumeHigh);
		 					}
		 				
		 					
		 					
		 			sample_l =  (short) audioData[i]*volume;
		 			if (sample_l < -32767.0f){
   			    	sample_l = -32767.0f; 
   			    	}
   			    
   					if (sample_l > 32767.0f){
   					sample_l = 32767.0f; 
   					}
   				
   					sl = (short)sample_l;
   					audioData[i] = sl;
   					dataOutputStream.writeShort(audioData[i]);
   		}
      		//audioTrack.setStereoVolume(y, y);
      	paranoidTrack.write(audioData, 0, o);
      	if (i == minBufferSize){
      	i = 0;
      	}
      }
      paranoidRecord.stop();
      paranoidTrack.stop();
	   } catch (IOException e) {
			
			e.printStackTrace();
			Log.d ("Erro","Falha na inicializacao dos componentes do loop");
		} 
	}
			
			
			
			
			 
			
	
	


}
