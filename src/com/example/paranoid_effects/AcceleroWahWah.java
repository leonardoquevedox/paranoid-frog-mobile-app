package com.example.paranoid_effects;


import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.os.Environment;
import android.util.Log;

import com.example.paranoid_base.ParanoidEffect;


public class AcceleroWahWah extends ParanoidEffect{
	boolean asc = true;

	
			public void AcceleroWahWahLoop(){		

	   try {
		   
		   
		    File file = new File(Environment.getExternalStorageDirectory(), "teste.pcm");
		  OutputStream outputStream = new FileOutputStream(file);
		  BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
		  DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);
			

		 paranoidTrack.play();
		 int i = 0;
		 int o = 0;
		 double sample_l;
		 short sl;
		
		 double volume = 0.0;
		 	paranoidRecord.startRecording();
		 		while(recording) {
		 				o = paranoidRecord.read(audioData, 0, minBufferSize);
		 				for (i=0;i < o; i++){
		 				   if (asc){
		 		   			 
		 		   					 //  volume += 0.004;
		 		   			   volume += axisY;
		 		   				//
		 		   				   }
		 		   				   else {
		 		   					//   volume = volume - 0.004;
		 		   					volume = volume - axisY;
		 		   				   }
		 		   				   
		 		   				   if (volume > 0.0 && volume <1.0){
		 		   					   asc = true;
		 		   				   }
		 		   				 if (volume > 30.0 || volume == 30.0){
		 		   					 asc = false;
		 		   				 }
		 		   	
   	
		 			sample_l =  (short) audioData[i]*volume*4.0;
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
      		paranoidTrack.setStereoVolume(axisY, axisY);
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