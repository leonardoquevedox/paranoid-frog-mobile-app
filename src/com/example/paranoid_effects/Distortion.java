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

public class Distortion extends ParanoidEffect {
	boolean asc;

	public void DistortionLoop() {

		try {
			File file = new File(Environment.getExternalStorageDirectory(),
					"DistortionRecording.pcm");
			OutputStream outputStream = new FileOutputStream(file);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
					outputStream);
			DataOutputStream dataOutputStream = new DataOutputStream(
					bufferedOutputStream);

			paranoidTrack.play();
			int i = 0;
			int o = 0;
			double sample_l;
			short sl;

			double volume = 30.0;
			paranoidRecord.startRecording();
			while (recording) {
				o = paranoidRecord.read(audioData, 0, minBufferSize);
				for (i = 0; i < o; i++) {

					sample_l = (short) audioData[i] * volume;
					
					sl = (short) validateShortValue(sample_l);
					audioData[i] = sl;
					dataOutputStream.writeShort(audioData[i]);
					paranoidTrack.setStereoVolume(axisY, axisY);
				}
				// audioTrack.setStereoVolume(y, y);
				paranoidTrack.write(audioData, 0, o);
				if (i == minBufferSize) {
					i = 0;
				}
			}
			turnOffEffectLoop();
		} catch (IOException e) {

			e.printStackTrace();
			Log.d("Erro", "Falha na inicializacao dos componentes do loop");
		}
	}

}
